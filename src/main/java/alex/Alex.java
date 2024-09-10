package alex;

import alex.storage.Storage;
import alex.tasklist.TaskList;
import alex.ui.Ui;

/**
 * Represents the chatbot that is able to respond to commands given by the user.
 * An Alex object corresponds to a chatbot represented by a string referring to the
 * data file where the bot will load the list of tasks from.
 */
public class Alex {
    private String filePath;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Alex(String filePath) {
        this.filePath = filePath;
        String directoryPath = "./data";
        ui = new Ui();
        storage = new Storage(directoryPath, filePath);
        tasks = new TaskList(storage.loadTasksFromFile(filePath));
    }

    /**
     * Allows the user to input a command for the chatbot to respond to.
     * Handles all the commands given by the user.
     * If the chatbot does not understand the command, it will ask the user to try again.
     * Avoids deep nesting
     *
     * @param userInput input that is entered by the user
     * @return string representing what Alex will display to the user
     */
    public String handleCommand(String userInput) {

        if (userInput.equalsIgnoreCase("bye")) {
            return ui.byeMessage();
        }
        else if (userInput.equalsIgnoreCase("list")) {
            return tasks.handleList();

        } else if (userInput.startsWith("mark")) {
            String tmp = tasks.handleMark(userInput);
            storage.saveTasksToFile(filePath);
            return tmp;

        } else if (userInput.startsWith("unmark")) {
            String tmp = tasks.handleUnmark(userInput);
            storage.saveTasksToFile(filePath);
            return tmp;

        } else if (userInput.startsWith("todo")) {
            String tmp = tasks.handleTodo(userInput);
            storage.saveTasksToFile(filePath);
            return tmp;

        } else if (userInput.startsWith("deadline")) {
            String tmp = tasks.handleDeadline(userInput);
            storage.saveTasksToFile(filePath);
            return tmp;
        } else if (userInput.startsWith("event")) {
            String tmp = tasks.handleEvent(userInput);
            storage.saveTasksToFile(filePath);
            return tmp;
        } else if (userInput.startsWith("delete")) {
            String tmp = tasks.handleDelete(userInput);
            storage.saveTasksToFile(filePath);
            return tmp;
        } else if (userInput.startsWith("tasks on")) {
            return tasks.handleDate(userInput);

        } else if (userInput.startsWith("find")) {
            return tasks.handleFind(userInput);

        } else {
            return "Sorry, I don't understand that command. Did you make a typo?";
        }
    }

    /**
     *
     * @param input input entered by the user
     * @return string returned by the handleCommand method
     */
    public String getResponse(String input) {
        return handleCommand(input);
    }

    public static void main(String[] args) {
        Alex alex = new Alex("./data/alex.txt");

        alex.ui.welcomeMessage();
    }
}
