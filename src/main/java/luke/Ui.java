package luke;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import luke.command.Command;
import luke.env.Constants;
import luke.task.NoDescriptionException;
import luke.task.Task;
import luke.task.TaskList;
import luke.task.UnknownCommandException;

public class Ui {
    private Storage storage;
    private Parser parser;
    private TaskList taskList;
    private Boolean isRunning;

    public Ui(Storage storage, Parser parser, TaskList taskList) {
        this.storage = storage;
        this.parser = parser;
        this.taskList = taskList;
        this.isRunning = true;
    }

    // todo: integrate the JavaFX library into this file as a whole
    /**
     * Initialises the UI.
     */
    public void start() {
        try {
            List<String> lines = storage.loadData();
            for (String line : lines) {
                Command command = parser.parseSavedData(line);
                handleCommand(command, true);
            }
        } catch (NoSaveDataFoundException e) {
            handleMissingFile();
        } catch (IOException e) {
            System.out.println("hmmm... i ran into an issue while setting up. try launching me again.");
            System.exit(0);
        }
        handleUserInput();
    }

    // todo: define handleUserInput() such that it feeds input to the user dialog box and output to the luke dialog box
    /**
     * Defines a Scanner object to accept string input (which will be parsed into commands later).
     */
    public void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning && scanner.hasNextLine()) {
            String input = scanner.nextLine();
            Command command = parser.parseInputData(input);
            handleCommand(command, false);
        }
        scanner.close();
    }

    // todo: figure out how the UI should handle the special case of missing files
    /**
     * Provides the user with the choice to create a save file if a save file cannot be found.
     * Exits if an IOException is thrown or if the user chooses not to make a save file.
     */
    public void handleMissingFile() {
        System.out.println("i couldn't find a saved task list. you will need to create one to continue using me.\n"
                + "would you like to create one? (y/n)");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
            case "y", "yes" -> {
                try {
                    storage.createSaveFile();
                    System.out.println("save file created! ok, i'm all ears now. tell me what you need.");
                } catch (IOException e) {
                    System.out.println("oof, i couldn't create the file. i'll exit first - try restarting me!");
                    System.exit(0);
                }
            }
            case "n", "no" -> {
                System.out.println("alright then. cya ;)");
                System.exit(0);
            }
            default -> System.out.println("didn't quite understand what you said there. try again?");
            }
        }
        System.exit(0);
    }

    /**
     * Determines which procs to invoke depending on the command word.
     * @param command command word and its associated args
     * @param isLoadingFromDisk true if the command was loaded from save data
     * @return command output
     */
    public String handleCommand(Command command, boolean isLoadingFromDisk) {
        switch (command.getCommand()) {
        case "bye" -> {
            isRunning = false;
            return """
                    ____________________________________________________________
                    yeah bye bye to you too human being
                    ____________________________________________________________
                    """;
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

    public String showList() {
        return "here's everything that's in your list:\n" + taskList.printList();
    }

    /**
     * Marks or unmark tasks
     * @param command the mark/unmark command word and its associated tasks
     */
    public String handleTaskMarking(Command command) {
        int taskToMark = Integer.parseInt(command.getArgs());
        try {
            Task task = taskList.getTask(taskToMark - 1);
            task.changeMark();
            storage.saveData(taskList);
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
    public String handleDelete(Command command) {
        int taskToDelete = Integer.parseInt(command.getArgs());
        try {
            Task deletedTask = taskList.removeTask(taskToDelete - 1);
            storage.saveData(taskList);
            return "alright i've purged this task for you:\n"
                    + Constants.INDENT + deletedTask.taskDescription() + "\n"
                    + taskList.listSizeUpdateMessage();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return TaskList.taskNotFound(taskToDelete);
        }
    }

    public String handleFind(Command command) {
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
    public String handleAddTask(Command command, boolean isLoadingFromDisk) {
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
