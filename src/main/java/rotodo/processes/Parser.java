package rotodo.processes;

import rotodo.exception.IncompleteInputException;
import rotodo.exception.InvalidInputException;
import rotodo.tasklist.TaskList;

public class Parser {
    private static TaskList taskList = TaskList.of();

    /**
     * Different types of tasks
     */
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";

    /**
     * Different types of commands
     */
    private static final String HELP = "help";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";
    private static final String LIST = "list";
    private static final String EXIT = "bye";

    /**
     * Process user inputs
     * 
     * @param input user input
     * @throws InvalidInputException
     */
    public static String process(String input) throws InvalidInputException {
        String[] token = input.split(" ", 2);
        boolean delete = false;
        boolean mark = false;
        
        String output = "";
        switch (token[0]) {
            case LIST:
                output = taskList.toString();
                break;

            case EXIT:
                Ui.exit();
                break;

            case DELETE:
                delete = true;
            case MARK:
                mark = true;
            case UNMARK:
                if (token.length < 2) {
                    throw new IncompleteInputException(
                        "RoTodo need task number");
                }

                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(token[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new InvalidInputException(String.format("'%s' not a "
                        + "number RoTodo knows (and RoTodo know much numbers, "
                        + "like 1s and 0s)", token[1]));
                }
                if (delete) {
                    output = taskList.deleteTask(taskNumber);
                    break;
                }
                if (mark) output = taskList.markDone(taskNumber);
                else output = taskList.unmarkDone(taskNumber);
                break;

            case TODO:
                if (token.length < 2) {
                    throw new IncompleteInputException(
                        "RoTodo can't read you mind, otherwise "
                        + "RoTodo's creator would be rich!\n      "
                        + "RoTodo needs a task description");
                }
                output = taskList.addTask(token[1]);
                break;
            
            case DEADLINE:
                if (token.length < 2) {
                    throw new IncompleteInputException(
                        "RoTodo can't read you mind, otherwise "
                        + "RoTodo's creator would be rich!\n      "
                        + "RoTodo needs a task description and deadline");
                }
                token = token[1].split(" /by ", 2);
                if (token.length < 2) {
                    throw new IncompleteInputException(
                        "RoTodo can't read you mind, otherwise "
                        + "RoTodo's creator would be rich!\n      "
                        + "RoTodo needs a task description and deadline");
                }
                output = taskList.addTask(token[0], token[1]);
                break;

            case EVENT:
                if (token.length < 2) {
                    throw new IncompleteInputException(
                        "RoTodo can't read you mind, otherwise "
                        + "RoTodo's creator would be rich!\n      "
                        + "RoTodo needs a task description, from and to date/time");
                }
                token = token[1].split(" /from | /to ", 3);
                if (token.length < 3) {
                    throw new IncompleteInputException(
                        "RoTodo can't read you mind, otherwise "
                        + "RoTodo's creator would be rich!\n      "
                        + "RoTodo needs a task description, from and to date/time");
                }
                boolean fromBeforeTo = input.indexOf("/from") < input.indexOf("/to");
                if (fromBeforeTo) {
                    output = taskList.addTask(token[0], token[1], token[2]);
                } else {
                    output = taskList.addTask(token[0], token[2], token[1]);
                }
                break;
            
            case HELP:
                Ui.help();
        
            default:
                throw new InvalidInputException(
                    "Reep Roop... RoTodo Read No Understand?");
        }
        InvalidInputException.noError();
        return output;
    }
}
