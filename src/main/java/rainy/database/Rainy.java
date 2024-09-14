package rainy.database;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import rainy.commands.*;
import rainy.gui.Main;
import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;
import rainy.tasks.TaskTracker;

/**
 * Initializes the Rainy Chatbot.
 * The Rainy program is a greenfield project building a rudimentary chatbot.
 *
 * @author Tan Soo Yap
 * @since 2024-08-19
 */
public class Rainy {
    /**
     * Serves as the entry point for the Rainy chatbot.
     *
     * @param args  Command-line arguments passed to the program as an array of {@code String} objects.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    /**
     * Prints out an output based on user command.
     *
     * @param scanCommand  User command typed into the TextField.
     * @throws InvalidIndexException         Thrown by <code>TaskManager</code> object when user provides
     *                                       a non-existent task number.
     * @throws InvalidMarkAndUnmarkException Thrown by <code>Task</code> object when user wants to mark a
     *                                       marked tasked or unmark an unmarked task.
     * @throws IOException
     */
    public static void acceptInput(String scanCommand) throws InvalidIndexException, InvalidMarkAndUnmarkException,
            IOException {
        // Initialize UI, Storage, TaskTracker, Parser, and File objects
        UI ui = new UI();
        Storage storage = new Storage();
        TaskTracker tm;
        Parser ps = new Parser();
        File newFile = new File("src/main/java/rainy.txt");
        tm = storage.copyPreviousFiles(newFile);
        tm.receivedFirstInput();
        ps.firstInput(scanCommand);
        String[] input = ps.getInput();
        String[] splitByTask = ps.getSplitByTask();
        String message = ps.getMessage();
        int validResponse = ps.getCount();
        assert(validResponse >= -1);
        Instructions instruction = ps.enumOperator(message);
        // I used switch instructions here to handle the various user inputs.
        switch (instruction) {
        case LIST:
            List listCommand = new List(tm);
            listCommand.getResponse();
            break;

        case MARK:
            Mark markCommand = new Mark(validResponse, tm);
            tm = markCommand.getResponse();
            break;

        case UNMARK:
            Unmark unmarkCommand = new Unmark(validResponse, tm);
            tm = unmarkCommand.getResponse();
            break;

        case DELETE:
            Delete deleteCommand = new Delete(validResponse, tm);
            tm = deleteCommand.getResponse();
            break;

        case TODO:
            String taskName = splitByTask[0].substring(5);
            ToDoCommand toDoCommand = new ToDoCommand(input, taskName, tm);
            tm = toDoCommand.getResponse();
            break;

        case DEADLINE:
            DeadlineCommand deadlineCommand = new DeadlineCommand(splitByTask, input, tm);
            tm = deadlineCommand.getResponse();
            break;

        case EVENT:
            EventCommand eventCommand = new EventCommand(splitByTask, input, tm);
            tm = eventCommand.getResponse();
            break;

        case SORT:
            SortCommand sortCommand = new SortCommand(tm);
            tm = sortCommand.getResponse();
            break;

        case FIND:
            String searchKeyword = ps.findTask(scanCommand);
            FindCommand findCommand = new FindCommand(tm, searchKeyword);
            tm = findCommand.getResponse();
            break;

        case BYE:
            ByeCommand byeCommand = new ByeCommand();
            byeCommand.getResponse();
            break;

        case INVALID:
            ui.noCategoryDeclared();
            break;

        default:
            ui.noCategoryDeclared();
        }
        File f = new File("src/main/java/rainy.txt");
        storage.writeOverFile(f, tm);
        System.out.print("^");
    }
}

