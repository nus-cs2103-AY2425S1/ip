package tohru;

import tohru.command.Command;
import tohru.command.Parser;
import tohru.exception.TohruException;
import tohru.storage.FileStore;
import tohru.task.TodoList;
import tohru.ui.Ui;

/**
 * Tohru represents the chatbot that keeps track of user's tasks
 */
public class Tohru {

    private FileStore store;
    private TodoList todoList;
    private Ui ui;

    /**
     * Setup up the chatbot
     *
     * @param filePath The location to store the save file
     */
    public Tohru(String filePath) {
        ui = new Ui();
        store = new FileStore(filePath);
        todoList = new TodoList(store.retrieveTodoList());
    }

    /**
     * Execute the main functionalities of the chatbot
     */
    public void run() {
        // Greetings
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
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
    }

    public static void main(String[] args) {
        new Tohru("./data/todosData.txt").run();
    }

}
