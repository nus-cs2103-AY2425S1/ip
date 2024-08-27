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

public class KorolevStorage {
    private static final String home = System.getProperty("user.dir");

    private static final Path dir = Paths.get(
            home, "src", "main", "java", "data");

    private static final Path path = Paths.get(
            home, "src", "main", "java", "data", "korolev.txt");

    private void createNewFile() {
        if (!java.nio.file.Files.exists(path)) {
            try {
                Files.createDirectories(KorolevStorage.dir);
                Files.createFile(path);
                File record = new File(String.valueOf(path));
                boolean test = record.createNewFile();
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }
    }

    public void writeToFile(String msg) {
        createNewFile();

        try {
            FileWriter writer = new FileWriter(String.valueOf(path));
            writer.write(msg);
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public void readLines(ArrayList<KorolevTask> events) {
        createNewFile();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(String.valueOf(path)));
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
