package com.meow;
import java.util.Arrays;
import java.util.Scanner;

import com.meow.com.tasks.Deadline;
import com.meow.com.tasks.Event;
import com.meow.com.tasks.Task;
import com.meow.com.tasks.Todo;

/**
 * Class handles and parses all user inputs
 */
public class Parser {
    private Scanner scanner;
    private TaskList taskList;

    /**
     * Enumerations for valid commands
     */
    public enum Commands {
        TODO, DEADLINE, EVENT, LIST, DELETE, MARK, UNMARK, BYE,
        FIND, UPDATE
    }

    /**
     * Constructor for Parser class
     * @param TaskList
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Validates the command input
     * @param inputType which is a string that takes in user input
     * @return String
     */

    public String validateCommand(String inputType) throws Meowception {
        try {         
            String[] parts = inputType.split(" ");
            Commands userCommand = Commands.valueOf(parts[0].toUpperCase());
            return outputTask(userCommand, inputType);
        } catch (StringIndexOutOfBoundsException e) {
            throw new Meowception("404");
        } catch (IllegalArgumentException e) {
            throw new Meowception("001");
        }
    }

    /*
     * Outputs the task based on the command
     * @param Commands cmd representing the enum
     * @param String inputType representing the input written by the user
     * @return String msg representing the message to be displayed
     */

    private String outputTask(Commands cmd, String inputType) throws Meowception {
        switch (cmd) {
        case MARK:
            try {
                assert inputType.substring(5).trim().isEmpty() : "Should be a task number here";
                if (!inputType.substring(5).trim().isEmpty()) {
                    return parseMarkTask(inputType.substring(5));

                } else {
                    throw new Meowception("100");
                }
            } catch (Meowception err) {
                throw err;
            } catch (StringIndexOutOfBoundsException e) {
                Meowception err = new Meowception("100");
                throw err;
            }

        case UNMARK:
            try {
                assert inputType.substring(7).trim().isEmpty() : "Should be a task number here";
                if (!inputType.substring(7).trim().isEmpty()) {
                    return parseUnmarkTask(inputType.substring(7));
                } else {
                    throw new Meowception("100");
                }
            } catch (Meowception err) {
                throw err;
            } catch (StringIndexOutOfBoundsException e) {
                Meowception err = new Meowception("100");
                throw err;
            }
        case LIST:
            return taskList.displayList();

        case TODO:
            try {
                Task todoTask = parseTodoTask(inputType.substring(5));
                return taskList.addTask(todoTask);
            } catch (Meowception e) {
                throw e;
            } catch (StringIndexOutOfBoundsException e) {
                Meowception err = new Meowception("100");
                throw err;
            }

        case DEADLINE:
            // Cleaning data so that it identifies deadline correctly and the task.
            try {
                Task deadlineTask = parseDeadlineTask(inputType.substring(9));
                return taskList.addTask(deadlineTask);
            } catch (Meowception err) {
                throw err;
            } catch (StringIndexOutOfBoundsException e) {
                Meowception err = new Meowception("100");
                throw err;
            }

        case EVENT:
            try {
                Task eventTask = parseEventTask(inputType.substring(6));
                return taskList.addTask(eventTask);
            } catch (Meowception err) {
                throw err;
            } catch (StringIndexOutOfBoundsException e) {
                Meowception err = new Meowception("100");
                throw err;
            }

        case DELETE:
            try {
                assert inputType.substring(6).trim().isEmpty() : "Should be a task number here";
                if (!inputType.substring(6).trim().isEmpty()) {
                    return parseDeleteTask(inputType.substring(6));
                } else {
                    throw new Meowception("100");
                }
            } catch (Meowception err) {
                throw err;
            } catch (StringIndexOutOfBoundsException e) {
                Meowception err = new Meowception("100");
                throw err;

            }
        case FIND:
            try {
                assert inputType.substring(5).trim().isEmpty() : "Should be a task number here";
                if (!inputType.substring(5).trim().isEmpty()) {
                    return parseFindTask(inputType);
                } else {
                    // purpose here is to throw error if its a blank string
                    throw new Meowception("100");
                }
            } catch (Meowception err) {
                throw err;
            } catch (StringIndexOutOfBoundsException e) {
                Meowception err = new Meowception("100");
                throw err;
            }
        case UPDATE:
            try {
                if (!inputType.substring(7).trim().isEmpty()) {
                    System.out.println("Input is, " + inputType.substring((7)));
                    return parseUpdateTask(inputType.substring(7));
                } else {
                    throw new Meowception("100");
                }
            } catch (Meowception err) {
                throw err;
            } catch (StringIndexOutOfBoundsException e) {
                Meowception err = new Meowception("100");
                throw err;
            }

        case BYE:
            break;
        default:
            throw new Meowception("001");
        }

        return inputType;
    }
            
    /**
     * Parses the input command and returns a task
     * @param String task input from the user
     * @return String which is the list
     */
    private String parseFindTask(String task) throws Meowception {
        String keyword = task.substring(5).trim();
        TaskList local = taskList.findTasks(keyword);
        return local.displayList();
    }

    /**
     * Parses the input command and returns a todo task
     * @param String command input from the user
     * @return Todo task
     */
    private Task parseTodoTask(String command) throws Meowception {
        if (command.trim().isEmpty()) {
            throw new Meowception("100");
        } else {
            return new Todo(command);
        }
    }

