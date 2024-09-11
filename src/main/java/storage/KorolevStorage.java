package storage;

import exception.ParseException;
import task.KorolevTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import parser.EventParser;

/**
 * Represents a manager of interaction between the application and local storage
 */
public class KorolevStorage {
    private static final String home = System.getProperty("user.dir");

    private static final Path dir = Paths.get(
            home, "src", "main", "java", "data");

    private static final Path PATH = Paths.get(
            home, "src", "main", "java", "data", "korolev.txt");

    private void createNewFile() {
        if (!java.nio.file.Files.exists(PATH)) {
            try {
                Files.createDirectories(KorolevStorage.dir);
                Files.createFile(PATH);
                File record = new File(String.valueOf(PATH));
                boolean test = record.createNewFile();
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }
    }

    /**
     * Writes new information to the specific file in data folder.
     *
     * @param msg the message being written to the file
     */
    public void writeToFile(String msg) {
        createNewFile();

        try {
            assert !msg.isEmpty();
            FileWriter writer = new FileWriter(String.valueOf(PATH));
            writer.write(msg);
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    /**
     * Reads records from data file and parse them into KorolevTask.
     *
     * @param events the list of KorolevTask to store the tasks based on stored information
     */
    public void readLines(ArrayList<KorolevTask> events) {
        createNewFile();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(String.valueOf(PATH)));
            String line = bfr.readLine();
            while(line != null) {
                events.add(EventParser.parseLoadedRecord(line));
                line = bfr.readLine();
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

}
