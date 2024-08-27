import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Storage object that handles writing and reading tasks from a local file.
 * @author Lee Ze Hao (A0276123J)
 */

public class Storage {
    private String filePath;

    /**
     * Creates a new Storage object.
     * @param filePath The file path data is stored to and read from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Takes a list of tasks and writes them to a text file at the filePath given during object creation.
     * @param taskList The list of tasks to be written to the file.
     */
    public void writeTasksToFile(ArrayList<Task> taskList) {
        
    }

    /**
     * Reads a list of tasks from a text file at the filePath given during object creation.
     * @return ArrayList<Task> List of tasks stored in the text file.
     */
    public ArrayList<Task> readTasksFromFile() {
        return new ArrayList<Task>();
    }
}
