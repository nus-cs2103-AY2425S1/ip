package PHambot;

import PHambot.utils.Utilities;
import PHambot.command.*;
import PHambot.parser.Parser;
import PHambot.task.TaskList;
import PHambot.storage.UserData;
import jdk.jshell.execution.Util;

import javax.lang.model.type.ErrorType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class PHamBot {
    private static TaskList tasks;
    private static final String[] UserGreetings = {"Hello", "Hi", "What's up"};

    public static void main(String[] args) {
        Greet();
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

    public static void Greet() {
        String greeting = "Hi! I'm PHamBot\nHappy to be of service to you today!";
        Utilities.OutlineMessage(greeting);
    }

    public static void SayGoodbye() {
        String goodbye = "Hope I was able to help\nGoodbye!";
        Utilities.OutlineMessage(goodbye);
    }

}
