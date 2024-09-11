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

import static components.Parser.getCommandTypeFromStorage;

/**
 * Represents a storage that stores and loads tasks from a file.
 */
public class Storage {
    private final String filePath;

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
    private File getFileOrMakeFile() throws IOException {
        File fileObj = new File(filePath);
        if (!fileObj.getParentFile().exists()) {
            fileObj.getParentFile().mkdirs();
        }
        if (fileObj.createNewFile()) {
            //file created
        } else {
            //file already exist
        }
        return fileObj;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File fileObj = getFileOrMakeFile();
            Scanner scanner = new Scanner(fileObj);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                boolean marked = data.charAt(7) == 'X';
                char taskType = data.charAt(4);
                String taskDescription = data.substring(10);
                try {
                    switch (getCommandTypeFromStorage(taskType)) {
                    case TODO:
                        list.add(new Todo(taskDescription));
                        break;
                    case DEADLINE:
                        String byDate = data.substring(data.lastIndexOf("(by: ") + 5, data.lastIndexOf(")"));
                        if (data.contains("(by:")) {
                            list.add(new Deadline(taskDescription, byDate));
                        } else {
                            System.out.println("Not enough arguments");
                        }
                        break;
                    case EVENT:
                        String betweenBrackets = data.substring(data.lastIndexOf("(") + 1, data.lastIndexOf(")"));
                        String fromDate = betweenBrackets.split("from: ")[1].split(" to: ")[0].strip();
                        String toDate = betweenBrackets.split("to: ")[1];
                        list.add(new Event(taskDescription, fromDate, toDate));
                        break;
                    }
                    if (marked) {
                        list.get(list.size() - 1).markAsDone();
                    }
                } catch (LightException e) {
                    System.out.println(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * Writes text to the storage file.
     *
     * @param textToAdd The text to be written to the storage file.
     */
    public void write(String textToAdd) {
        try {
            File fileObj = getFileOrMakeFile();
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(textToAdd);
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
