package fr.jaladev.csvconverter.service;

import fr.jaladev.csvconverter.exception.InvalideLigneException;
import fr.jaladev.csvconverter.model.Error;
import fr.jaladev.csvconverter.model.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * parse le fichier CSV.
 */
public class ReaderService {

    /** Logger */
    private static Logger LOG = LoggerFactory.getLogger(ReaderService.class);


    /**
     * lecture du fichier CSV
     * @param srcFileName le nom du fichier
     * @return un report
     */
    public static Report readFile(final String srcFileName) {
        final Report report = new Report();
        LOG.debug("Début lecture fichier : " + srcFileName);
        try (Stream<String> stream = Files.lines(Paths.get(srcFileName))) {
            report.setInputFile(srcFileName);
            final AtomicInteger lineNumber = new AtomicInteger();
            stream
                    .map(l -> LigneCsvService.buildLigneCsv(l, lineNumber.incrementAndGet()))
                    .forEachOrdered(ligne -> {
                                try {
                                    LigneCsvService.validateLigne(ligne);
                                    report.addLigne(ligne);
                                } catch (InvalideLigneException ex) {
                                    LOG.info("Ligne " + ex.getLineNumber() + " erreur : " + ex.getMessage(), ex);
                                    report.addError(Error.builder()
                                            .line(ex.getLineNumber())
                                            .message(ex.getMessage())
                                            .value(ex.getLine())
                                            .build());
                                }
                            }

                    );
        } catch (IOException ex) {
            LOG.error("Erreur lecture fichier", ex);
        }
        return report;
    }
}
