package spiderman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles the loading and saving of tasks to and from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object that will handle the given file path.
     *
     * @param filePath The file path where tasks will be loaded from and saved to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * The tasks are read from the file and converted into their respective
     * task types (Todo, Deadline, Event).
     *
     * @return An ArrayList containing all tasks loaded from the storage file.
     */
    public ArrayList<Task> loadTasksFromStorage() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File myObj = new File(this.filePath);
            Scanner scan = new Scanner(myObj);
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                String[] savedTasks = data.split("\\|");

                switch (savedTasks[0]) {
                case "T":
                    Todo todo = new Todo(savedTasks[2]);
                    if (savedTasks[1].equals("T")) {
                        todo.markAsDone();
                    } else {
                        todo.markAsNotDone();
                    }
                    taskList.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(savedTasks[2], LocalDate.parse(savedTasks[3]));
                    if (savedTasks[1].equals("T")) {
                        deadline.markAsDone();
                    } else {
                        deadline.markAsNotDone();
                    }
                    taskList.add(deadline);
                    break;
                case "E":
                    Event event = new Event(savedTasks[2],
                            LocalDateTime.parse(savedTasks[3]),
                            LocalDateTime.parse(savedTasks[4]));

                    if (savedTasks[1].equals("T")) {
                        event.markAsDone();
                    } else {
                        event.markAsNotDone();
                    }
                    taskList.add(event);
                    break;
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.print("");
        }
        return taskList;
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param taskList The list of tasks to be saved to the file.
     */
    public void saveTasksToStorage(ArrayList<Task> taskList) {
        try (FileWriter writer = new FileWriter(this.filePath)) {
            for (Task task : taskList) {
                writer.write(task.toTextFormat());
                writer.write("\n");
            }
            System.out.println("Tasks have been saved to tasks.txt.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file.");
        }
    }
}
