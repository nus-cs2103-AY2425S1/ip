package PHambot;

import PHambot.command.Command;
import PHambot.command.ExitCommand;
import PHambot.parser.Parser;
import PHambot.storage.UserData;
import PHambot.task.TaskList;
import PHambot.utils.Utilities;

import java.util.Scanner;

/**
 * Represents the main class of the PHamBot application.
 */
public class PHamBot {
    private static TaskList tasks;
    private static final String[] UserGreetings = {"Hello", "Hi", "What's up"};

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        UserData data = new UserData();
        tasks = data.getTasks();
        Command.setUserData(tasks);

        while (true) {
            String input = scanner.nextLine();

            Command command = parser.parseCommand(input);

            if (command != null) {
                command.executeCommand();
                if (command instanceof ExitCommand) {
                    data.setTasks(tasks);
                    data.saveTasks();
                    break;
                }
            }
        }
    }

    public static void greet() {
        String greeting = "Hi! I'm PHamBot\nHappy to be of service to you today!";
        Utilities.OutlineMessage(greeting);
    }

    public static void sayGoodbye() {
        String goodbye = "Hope I was able to help\nGoodbye!";
        Utilities.OutlineMessage(goodbye);
    }

}
