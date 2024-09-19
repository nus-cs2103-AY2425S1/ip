package utility;

import exception.DescriptionNotFoundException;
import task.Task;
import task.TaskList;
import task.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * The Parser class is responsible for interpreting user commands,
 * managing task creation, and parsing date and time inputs.
 */
public class Parser {

    Message message = new Message();

    /**
     * Trims the user input command to extract the relevant part of the command.
     *
     * @param userInput The entire user input string.
     * @param size      The number of characters to be removed from the beginning.
     * @return The trimmed command.
     */
    private String trimCommand(String userInput, int size) {
        return userInput.substring(size).trim();
    }

    /**
     * Parses the user input command and executes the corresponding task action.
     *
     * @param tasks     The current list of tasks.
     * @param userInput The command input by the user.
     * @param storage   The storage object used to save tasks.
     * @return A response message based on the command executed.
     */
    public String parseCommand(TaskList tasks, String userInput, Storage storage) {
        userInput = userInput.trim();

        if (userInput.equals("bye") || userInput.equals("exit")) {
            storage.save(tasks);
            System.exit(0);
            return null;
        }

        if (userInput.equals("list")) {
            String result = tasks.listTask();
            if (result == null) {
                return message.printNoTask();
            }
            return message.printHeader("list") + result;
        }

        // Mark task as done
        if (userInput.startsWith("mark")) {
            try {
                int taskNumber = Integer.parseInt(trimCommand(userInput, 5));
                if (!tasks.markTask(taskNumber)) {
                    return message.printTaskNotExists();
                } else {
                    storage.save(tasks);
                    return message.printHeader("mark") + tasks.getTaskByID(taskNumber);
                }
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                return message.printEmptyID();
            }
        }

        // Unmark task as not done
        if (userInput.startsWith("unmark")) {
            try {
                int taskNumber = Integer.parseInt(trimCommand(userInput, 7));
                if (!tasks.unmarkTask(taskNumber)) {
                    return message.printTaskNotExists();
                } else {
                    storage.save(tasks);
                    return message.printHeader("unmark") + tasks.getTaskByID(taskNumber);
                }
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                return message.printEmptyID();
            }
        }

        // Delete a task
        if (userInput.startsWith("delete")) {
            try {
                int taskNumber = Integer.parseInt(trimCommand(userInput, 7));
                Task toBeRemoved = tasks.getTaskByID(taskNumber);
                if (!tasks.deleteTask(taskNumber) || toBeRemoved == null) {
                    return message.printTaskNotExists();
                } else {
                    storage.save(tasks);
                    return message.printHeader("delete") + toBeRemoved + message.printTaskCount(tasks.getTaskCount());
                }
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                return message.printEmptyID();
            }
        }

        // Add ToDo task
        if (userInput.startsWith("todo")) {
            try {
                String desc = trimCommand(userInput, 5);
                if (desc.isEmpty()) {
                    throw new DescriptionNotFoundException();
                }
                Task newTask = tasks.createTask(TaskType.TODO, desc);
                if (!tasks.addTask(newTask)) {
                    return message.printExists();
                }
                storage.save(tasks);
                return message.printAddTask(newTask, tasks.getTaskCount());
            } catch (StringIndexOutOfBoundsException | DescriptionNotFoundException e) {
                return message.printEmptyError(TaskType.TODO);
            }
        }

        // Add Deadline task
        if (userInput.startsWith("deadline")) {
            try {
                String[] parts = userInput.substring(9).split(" /by ");
                String desc = parts[0].trim();
                if (desc.isEmpty()) {
                    throw new DescriptionNotFoundException();
                }
                LocalDateTime by = parseDateTime(parts[1].trim());
                if (by == null) {
                    return message.printInvalidDate();
                }
                Task newTask = tasks.createTask(TaskType.DEADLINE, desc, by);
                if (!tasks.addTask(newTask)) {
                    return message.printExists();
                }
                storage.save(tasks);
                return message.printAddTask(newTask, tasks.getTaskCount());
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | DescriptionNotFoundException e) {
                return message.printEmptyError(TaskType.DEADLINE);
            }
        }

        // Add Event task
        if (userInput.startsWith("event")) {
            try {
                String[] parts = userInput.substring(6).split(" /from | /to ");
                String desc = parts[0].trim();
                if (desc.isEmpty()) {
                    throw new DescriptionNotFoundException();
                }
                LocalDateTime from = parseDateTime(parts[1].trim());
                LocalDateTime to = parseDateTime(parts[2].trim());
                if (from == null || to == null) {
                    return message.printInvalidDate();
                }
                Task newTask = tasks.createTask(TaskType.EVENT, desc, from, to);
                if (!tasks.addTask(newTask)) {
                    return message.printExists();
                }
                storage.save(tasks);
                return message.printAddTask(newTask, tasks.getTaskCount());
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | DescriptionNotFoundException e) {
                return message.printEmptyError(TaskType.EVENT);
            }
        }

        // Find task by keyword
        if (userInput.startsWith("find")) {
            try {
                String keyword = trimCommand(userInput, 5);
                if (keyword.isEmpty()) {
                    return message.printEmptyKey();
                }
                String result = tasks.findTask(keyword);
                if (result == null) {
                    return message.printNoTask();
                } else {
                    return message.printHeader("find") + result;
                }
            } catch (StringIndexOutOfBoundsException e) {
                return message.printEmptyKey();
            }
        }

        return message.printDefault();
    }

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTimeStr The date and time string to parse.
     * @return The parsed LocalDateTime object, or {@code null} if parsing fails.
     */
    public LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException | NullPointerException e) {
            return null;
        }
    }
}
