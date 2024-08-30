package bot;

import bot.enums.Command;
import bot.exceptions.*;
import bot.storage.Storage;
import bot.tasks.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Bot {
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;
    private final Storage storage;

    public Bot() {
        tasks = new TaskList();
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        try {
            storage.loadTasks(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load task from disk" + e.getMessage());
        }
    }

    public void run() {
        ui.start();

        // TODO: Abstract scanner into Ui class as well
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            handleInput(sc.nextLine());
        }
    }

    private void handleInput(String input) {
        Parser.ParsedInput parsedInput;
        try {
            parsedInput = parser.parseInput(input);
        } catch (InvalidCommandException e) {
            ui.printMessage(e.getMessage());
            return;
        }

        Command cmd = parsedInput.getCmd();
        String args = parsedInput.getArgs();

        try {
            switch (cmd) {
            case LIST:
                handleList();
                break;
            case TODO, DEADLINE, EVENT:
                handleAddTask(cmd.name(), args);
                break;
            case MARK:
                handleMarkTask(args);
                break;
            case UNMARK:
                handleUnmarkTask(args);
                break;
            case DELETE:
                handleDeleteTask(args);
                break;
            case BYE:
                ui.exit();
                System.exit(0);
            default:
                // This should never happen
                ui.printMessage("Command not found");
            }
        } catch (BotException e) {
            ui.printMessage(e.getMessage());
        }
    }

    private void handleList() {
        ui.printMessage("Here are the tasks in your list:\n" + tasks.toString());
    }

    private void handleAddTask(String cmd, String args) throws InvalidTaskDescriptionException, DateTimeParseException {
        Task taskToAdd;
        if (cmd.equals(Command.TODO.name)) {
            if (args.isEmpty()) {
                throw new EmptyTodoException();
            }
            taskToAdd = new Todo(args);
        } else if (cmd.equals(Command.DEADLINE.name)) {
            taskToAdd = parser.parseDeadlineTask(args);
        } else {
            // Last command is guaranteed to be "event" by the switch statement
            taskToAdd = parser.parseEventTask(args);
        }

        int newTaskIndex = tasks.add(taskToAdd);

        // Save tasks to disk
        try {
            storage.saveTaskList(tasks);
        } catch (IOException e) {
            // Revert add task
            tasks.remove(newTaskIndex);
            System.out.println("Task failed to be added: " + e.getMessage());
            return;
        }

        String response = String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                tasks.get(newTaskIndex).toString(),
                tasks.size()
        );
        ui.printMessage(response);
    }

    private void handleDeleteTask(String args) throws InvalidTaskIdException {
        int index = getTaskIndex(args);
        if (index < 0 || index > tasks.size() - 1) {
            throw new InvalidTaskIdException(index + 1);
        }
        Task deletedTask = tasks.remove(index);

        // Save tasks to disk
        try {
            storage.saveTaskList(tasks);
        } catch (IOException e) {
            // Revert delete task
            tasks.add(deletedTask);
            System.out.println("Task failed to be deleted: " + e.getMessage());
            return;
        }

        String response = String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.",
                deletedTask.toString(),
                tasks.size()
        );
        ui.printMessage(response);
    }

    private void handleMarkTask(String args) throws InvalidTaskIdException {
        int index = getTaskIndex(args);
        if (index < 0 || index > tasks.size() - 1) {
            throw new InvalidTaskIdException(index + 1);
        }
        boolean isPrevMarked = tasks.isMarked(index);
        Task markedTask = tasks.mark(index);

        // Save tasks to disk
        try {
            storage.saveTaskList(tasks);
        } catch (IOException e) {
            // Revert mark task
            if (isPrevMarked) {
                tasks.mark(index);
            } else {
                tasks.unmark(index);
            }
            System.out.println("Task failed to be mark: " + e.getMessage());
            return;
        }

        ui.printMessage("Nice! I've marked this task as done:\n" + markedTask);
    }

    private void handleUnmarkTask(String args) throws InvalidTaskIdException {
        int index = getTaskIndex(args);
        if (index < 0 || index > tasks.size() - 1) {
            throw new InvalidTaskIdException(index + 1);
        }
        boolean isPrevMarked = tasks.isMarked(index);
        Task unmarkedTask = tasks.unmark(index);

        // Save tasks to disk
        try {
            storage.saveTaskList(tasks);
        } catch (IOException e) {
            // Revert unmark task
            if (isPrevMarked) {
                tasks.mark(index);
            } else {
                tasks.unmark(index);
            }
            System.out.println("Task failed to be unmarked: " + e.getMessage());
            return;
        }

        ui.printMessage("OK, I've marked this task as not done yet:\n" + unmarkedTask);
    }

    private int getTaskIndex(String input) {
        return Integer.parseInt(input.split(" ")[0]) - 1;
    }

    public static void main(String[] args) {
        new Bot().run();
    }
}
