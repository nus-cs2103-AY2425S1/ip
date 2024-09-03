package tohru;

import tohru.command.Command;
import tohru.command.Parser;
import tohru.exception.CorruptSaveException;
import tohru.exception.TohruException;
import tohru.storage.FileStore;
import tohru.task.TodoList;
import tohru.ui.Tui;
import tohru.ui.Ui;

/**
 * Represents the chatbot that keeps track of user's tasks.
 */
public class Tohru {

    private FileStore store;
    private TodoList todoList;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Setups up the chatbot.
     *
     * @param filePath The location to store the save file.
     */
    public Tohru(String filePath, Ui ui) {
        this.ui = ui;
        store = new FileStore(filePath);
        try {
            todoList = new TodoList(store.retrieveTodoList());
        } catch (CorruptSaveException e) {
            ui.showError(e.getMessage());
            todoList = new TodoList(e.getSavedEntries());
        } catch (TohruException e) {
            ui.showError(e.getMessage());
            todoList = new TodoList();
        } finally {
            ui.showDivider();
        }

    }

    /**
     * Executes the main functionalities of the text-based chatbot.
     */
    public void runTui() {
        // Greetings
        ui.showWelcome();

        while (!isExit) {
            process();
        }
    }

    /**
     * Handles the request and responses related functionalities of the text-based chatbot.
     */
    public void process() {
        try {
            String prompt = ui.readCommand();
            ui.showDivider();
            Command cmd = Parser.parse(prompt);
            cmd.execute(todoList, ui, store);
            isExit = cmd.isExit();
        } catch (TohruException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showDivider();
        }
    }

    public static void main(String[] args) {
        new Tohru("./data/todosData.txt", new Tui()).runTui();
    }

}
