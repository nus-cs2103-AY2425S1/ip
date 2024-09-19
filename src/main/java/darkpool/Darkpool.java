package darkpool;

import darkpool.command.Command;
import darkpool.gui.Gui;
import darkpool.parser.Parser;
import darkpool.storage.Storage;
import darkpool.tasklist.TaskList;

/**
 * Darkpool is a task manager that helps users to keep track of their tasks.
 * It can add, delete, mark as done, find tasks and list all tasks.
 */
public class Darkpool {

    private final Storage storage;
    private TaskList taskList;
    private final Gui gui;

    public Darkpool() {
        gui = new Gui();
        String filePath = "data/tasks.txt";
        storage = new Storage(filePath);


        try {
            taskList = new TaskList(storage.loadData());
        } catch (DarkpoolException e) {
            // Show error message if loading data fails
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Returns the response of the user input.
     *
     * @param input User input.
     * @return Response of the user input.
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be null";
        try {
            Command command = Parser.parse(input);
            String response = command.execute(taskList, gui, storage);
            assert response != null : "Response should not be null after command execution";

            if (command.isExit()) {
                return gui.goodbye();
            }
            return response;
        }  catch (DarkpoolException e) {
            return e.getMessage();
        }
    }

}