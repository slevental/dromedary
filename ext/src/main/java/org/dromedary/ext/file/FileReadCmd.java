package org.dromedary.ext.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.concordion.api.*;
import org.concordion.api.listener.AssertFailureEvent;
import org.concordion.api.listener.AssertSuccessEvent;
import org.concordion.internal.listener.AssertResultRenderer;

import java.io.File;
import java.io.IOException;

public class FileReadCmd extends AbstractCommand {
    public static final String COMMAND = "assertFile";
    private AssertResultRenderer listeners = new AssertResultRenderer();

    @Override
    public void verify(CommandCall commandCall, Evaluator evaluator, ResultRecorder resultRecorder) {
        File file = new File(commandCall.getExpression());
        try {
            String expected = (String) evaluator.getVariable("#TEXT");
            String actual = FileUtils.readFileToString(file);
            if (!StringUtils.equals(expected, actual)) {
                resultRecorder.record(Result.FAILURE);
                listeners.failureReported(new AssertFailureEvent(commandCall.getElement(), expected, actual));
            } else {
                resultRecorder.record(Result.SUCCESS);
                listeners.successReported(new AssertSuccessEvent(commandCall.getElement()));
            }
        } catch (IOException e) {
            throw new AssertionError("Cannot read from file: " + file.getAbsolutePath() + ": " + e.getMessage());
        }
    }
}
