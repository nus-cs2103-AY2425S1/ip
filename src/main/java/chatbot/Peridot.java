package chatbot;

import storage.Storage;
import tasks.TaskList;

/**
 * Class for the chatbot Peridot.
 */
public class Peridot {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for the chatbot Peridot.
     *
     * @param filePath filepath to local todo list storage.
     */
    public Peridot(String filePath) {
        storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
        ui = new Ui(taskList, storage);
    }

    /**
     * Starts the chatbot "Peridot"
     */
    public void start() {
        ui.run();
        storage.write(taskList.getTaskList());
    }

    /**
     * Gets the bot response from the chatbot depending on user input
     *
     * @param input user input to the bot.
     * @return bot response to the inputted user input.
     */
    public String getResponse(String input) {
        try {
            String response = Parser.answer(input, taskList);
            storage.write(taskList.getTaskList());
            return response;
        } catch (Exception e) {
            return "type 'help' to see list of commands";
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
    }
}
