package bob;
import exceptions.CommandNotFoundException;
import exceptions.MissingParamsException;
import exceptions.PositionException;
import misc.Storage;
import misc.Ui;
import task.Tasklist;

/**
 * Main class for the chatbot Bob. It has a <code>Storage</code> to allow read and write,
 * and a <code>Tasklist</code> to manage tasks and a <code>Ui</code> to handle responses.
 */
public class Bob {
    private Storage storage;
    private Tasklist tasklist;
    private Ui ui;

    public Bob() {
        this.storage = new Storage("src/main/data/dataFile.txt");
        this.tasklist = new Tasklist();
        this.ui = new Ui();
    }

    public static void main(String[] args) throws CommandNotFoundException,
            MissingParamsException, PositionException {
        System.out.println("Hello!");
    }

    public String greeting() {
        return ("Hello! Me bob");
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String cmd = input.split(" ", 2)[0];
        String rest = input.split(" ", 2).length > 1 ? input.split(" ", 2)[1] : "";
        String[] strArr = new String[]{cmd, rest};
        String retStr = "";

        try {
            // main command control flow
            switch (strArr[0].toLowerCase()) {
            case "bye":
                return ui.endGame();

            case "list":
                return ui.replyGetList(tasklist);

            case "mark":
                retStr = ui.replyMarkDone(Integer.parseInt(strArr[1]) - 1, tasklist);
                storage.updateDataFile(tasklist.getList());
                return retStr;

            case "unmark":
                retStr = ui.replyMarkUndone(Integer.parseInt(strArr[1]) - 1, tasklist);
                storage.updateDataFile(tasklist.getList());
                return retStr;

            case "todo":
                retStr = ui.replyTodo(strArr[1], tasklist);
                storage.updateDataFile(tasklist.getList());
                return retStr;

            case "event":
                retStr = ui.replyEvent(strArr[1], tasklist);
                storage.updateDataFile(tasklist.getList());
                return retStr;

            case "deadline":
                retStr = ui.replyDeadline(strArr[1], tasklist);
                storage.updateDataFile(tasklist.getList());
                return retStr;

            case "delete":
                retStr = ui.replyDelete(Integer.parseInt(strArr[1]) - 1, tasklist);
                storage.updateDataFile(tasklist.getList());
                return retStr;

            case "find":
                retStr = ui.replyFind(strArr[1], tasklist);
                return retStr;

            case "findexact":
                retStr = ui.replyFindExact(strArr[1], tasklist);
                return retStr;

            default:
                throw new CommandNotFoundException();

            }
        } catch (MissingParamsException | PositionException | CommandNotFoundException c) {
            System.out.println(c);
            return c.toString();
        }
    }
}
