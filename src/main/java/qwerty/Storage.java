package qwerty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import qwerty.task.Deadline;
import qwerty.task.Event;
import qwerty.task.Task;
import qwerty.task.Todo;

public class Storage {
    /** The file path of the save file */
    private String savePath;

    public Storage(String filePath) {
        this.savePath = filePath;
    }

    /**
     * Saves the current list of tasks in the given TaskList to the hard drive.
     * Writes into a file at the specified path in savePath.
     * If the file does not exist, create it.
     *
     * @param taskList The TaskList object.
     */
    public void saveTasks(TaskList taskList) {
        File file = new File(savePath);
        if (file.exists()) {
            try {
                FileWriter writer = new FileWriter(savePath);
                writer.write(taskList.generateSaveString());
                writer.close();
            } catch (IOException e) {
                System.out.println("Could not write to save file: " + e.getMessage());
            }
        } else {
            try {
                file.createNewFile();
                saveTasks(taskList);
            } catch (IOException e) {
                System.out.println("Could not create save file: " + e.getMessage());
            }
        }
    }

    /**
     * Loads tasks into the given TaskList.
     * Parses the string contents of the file located at the specified savepath.
     * If the file does not exist, do nothing.
     * If certain arguments are missing, prints an error statement.
     *
     * @param taskList The TaskList object to load into.
     */
    public void loadTasks(TaskList taskList) {
        File file = new File(savePath);
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                int lineNumber = 0;
                while (scanner.hasNextLine()) {
                    lineNumber++;
                    try {
                        String[] args = scanner.nextLine().split("\\|");
                        switch (args[0]) {
                        case "":
                            break; // skip empty lines
                        case "T":
                            if (args.length < 3) {
                                throw new QwertyException("Missing arguments for Todo task");
                            }
                            Task todo = new Todo(args[2]);
                            if (args[1].equals("X")) {
                                todo.markAsDone();
                            }
                            taskList.addTask(todo);
                            break;
                        case "D":
                            if (args.length < 3) {
                                throw new QwertyException("Missing arguments for Deadline task");
                            }
                            Task deadline = new Deadline(
                                    args[2],
                                    LocalDateTime.parse(args[3], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                            );
                            if (args[1].equals("X")) {
                                deadline.markAsDone();
                            }
                            taskList.addTask(deadline);
                            break;
                        case "E":
                            if (args.length < 3) {
                                throw new QwertyException("Missing arguments for Event task");
                            }
                            Task event = new Event(args[2],
                                    LocalDateTime.parse(args[3], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                                    LocalDateTime.parse(args[4], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                            );
                            if (args[1].equals("X")) {
                                event.markAsDone();
                            }
                            taskList.addTask(event);
                            break;
                        default:
                            System.out.println("Unrecognised task identifier: " + args[0]);
                            break;
                        }
                    } catch (QwertyException e) {
                        System.out.println("Error while reading from line "
                                + lineNumber + ": " + e.getMessage());
                    }
                }
            } catch (FileNotFoundException e) {
                // this should not happen, already checked if file exists
            }
        }
    }
}
