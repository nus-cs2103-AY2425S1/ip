package seedu.avo.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.avo.exceptions.AvoException;

/**
 * Represents a file storage
 * @param <T> type of data stored
 */
public class FileStorage<T> extends Storage<T, String> {
    private final String filePath;
    private final FileParser<T> parser;

    /**
     * @param dirPath The file path of the file storage
     * @param parser A parser to read the contents of the file
     */
    public FileStorage(String dirPath, FileParser<T> parser) {
        this.parser = parser;
        filePath = dirPath + "/" + "avo.txt";
        try {
            Files.createDirectories(Paths.get(dirPath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void write(String data) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<T> fetchAll() {
        List<T> result = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                T data = parser.parse(s.nextLine());
                result.add(data);
            }
        } catch (FileNotFoundException | AvoException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
