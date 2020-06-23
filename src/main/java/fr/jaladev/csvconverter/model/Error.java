package fr.jaladev.csvconverter.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Entité représentant une ligne error du fichier de sortie
 */
@Getter
@Setter
@Builder
public class Error {

    /** le numero de la ligne en erreur */
    @JacksonXmlProperty(isAttribute=true)
    private int line;

    /** le message d erreur */
    @JacksonXmlProperty(isAttribute=true)
    private String message;

    /** la value de l'erreur */
    @JacksonXmlText
    private String value;
}
