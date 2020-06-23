package fr.jaladev.csvconverter;


import fr.jaladev.csvconverter.model.Report;
import fr.jaladev.csvconverter.service.AbstractWriterService;
import fr.jaladev.csvconverter.service.JsonWriterService;
import fr.jaladev.csvconverter.service.ReaderService;
import fr.jaladev.csvconverter.service.XmlWriterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

/**
 * classe lancement application
 */
@SpringBootApplication
public class CsvconverterApplication implements CommandLineRunner {

    /** Logger */
    private static Logger LOG = LoggerFactory.getLogger(CsvconverterApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CsvconverterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Lancement Csvconverter");

        if (args.length != 2) {
            LOG.error("Le nombre d'arguments n'est pas correct");
            exit(1);
        } else if (!"json".equals(args[1]) && !"xml".equals(args[1])) {
            LOG.error("Le type en sortie doit etre json ou xml");
            exit(1);
        }

        AbstractWriterService writerService = this.getWriter(args[1]);
        if (writerService == null) {
            LOG.error("Impossible d'instancier le Writer");
            exit(1);
        }

        final Report report = ReaderService.readFile(args[0]);

        writerService.writeFile(report, "output." + args[1]);

        LOG.info("Csvconverter fini");
    }

    /**
     * instancie un writer en fct de la sortie souhaitée
     * @param arg sortie souhaitée (json ou xml)
     * @return AbstractWriterervice
     */
    private AbstractWriterService getWriter(String arg) {
        if ("json".equals(arg)) {
            return new JsonWriterService();
        } else if ("xml".equals(arg)) {
            return new XmlWriterService();
        }
        return null;
    }
}
