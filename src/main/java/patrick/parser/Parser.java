package patrick.parser;

import patrick.tasklist.*;
import patrick.ui.Ui;

/**
 * The {@code Parser} class is responsible for parsing user input and determining
 * the appropriate action based on the command. It uses the {@code Type} enum
 * to classify different types of commands and executes the corresponding tasks.
 */
public class Parser {
    Type inputType;

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
     * @return the type of the command that was executed.
     */
    public Type parseTask(String userInput) {
        checkType(userInput);

        switch (inputType) {
            case LIST:
                Ui.printFileContents();
                break;

            case BYE:
                return Type.BYE;

            case MARK:
                try {
                    TaskList.mark(userInput);
                    break;
                } catch (PatrickException e) {
                    Ui.showErrorMsg(e.getMessage());
                    break;
                }

            case UNMARK:
                try {
                    TaskList.unmark(userInput);
                    break;
                } catch (PatrickException e) {
                    Ui.showErrorMsg(e.getMessage());
                    break;
                }

            case TODO:
                try {
                    ToDo.toDoTask(userInput);
                    break;
                } catch (PatrickException e) {
                    Ui.showErrorMsg(e.getMessage());
                    break;
                }

            case DEADLINE:
                try {
                    Deadline.deadlineTask(userInput);
                    break;
                } catch (PatrickException e) {
                    Ui.showErrorMsg(e.getMessage());
                    break;
                }

            case EVENT:
                try {
                    Event.eventTask(userInput);
                    break;
                } catch (PatrickException e) {
                    Ui.showErrorMsg(e.getMessage());
                    break;
                }

            case DELETE:
                try {
                    TaskList.delete(userInput);
                    break;
                } catch (PatrickException e) {
                    Ui.showErrorMsg(e.toString());
                    break;
                }

            case FORMATS:
                Ui.formats();
                break;

            case FIND:
                try {
                    TaskList.findTask(userInput);
                    break;
                } catch (PatrickException e) {
                    Ui.showErrorMsg(e.getMessage());
                }

            default:
                Ui.showErrorMsg("What are you trying to say man. Re-enter your command \n");
                break;
        }
        return inputType;
    }

    /**
     * Determines the type of command based on the user's input and sets the {@code inputType} accordingly.
     *
     * @param input the input provided by the user.
     */
    public void checkType(String input) {
        if (input.startsWith("list"))
            inputType = Type.LIST;
        else if (input.startsWith("bye"))
            inputType = Type.BYE;
        else if (input.startsWith("mark"))
            inputType = Type.MARK;
        else if (input.startsWith("unmark"))
            inputType = Type.UNMARK;
        else if (input.startsWith("todo"))
            inputType = Type.TODO;
        else if (input.startsWith("deadline"))
            inputType = Type.DEADLINE;
        else if (input.startsWith("event"))
            inputType = Type.EVENT;
        else if (input.startsWith("delete"))
            inputType = Type.DELETE;
        else if (input.startsWith("formats"))
            inputType = Type.FORMATS;
        else if (input.startsWith("find"))
            inputType = Type.FIND;
        else
            inputType = Type.ERROR;
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
