package chatbot;

import storage.Storage;
import tasks.TaskList;

/**
 * Class for the chatbot peridot.
 * It acts as a todolist tracker.
 */
public class Peridot {

    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor class for the peridot bot.
     *
     * @param filePath filepath for local tasklist storage.
     */
    public Peridot(String filePath) {
        storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
    }

    /**
     * Returns a response from the bot.
     *
     * @param input user input to respond to
     * @return bot response
     */
    public String getResponse(String input) {
        try {
            String response = Parser.answer(input, taskList);
            storage.write(taskList.getTaskList());
            return response;
        } catch (Exception e) {
            return "?";
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
    }
}
