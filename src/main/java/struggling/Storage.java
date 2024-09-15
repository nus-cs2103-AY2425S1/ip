package struggling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final File file;

    /**
     * Initializes Storage object to create directory and save file
     * to store user data.
     *
     * @param filePath Relative file path of save file.
     * @throws IOException If an I/ O error occurs.
     */
    Storage(String filePath) throws IOException {
        String directory = filePath.substring(0, filePath.lastIndexOf("/"));
        new File(directory).mkdir();
        this.file = new File(filePath);
        file.createNewFile();
    }

    /**
     * Reads the data from save file and returns an ArrayList
     * of string that indicates the state of each task.
     *
     * @return An ArrayList of task state.
     * @throws FileNotFoundException If the save file cannot be found.
     */
    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<>();

        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            input.add(s.nextLine());
        }

        return input;
    }

    /**
     * Stores the data provide in a file.
     *
     * @throws IOException If an I/ O error occurs.
     */
    public void save(ArrayList<String> data) throws IOException {
        resetSaveFile();

        FileWriter fw = new FileWriter(file, true);
        for (String d : data) {
            fw.write(d + System.lineSeparator());
        }

        fw.close();
    }

    private void resetSaveFile() throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write("");
        fw.close();
    }
}
