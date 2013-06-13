package org.dromedary.ext;

import org.concordion.integration.junit4.ConcordionRunner;
import org.dromedary.ext.config.ConfigInjector;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

import java.io.IOException;

public class DromedaryRunner extends ConcordionRunner {
    public DromedaryRunner(Class<?> fixtureClass) throws InitializationError {
        super(fixtureClass);
    }

    @Override
    protected Statement withBeforeClasses(Statement statement) {
        TestClass testClass = this.getTestClass();
        Configs cfg = testClass.getJavaClass().getAnnotation(Configs.class);

        if (cfg != null) {
            for (String each : cfg.value()) {
                try {
                    ConfigInjector.addConfig(each);
                } catch (IOException e) {
                    throw new AssertionError(e.getMessage());
                }
            }
        }

        return super.withBeforeClasses(statement);
    }
}
