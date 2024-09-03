package tohru;

import javafx.application.Application;
import tohru.command.Command;
import tohru.command.Parser;
import tohru.exception.CorruptSaveException;
import tohru.exception.TohruException;
import tohru.storage.FileStore;
import tohru.task.TodoList;
import tohru.ui.FxAdapter;
import tohru.ui.Gui;
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
    public Tohru(String filePath) {
        store = new FileStore(filePath);
    }

    /**
     * Executes the main functionalities of the text-based chatbot.
     */
    private void runTui() {
        ui = new Tui();
        load();
        // Greetings
        ui.showWelcome();

        while (!isExit) {
            process();
        }
    }

    /**
     * Executes the main functionalities of the gui-based chatbot.
     */
    private void runGui(String[] args) {
        ui = new FxAdapter(this);
        load();
        Application.launch(Gui.class, args);
    }

    /**
     * Loads the content of save from disk at startup
     */
    private void load() {
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
        new Tohru("./data/todosData.txt").runGui(args);
    }

}
