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
     * @return If command received should terminate the Chatbot
     * @throws ChatterBoxError For any ChatterBox related errors.
     */
    public String doCommand(String input) throws ChatterBoxError {
        String message;
        try {
            String[] command = Parser.processInput(input);
            switch (Commands.valueOf(command[0].toUpperCase())) {
            case BYE:
                storage.writeToSave(taskList);
                assert command.length == 1 : "There should only be one fields in the command";
                return """
                       ____________________________________________________________
                       Bye. Hope to see you again soon!
                       ____________________________________________________________
                       """;
            case LIST:
                assert command.length == 1 : "There should only be one fields in the command";
                return taskList.toString();
            case MARK:
                assert command.length == 2 : "There should only be two fields in the command";
                try {
                    message = taskList.getItem(Integer.parseInt(command[1])).setCompleted(true);
                    return message;
                } catch (IndexOutOfBoundsException e) {
                    throw new ChatterBoxNullTaskError();
                }
            case UNMARK:
                assert command.length == 2 : "There should only be two fields in the command";
                try {
                    message = taskList.getItem(Integer.parseInt(command[1])).setCompleted(false);
                    return message;
                } catch (IndexOutOfBoundsException e) {
                    throw new ChatterBoxNullTaskError();
                }
            case DELETE:
                assert command.length == 2 : "There should only be two fields in the command";
                message = taskList.removeItem(Integer.parseInt(command[1]));
                return message;
            case TODO:
                assert command.length == 2 : "There should only be two fields in the command";
                message = taskList.addItem(new ToDo(command[1]));
                return message;
            case DEADLINE:
                assert command.length == 3 : "There should only be three fields in the command";
                message = taskList.addItem(
                        new Deadline(command[1], Parser.processDateTime(command[2]))
                );
                return message;
            case EVENT:
                assert command.length == 4 : "There should only be four fields in the command";
                message = taskList.addItem(
                        new Event(command[1], Parser.processDateTime(command[2]),
                                Parser.processDateTime(command[3]))
                );
                return message;
            case FIND:
                assert command.length == 2 : "There should only be two fields in the command";
                message = taskList.findItem(command[1]);
                return message;
            default:
                throw new ChatterBoxError();
            }
        } catch (ChatterBoxError e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Chatterbox("data/chatterbox_save.txt");
    }
}
