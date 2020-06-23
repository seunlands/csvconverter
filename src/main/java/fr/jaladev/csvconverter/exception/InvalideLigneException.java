package fr.jaladev.csvconverter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Exception levée lors d'un controle de ligne invalide.
 */
@Getter
@Setter
@AllArgsConstructor
public class InvalideLigneException extends Exception {

    /** le message */
    private String message;

    /** le numero de la ligne en erreur */
    private int lineNumber;

    /** la ligne en erreur */
    private String line;
}
