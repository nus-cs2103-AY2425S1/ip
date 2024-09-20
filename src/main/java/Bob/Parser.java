package bob;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

/**
 * Represents a parser that parses user input and executes the corresponding
 * commands.
 */
class Parser {

    /**
     * Parses a string into a LocalDateTime object.
     *
     * @param input
     * @return LocalDateTime
     * @throws BobException
     */
    public static LocalDateTime parseDateTime(String input) throws BobException {
        try {
            // Correct format: "dd/MM/yyyy HHmm" (e.g., "02/12/2019 1800")
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH);
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new BobException("The date and time format is incorrect: " + e.getMessage());
        }
    }

    /**
     * Returns a list of tasks in a human-readable string.
     *
     * @param tasks
     */
    public static String executeListCommand(List<Task> tasks) {
        String out = "Here are the tasks in your list: \n";
        for (int i = 0; i < tasks.size(); i++) {
            out += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return out;
    }

    /**
     * Command to mark, unmark, or delete a task.
     *
     * @param userInput
     * @param tasks
     * @return String
     * @throws BobException
     * @throws IOException
     */
    public static String executeTaskModificationCommands(String userInput, List<Task> tasks) 
            throws BobException, IOException {
        String[] words = userInput.split(" ");
        int index = Integer.parseInt(words[1]) - 1;

        if (index < 0 || index >= tasks.size()) {
            throw new BobException("The task index you provided is out of range.");
        }

        if (userInput.startsWith("mark")) {
            tasks.get(index).markAsDone();
            return "Nice! I've marked this task as done: \n" + tasks.get(index);
        } else if (userInput.startsWith("unmark")) {
            tasks.get(index).unmarkAsDone();
            return "OK, I've marked this task as not done yet: \n" + tasks.get(index);
        } else if (userInput.startsWith("delete")) {
            Task removed = tasks.remove(index);
            return "Noted. I've removed this task: \n" + removed + "\nNow you have " 
                    + tasks.size() + " tasks in the list.";
        }
        Storage.saveTasks(tasks);

        return "Invalid command.";
    }

    /**
     * Parses the user input to create a deadline task.
     * 
     * @param userInput
     * @return Deadline
     * @throws BobException
     */
    public static Task getDeadlineFromUserInput(String userInput) throws BobException {
        String[] parts = userInput.substring(9).split(" /by ");

        if (parts[0].trim().isEmpty()) {
            throw new BobException("The description of a deadline cannot be empty.");
        }

        LocalDateTime by = Parser.parseDateTime(parts[1].trim());
        return new Deadline(parts[0].trim(), by);
    }

    /**
     * Parses the user input to create a todo task.
     * 
     * @param userInput
     * @return Todo
     * @throws BobException
     */
    public static Task getTodoFromUserInput(String userInput) throws BobException {
        String description = userInput.substring(5).trim();

        if (description.isEmpty()) {
            throw new BobException("The description of a todo cannot be empty.");
        }

        return new Todo(description);
    }

    /**
     * Parses the user input to create an event task.
     * 
     * @param userInput
     * @return Event
     * @throws BobException
     */
    public static Task getEventFromUserInput(String userInput) throws BobException {
        String[] parts = userInput.substring(6).split(" /from ");
        String[] times = parts[1].split(" /to ");

        if (parts[0].trim().isEmpty()) {
            throw new BobException("The description of an event cannot be empty.");
        }

        LocalDateTime from = Parser.parseDateTime(times[0].trim());
        LocalDateTime to = Parser.parseDateTime(times[1].trim());
        return new Event(parts[0].trim(), from, to);
    }

    /**
     * Creates a new task based on the user input and adds it to the list of
     * tasks.
     *
     * @param userInput
     * @param tasks
     * @throws BobException
     * @throws IOException
     */
    public static String executeTaskCreationCommands(String userInput, List<Task> tasks) 
            throws BobException, IOException {
        Task newTask = null;
        if (userInput.startsWith("todo")) {
            newTask = getTodoFromUserInput(userInput);

        } else if (userInput.startsWith("deadline")) {
            newTask = getDeadlineFromUserInput(userInput);

        } else if (userInput.startsWith("event")) {
            newTask = getEventFromUserInput(userInput);

        } else if (userInput.startsWith("find")) {
            String keyword = userInput.substring(5).trim();
            return Storage.findTasks(tasks, keyword);
        } else {
            throw new BobException("I'm sorry, but I don't know what that means :(");
        }

        tasks.add(newTask);
        Storage.saveTasks(tasks);
        return "Got it. I've added this task: \n" + newTask + "\nNow you have " + tasks.size() 
                + " tasks in the list.";
    }
}
