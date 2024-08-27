package patrick.parser;

import patrick.tasklist.*;

import patrick.ui.Ui;

public class Parser {
    Type inputType;
    public enum Type {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, ERROR, FORMATS
    }
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
                    Ui.showErrorMsg(e.toString());
                    break;
                }

            case UNMARK:
                try {
                    TaskList.unmark(userInput);
                    break;
                } catch (PatrickException e) {
                    Ui.showErrorMsg(e.toString());
                    break;
                }

            case TODO:
                try {
                    ToDo.toDoTask(userInput);
                    break;
                } catch (PatrickException e) {
                    Ui.showErrorMsg(e.toString());
                    break;
                }

            case DEADLINE:
                try {
                    Deadline.deadlineTask(userInput);
                    break;
                } catch (PatrickException e) {
                    Ui.showErrorMsg(e.toString());
                    break;
                }

            case EVENT:
                try {
                    Event.eventTask(userInput);
                    break;
                } catch (PatrickException e) {
                    Ui.showErrorMsg(e.toString());
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

            default:
                Ui.showErrorMsg("What are you trying to say man. Re-enter your command \n");
                break;
        }
        return inputType;
    }
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
        else
            inputType = Type.ERROR;
    }

    public static class PatrickException extends Exception {
        public PatrickException(String str) {
            super(str);
        }
    }

}
