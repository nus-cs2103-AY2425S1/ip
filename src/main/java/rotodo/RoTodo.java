package rotodo;
import rotodo.commands.Command;
import rotodo.exception.InvalidInputException;
import rotodo.processes.Parser;
import rotodo.processes.Storage;
import rotodo.processes.Ui;
import rotodo.tasklist.TaskList;

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
    private Ui ui;

    /**
     * Initialise Storage, TaskList and Ui. Saved data
     * is loaded through Storage.
     */
    public RoTodo() {
        storage = new Storage("./data/rotodo.txt");
        ui = new Ui();
        tasks = new TaskList();
        storage.loadList(tasks);
    }

    /**
     * Starts RoTodo's CLI
     */
    public void run() {
        ui.showMessage();
        boolean hasExited = false;
        while (!hasExited) {
            try {
                String command = ui.readCommand();
                Command c = Parser.process(command);
                c.execute(tasks, ui, storage);
                hasExited = c.isExit();
            } catch (InvalidInputException e) {
                ui.addMessage(e.toString());
            } finally {
                ui.showMessage();
            }
        }
    }

    public static void main(String[] args) {
        RoTodo rotodo = new RoTodo();
        rotodo.run();
    }
}
