package kotori;

import kotori.command.Command;
import kotori.command.ExitCommand;
import kotori.command.GreetCommand;
import kotori.parser.Parser;
import kotori.storage.Storage;
import kotori.taskList.TaskList;
import java.util.Scanner;

public class Kotori {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public Kotori () {
        this.storage = new Storage("data", "Kotori.txt");
        this.taskList = storage.load();
        this.parser = new Parser(storage, taskList);
    }

    public void run() {
        // Create a scanner
        Scanner s = new Scanner(System.in);
        // Greet the user
        new GreetCommand().execute();
        // Get the input and execute the correct command
        while (s.hasNextLine()) {
            Command command = parser.parse(s.nextLine());
            command.execute();
            if (command instanceof ExitCommand) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Kotori().run();
    }
}


