package main.java.util;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

import java.util.ArrayList;

public class Storage {
    private static final Path FILEPATH = Paths.get("/main/java/data/Karen.txt");
    private Storage() {
        return;
    }

    /**
     * Handles the creation of Karen.txt at startup, if needed
     *
     */
    public static void initFile() {
        try {
            if (!Files.exists(FILEPATH)) {
                Files.createFile(FILEPATH);
            }
        } catch (IOException e) {
            System.out.println("Error occured when opening the file");
        }
    }

    public static void saveToFile(ArrayList<String> textToSave) {
        try {
            Files.write(FILEPATH, textToSave, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error while saving to file");
        }
    }
}
