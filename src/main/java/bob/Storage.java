package bob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

import bob.exceptions.EmptyArgumentException;
import bob.exceptions.MissingArgumentException;
import bob.tasks.Task;
import bob.tasks.TaskList;

/**
 * Handles all the storage related actions
 */
public class Storage {

    /**
     * Reads the data from the given file and adds it to the taskList
     *
     * @param taskList TaskList to which all the data is to be added into
     * @param filePath File path to the file from which the data is to be read from
     */
    public static void readData(TaskList taskList, String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath), "UTF-8")) {
            while (scanner.hasNextLine()) {
                String in = scanner.nextLine();
                try {
                    Task newTask;
                    switch (in.split(" ")[0]) {
                    case "deadline":
                        newTask = Parser.newDeadline(in.split(" ", 3)[2]);
                        break;
                    case "event":
                        newTask = Parser.newEvent(in.split(" ", 3)[2]);
                        break;
                    case "todo":
                        newTask = Parser.newToDo(in.split(" ", 3)[2]);
                        break;
                    default:
                        System.out.println("Corrupted data found");
                        continue;
                    }
                    if (Objects.equals(in.split(" ")[1], "true")) {
                        newTask.mark();
                    }
                    taskList.addTask(newTask);
                } catch (MissingArgumentException | EmptyArgumentException | DateTimeParseException e) {
                    UI.printMessage("Corrupted data found");
                }
            }
            if (taskList.size() == 0) {
                UI.printMessage("No data was loaded");
            } else {
                UI.printMessage(String.format("Successfully loaded %d entries from last save", taskList.size()));
            }
        } catch (FileNotFoundException e) {
            UI.printMessage("No saved data found");
        }
    }

    /**
     * Exports the taskList to be stored in a file
     *
     * @param taskList TaskList which is to be exported
     * @param filePath File path to the file to which the data is to be exported to
     */
    public static void writeData(TaskList taskList, String filePath) {
        try {
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");
            writer.println(taskList.export());
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            UI.printMessage("Failed to save data");
        }

    }
}
