package fr.jaladev.csvconverter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.jaladev.csvconverter.model.Report;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * classe abstraite qui fait l'ecriture du fichier en sortie.
 */
public abstract class AbstractWriterService {

    /**
     * retourne le logger de la classe concrète.
     * @return Logger
     */
    protected abstract Logger getLogger();

    /**
     * retourne le serializer jackson.
     * @return ObjectMapper
     */
    protected abstract ObjectMapper getMapper();

    /**
     * ecrit le fichier en sortie
     * @param report le report à écrire
     * @param destFile le chemin du fichier en sortie
     */
    public void writeFile(final Report report, final String destFile) {
        try {
            final String result = this.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(report);
            Files.write(Paths.get(destFile), result.getBytes());
        } catch (IOException e) {
            this.getLogger().error("Erreur lors de l'écriture du fichier", e);
        }

    }
}
