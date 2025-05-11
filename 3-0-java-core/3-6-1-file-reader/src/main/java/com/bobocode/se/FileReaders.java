package com.bobocode.se;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

/**
 * {@link FileReaders} provides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName) {
        try (Stream<String> stream = Files.lines(createPathFromFileName(fileName))) {
            return stream
                    .collect(Collectors.joining("\n"));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path createPathFromFileName(String fileName) throws FileNotFoundException, URISyntaxException {
        requireNonNull(fileName);
        URL resource = FileReaders.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new FileNotFoundException();
        }
        return Path.of(resource.toURI());
    }
}
