package bao.main;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import bao.task.Deadline;
import bao.task.Event;
import bao.task.Task;
import bao.task.ToDo;

/**
 * The Parser class is responsible for interpreting user input and executing the needed actions, such as adding,
 * listing, marking and un-marking or deleting tasks.
 */
public class Parser {

    /**
     * Parses the user command and executes the appropriate action.
     *
     * @param command Full user command string.
     * @param tasks TaskList object containing all current tasks.
     * @param storage Storage object for saving and loading tasks.
     */
    public static String parse(String command, TaskList tasks, Storage storage) {
        assert command != null : "Command should not be null";
        assert tasks != null : "Task list should not be null";
        assert storage != null : "Storage should not be null";

        StringBuilder response = new StringBuilder();
        String[] parts = command.split(" ", 2);
        String commandType = parts[0];
        String args = parts.length > 1 ? parts[1] : "";
        assert args != null : "Arguments of the command should not be null";

        switch (commandType) {
        case "welcome" -> response.append(handleWelcome());
        case "bye" -> handleBye();
        case "list" -> response.append(handleList(tasks));
        case "mark" -> response.append(handleMark(args, tasks, storage));
        case "unmark" -> response.append(handleUnmark(args, tasks, storage));
        case "todo" -> response.append(handleToDo(args, tasks, storage));
        case "deadline" -> response.append(handleDeadline(args, tasks, storage));
        case "event" -> response.append(handleEvent(args, tasks, storage));
        case "delete" -> response.append(handleDelete(args, tasks, storage));
        case "on" -> response.append(handleOn(args, tasks));
        case "find" -> response.append(handleFind(args, tasks));
        case "tag" -> response.append(handleTag(args, tasks, storage));
        case "untag" -> response.append(handleRemoveTag(args, tasks, storage));
        default -> response.append("Bao needs a proper command :(");
        }
        return response.toString();
    }

    private static String handleWelcome() {
        return "Bao says hello! Bao's name is Bao but you can call me Bao\n\nBao is ready for instructions";
    }

    private static void handleBye() {
        System.exit(0);
    }

    private static String handleList(TaskList tasks) {
        if (tasks.getTasks().isEmpty()) {
            return "Bao is not tracking anything!";
        }
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return response.toString().trim();
    }

    private static String handleMark(String args, TaskList tasks, Storage storage) {
        try {
            int index = Integer.parseInt(args) - 1;
            tasks.getTask(index).mark();
            storage.save(tasks.getTasks());
            return "Bao has marked it as done!\n" + tasks.getTask(index);
        } catch (Exception e) {
            return "Bao needs a valid task number to mark!";
        }
    }

    private static String handleUnmark(String args, TaskList tasks, Storage storage) {
        try {
            int index = Integer.parseInt(args) - 1;
            tasks.getTask(index).unmark();
            storage.save(tasks.getTasks());
            return "Bao has marked it as not done!\n";
        } catch (Exception e) {
            return "Bao needs a valid task number to unmark!";
        }
    }

    private static String handleToDo(String args, TaskList tasks, Storage storage) {
        if (args.isEmpty()) {
            return "Bao needs a description of the task!";
        } else {
            try {
                tasks.addTask(new ToDo(args));
                storage.save(tasks.getTasks());
                return "Bao got it! Bao is now tracking: " + tasks.getTask(tasks.size() - 1).toString();
            } catch (IOException e) {
                return "Bao could not save tasks";
            }
        }
    }

    private static String handleDeadline(String args, TaskList tasks, Storage storage) {
        String[] argParts = args.split(" /by ");
        assert argParts.length >= 2 : "Bao needs a proper description and deadline for the task!";
        try {
            LocalDateTime dateTime = LocalDateTime.parse(argParts[1], Bao.getInputDateFormat());
            tasks.addTask(new Deadline(argParts[0], dateTime));
            storage.save(tasks.getTasks());
            return "Bao got it! Bao is now tracking: " + tasks.getTask(tasks.size() - 1).toString();
        } catch (DateTimeParseException e) {
            return "Bao needs a valid date format";
        } catch (IOException e) {
            return "Bao could not save tasks";
        }
    }

