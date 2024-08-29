package main.java.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Storage() {
        return;
    }

    /**
     * Handles the creation of Karen.txt at startup, if needed
     *
     */
    public static void initFile() {
        try {
            Path filePath = Paths.get("/main/java/data/Karen.txt");

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error occured when opening the file");
        }

    }
}
