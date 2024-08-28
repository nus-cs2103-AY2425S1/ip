package Bot;

import java.util.Scanner;


//
public class Duke {
    private static String name = "Bot.Duke";
    private ListManager DukeManager = new ListManager();

    private FileManager DukeFileManager = new FileManager("src/main/java/data");
    private Parser parser = new Parser(DukeManager, DukeFileManager);


    // Possible use of Task
    public enum TaskType {
        TODO,EVENT,DEADLINE
    }
    private void exit() {
        System.out.println("Bye! Hope to see you again.");
    }

    private void greet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm " + name + ". How can I assist you today?");
        DukeFileManager.readFile();
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            parser.parseCommand(command);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
    }
}
