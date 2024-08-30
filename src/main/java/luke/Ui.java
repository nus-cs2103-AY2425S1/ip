package luke;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import luke.command.Command;
import luke.env.Constants;
import luke.task.NoDescriptionException;
import luke.task.UnknownCommandException;
import luke.task.Task;
import luke.task.TaskList;

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
        acceptCommand();
    }

    /**
     * Defines a Scanner object to accept string input (which will be parsed into commands later).
     */
    public void acceptCommand() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning && scanner.hasNextLine()) {
            String input = scanner.nextLine();
            Command command = parser.parseInputData(input);
            handleCommand(command, false);
        }
        scanner.close();
    }

    /**
     * Provides the user with the choice to create a save file if a save file cannot be found.
     * Exits if an IOException is thrown or if the user chooses not to make a save file.
     */
    private void handleMissingFile() {
        System.out.println("i couldn't find a saved task list. you will need to create one to continue using me.\n"
                + "would you like to create one? (y/n)");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
            case "y", "yes" -> {
                try {
                    storage.createSaveFile();
                    System.out.println(Constants.DIVIDER);
                    System.out.println("save file created! ok, i'm all ears now. tell me what you need.");
                    System.out.println(Constants.DIVIDER);
                } catch (IOException e) {
                    System.out.println(Constants.DIVIDER);
                    System.out.println("oof, i couldn't create the file. i'll exit first - try restarting me!");
                    System.out.println(Constants.DIVIDER);
                    System.exit(0);
                }
            }
            case "n", "no" -> {
                System.out.println(Constants.DIVIDER);
                System.out.println("alright then. cya ;)");
                System.out.println(Constants.DIVIDER);
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
     */
    public void handleCommand(Command command, boolean isLoadingFromDisk) {
//        System.out.println("command: " + command.getCommand());
        switch (command.getCommand()) {
        case "bye" -> {
            System.out.println("""
                    ____________________________________________________________
                    yeah bye bye to you too human being
                    ____________________________________________________________
                    """);
            isRunning = false;
        }
        case "list" -> showList();
        case "mark", "unmark" -> handleTaskMarking(command);
        case "delete" -> handleDelete(command);
        default -> handleAddTask(command, isLoadingFromDisk);
        }
    }

    /**
     * Marks or unmark tasks
     * @param command the mark/unmark command word and its associated tasks
     */
    public void handleTaskMarking(Command command) {
        int taskToMark = Integer.parseInt(command.getArgs());
        try {
            Task task = taskList.getTask(taskToMark - 1);
            task.changeMark();
            System.out.println(Constants.DIVIDER + (command.getCommand().equals("mark")
                    ? "ok i've marked"
                    : "ok i've unmarked")
                    + " this task:");
            System.out.println(Constants.INDENT + task.taskDescription());
            System.out.println(Constants.DIVIDER);
            storage.saveData(taskList);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            TaskList.taskNotFound(taskToMark);
        }
    }

    /**
     * Deletes a task from the task list
     * @param command the "delete" command word and its arg -- the index of the relevant task in the task list
     */
    public void handleDelete(Command command) {
        int taskToDelete = Integer.parseInt(command.getArgs());
        try {
            taskList.removeTask(taskToDelete - 1);
            storage.saveData(taskList);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            TaskList.taskNotFound(taskToDelete);
        }
    }

    /**
     * Adds a task to the task list and catches possible errors stemming from this addition.
     * @param command command word and its associated args
     * @param isLoadingFromDisk true if the command was loaded from save data
     */
    public void handleAddTask(Command command, boolean isLoadingFromDisk) {
//        System.out.println("getMark: " + command.getMark());
        try {
            taskList.addToList(command.getCommand(),
                    command.getArgs(),
                    command.getMark().equalsIgnoreCase("x"),
                    isLoadingFromDisk);
        } catch (NoDescriptionException e) {
            System.out.println(Constants.DIVIDER);
            System.out.println("hmm...a description seems to be missing. try again?");
            System.out.println(Constants.DIVIDER);
        } catch (UnknownCommandException e) {
            System.out.println(Constants.DIVIDER);
            System.out.println("hmm... i don't quite recognise that command. try again?");
            System.out.println(Constants.DIVIDER);
        }
    }

    /**
     * Prints out the current state of the task list.
     */
    public void showList() {
        System.out.println(Constants.DIVIDER + "here's everything that's in your list:");
        taskList.printList();
        System.out.println(Constants.DIVIDER);
    }
}
