package me.lukecs.lzw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Luke Collishaw
 * @version v1.0
 * @since 2021-01-19
 */
public class FileUtilities {

    /**
     * Takes a file path and converts the contents into String, using UTF_8 charset.
     *
     * @param path The absolute (not relative) path or location of the file.
     * @return The file read as a string, otherwise an empty string.
     */
    public static String fileToString(String path) {
        try {
            // default StandardCharsets.UTF_8
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            return "";
        }
    }

}
