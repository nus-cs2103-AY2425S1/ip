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
     * Command to list all tasks.
     *
     * @param tasks
     */
    public static void processListCommand(List<Task> tasks) {
        String out = "Here are the tasks in your list: \n";
        for (int i = 0; i < tasks.size(); i++) {
            out += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        Ui.dialogue(out);
    }

    /**
     * Command to mark, unmark, or delete a task.
     *
     * @param userInput
     * @param tasks
     * @throws BobException
     * @throws IOException
     */
    public static void processTaskModificationCommands(String userInput, List<Task> tasks) throws BobException, IOException {
        String[] words = userInput.split(" ");
        int index = Integer.parseInt(words[1]) - 1;

        if (index < 0 || index >= tasks.size()) {
            throw new BobException("The task index you provided is out of range.");
        }

        if (userInput.startsWith("mark")) {
            tasks.get(index).markAsDone();
            Ui.dialogue("Nice! I've marked this task as done: \n" + tasks.get(index));
        } else if (userInput.startsWith("unmark")) {
            tasks.get(index).unmarkAsDone();
            Ui.dialogue("OK, I've marked this task as not done yet: \n" + tasks.get(index));
        } else if (userInput.startsWith("delete")) {
            Task removed = tasks.remove(index);
            Ui.dialogue("Noted. I've removed this task: \n" + removed + "\nNow you have " + tasks.size() + " tasks in the list.");
        }
        Storage.saveTasks(tasks);
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
    public static void processTaskCreationCommands(String userInput, List<Task> tasks) throws BobException, IOException {
        Task newTask = null;
        if (userInput.startsWith("todo")) {
            String description = userInput.substring(5).trim();

            if (description.isEmpty()) {
                throw new BobException("The description of a todo cannot be empty.");
            }
            
            newTask = new Todo(description);

        } else if (userInput.startsWith("deadline")) {
            String[] parts = userInput.substring(9).split(" /by ");
            if (parts[0].trim().isEmpty()) {
                throw new BobException("The description of a deadline cannot be empty.");
            }
            LocalDateTime by = Parser.parseDateTime(parts[1].trim());
            newTask = new Deadline(parts[0].trim(), by);

        } else if (userInput.startsWith("event")) {
            String[] parts = userInput.substring(6).split(" /from ");
            String[] times = parts[1].split(" /to ");
            if (parts[0].trim().isEmpty()) {
                throw new BobException("The description of an event cannot be empty.");
            }
            LocalDateTime from = Parser.parseDateTime(times[0].trim());
            LocalDateTime to = Parser.parseDateTime(times[1].trim());
            newTask = new Event(parts[0].trim(), from, to);
        }

        tasks.add(newTask);
        Ui.dialogue("Got it. I've added this task: \n" + newTask + "\nNow you have " + tasks.size() + " tasks in the list.");
        Storage.saveTasks(tasks);
    }
}
