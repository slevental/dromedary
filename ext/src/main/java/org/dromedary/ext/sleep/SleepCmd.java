package org.dromedary.ext.sleep;

import org.apache.commons.io.FileUtils;
import org.concordion.api.AbstractCommand;
import org.concordion.api.CommandCall;
import org.concordion.api.Evaluator;
import org.concordion.api.ResultRecorder;

import java.io.File;
import java.io.IOException;

public class SleepCmd extends AbstractCommand {
    public static final String COMMAND = "sleep";

    @Override
    public void execute(CommandCall commandCall, Evaluator evaluator, ResultRecorder resultRecorder) {
        try {
            Thread.sleep(Long.parseLong((String) evaluator.getVariable("#TEXT")));
        } catch (InterruptedException e) {
            throw new AssertionError("Interrupted");
        }
    }
}
