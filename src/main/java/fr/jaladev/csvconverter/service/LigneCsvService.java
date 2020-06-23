package fr.jaladev.csvconverter.service;


import fr.jaladev.csvconverter.exception.InvalideLigneException;
import fr.jaladev.csvconverter.model.LigneCsv;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * contient les méthodes métiers liées au parsage du fichier CSV.
 */
public class LigneCsvService {

    /**
     * construit les références à partir d'une ligne CSV.
     * @param line la ligne à parser
     * @param lineNumber le numero de la ligne
     * @return une référence
     */
    public static LigneCsv buildLigneCsv(final String line, final int lineNumber) {
        LigneCsv ligne = null;
        if (!StringUtils.isEmpty(line)) {
            final String[] tokens = StringUtils.tokenizeToStringArray(line, ";");
            if (tokens.length != 4) {
                ligne = LigneCsv.builder()
                        .ligne(line)
                        .lineNumber(lineNumber)
                        .build();
            } else {

                    ligne = LigneCsv.builder()
                            .numReference(tokens[0])
                            .color(tokens[1])
                            .price(tokens[2])
                            .size(tokens[3])
                            .lineNumber(lineNumber)
                            .ligne(line)
                            .build();
            }
        }

        return ligne;

    }

    /**
     * valide le contenu de la ligne
     * @param ligne entité ligne
     * @throws InvalideLigneException si erreur de controle
     */
    public static void validateLigne(final LigneCsv ligne) throws InvalideLigneException {

        //si toutes les valeurs ne sont pas renseignées alors erreur
        if (ligne.getColor() == null || ligne.getNumReference() == null || ligne.getSize() == null
            || ligne.getPrice() == null) {
            throw new InvalideLigneException("Reference has incorrect number of tokens", ligne.getLineNumber(), ligne.getLigne());
        }
        //color = R, V ou B
        if (!Pattern.compile("^[RGB]$").matcher(ligne.getColor()).find()){
            throw new InvalideLigneException("Incorrect value for color", ligne.getLineNumber(), ligne.getLigne());
        }

    }
}
