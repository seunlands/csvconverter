package fr.jaladev.csvconverter.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * entité représentant le fichier en sortie
 */
@Getter
@Setter
@JacksonXmlRootElement(localName = "report")
public class Report {

    /** le nom du fichier en entrée */
    private String inputFile;

    /** les références */
    private List<LigneCsv> references;

    /** les erreurs */
    @JacksonXmlElementWrapper(localName = "errors")
    @JacksonXmlProperty(localName = "error")
    private List<Error> errors;

    /**
     * ajoute un ligne référence. Instancie la liste si nécessaire.
     * @param ligne la ligne à ajouter
     */
    public void addLigne(final LigneCsv ligne) {
        if (references == null) {
            references = new ArrayList<>();
        }
        references.add(ligne);
    }

    /**
     * ajoute un ligne error. Instancie la liste si nécessaire.
     * @param error error à ajouter
     */
    public void addError(final Error error) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(error);
    }
}
