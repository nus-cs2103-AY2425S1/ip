package luke;

import java.util.List;

import javafx.application.Platform;
import luke.command.Command;
import luke.env.Constants;
import luke.task.NoDescriptionException;
import luke.task.Task;
import luke.task.TaskList;
import luke.task.UnknownCommandException;

public class Ui {
    private static TaskList taskList = new TaskList();
    private static Boolean isRunning = true;

    //    public Ui(Storage storage, Parser parser, TaskList taskList) {
    //        this.storage = storage;
    //        this.parser = parser;
    //        this.taskList = taskList;
    //        this.isRunning = true;
    //    }

    // todo: define handleUserInput() such that it feeds input to the user dialog box and output to the luke dialog box
    /**
     * Defines a Scanner object to accept string input (which will be parsed into commands later).
     */
    public static String handleUserInput(String input) {
        Command command = Parser.parseInputData(input);
        return handleCommand(command, false);
    }

    /**
     * Determines which procs to invoke depending on the command word.
     * @param command command word and its associated args
     * @param isLoadingFromDisk true if the command was loaded from save data
     * @return command output
     */
    public static String handleCommand(Command command, boolean isLoadingFromDisk) {
        switch (command.getCommand()) {
        case "bye" -> {
            isRunning = false;
            Platform.exit();
            return "yeah bye bye to you too human being <3";
        }
        case "list" -> {
            return showList();
        }
        case "mark", "unmark" -> {
            return handleTaskMarking(command);
        }
        case "delete" -> {
            return handleDelete(command);
        }
        case "find" -> {
            return handleFind(command);
        }
        default -> {
            return handleAddTask(command, isLoadingFromDisk);
        }
        }
    }

    public static String showList() {
        return "here's everything that's in your list:\n" + taskList.printList();
    }

    /**
     * Marks or unmark tasks
     * @param command the mark/unmark command word and its associated tasks
     */
    public static String handleTaskMarking(Command command) {
        int taskToMark = Integer.parseInt(command.getArgs());
        try {
            Task task = taskList.getTask(taskToMark - 1);
            task.changeMark();
            Storage.saveData(taskList);
            return (command.getCommand().equals("mark")
                    ? "ok i've marked"
                    : "ok i've unmarked")
                    + " this task:\n"
                    + Constants.INDENT + task.taskDescription();

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return TaskList.taskNotFound(taskToMark);
        }
    }

    /**
     * Deletes a task from the task list
     * @param command the "delete" command word and its arg -- the index of the relevant task in the task list
     */
    public static String handleDelete(Command command) {
        int taskToDelete = Integer.parseInt(command.getArgs());
        try {
            Task deletedTask = taskList.removeTask(taskToDelete - 1);
            Storage.saveData(taskList);
            return "alright i've purged this task for you:\n"
                    + Constants.INDENT + deletedTask.taskDescription() + "\n"
                    + taskList.listSizeUpdateMessage();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return TaskList.taskNotFound(taskToDelete);
        }
    }

    public static String handleFind(Command command) {
        String filteredList = "";
        List<Task> matchingTasks = taskList.findTasks(command.getArgs());
        for (int i = 0; i < matchingTasks.size(); i++) {
            filteredList = filteredList + (i + 1) + ". " + matchingTasks.get(i).taskDescription() + "\n";
        }
        return "here are the matching tasks in your list:\n" + filteredList;
    }

    /**
     * Adds a task to the task list and catches possible errors stemming from this addition.
     * @param command command word and its associated args
     * @param isLoadingFromDisk true if the command was loaded from save data
     */
    public static String handleAddTask(Command command, boolean isLoadingFromDisk) {
        try {
            return taskList.addToList(command.getCommand(),
                    command.getArgs(),
                    command.getMark().equalsIgnoreCase("x"),
                    isLoadingFromDisk);
        } catch (NoDescriptionException e) {
            return "hmm...a description seems to be missing. try again?";
        } catch (UnknownCommandException e) {
            return "hmm... i don't quite recognise that command. try again?";
        }
    }
}
