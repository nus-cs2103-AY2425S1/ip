package ned;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ned.exceptions.CacheFileNotFoundException;
import ned.exceptions.CacheFileNotUsableException;
import ned.exceptions.NedException;
import ned.tasks.Task;

/**
 * Represents the storage used to save the current lists of tasks into a cached file, as well to load from it when
 * the chatbot starts.
 */
public class Storage {

    private File cacheFile;
    private String cacheFilePath;

    /**
     * Constructs a storage instance which handles loading and saving to the cached list of tasks.
     *
     * @param filePath The path of the cached list of tasks
     */
    public Storage(String filePath) {
        this.cacheFilePath = filePath;
        File f = new File(filePath);
        this.cacheFile = f;
    }

    /**
     * Loads in the cached list of tasks.
     *
     * @return An ArrayList with parameterized type Task that can be used to instantiate a TaskList instance
     * @throws NedException If the cached file does not exist
     */
    public ArrayList<Task> load() throws NedException {
        //returns the arraylist which TaskList will save
        ArrayList<Task> newListOfTasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(this.cacheFile);
            while (s.hasNext()) {
                Task newTask = Parser.parseSavedTask(s.nextLine());
                newListOfTasks.add(newTask);
            }
        } catch (FileNotFoundException e) {
            throw new CacheFileNotFoundException("M'lord, do not be alarmed, but it appears that there was no previous "
                    + "saved task file. Not to worry, we'll sort this out yet...");
        }
        return newListOfTasks;
    }

    /**
     * Saves the list of tasks into the cached list of tasks.
     *
     * @param listOfTasks The current TaskList instance to be cached
     * @throws NedException If the cached file path cannot be written too. Usual reasons include protected folders or
     *                      if the existing parent folders to the cached list of tasks does not exist, as this method
     *                      does not create those directories
     */

    public void save(TaskList listOfTasks) throws NedException {
        int sizeOfList = listOfTasks.getSize();
        try {
            FileWriter fw = new FileWriter(this.cacheFilePath);
            for (int i = 0; i < sizeOfList; i++) {
                fw.write(listOfTasks.getTaskTextForm(i) + "\n");
            }
            fw.close();
        } catch (FileNotFoundException e) {
            throw new CacheFileNotUsableException("M'lord, it appears that the cache file cannot be accessed, "
                    + "ensure that it is in a writable " + "place");
        } catch (IOException e) {
            throw new CacheFileNotUsableException("M'lord, it appears there was an error accessing the cached file, "
                    + "please check that I am able to access it");
        }
    }
}
