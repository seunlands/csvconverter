package fr.jaladev.csvconverter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * entité représentant une référence
 */
@Getter
@Setter
@Builder
public class LigneCsv {

    /** le numero de la refereance */
    @JacksonXmlProperty(isAttribute=true)
    private String numReference;

    /** la taille */
    @JacksonXmlProperty(isAttribute=true)
    private String size;

    /** le prix */
    @JacksonXmlProperty(isAttribute=true)
    private String price;

    /** la couleur */
    @JsonProperty("type")
    @JacksonXmlProperty(isAttribute=true)
    private String color;

    /** le numero de ligne */
    @JsonIgnore
    private int lineNumber;

    /** la ligne */
    @JsonIgnore
    private String ligne;

}
