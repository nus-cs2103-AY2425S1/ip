import java.time.LocalDateTime;

import chatterboxerrors.ChatterBoxDataFileError;
import chatterboxerrors.ChatterBoxError;
import chatterboxerrors.ChatterBoxNullTaskError;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import utils.Commands;
import utils.Parser;
import utils.Storage;
import utils.StoredList;
import utils.TextUi;

/**
 * Represents a Chatbot.
 */
public class Chatterbox {
    private static final String BYE_MESSAGE = """
                       ____________________________________________________________
                       Bye. Hope to see you again soon!
                       ____________________________________________________________
                       """;
    private final StoredList taskList;
    private final Storage storage;
    private final TextUi textUi;
    /**
     * Initialised an instance of Chatterbox with the given save file.
     * @param saveFilePath The filepath of a save file.
     */
    public Chatterbox(String saveFilePath) {
        storage = new Storage(saveFilePath);
        textUi = new TextUi();
        try {
            storage.readFromSave();
        } catch (ChatterBoxDataFileError e) {
            textUi.printMessage(e.getMessage());
        }
        taskList = storage.getSaveList();
        textUi.printWelcome();
        textUi.printTasks(taskList);
    }

    /**
     * Performs the command given and returns whether the Chatbot should be terminated.
     * @param input The command from the user.
     * @return Message for the outcome of command.
     * @throws ChatterBoxError For any ChatterBox related errors.
     */
    public String doCommand(String input) throws ChatterBoxError {
        String message;
        try {
            String[] command = Parser.processInput(input);
            switch (Commands.valueOf(command[0].toUpperCase())) {
            case BYE:
                storage.writeToSave(taskList);
                return BYE_MESSAGE;
            case LIST:
                return taskList.toString();
            case MARK:
                try {
                    message = taskList.getItem(Integer.parseInt(command[1])).setCompleted(true);
                    return message;
                } catch (IndexOutOfBoundsException e) {
                    throw new ChatterBoxNullTaskError();
                }
            case UNMARK:
                try {
                    message = taskList.getItem(Integer.parseInt(command[1])).setCompleted(false);
                    return message;
                } catch (IndexOutOfBoundsException e) {
                    throw new ChatterBoxNullTaskError();
                }
            case DELETE:
                message = taskList.removeItem(Integer.parseInt(command[1]));
                return message;
            case TODO:
                message = taskList.addItem(new ToDo(command[1]));
                return message;
            case DEADLINE:
                message = taskList.addItem(
                        new Deadline(command[1], Parser.processDateTime(command[2]))
                );
                return message;
            case EVENT:
                message = taskList.addItem(
                        new Event(command[1], Parser.processDateTime(command[2]),
                                Parser.processDateTime(command[3]))
                );
                return message;
            case FIND:
                message = taskList.findItem(command[1]);
                return message;
            default:
                throw new ChatterBoxError();
            }
        } catch (ChatterBoxError e) {
            return e.getMessage();
        }
    }

    /**
     * Searches the task list and look for upcoming task to be shown.
     * @return The task to be displayed.
     */
    public String reminder() {
        LocalDateTime now = LocalDateTime.now();
        return taskList.reminder(now);
    }

    public static void main(String[] args) {
        new Chatterbox("data/chatterbox_save.txt");
    }
}
