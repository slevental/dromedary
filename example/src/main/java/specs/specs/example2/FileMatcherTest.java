package specs.specs.example2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.concordion.api.extension.Extensions;
import org.concordion.ext.LoggingTooltipExtension;
import org.dromedary.ext.Configs;
import org.dromedary.ext.DromedaryExtension;
import org.dromedary.ext.DromedaryRunner;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FileUtils.writeStringToFile;

/* Run this class as a JUnit test. */

@RunWith(DromedaryRunner.class)
@Extensions({DromedaryExtension.class, LoggingTooltipExtension.class})
@Configs({"classpath:test.properties"})
public class FileMatcherTest {
    private static final Log LOG = LogFactory.getLog(FileMatcherTest.class);

    public void execute() throws IOException {
        File from = new File("/tmp/1");
        File to = new File("/tmp/2");
        LOG.info("writing to file: " + to.getAbsolutePath());
        LOG.error("Error happened");
        writeStringToFile(to, readFileToString(from).replaceAll("\\s+", " "));
    }
}
