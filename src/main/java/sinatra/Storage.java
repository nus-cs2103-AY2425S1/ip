package sinatra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles the reading and writing of tasks to a file.
 */
public class Storage {

    private String fileName = "";

    /**
     * Constructs a new Storage instance with the specified file name.
     *
     * @param fileName the name of the file to store tasks
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Appends a line to the text file.
     *
     * @param line the line to append to the file
     */
    public void appendLineToTxtFile(String line) {
        System.out.println("appending line");
        File f = new File(this.fileName);
        assert f.exists() : this.fileName + " does not exist";
        try {
            FileWriter file = new FileWriter(this.fileName, true);
            try {
                file.write(line);
                file.write("\n");
                file.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return a list of tasks loaded from the file
     */
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(this.fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(":");
                if (data.length == 0) {
                    break;
                }
                String className = data[0];
                if (className.equals("Sinatra.ToDo")) {
                    tasks.add(ToDo.newObjectFromData(data[1]));
                } else if (className.equals("Sinatra.Event")) {
                    tasks.add(Event.newObjectFromData(data[1]));
                } else if (className.equals("Sinatra.Deadline")) {
                    tasks.add(Deadline.newObjectFromData(data[1]));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not created");
            File file = new File(this.fileName);
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return tasks;
    }
}
