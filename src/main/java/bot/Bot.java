package bot;

import bot.enums.Command;
import bot.exceptions.BotException;
import bot.exceptions.InvalidCommandException;
import bot.exceptions.EmptyTodoException;
import bot.exceptions.InvalidTaskIdException;
import bot.exceptions.InvalidTaskDescriptionException;
import bot.storage.Storage;
import bot.tasks.TaskList;
import bot.tasks.Todo;
import bot.tasks.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Bot {
    private final TaskList tasks;
    private final Parser parser;
    private final Storage storage;

    public Bot() {
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage();
        try {
            storage.loadTasks(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load task from disk" + e.getMessage());
        }
    }

    public String handleInput(String input) {
        Parser.ParsedInput parsedInput;
        try {
            parsedInput = parser.parseInput(input);
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }

        Command cmd = parsedInput.getCmd();
        String args = parsedInput.getArgs();

        try {
            return switch (cmd) {
                case LIST -> handleList();
                case TODO, DEADLINE, EVENT -> handleAddTask(cmd, args);
                case MARK -> handleMarkTask(args);
                case UNMARK -> handleUnmarkTask(args);
                case DELETE -> handleDeleteTask(args);
                case FIND -> handleFindTask(args);
            };
        } catch (BotException e) {
            return e.getMessage();
        }
    }

    private String handleList() {
        return "Here are the tasks in your list:\n" + tasks.toString();
    }

    private String handleFindTask(String args) {
        return "Here are the tasks in your list:\n" + tasks.search(args);
    }

    private String handleAddTask(Command cmd, String args) throws InvalidTaskDescriptionException, DateTimeParseException {
        Task taskToAdd;
        if (cmd.equals(Command.TODO)) {
            if (args.isEmpty()) {
                throw new EmptyTodoException();
            }
            taskToAdd = new Todo(args);
        } else if (cmd.equals(Command.DEADLINE)) {
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
            return "Task failed to be added: " + e.getMessage();
        }

        return String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                tasks.get(newTaskIndex).toString(),
                tasks.size()
        );
    }

    private String handleDeleteTask(String args) throws InvalidTaskIdException {
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
            return "Task failed to be deleted: " + e.getMessage();
        }

        return String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.",
                deletedTask.toString(),
                tasks.size()
        );
    }

    private String handleMarkTask(String args) throws InvalidTaskIdException {
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
            return "Task failed to be mark: " + e.getMessage();
        }

        return "Nice! I've marked this task as done:\n" + markedTask;
    }

    private String handleUnmarkTask(String args) throws InvalidTaskIdException {
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
            return "Task failed to be unmarked: " + e.getMessage();
        }

        return "OK, I've marked this task as not done yet:\n" + unmarkedTask;
    }

    private int getTaskIndex(String input) {
        return Integer.parseInt(input.split(" ")[0]) - 1;
    }
}
