package darkpool;

import darkpool.command.Command;
import darkpool.gui.Gui;
import darkpool.util.DarkpoolException;
import darkpool.parser.Parser;
import darkpool.util.Storage;
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
        try {
            Command command = Parser.parse(input);
            String response = command.execute(taskList, gui, storage);
            if (command.isExit()) {
                return gui.goodbye();
            }
            return response;
        }  catch (DarkpoolException e) {
            return e.getMessage();
        }
    }

}