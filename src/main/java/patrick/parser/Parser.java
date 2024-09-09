package patrick.parser;

import javafx.scene.control.TableColumn;
import patrick.storage.Storage;
import patrick.tasklist.*;
import patrick.ui.Ui;

/**
 * The {@code Parser} class is responsible for parsing user input and determining
 * the appropriate action based on the command. It uses the {@code Type} enum
 * to classify different types of commands and executes the corresponding tasks.
 */
public class Parser {
    private Type inputType;

    /**
     * Enum representing the different types of commands that can be parsed.
     */
    public enum Type {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, ERROR, FORMATS, FIND
    }

    /**
     * Parses the user's input to determine the type of task and executes the appropriate action.
     *
     * @param userInput the input provided by the user.
     * @return the response message based on the executed command.
     */
    public String parseTask(String userInput) {
        assert userInput != null : "User Input cannot be null";
        checkType(userInput);
        String response;

        switch (inputType) {
        case LIST:
            response = Ui.printFileContents();
            break;

        case BYE:
            response = "BYE";
            break;

        case MARK:
            try {
                response = TaskList.mark(userInput);
                break;
            } catch (PatrickException e) {
                response = e.getMessage();
                break;
            }

        case UNMARK:
            try {
                response = TaskList.unmark(userInput);
                break;
            } catch (PatrickException e) {
                response = e.getMessage();
                break;
            }

        case TODO:
            try {
                response = ToDo.toDoTask(userInput);
                break;
            } catch (PatrickException e) {
                response = e.getMessage();
                break;
            }

        case DEADLINE:
            try {
                response = Deadline.deadlineTask(userInput);
                break;
            } catch (PatrickException e) {
                response = e.getMessage();
                break;
            }

        case EVENT:
            try {
                response = Event.eventTask(userInput);
                break;
            } catch (PatrickException e) {
                response = e.getMessage();
                break;
            }

        case DELETE:
            try {
                response = TaskList.delete(userInput);
                break;
            } catch (PatrickException e) {
                response = e.getMessage();
                break;
            }

        case FORMATS:
            response = Ui.formats();
            break;

        case FIND:
            try {
                response = TaskList.findTask(userInput);
                break;
            } catch (PatrickException e) {
                response = e.getMessage();
                break;
            }

        default:
            response = "What are you trying to say man. Re-enter your command \n";
            break;
        }
        assert response != null : "Response cannot be null";
        return response;
    }

    /**
     * Determines the type of command based on the user's input and sets the {@code inputType} accordingly.
     *
     * @param input the input provided by the user.
     */
    public void checkType(String input) {
        assert input != null : "input cannot be null";

        if (input.startsWith("list")) {
            inputType = Type.LIST;
        } else if (input.startsWith("bye")) {
            inputType = Type.BYE;
        } else if (input.startsWith("mark")) {
            inputType = Type.MARK;
        } else if (input.startsWith("unmark")) {
            inputType = Type.UNMARK;
        } else if (input.startsWith("todo")) {
            inputType = Type.TODO;
        } else if (input.startsWith("deadline")) {
            inputType = Type.DEADLINE;
        } else if (input.startsWith("event")) {
            inputType = Type.EVENT;
        } else if (input.startsWith("delete")) {
            inputType = Type.DELETE;
        } else if (input.startsWith("formats")) {
            inputType = Type.FORMATS;
        } else if (input.startsWith("find")) {
            inputType = Type.FIND;
        } else {
            inputType = Type.ERROR;
        }
    }

    /**
     * Checks if a given task already exists in the task list.
     * <p>
     * This method iterates over the existing tasks in the storage and compares each with the provided task
     * based on their string representations.
     *
     * @param task The task to be checked for duplication.
     * @return true if the task is a duplicate (i.e., an identical task exists in the list), false otherwise.
     */
    public static boolean isDuplicate(Task task) {
        for (int i = 0; i < Storage.getList().size(); i++) {
            if (Storage.getList().get(i).toString().equals(task.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Exception class used for handling specific exceptions in the Parser class.
     */
    public static class PatrickException extends Exception {
        /**
         * Constructs a new {@code PatrickException} with the specified detail message.
         *
         * @param str the detail message.
         */
        public PatrickException(String str) {
            super(str);
        }
    }
}
