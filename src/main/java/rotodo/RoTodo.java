package rotodo;

import javafx.application.Application;
import rotodo.commands.Command;
import rotodo.exception.InvalidInputException;
import rotodo.processes.Gui;
import rotodo.processes.Parser;
import rotodo.processes.Storage;
import rotodo.tasklist.TaskList;
import rotodo.view.Main;

/**
 * __________       __________            __   _____
 * \______   \  ____\__   ___/____    ___|  | /  _  \   ____
 *  |       _/ /  _ \ |   |  /  _ \  /  _   ||  / \  | /  _ \   ___
 *  |    |   \(  <_> ||   | (  <_> |(  <_>  ||  \_/  |(  <_> | / o \  _
 *  |____|_  / \____/ |___|  \____/  \_____/  \_____/  \____/  \___/ (_) O o .
 *         \/
 * This is the main class of RoTodo.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class RoTodo {
    private Storage storage;
    private TaskList tasks;
    private Gui gui;
    private boolean hasExited = false;

    /**
     * Initialise Storage, TaskList and Ui. Saved data
     * is loaded through Storage.
     */
    public RoTodo() {
        storage = new Storage("./data/rotodo.txt");
        gui = new Gui();
        tasks = new TaskList();
        storage.loadList(tasks);
    }

    /**
     * Starts RoTodo's CLI
     */
    public void run() {
        gui.showMessage();
        boolean hasExited = false;
        while (!hasExited) {
            try {
                String command = gui.readCommand();
                Command c = Parser.process(command);
                c.execute(tasks, gui, storage);
                hasExited = c.isExit();
            } catch (InvalidInputException e) {
                gui.addMessage(e.toString());
            } finally {
                gui.showMessage();
            }
        }
    }

    public String getResponse() {
        return gui.getMessage();
    }

    /**
     * Retrieve output text for GUI based on input.
     *
     * @param input to parse and execute
     * @return output text to be displayed
     */
    public String getResponse(String input) {
        assert input != null : "Input cannot be null";
        try {
            Command c = Parser.process(input);
            assert c != null : "Command should not be null";
            c.execute(tasks, gui, storage);
            hasExited = c.isExit();
        } catch (InvalidInputException e) {
            gui.addMessage(e.toString());
        }
        return gui.getMessage();
    }

    public boolean hasExited() {
        return this.hasExited;
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            Main.setUserName(args[0]);
        }
        Application.launch(Main.class, args);
    }
}
