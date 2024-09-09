package twilight;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles creation of, reading from, and writing to text file which stores the task list.
 */
public class Storage {
    private final String filePath;


    /**
     * Instantiates Storage.
     *
     * @param filePath Path to the storage file from root.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Overrides old file and saves the data stored in the task list.
     *
     * @param Tasks Tasklist to be stored.
     * @throws IOException When there is an issue with the file preventing saving.
     */
    public void saveData(ArrayList<Task> Tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : Tasks) {
            fw.write(task.toStorageString());
            fw.write(System.getProperty( "line.separator" ));
        }
        fw.close();
    }

    /**
     * Returns an arraylist with the content in the file to be read upon start up of Twilight.
     */
    private ArrayList<String> readStoredContent() {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            ArrayList<String> Tasks = new ArrayList<String>();
            while (s.hasNext()) {
                Tasks.add(s.nextLine());
            }
            return Tasks;
        } catch (FileNotFoundException e) {
            System.out.println("There was no datafile stored for twilight tasks");
            return new ArrayList<String>();
        }
    }

    /**
     * Converts the String format of the tasks in file to Arraylist</tasks>.
     *
     * @return Arraylist to be stored in TaskList
     */
    public ArrayList<Task> getStoredTasks() {
        ArrayList<String> entries = readStoredContent();
        ArrayList<Task> Tasks = new ArrayList<Task>();
        for (String entry : entries) {
            String[] input = entry.split(",");
            if (input[0].equals("T")) {
                Tasks.add(new Todo(input[1].equals("1"), input[2]));
            } else if ((input[0].equals("E"))) {
                Tasks.add(new Event(input[1].equals("1"), input[2], input[3], input[4]));
            } else {
                Tasks.add(new Deadline(input[1].equals("1"), input[2], input[3]));
            }
        }
        return Tasks;
    }
}
