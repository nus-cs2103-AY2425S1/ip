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

        switch (commandType) {
        case "welcome" -> {
            response.append("Bao says hello! Bao's name is Bao but you can call me Bao\n")
                    .append("\n")
                    .append("Bao is ready for instructions");
        }
        case "bye" -> {
            response.append("Bye :( Come back soon!");
            System.exit(0);
        }
        case "list" -> {
            if (tasks.getTasks().isEmpty()) {
                response.append("Bao is not tracking anything!");
                break;
            }
            for (int i = 0; i < tasks.size(); i++) {
                response.append((i + 1)).append(". ").append(tasks.getTask(i).toString());
                if (i != tasks.size() - 1) {
                    response.append("\n");
                }
            }
        }
        case "mark" -> {
            try {
                int index = Integer.parseInt(args) - 1;
                tasks.getTask(index).mark();
                storage.save(tasks.getTasks());
                response.append("Bao has marked it as done!").append("\n");
                response.append(tasks.getTask(index).toString());
            } catch (Exception e) {
                response.append("Bao needs a valid task number to mark!");
            }
        }
        case "unmark" -> {
            try {
                int index = Integer.parseInt(args) - 1;
                tasks.getTask(index).unmark();
                storage.save(tasks.getTasks());
                response.append("Bao has marked it as not done!").append("\n");
                response.append(tasks.getTask(index).toString());
            } catch (Exception e) {
                response.append("Bao needs a valid task number to unmark!");
            }
        }
        case "todo" -> {
            if (args.isEmpty()) {
                response.append("Bao needs a description of the task!");
            } else {
                try {
                    tasks.addTask(new ToDo(args));
                    storage.save(tasks.getTasks());
                    response.append("Bao got it! Bao is now tracking: ");
                    response.append(tasks.getTask(tasks.size() - 1).toString());
                } catch (IOException e) {
                    response.append("Bao could not save tasks");
                }
            }
        }
        case "deadline" -> {
            String[] argParts = args.split(" /by ");
            if (argParts.length < 2) {
                response.append("Bao needs a proper description and deadline for the task!");
            } else {
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(argParts[1], Bao.getInputDateFormat());
                    tasks.addTask(new Deadline(argParts[0], dateTime));
                    storage.save(tasks.getTasks());
                    response.append("Bao got it! Bao is now tracking: ");
                    response.append(tasks.getTask(tasks.size() - 1).toString());
                } catch (DateTimeParseException e) {
                    response.append("Bao needs a valid date format");
                } catch (IOException e) {
                    response.append("Bao could not save tasks");
                }
            }
        }
        case "event" -> {
            String[] argParts = args.split(" /from | /to ");
            if (argParts.length < 3) {
                response.append("Bao needs a proper description and duration for the task!");
            } else {
                try {
                    LocalDateTime from = LocalDateTime.parse(argParts[1], Bao.getInputDateFormat());
                    LocalDateTime to = LocalDateTime.parse(argParts[2], Bao.getInputDateFormat());
                    tasks.addTask(new Event(argParts[0], from, to));
                    storage.save(tasks.getTasks());
                    response.append("Bao got it! Bao is now tracking: ");
                    response.append(tasks.getTask(tasks.size() - 1).toString());
                } catch (DateTimeParseException e) {
                    response.append("Bao needs a valid date format");
                } catch (IOException e) {
                    response.append("Bao could not save tasks");
                }
            }
        }
        case "delete" -> {
            try {
                int index = Integer.parseInt(args) - 1;
                Task removed = tasks.getTask(index);
                tasks.deleteTask(index);
                storage.save(tasks.getTasks());
                response.append("Bao has removed this task:").append("\n")
                        .append(removed.toString()).append("\n")
                        .append("Bao is now tracking ").append(tasks.size()).append(" tasks");
            } catch (Exception e) {
                response.append("Bao needs a task number to delete!");
            }
        }
        case "on" -> {
            try {
                LocalDate date = LocalDate.parse(args, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                response.append("Bao showing tasks on ").append(date.format(Bao.getDateOnlyFormat())).append(":");
                boolean found = false;
                for (Task task : tasks.getTasks()) {
                    if (task instanceof Deadline) {
                        Deadline deadlineTask = (Deadline) task;
                        if (deadlineTask.getDate().toLocalDate().equals(date)) {
                            response.append(deadlineTask.toString());
                            found = true;
                        }
                    } else if (task instanceof Event) {
                        Event eventTask = (Event) task;
                        if (eventTask.getFromDateTime().toLocalDate().equals(date)) {
                            response.append(eventTask.toString());
                            found = true;
                        }
                    }
                    if (!found) {
                        response.append("Bao cannot find any tasks on this date!");
                    }
                }

            } catch (DateTimeParseException e) {
                response.append("Bao needs a valid date format such as 2024-08-28");
            }
        }
        case "find" -> {
            if (args.isEmpty()) {
                response.append("Bao needs a keyword to find in the tasks!");
            } else {
                ArrayList<Task> foundTasks = tasks.findTasks(args.trim());
                if (foundTasks.isEmpty()) {
                    response.append("Bao could not find any tasks with the keyword");
                } else {
                    response.append("Bao found these tasks with the keyword!");
                    for (int i = 0; i < foundTasks.size(); i++) {
                        response.append((i + 1)).append(". ").append(foundTasks.get(i).toString());
                    }
                }
            }
        }
        default -> {
            response.append("Bao needs a proper command :(");
        }
        }
        return response.toString();
    }
}
