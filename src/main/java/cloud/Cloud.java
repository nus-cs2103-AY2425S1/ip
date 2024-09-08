package cloud;

import cloud.command.Command;
import cloud.exception.CloudException;
import cloud.util.Parser;
import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

public class Cloud {

    private static final String EXIT_COMMAND = "bye";
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    public Cloud() {
        this.storage = new Storage();
        this.tasks = storage.readData();
        this.parser = new Parser();
        this.ui = new Ui();
    }

    private void greet() {
        System.out.println(
                "Hello! I'm Cloud\n" +
                        "What can I do for you?"
        );
    }

    private void printHorizLine() {
        System.out.println(
                "____________________________________________________________"
        );
    }

    private void echo(String message) {
        System.out.println(message);
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String getResponse(String userInput) {
        try {
            Command command = parser.parse(userInput);
            return command.execute(tasks, ui, storage);
        } catch (CloudException e) {
            return e.getMessage();
        }
    }

    public String getGreeting() {
        return "Hi I am Cloud! Your personal slave!";
    }
}
