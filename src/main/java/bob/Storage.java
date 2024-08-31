package bob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The {@code Storage} class serves as the storage location for loading and saving of tasks to and from a file.
 * It manages file creation, reading, and writing, ensuring that tasks are persisted between sessions.
 */
public class Storage {
    private File file;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     * If the file does not exist, it attempts to create a new file.
     *
     * @param filePath the file path where the tasks will be stored.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        try {
            if (this.file.createNewFile()) {
                Printer.prettyPrint(new String[] { "A new save file has been created."});
            }
        } catch (IOException e) {
            Printer.prettyPrint(new String[] {"OOPS! The save file could not be found or created."});
        }
    }

    /**
     * Loads tasks from the file into a {@code TaskList} object.
     * If the file is not found, an empty {@code TaskList} is returned.
     *
     * @return a {@code TaskList} containing the tasks loaded from the file.
     */
    public TaskList loadFile() {
        TaskList taskList = new TaskList();
        int i = 1;
        Scanner scanner;
        try {
            scanner = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            Printer.prettyPrint(new String[] {"OOPS! The save file could not be found."});
            return taskList;
        }
        while (scanner.hasNextLine()) {
            String text = scanner.nextLine();
            String[] input = text.split(" \\| ");
            if (input[0].equals("T")) {
                taskList.todo(input[2]);
            } else if (input[0].equals("D")) {
                taskList.deadline(input[2], input[3]);
            } else if (input[0].equals("E")) {
                taskList.event((input[2]), input[3], input[4]);
            }

            if (input[1].equals("1")) {
                taskList.mark(i);
            }
            i++;
        }

        Printer.prettyPrint(new String[] {"Saved tasks have been successfully loaded."});
        return taskList;
    }

    /**
     * Saves the tasks from a {@code TaskList} object to the file.
     * If an error occurs during saving, an error message is printed.
     *
     * @param taskList the {@code TaskList} containing the tasks to be saved.
     */
    public void saveFile(TaskList taskList) {
        FileWriter writer;
        try {
            writer = new FileWriter(this.file);
            writer.write(taskList.getSaveFormat());
            writer.close();
        } catch (IOException e) {
            Printer.prettyPrint(new String[]{"OOPS! There was a problem saving the file."});
        }

        Printer.prettyPrint(new String[]{"Your tasks have been successfully saved."});
    }
}
