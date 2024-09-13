package milo;

import milo.lists.ClientsList;
import milo.storage.ClientStorage;
import milo.storage.TaskStorage;
import milo.command.Command;
import milo.parser.Parser;
import milo.lists.TaskList;
import milo.ui.ClientUi;
import milo.ui.TaskUi;

/**
* Represents the task bot programme
* containing components such as
* Storage, TaskList, Ui
 */
public class Milo {
    private final TaskStorage taskStorage;
    private final ClientStorage clientStorage;
    private final TaskList tasks;
    private final TaskUi taskUi;
    private final ClientUi clientUi;
    private final Parser parser;
    private final ClientsList clients;

    /**
    * Initialise Milo bot and its Ui, Storage, TaskList field
     */
    public Milo() {
        this.taskUi = new TaskUi();
        this.clientUi = new ClientUi();
        // Initialise task storage
        this.taskStorage = new TaskStorage("./src/data/miloData.txt");
        // Reads task data from storage
        this.tasks = new TaskList(taskStorage.readData());
        // Initialise client storage
        this.clientStorage = new ClientStorage("./src/data/miloClients.txt");
        // Reads client data from storage
        this.clients = new ClientsList(clientStorage.readData());
        this.parser = new Parser();
    }

    /**
    * Main method
     */
    public static void main(String[] args) {
        new Milo().run();
    }


    /**
     * Method running Milo
     */
    public void run() {
        taskUi.greetUser();
        runCommandLoopTilBye();
    }

    /**
     * Method looping for user input
     */
    public void runCommandLoopTilBye() {
        String input;
        do {
            input = taskUi.getUserInput();
            assert input != null;
            parser.readInput(input);
            // Save data to storage
        } while (!input.toLowerCase().strip().equals("bye"));
        taskStorage.saveTaskData(this.tasks);
    }

    /**
     * method to return Milo response from user input
     *
     * @param input user text input
     */
    public String getResponse(String input) {
        // Check if input equals to bye
        if (input.toLowerCase().strip().equals("bye")) {
            // Save data to storage
            taskStorage.saveTaskData(this.tasks);
            clientStorage.saveClientData(this.clients);
        }
        Command userCommand = parser.readInput(input);
        userCommand.execute(this.tasks, this.clients);
        return userCommand.commandToString(this.taskUi, this.clientUi, this.tasks, this.clients);
    }
}
