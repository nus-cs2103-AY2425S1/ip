package pochat.bot;
import pochat.exceptions.DateTimeInvalidException;
import pochat.exceptions.DeadlineFormatInvalidException;
import pochat.exceptions.EventFormatInvalidException;
import pochat.exceptions.TaskDescriptionEmptyException;

/**
 * This class handles the business logic of the chatbot and handles
 *     input / output from the user.
 */
public class PoChat {
    private TaskList taskList;

    /**
     * Constructor sets up the <code>PoChat</code> object
     *     with an empty TaskList
     */
    public PoChat() {
        this.taskList = new TaskList();
    }

    /**
     * Loads the chat data stored in the history
     * @param chatData the ChatData data handler to access the history
     */
    public void load(ChatData chatData) {
        this.taskList = chatData.toTaskList();
    }

    /**
     * Saves the current data in the TaskList to the history
     * @param chatData the ChatData data handler to access the history
     */
    public void save(ChatData chatData) {
        chatData.save(this.taskList);
    }

    /**
     * Returns the right response to the user based on the input entered.
     * @param textInput from the user
     * @return response from the parser as a string
     */
    public String getResponse(String textInput) {
        try {
            return Parser.of(this.taskList).parse(textInput);
        } catch (DeadlineFormatInvalidException | DateTimeInvalidException
                 | TaskDescriptionEmptyException | EventFormatInvalidException e) {
            return e.getMessage();
        }
    }
}
