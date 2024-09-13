package main;

import command.AddTaskCommand;
import command.Command;
import command.DeleteCommand;
import command.ListCommand;
import command.MarkCommand;
import command.MiscCommand;
import command.SearchCommand;
import command.UnMarkCommand;
import exception.DukeException;


/**
 * The parser is used to breakdown user input and does different actions depending
 * on the input.
 */
public class Parser {

    /**
     * Takes in response from user and runs different functions based on the input
     * If it is a terminating command, returns false to break the loop
     * @param response user input
     * @return whether the program continues to run
     */
    public static Command parse(String response) throws DukeException {
        String[] splitResponse = response.split(" ", 2);
        switch (splitResponse[0]) {
        case "todo", "event", "deadline":
            return handleAdd(splitResponse);
        case "list":
            return handleList();
        case "mark":
            return handleMark(splitResponse[1]);
        case "unmark":
            return handleUnmark(splitResponse[1]);
        case "delete":
            return handleDelete(splitResponse[1]);
        case "find":
            return handleSearch(splitResponse[1]);
        case "bye":
            return handleBye();
        default: {
            try {
                throw new DukeException("I dont understand what you are trying to say :(");
            } catch (DukeException e) {
                System.out.println("________________________________");
                System.out.println(e.getMessage() + "\n________________________________");
            }
        }
        }
        return new MiscCommand("I dont understand what you are trying to say :(");
    }
    /**
     * Handles addition of task into tasklist depending on the input
     * @param splitResponse details of the task
     */
    public static Command handleAdd(String[] splitResponse) {
        try {
            switch (splitResponse[0]) {
            case "todo":
                return new AddTaskCommand(splitResponse[1], "todo");
            case "event":
                return new AddTaskCommand(splitResponse[1], "event");
            case "deadline":
                return new AddTaskCommand(splitResponse[1], "deadline");
            default:
                throw new DukeException("I dont understand what you are trying to say :(");
            }
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "\n________________________________");
        }
        return new MiscCommand("I dont understand what you are trying to say :(");
    }

    public static Command handleList() {
        return new ListCommand();
    }

    /**
     * Handles the searching of word
     * @param word String to be found
     */
    public static Command handleSearch(String word) {
        return new SearchCommand(word);
    }


    /**
     * Handles the logic of marking a task as done
     * @param description index of task in list
     */
    public static Command handleMark(String description) {
        int index = Integer.parseInt(description) - 1;
        return new MarkCommand(index);
    }

    /**
     * Handles the logic of unmarking a task as done
     * @param description index of task in list
     */
    public static Command handleUnmark(String description) {
        int index = Integer.parseInt(description) - 1;
        return new UnMarkCommand(index);
    }
    /**
     * Handles the logic of deleting a task from task list
     * @param description index of task in list
     */
    public static Command handleDelete(String description) {
        int index = Integer.parseInt(description) - 1;
        return new DeleteCommand(index);
    }

    /**
     * Handles the logic when program terminating input is given
     * @return whether the program should continue to run
     */
    public static Command handleBye() {
        return new MiscCommand("________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "________________________________");
    }
}

