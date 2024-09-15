package darkpool;

import darkpool.command.Command;
import darkpool.gui.Gui;
import darkpool.parser.Parser;
import darkpool.storage.Storage;
import darkpool.tasklist.TaskList;

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