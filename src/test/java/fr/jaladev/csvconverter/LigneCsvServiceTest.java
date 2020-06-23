package fr.jaladev.csvconverter;

import fr.jaladev.csvconverter.exception.InvalideLigneException;
import fr.jaladev.csvconverter.model.LigneCsv;
import fr.jaladev.csvconverter.service.LigneCsvService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/** classe de tests unitaire des méthodes de la classe LigneCsvService */
public class LigneCsvServiceTest {

    @Test
    public void whenValidateLigneCsv_thenNoException_givenLigneIsCorrect() {
        final LigneCsv ligneCorrect = LigneCsv.builder()
                .size("10")
                .price("15.25")
                .color("R")
                .numReference("1234567890")
                .build();
        Assertions.assertThatCode(() -> LigneCsvService.validateLigne(ligneCorrect))
                .doesNotThrowAnyException();
    }

    @Test
    public void whenValidationLigneCsv_thenException_givenColorIncorrect() {
        final LigneCsv ligneColorIncorrect = LigneCsv.builder()
                .size("10")
                .price("15.25")
                .color("V")
                .numReference("1234567890")
                .build();


        Assertions.assertThatExceptionOfType(InvalideLigneException.class)
                .isThrownBy(() -> LigneCsvService.validateLigne(ligneColorIncorrect))
                .withMessage("Incorrect value for color");
    }


    @Test
    public void whenValidationLigneCsv_thenException_givenPriceEmptyIncorrect() {
        final LigneCsv ligneColorIncorrect = LigneCsv.builder()
                .size("10")
                .color("R")
                .numReference("1234567890")
                .build();


        Assertions.assertThatExceptionOfType(InvalideLigneException.class)
                .isThrownBy(() -> LigneCsvService.validateLigne(ligneColorIncorrect))
                .withMessage("Reference has incorrect number of tokens");
    }

}
