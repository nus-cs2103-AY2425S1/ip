package rainy.database;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import rainy.commands.ByeCommand;
import rainy.commands.DeadlineCommand;
import rainy.commands.Delete;
import rainy.commands.EventCommand;
import rainy.commands.FindCommand;
import rainy.commands.List;
import rainy.commands.Mark;
import rainy.commands.SortCommand;
import rainy.commands.ToDoCommand;
import rainy.commands.Unmark;
import rainy.commands.UpdateCommand;
import rainy.gui.Main;
import rainy.rainyexceptions.InvalidDeadlineParametersException;
import rainy.rainyexceptions.InvalidEventParametersException;
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
    private static final int INVALID_RESPONSE = -1;
    private static final int TASK_INDEX = 0;
    private static final int START_INDEX = 5;
    private static final String END_OF_OUTPUT = "^";

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
            IOException, InvalidDeadlineParametersException, InvalidEventParametersException {
        // Initialize UI, Storage, TaskTracker, Parser, and File objects
        UI ui = new UI();
        Storage storage = new Storage();
        TaskTracker tm;
        Parser ps = new Parser();
        try {
            File newFile = new File("data/rainy.txt");
            tm = storage.copyPreviousFiles(newFile);
        } catch (Exception e) {
            tm = new TaskTracker();
        }
        tm.receivedFirstInput();
        ps.firstInput(scanCommand);
        String[] input = ps.getInput();
        String[] splitByTask = ps.getSplitByTask();
        String[] updateParameters = ps.getUpdateParameters();
        String message = ps.getMessage();
        int validResponse = ps.getCount();
        assert(validResponse >= INVALID_RESPONSE);
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
            String taskName = splitByTask[TASK_INDEX].substring(START_INDEX);
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

        case UPDATE:
            UpdateCommand updateCommand = new UpdateCommand(validResponse, tm, updateParameters);
            tm = updateCommand.getResponse();
            break;

        case INVALID:
            ui.noCategoryDeclared();
            break;

        default:
            ui.noCategoryDeclared();
        }
        File f = new File("data/rainy.txt");
        storage.writeOverFile(f, tm);
        System.out.print(END_OF_OUTPUT);
    }
}

