package seanbot;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import seanbot.tasks.Deadline;
import seanbot.tasks.Event;
import seanbot.tasks.Task;
import seanbot.tasks.Todo;

/**
 * The Parser class processes user input and triggers corresponding commands.
 */
public class Parser {

    public void parse(String userInput, TaskList tasks, Ui ui, Storage storage) throws SeanBotException, IOException {
        assert userInput != null : "User Input cannot be null";
        assert tasks != null : "Tasks cannot be null";
        assert ui != null : "Ui object cannot be null";
        assert storage != null : "Storage object cannot be null";

        String[] part = userInput.split(" ", 2);
        String command = part[0];

        switch (command) {
            case "b":
                handleExit(ui);
                break;
            case "l":
                handleList(ui, tasks);
                break;
            case "m":
                handleMark(part, tasks, storage);
                break;
            case "um":
                handleUnmark(part, tasks, storage);
                break;
            case "t":
                handleTodo(part, tasks, storage);
                break;
            case "dl":
                handleDeadline(part, tasks, storage);
                break;
            case "e":
                handleEvent(part, tasks, storage);
                break;
            case "del":
                handleDelete(part, tasks, storage);
                break;
            case "f":
                handleFind(part, tasks, ui);
                break;
            default:
                throw new SeanBotException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private void handleExit(Ui ui) {
        ui.showExitMessage();
    }

    private void handleList(Ui ui, TaskList tasks) {
        ui.showTaskList(tasks);
    }

    private void handleMark(String[] part, TaskList tasks, Storage storage) throws SeanBotException, IOException {
        if (part.length < 2) {
            throw new SeanBotException("The task number of mark cannot be empty");
        }
        int markIndex = Integer.parseInt(part[1]) - 1;
        if (markIndex < 0 || markIndex >= tasks.size()) {
            throw new SeanBotException("The task number must be valid.");
        }
        tasks.getTask(markIndex).markAsDone();
        storage.save(tasks.getTasks());
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.getTask(markIndex));
    }

    private void handleUnmark(String[] part, TaskList tasks, Storage storage) throws SeanBotException, IOException {
        if (part.length < 2) {
            throw new SeanBotException("The task number of unmark cannot be empty");
        }
        int unmarkIndex = Integer.parseInt(part[1]) - 1;
        if (unmarkIndex < 0 || unmarkIndex >= tasks.size()) {
            throw new SeanBotException("The task number must be valid.");
        }
        tasks.getTask(unmarkIndex).markAsUndone();
        storage.save(tasks.getTasks());
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.getTask(unmarkIndex));
    }

    private void handleTodo(String[] part, TaskList tasks, Storage storage) throws SeanBotException, IOException {
        if (part.length < 2 || part[1].trim().isEmpty()) {
            throw new SeanBotException("The description of a todo cannot be empty.");
        }
        Task todo = new Todo(part[1]);
        tasks.addTask(todo);
        storage.save(tasks.getTasks());
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + todo);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private void handleDeadline(String[] part, TaskList tasks, Storage storage) throws SeanBotException, IOException {
        String[] specifications = part[1].split(" /by ");
        if (specifications.length < 2) {
            throw new SeanBotException("The description or deadline cannot be empty.");
        }
        try {
            LocalDate by = LocalDate.parse(specifications[1].trim());
            Task deadline = new Deadline(specifications[0], by.toString());
            tasks.addTask(deadline);
            storage.save(tasks.getTasks());
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + deadline);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            throw new SeanBotException("Invalid date format. Please use the format yyyy-MM-dd.");
        }
    }

    private void handleEvent(String[] part, TaskList tasks, Storage storage) throws SeanBotException, IOException {
        String[] firstPart = part[1].split(" /from ", 2);
        if (firstPart.length < 2) {
            throw new SeanBotException("The description of an event cannot be empty.");
        }
        String[] secondPart = firstPart[1].split(" /to ", 2);
        if (secondPart.length < 2) {
            throw new SeanBotException("The end time of an event cannot be empty.");
        }

        String description = firstPart[0].trim();
        String from = secondPart[0].trim();
        String to = secondPart[1].trim();

        try {
            LocalDateTime startTime = LocalDateTime.parse(from);
            LocalDateTime endTime = LocalDateTime.parse(to);
            Task event = new Event(description, startTime.toString(), endTime.toString());
            tasks.addTask(event);
            storage.save(tasks.getTasks());
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + event);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            throw new SeanBotException("Invalid date and time format. Please use the format yyyy-MM-ddTHH:mm.");
        }
    }

    private void handleDelete(String[] part, TaskList tasks, Storage storage) throws SeanBotException, IOException {
        int deleteIndex = Integer.parseInt(part[1]) - 1;
        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new SeanBotException("The task number to delete must be valid.");
        }
        Task removedTask = tasks.getTask(deleteIndex);
        tasks.deleteTask(deleteIndex);
        storage.save(tasks.getTasks());
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private void handleFind(String[] part, TaskList tasks, Ui ui) throws SeanBotException {
        if (part.length < 2 || part[1].trim().isEmpty()) {
            throw new SeanBotException("The keyword for find cannot be empty.");
        }
        List<Task> foundTasks = tasks.findTasks(part[1]);
        ui.showFoundTasks(foundTasks);
    }
}
