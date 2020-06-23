package fr.jaladev.csvconverter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * écrit le fichier en sortie au format XML
 */
public class XmlWriterService extends AbstractWriterService {
    private static Logger LOG = LoggerFactory.getLogger(XmlWriterService.class);

    @Override
    protected Logger getLogger() {
        return LOG;
    }

    @Override
    protected ObjectMapper getMapper() {
        final XmlMapper mapper = new XmlMapper();
        mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        return mapper;
    }
}
