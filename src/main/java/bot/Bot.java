package bot;

import bot.exceptions.*;
import bot.storage.Storage;
import bot.tasks.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bot {
    private final TaskList tasks;
    private final Ui ui;
    private Parser parser;
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

    private enum Command {
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        BYE("bye"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

        public final String name;

        Command(String name) {
            this.name = name;
        }

        public static Command fromString(String input) throws UnknownCommandException {
            for (Command cmd : Command.values()) {
                if (cmd.name.equals(input)) {
                    return cmd;
                }
            }
            throw new UnknownCommandException(input);
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
        Pattern regex = Pattern.compile("(\\w+)\\s*(.*)");
        Matcher matcher = regex.matcher(input);
        if (matcher.matches()) {
            String cmd = matcher.group(1);
            String args = matcher.group(2);

            Command cmdEnum;
            try {
                cmdEnum = Command.fromString(cmd);
            } catch (UnknownCommandException e) {
                ui.printMessage(e.getMessage());
                return;
            }

            try {
                switch (cmdEnum) {
                case LIST:
                    handleList();
                    break;
                case TODO, DEADLINE, EVENT:
                    handleAddTask(cmd, args);
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
        } else {
            // This should never happen
            ui.printMessage("Command not found");
        }
    }

    private void handleList() {
        ui.printMessage("Here are the tasks in your list:\n" + tasks.toString());
    }

    private void handleAddTask(String cmd, String args) throws InvalidTaskDescriptionException, DateTimeParseException {
        int newTaskIndex;
        if (cmd.equals(Command.TODO.name)) {
            if (args.isEmpty()) {
                throw new EmptyTodoException();
            }
            newTaskIndex = tasks.add(new Todo(args));
        } else if (cmd.equals(Command.DEADLINE.name)) {
            Pattern regex = Pattern.compile("(.*)\\s/by\\s(.*)");
            Matcher matcher = regex.matcher(args);
            if (matcher.matches()) {
                String task = matcher.group(1);
                String deadline = matcher.group(2);
                newTaskIndex = tasks.add(new Deadline(task, LocalDate.parse(deadline)));
            } else {
                throw new InvalidTaskDescriptionException(args);
            }
        } else {
            // Last command is guaranteed to be "event" by the switch statement
            Pattern regex = Pattern.compile("(.*)\\s/from\\s(.*)\\s/to\\s(.*)");
            Matcher matcher = regex.matcher(args);
            if (matcher.matches()) {
                String task = matcher.group(1);
                String from = matcher.group(2);
                String to = matcher.group(3);
                newTaskIndex = tasks.add(new Event(task, LocalDate.parse(from), LocalDate.parse(to)));
            } else {
                throw new InvalidTaskDescriptionException(args);
            }
        }

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
