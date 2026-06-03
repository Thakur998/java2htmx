package com.j2htmx.auto.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CssFileStorageStrategy implements CSSStorageStrategy {

    private static final Pattern CSS_CLASS_PATTERN =
            Pattern.compile(
                    "\\.([\\w\\-]+)\\s*\\{([^}]*)\\}",
                    Pattern.MULTILINE
            );

    public Properties load(String source)
            throws IOException {

        Properties props = new Properties();

        String css =
                Files.readString(
                        Path.of(source + ".css")
                );

        Matcher matcher =
                CSS_CLASS_PATTERN.matcher(css);

        while (matcher.find()) {

            String className =
                    matcher.group(1).trim();

            String style =
                    matcher.group(2)
                            .replace("\n", " ")
                            .replace("\r", " ")
                            .replaceAll("\\s+", " ")
                            .trim();

            props.setProperty(className, style);
        }

        return props;
    }


    public void save(Properties props,
                     String destination) {

        throw new UnsupportedOperationException(
                "Saving CSS files not supported."
        );
    }
}