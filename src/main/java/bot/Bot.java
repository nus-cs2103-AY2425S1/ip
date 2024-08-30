package bot;

import bot.exceptions.*;
import bot.storage.Storage;
import bot.tasks.*;
import bot.utils.Formatter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bot {
    private List<Task> tasks;
    private final Storage storage;

    public Bot() {
        storage = new Storage();
        try {
            tasks = storage.loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load task from disk" + e.getMessage());
            System.exit(0);
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

    public static void main(String[] args) {
        // Initialization
        Bot bot = new Bot();

        printBotMessage("Hello! I'm ChadGPT. What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            bot.handleInput(sc.nextLine());
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
                printBotMessage(e.getMessage());
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
                    printBotMessage("Bye. Hope to see you again soon!");
                    System.exit(0);
                default:
                    // This should never happen
                    printBotMessage("Command not found");
                }
            } catch (BotException e) {
                printBotMessage(e.getMessage());
            }
        } else {
            // This should never happen
            printBotMessage("Command not found");
        }
    }

    private void handleList() {
        printBotMessage("Here are the tasks in your list:\n" + Formatter.formatList(tasks));
    }

    private void handleAddTask(String cmd, String args) throws InvalidTaskDescriptionException, DateTimeParseException {
        if (cmd.equals(Command.TODO.name)) {
            if (args.isEmpty()) {
                throw new EmptyTodoException();
            }
            tasks.add(new Todo(args));
        } else if (cmd.equals(Command.DEADLINE.name)) {
            Pattern regex = Pattern.compile("(.*)\\s/by\\s(.*)");
            Matcher matcher = regex.matcher(args);
            if (matcher.matches()) {
                String task = matcher.group(1);
                String deadline = matcher.group(2);
                tasks.add(new Deadline(task, LocalDate.parse(deadline)));
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
                tasks.add(new Event(task, LocalDate.parse(from), LocalDate.parse(to)));
            } else {
                throw new InvalidTaskDescriptionException(args);
            }
        }

        // Save tasks to disk
        try {
            storage.saveTaskList(tasks);
        } catch (IOException e) {
            // Revert add task
            tasks.remove(tasks.size() - 1);
            System.out.println("Task failed to be added: " + e.getMessage());
            return;
        }

        Task newTask = tasks.get(tasks.size()-1);
        String response = String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                newTask.toString(),
                tasks.size()
        );
        printBotMessage(response);
    }

    private void handleDeleteTask(String args) throws InvalidTaskIdException {
        int index = getTaskIndex(args);
        if (index < 0 || index > tasks.size() - 1) {
            throw new InvalidTaskIdException(index + 1);
        }
        Task taskToDelete = tasks.get(index);
        tasks.remove(taskToDelete);

        // Save tasks to disk
        try {
            storage.saveTaskList(tasks);
        } catch (IOException e) {
            // Revert delete task
            tasks.add(taskToDelete);
            System.out.println("Task failed to be deleted: " + e.getMessage());
            return;
        }

        String response = String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.",
                taskToDelete.toString(),
                tasks.size()
        );
        printBotMessage(response);
    }

    private void handleMarkTask(String args) throws InvalidTaskIdException {
        int index = getTaskIndex(args);
        if (index < 0 || index > tasks.size() - 1) {
            throw new InvalidTaskIdException(index + 1);
        }
        boolean isPrevDone = tasks.get(index).isDone();
        tasks.get(index).markAsDone();

        // Save tasks to disk
        try {
            storage.saveTaskList(tasks);
        } catch (IOException e) {
            // Revert mark task
            if (isPrevDone) {
                tasks.get(index).markAsDone();
            } else {
                tasks.get(index).markAsIncomplete();
            }
            System.out.println("Task failed to be mark: " + e.getMessage());
            return;
        }

        printBotMessage("Nice! I've marked this task as done:\n" + tasks.get(index));
    }

    private void handleUnmarkTask(String args) throws InvalidTaskIdException {
        int index = getTaskIndex(args);
        if (index < 0 || index > tasks.size() - 1) {
            throw new InvalidTaskIdException(index + 1);
        }
        boolean isPrevDone = tasks.get(index).isDone();
        tasks.get(index).markAsIncomplete();

        // Save tasks to disk
        try {
            storage.saveTaskList(tasks);
        } catch (IOException e) {
            // Revert unmark task
            if (isPrevDone) {
                tasks.get(index).markAsDone();
            } else {
                tasks.get(index).markAsIncomplete();
            }
            System.out.println("Task failed to be unmarked: " + e.getMessage());
            return;
        }

        printBotMessage("OK, I've marked this task as not done yet:\n" + tasks.get(index));
    }

    private int getTaskIndex(String input) {
        return Integer.parseInt(input.split(" ")[0]) - 1;
    }

    /**
     * Prints a formatted bot message
     *
     * @param msg the string message to be printed
     */
    private static void printBotMessage(String msg) {
        System.out.println(Formatter.formatBotMessage(msg));
    }
}