    private static String handleEvent(String args, TaskList tasks, Storage storage) {
        String[] argParts = args.split(" /from | /to ");
        assert argParts.length >= 3 : "Bao needs a proper description and duration for the task!";
        try {
            LocalDateTime from = LocalDateTime.parse(argParts[1], Bao.getInputDateFormat());
            LocalDateTime to = LocalDateTime.parse(argParts[2], Bao.getInputDateFormat());
            tasks.addTask(new Event(argParts[0], from, to));
            storage.save(tasks.getTasks());
            return "Bao got it! Bao is now tracking: " + tasks.getTask(tasks.size() - 1).toString();
        } catch (DateTimeParseException e) {
            return "Bao needs a valid date format";
        } catch (IOException e) {
            return "Bao could not save tasks";
        }
    }

    private static String handleDelete(String args, TaskList tasks, Storage storage) {
        try {
            int index = Integer.parseInt(args) - 1;
            Task removed = tasks.getTask(index);
            tasks.deleteTask(index);
            storage.save(tasks.getTasks());
            return "Bao has removed this task:\n" + removed.toString()
                    + "\nBao is now tracking " + tasks.size() + " tasks";
        } catch (Exception e) {
            return "Bao needs a task number to delete!";
        }
    }

    private static String handleOn(String args, TaskList tasks) {
        try {
            LocalDate date = LocalDate.parse(args, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            StringBuilder response = new StringBuilder("Bao showing tasks on "
                    + date.format(Bao.getDateOnlyFormat()) + ":");
            boolean found = false;

            for (Task task : tasks.getTasks()) {
                if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    if (deadlineTask.getDate().toLocalDate().equals(date)) {
                        response.append("\n").append(deadlineTask.toString());
                        found = true;
                    }
                } else if (task instanceof Event) {
                    Event eventTask = (Event) task;
                    if (eventTask.getFromDateTime().toLocalDate().equals(date)) {
                        response.append("\n").append(eventTask.toString());
                        found = true;
                    }
                }
            }

            if (!found) {
                response.append("Bao cannot find any tasks on this date!");
            }
            return response.toString();
        } catch (DateTimeParseException e) {
            return "Bao needs a valid date format such as 2024-08-28";
        }
    }

    private static String handleFind(String args, TaskList tasks) {
        if (args.isEmpty()) {
            return "Bao needs a keyword to find in the tasks!";
        } else {
            ArrayList<Task> foundTasks = tasks.findTasks(args.trim());
            assert foundTasks != null : "Result of findTasks must not be null";

            if (foundTasks.isEmpty()) {
                return "Bao could not find any tasks with the keyword";
            } else {
                StringBuilder response = new StringBuilder("Bao found these tasks with the keyword!");
                for (int i = 0; i < foundTasks.size(); i++) {
                    response.append("\n").append(i + 1).append(". ").append(foundTasks.get(i).toString());
                }
                return response.toString();
            }
        }
    }

    private static String handleTag(String args, TaskList tasks, Storage storage) {
        String[] argParts = args.split(" ", 2);
        if (argParts.length < 2) {
            return "Bao needs a task number and a tag to assign!";
        }
        try {
            int index = Integer.parseInt(argParts[0]) - 1;
            String tag = argParts[1];
            tasks.getTask(index).addTag(tag);
            storage.save(tasks.getTasks());
            return "Bao has added the tag #" + tag + " to this task!\n" + tasks.getTask(index);
        } catch (Exception e) {
            return "Bao needs a valid task number and a valid tag!";
        }
    }

    private static String handleRemoveTag(String args, TaskList tasks, Storage storage) {
        String[] argParts = args.split(" ", 2);
        if (argParts.length < 2) {
            return "Bao needs a task number and a tag to remove!";
        }
        try {
            int index = Integer.parseInt(argParts[0]) - 1;
            String tag = argParts[1];
            boolean removed = tasks.getTask(index).removeTag(tag);
            storage.save(tasks.getTasks());
            if (removed) {
                return "Bao has removed the tag #" + tag + " from this task!\n" + tasks.getTask(index);
            } else {
                return "Bao could not find the tag #" + tag + " from this task!\n" + tasks.getTask(index);
            }
        } catch (Exception e) {
            return "Bao needs a valid task number and a valid tag!";
        }
    }
}
