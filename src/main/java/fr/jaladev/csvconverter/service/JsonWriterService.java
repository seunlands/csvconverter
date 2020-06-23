package fr.jaladev.csvconverter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ecrit un fichier en sortie au format JSON
 */
public class JsonWriterService extends AbstractWriterService {
    private static Logger LOG = LoggerFactory.getLogger(JsonWriterService.class);

    @Override
    protected Logger getLogger() {
        return LOG;
    }

    @Override
    protected ObjectMapper getMapper() {
        return new ObjectMapper();
    }
}
