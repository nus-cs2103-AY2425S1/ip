import command.*;
import parser.Parser;
import tasks.Task;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents the chatbot echoing out the replies based on the
 * input command word.
 *
 */
public class Echo {
    private String word;
    private TaskList list;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    public Echo() {
        this.storage = new Storage("chatHistory.txt");
        try {
            this.list = storage.loadTasks();
        } catch (IOException e) {
            this.list = new TaskList();
        }
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Sets the word for the echo object.
     * @param word Input command by the user.
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Returns the reply of the chatbot back to the user.
     * @return Reply by the chatbot.
     */
    public String echoOut() {

        String[] parts = word.split(" ", 2);
        String commandWord = parts[0];
        Command command;

        switch (commandWord) {
            case "list":
                command = new ListCommand();
                break;
            case "mark":
                command = new MarkCommand();
                break;
            case "unmark":
                command = new UnmarkCommand();
                break;
            case "find":
                command = new FindCommand();
                break;
            case "todo":
                command = new ToDoCommand();
                break;
            case "deadline":
                command = new DeadlineCommand();
                break;
            case "event":
                command = new EventCommand();
                break;
            case "update":
                command = new UpdateCommand();
                break;
            case "delete":
                command = new DeleteCommand();
                break;
            case "save":
                command = new SaveCommand();
                break;
            case "bye":
                command = new ByeCommand();
                break;
            default:
                command = new InvalidCommand();
        }
        return command.execute(parts, list, ui, storage, parser);
    }
}
