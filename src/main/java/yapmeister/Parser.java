package yapmeister;

import yapmeister.task.Task;
import yapmeister.task.ToDo;
import yapmeister.task.TaskList;
import yapmeister.task.Deadline;
import yapmeister.task.Event;
import yapmeister.task.InvalidDescriptionException;
import yapmeister.task.InvalidMarkException;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Parser that handles the logic for YapMeister
 * @author BlazeChron
 */
public class Parser {
    TaskList tasks;
    Storage storage;

    /**
     * Creates a Parser.
     * @param storage Storage where information is stored and saved.
     * @param tasks TaskList storing the tasks.
     */
    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }
    public void setTaskList(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Handles input given by UI by user.
     * @param input Input from user through UI.
     * @return boolean to indicate whether UI should continue running or terminate.
     */
    public boolean processInput(String input) {
        String[] command = input.split(" ");
        try {
            switch (command[0]) {
            case "bye":
                storage.saveLoadedTasks(tasks);
                return false;
            case "list":
                int i = 0;
                ArrayList<Task> taskArrayList = tasks.getTaskArrayList();
                for (Task task : taskArrayList) {
                    System.out.println(String.format("%d. %s", i + 1, task.toString()));
                    i++;
                }
                break;
            case "mark":
                int index = parseInt(command[1]) - 1;
                if (index >= tasks.getSize() || index < 0) {
                    throw new InvalidMarkException("No task at that index");
                }
                tasks.get(index).setCompleted(true);
                System.out.println("You did this:");
                System.out.println(tasks.get(index).toString());
                break;
            case "unmark":
                int umindex = parseInt(command[1]) - 1;
                if (umindex >= tasks.getSize() || umindex < 0) {
                    throw new InvalidMarkException("No task at that index");
                }
                tasks.get(umindex).setCompleted(false);
                System.out.println("You undid this:");
                System.out.println(tasks.get(umindex).toString());
                break;
            case "todo":
                //Fallthrough
            case "deadline":
                //Fallthrough
            case "event":
                Task task = null;
                task = createTaskInput(command[0], input);
                System.out.println("Added:");
                tasks.addTask(task);
                System.out.println(String.format("You have %d tasks", tasks.getSize()));
                break;
            case "delete":
                int dindex = parseInt(command[1]) - 1;
                if (dindex >= tasks.getSize() || dindex < 0) {
                    throw new InvalidMarkException("No task at that index");
                }
                Task removedTask = tasks.deleteTask(dindex);
                System.out.println("Removed this task");
                System.out.println(removedTask.toString());
                System.out.println(String.format("You have %d tasks", tasks.getSize()));
                break;
            default:
                System.out.println("Invalid input please yap yapology");
            }
        } catch (InvalidDescriptionException | InvalidMarkException | IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }
        return true;
    }

    /**
     * Creates Task based on user input.
     * @param type Type of Task to create.
     * @param input User input.
     * @return Task created.
     * @throws InvalidDescriptionException Error when input given is invalid for the type of task.
     */
    private Task createTaskInput(String type, String input) throws InvalidDescriptionException {
        Task task = null;
        String[] format;
        switch (type) {
        case "todo":
            format = input.split("todo ");
            if (format.length <= 1) {
                throw new InvalidDescriptionException("Invalid yapmeister.task.Task description");
            }
            task = new ToDo(format[1]);
            break;
        case "deadline":
            format = input.split("deadline | /by ");
            if (format.length <= 2) {
                throw new InvalidDescriptionException("Invalid yapmeister.task.Task description");
            }
            task = new Deadline(format[1], format[2]);
            break;
        case "event":
            format = input.split("event | /from | /to ");
            if (format.length <= 3) {
                throw new InvalidDescriptionException("Invalid yapmeister.task.Task description");
            }
            task = new Event(format[1], format[2], format[3]);
            break;
        }
        return task;
    }
}
