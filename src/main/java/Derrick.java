package ip.derrick ;
import java.awt.geom.NoninvertibleTransformException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Derrick {
    private ArrayList<Task> toDo = new ArrayList<>();
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    public Derrick() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasksFromFile());
    }

    /**
     * Runs the chatbot where the user can input his/her commands.
     */
    public void run(String input) {
        CommandHandler c = parser.returnCommand(input);
        c.execute(input, this.tasks, this.storage, this.ui);
    }
}
