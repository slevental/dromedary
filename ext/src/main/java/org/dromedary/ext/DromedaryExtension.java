package org.dromedary.ext;

import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.dromedary.ext.config.ConfigInjector;
import org.dromedary.ext.file.FileReadCmd;
import org.dromedary.ext.file.FileWriteCmd;
import org.dromedary.ext.sleep.SleepCmd;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.io.IOException;

public class DromedaryExtension implements ConcordionExtension {
    public static final String NS = "http://www.dromedary.org/schema";

    public void addTo(ConcordionExtender concordionExtender) {
        concordionExtender.withSource(ConfigInjector.INSTANCE);
        concordionExtender.withCommand(NS, FileWriteCmd.COMMAND, new FileWriteCmd());
        concordionExtender.withCommand(NS, FileReadCmd.COMMAND, new FileReadCmd());
        concordionExtender.withCommand(NS, SleepCmd.COMMAND, new SleepCmd());
    }
}
