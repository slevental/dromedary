package org.dromedary.ext.file;

import org.apache.commons.io.FileUtils;
import org.concordion.api.AbstractCommand;
import org.concordion.api.CommandCall;
import org.concordion.api.Evaluator;
import org.concordion.api.ResultRecorder;

import java.io.File;
import java.io.IOException;

public class FileWriteCmd extends AbstractCommand {
    public static final String COMMAND = "file";
    public static final String TEXT = "#TEXT";

    @Override
    public void setUp(CommandCall commandCall, Evaluator evaluator, ResultRecorder resultRecorder) {
        File file = new File(commandCall.getExpression());
        try {
            FileUtils.writeStringToFile(file, (String) evaluator.getVariable(TEXT));
        } catch (IOException e) {
            throw new AssertionError("Cannot write to file: " + file.getAbsolutePath() + " due to " + e.getMessage());
        }
    }
}