    /**
     * Parses the input command and returns a deadline task
     * @param String command input from the user
     * @return Deadline task
     */
    private Task parseDeadlineTask(String command) throws Meowception {
        if (command.contains("/by ")) {
            if (command.substring(command.indexOf("/by") + 3).trim().isEmpty()) {
                throw new Meowception("200");
            } else {
                String taskName = command.substring(0, command.indexOf("/by ") - 1);
                if (taskName.trim().isEmpty()) {
                    throw new Meowception("100");
                }
                String by = command.substring(command.indexOf("/by ") + 4);
                return new Deadline(taskName, by);
            }
        }
        throw new Meowception("200");
    }
    
    /**
     * Parses the input command and returns an event task
     * @param String command input from the user
     * @return Event task
     */
    private Task parseEventTask(String command) throws Meowception {
        if (!command.contains("/from ") || !command.contains("/to ")) {
            throw new Meowception("300");
        }

        if (command.substring(0, command.indexOf("/from")).trim().isEmpty()) {
            throw new Meowception("100");
        } else if (command.substring(command.indexOf("/from"), command.indexOf("/to ")).trim().isEmpty()) {
            throw new Meowception("300");
        } else if (command.substring(command.indexOf("/to")).trim().isEmpty()) {
            throw new Meowception("100");
        } else {
            String taskName = command.substring(0, command.indexOf("/from") - 1);
            String from = command.substring(command.indexOf("/from") + 6, command.indexOf("/to") - 1);
            String to = command.substring(command.indexOf("/to") + 4);
            return new Event(taskName, from, to);

        }
    }
    
    /**
     * Parses the input command and returns the task to be unmarked
     * @param task
     * @return String
     */
    private String parseUnmarkTask(String task) throws Meowception {
        int number = Integer.parseInt(task);
        assert number >= 1 : "number must be equal or greater to 1";
        if (number > taskList.getSize() || number < 1) {
            throw new Meowception("404");
        } else {
            return taskList.unmarkTask(number);
        }
    }

    /**
     * Parses the input command and returns the task to be marked
     * @param task
     * @return String
     */
    private String parseMarkTask(String task) throws Meowception {
        int number = Integer.parseInt(task);
        if (number > taskList.getSize() || number < 1) {
            throw new Meowception("404");
        } else {
            return taskList.markTask(number);
        }
    }

    /**
     * Parses the input command and returns the task to be deleted
     * @param task
     * @return String
     */
    private String parseDeleteTask(String task) throws Meowception {
        int number = Integer.parseInt(task);
        if (number > taskList.getSize() || number < 1) {
            throw new Meowception("404");
        } else {
            return taskList.deleteTask(number);
        }
    }

    /**
     * Parses the input to determine which and what to update
     * @param userInput
     * @return String
     */
    private String parseUpdateTask(String userInput) throws Meowception {
        // CASES here are to determine what kind of task to update
        String[] parts = userInput.split(" ");
        Commands task = Commands.valueOf(parts[0].toUpperCase());
        switch (task) {
        case TODO:
            return updateTodoTask(userInput.substring(5));
        case DEADLINE:
            return updateDeadlineTask(userInput.substring(9));
        case EVENT:
            return updateEventTask(userInput.substring(6));
        }
        throw new Meowception("001");
    }
    
    /**
     * Updates the todo task
     * @param userInput
     * @return String
     */
    private String updateTodoTask(String userInput) throws Meowception {
        try {
            String oldTaskname = userInput.substring(0, userInput.indexOf("/new "));
            String newTaskname = userInput.substring(userInput.indexOf("/new ") + 5);
            return taskList.updateTodoTask(oldTaskname, newTaskname);
        } catch (StringIndexOutOfBoundsException e) {
            throw new Meowception("100");
        }
    }

    /**
     * Updates the deadline task
     * @param userInput
     * @return String
     */
    private String updateDeadlineTask(String userInput) throws Meowception {
        try {
            String taskName = userInput.substring(0, userInput.indexOf(" /"));
            String newTaskData = userInput.substring(userInput.indexOf("/new ") + 5);
            if (userInput.contains("/by ")) {
                return taskList.updateDeadlineTask("time", taskName, newTaskData);
            } else if (userInput.contains("/name ")) {
                return taskList.updateDeadlineTask("name", taskName, newTaskData); 
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new Meowception("100");
        }
        return "Oopsies I can't update this task for some reason";
    }

    /**
     * Updates the event task
     * @param userInput
     * @return String
     */
    private String updateEventTask(String userInput) throws Meowception {
        try {
            String taskName = userInput.substring(0, userInput.indexOf(" /"));
            String newTaskData = userInput.substring(userInput.indexOf("/new ") + 5);
            if (userInput.contains("/to ")) {
                return taskList.updateEventTask("to", taskName, newTaskData);
            } else if (userInput.contains("/name ")) {
                return taskList.updateEventTask("name", taskName, newTaskData); 
            } else if (userInput.contains("/from ")) {
                return taskList.updateEventTask("from", taskName, newTaskData); 
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new Meowception("100");
        }
        return "Oopsies I can't update this task for some reason";
    }

    

    /**
     * Closes the scanner
     */
    public void closeScanner() {
        scanner.close();
    }
}
