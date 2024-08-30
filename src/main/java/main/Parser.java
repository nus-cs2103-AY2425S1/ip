package main;

import exception.CommandFoundButInvalidException;
import exception.CommandNotFoundException;
import exception.EmptyStringException;
import task.Task;

import java.util.List;

public class Parser {
    /* goal of the parser class is to take in a string input and determine the
    right command. After which, invokes the respective classes*/
    private String command;
    private String remainder;
    private TaskList allTasks;
    private String description;
    private Storage storage;
    private boolean isOver = false;
    private Ui ui;
    /**
     * Constructs a {@code Parser} instance which processes a given input String and performs
     * an action depending on the first word (command)
     *
     * @param input the user input string containing the command and remaining arguments
     * @param allTasks the {@code TaskList} instance where Tasks are stored and managed
     * @param s the {@code Storage} instance used for saving that state of tasks
     * @param ui the {@code Ui} instance for user interface
     * @throws EmptyStringException if the input String is empty
     * @throws CommandNotFoundException if the command is not recognized
     * @throws CommandFoundButInvalidException if the command is recognized but the remaining string
     *         are of invalid syntax
     */
    public Parser(String input, TaskList allTasks, Storage s, Ui ui) throws EmptyStringException, CommandNotFoundException, CommandFoundButInvalidException {
        this.allTasks = allTasks;
        this.ui = ui;
        if (input.isEmpty()) {
            throw new EmptyStringException();
        }
        if (input.split(" ", 2).length == 1) {
            this.command = input.split(" ", 2)[0];
            this.remainder = "";
        }
        if (input.split(" ", 2).length == 2) {
            this.command = input.split(" ", 2)[0];
            this.remainder = input.split(" ", 2)[1];
        }
        Commands cmd = Commands.fromString(command);
        switch(cmd) {
            case TODO:
                allTasks.addTodo(remainder);
                s.put(allTasks.getAllTasks());
                System.out.println(ui.addedMessage(allTasks.getLastAdded(), allTasks.getSize()));
                break;
            case DEADLINE:
                allTasks.addDeadline(remainder);
                s.put(allTasks.getAllTasks());
                System.out.println(ui.addedMessage(allTasks.getLastAdded(), allTasks.getSize()));
                break;
            case EVENT:
                allTasks.addEvent(remainder);
                s.put(allTasks.getAllTasks());
                System.out.println(ui.addedMessage(allTasks.getLastAdded(), allTasks.getSize()));
                break;
            case DELETE:
                allTasks.delete(remainder);
                s.put(this.allTasks.getAllTasks());
                System.out.println(ui.deleteMessage(this.allTasks.getLastDeleted(), this.allTasks.getSize()));
                break;
            case LIST:
                // throw to the main.ListAll class
                ListAll list = new ListAll(remainder, allTasks.getAllTasks());
                System.out.println(list.message());
                break;
            case MARK:
                allTasks.mark(remainder, allTasks.getAllTasks());
                s.put(allTasks.getAllTasks());
                System.out.println(ui.markedMessage(allTasks.getLastMarked()));
                break;
            case UNMARK:
                allTasks.unmark(remainder, allTasks.getAllTasks());
                s.put(allTasks.getAllTasks());
                System.out.println(ui.unmarkedMessage(allTasks.getLastUnmarked()));
                break;
            case FIND:
                // returns an ArrayList of task
                List<Task> found = allTasks.find(remainder, allTasks.getAllTasks());
                // print out the task using Ui
                ui.showList(found);
                break;
            case BYE:
                System.out.println(ui.bye());
                this.isOver = true;
                break;
        }
    }

    /**
     * Returns a boolean to determine if hyperion should carry on
     *
     * @return a boolean value depending on the command of the user
     */
    public boolean isOver() {
        return this.isOver;
    }
}
