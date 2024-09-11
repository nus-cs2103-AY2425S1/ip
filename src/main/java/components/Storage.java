package components;

import exceptions.LightException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage that stores and loads tasks from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a Storage object.
     *
     * @param filePath The file path of the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;

    }

    /**
     * Loads tasks from the storage file.
     *
     * @return The list of tasks loaded from the storage file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        Task task = null;
        try {
            File fileObj = new File(filePath);
            if (!fileObj.getParentFile().exists()) {
                fileObj.getParentFile().mkdirs();
            }
            if (fileObj.createNewFile()) {
                //file created
            } else {
                //file already exist
            }
            Scanner scanner = new Scanner(fileObj);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Boolean isMarked = data.charAt(7) == 'X';
                switch (data.charAt(4)) {
                case 'T':
                    try {
                        task = new Todo(data.substring(10));
                    } catch (LightException e) {
                        System.out.println(e);
                    }

                    break;
                case 'D':
                    try {
                        String betweenBrackets = data.substring(data.lastIndexOf("(") + 1, data.lastIndexOf(")"));
                        String byDate = betweenBrackets.replace("by: ", "");
                        task = new Deadline(data.substring(10, data.indexOf("(by:")), byDate);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Not enough arguments");
                    } catch (LightException e) {
                        System.out.println(e);
                    }

                    break;
                case 'E':
                    try {

                        String betweenBrackets = data.substring(data.lastIndexOf("(") + 1, data.lastIndexOf(")"));
                        String fromDate = betweenBrackets.substring(betweenBrackets.indexOf("from: ") + 6, betweenBrackets.indexOf("to: ")).stripTrailing();
                        String toDate = betweenBrackets.substring(betweenBrackets.indexOf("to: ") + 4);
                        task = new Event(data.substring(10, data.indexOf("(from:")), fromDate, toDate);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Not enough arguments");
                    } catch (LightException e) {
                        System.out.println(e);
                    }

                    break;
                }
                if (task != null && list.add(task)) {
                    if (isMarked) {
                        task.markAsDone();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(TaskList.arrayToNumberedString(list));
        return list;
    }

    /**
     * Writes text to the storage file.
     *
     * @param textToAdd The text to be written to the storage file.
     */
    public void write(String textToAdd) {
        try {
            File fileObj = new File(filePath);
            if (!fileObj.getParentFile().exists()) {
                fileObj.getParentFile().mkdirs();
            }
            if (fileObj.createNewFile()) {
                //file created
                System.out.println("Created new file");
            } else {
                //file already exist
                //System.out.println("File exist");
            }
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(textToAdd);
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
