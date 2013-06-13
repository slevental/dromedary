package org.dromedary.ext.config;

import org.apache.commons.io.input.ReaderInputStream;
import org.concordion.api.Resource;
import org.concordion.internal.ClassPathSource;
import org.concordion.internal.util.IOUtil;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Properties;

import static org.springframework.beans.factory.config.PropertyPlaceholderConfigurer.DEFAULT_PLACEHOLDER_PREFIX;
import static org.springframework.beans.factory.config.PropertyPlaceholderConfigurer.DEFAULT_PLACEHOLDER_SUFFIX;

public class ConfigInjector extends ClassPathSource implements PropertyPlaceholderHelper.PlaceholderResolver {
    public static final ConfigInjector INSTANCE;

    static {
        try {
            INSTANCE = new ConfigInjector();
        } catch (IOException e) {
            throw new AssertionError("Cannot initialize ConfigInjector");
        }
    }

    private final Properties props;
    private PropertyPlaceholderHelper helper;

    public static void addConfig(String uri) throws IOException {
        INSTANCE.props.load(new FileReader(ResourceUtils.getFile(uri)));
    }

    private ConfigInjector() throws IOException {
        props = new Properties();
        helper = new PropertyPlaceholderHelper(DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX);
    }

    public InputStream createInputStream(Resource resource) throws IOException {
        String res = IOUtil.readResourceAsString(resource.getPath());
        res = helper.replacePlaceholders(res, this);
        return new ReaderInputStream(new StringReader(res));
    }

    public String resolvePlaceholder(String placeholderName) {
        return (String) props.get(placeholderName);
    }
}
