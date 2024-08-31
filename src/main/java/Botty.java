import storage.StorageHandler;
import tasks.TaskManager;
import commands.*;
import exceptions.BottyException;
import exceptions.UnknownCommandException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Botty {
    private static final String logo = " ________  ________  _________  _________    ___    ___                 \n" +
            "|\\   __  \\|\\   __  \\|\\___   ___\\\\___   ___\\ |\\  \\  /  /|                \n" +
            "\\ \\  \\|\\ /\\ \\  \\|\\  \\|___ \\  \\_\\|___ \\  \\_| \\ \\  \\/  / /                \n" +
            " \\ \\   __  \\ \\  \\\\\\  \\   \\ \\  \\     \\ \\  \\   \\ \\    / /                 \n" +
            "  \\ \\  \\|\\  \\ \\  \\\\\\  \\   \\ \\  \\     \\ \\  \\   \\/  /  /                  \n" +
            "   \\ \\_______\\ \\_______\\   \\ \\__\\     \\ \\__\\__/  / /                    \n" +
            "    \\|_______|\\|_______|    \\|__|      \\|__|\\___/ /                     \n" +
            "                                           \\|___|/                      \n" +
            "                                                                        \n" +
            " _________  ___  ___  _______           ________  ________  _________   \n" +
            "|\\___   ___\\\\  \\|\\  \\|\\  ___ \\         |\\   __  \\|\\   __  \\|\\___   ___\\ \n" +
            "\\|___ \\  \\_\\ \\  \\\\\\  \\ \\   __/|        \\ \\  \\|\\ /\\ \\  \\|\\  \\|___ \\  \\_| \n" +
            "     \\ \\  \\ \\ \\   __  \\ \\  \\_|/__       \\ \\   __  \\ \\  \\\\\\  \\   \\ \\  \\  \n" +
            "      \\ \\  \\ \\ \\  \\ \\  \\ \\  \\_|\\ \\       \\ \\  \\|\\  \\ \\  \\\\\\  \\   \\ \\  \\ \n" +
            "       \\ \\__\\ \\ \\__\\ \\__\\ \\_______\\       \\ \\_______\\ \\_______\\   \\ \\__\\\n" +
            "        \\|__|  \\|__|\\|__|\\|_______|        \\|_______|\\|_______|    \\|__|";

    private static final String bottySymbol = "Botty: ";
    private static final String bottyIndentation = "       ";
    private Scanner inputScanner;
    private Map<String, Command> commands;
    private TaskManager taskManager;
    public Botty(Map<String, Command> commands) {
        this.commands = commands;
    }
    public static void main(String[] args) {

        Map<String, Command> commands = new HashMap<>();
        commands.put("list", new ListCommand());
        commands.put("mark", new MarkCommand());
        commands.put("unmark", new UnmarkCommand());
        commands.put("todo", new TodoCommand());
        commands.put("deadline", new DeadlineCommand());
        commands.put("event", new EventCommand());
        commands.put("delete", new DeleteCommand());



        Botty botty = new Botty(commands);

        botty.beginInteraction();

    }

    public void beginInteraction() {
        taskManager = new TaskManager();
        StorageHandler storageHandler = new StorageHandler("./data", "tasks");
        storageHandler.loadTaskList(taskManager);

        inputScanner = new Scanner(System.in);

        displayIntroduction();


        while (true) {
            try {
                System.out.println();

                String userInput = inputScanner.nextLine();

                ParsedInput parsedInput = ParsedInput.parse(userInput);

                if (parsedInput.getCommand().equals("bye")) {
                    break;
                }

                if (!commands.containsKey(parsedInput.getCommand())) {
                    throw new UnknownCommandException(parsedInput.getCommand());
                }

                reply(commands.get(parsedInput.getCommand()).execute(taskManager, parsedInput));

            } catch (BottyException exception) {
                reply(exception.getMessage());
            }
        }

        inputScanner.close();
        reply("Thank you for your continued patronage. Goodbye!");
        storageHandler.saveTaskList(taskManager);
    }

    private void displayIntroduction() {
        System.out.println(logo);
        System.out.println();

        reply("Hello, I am Botty the Bot, how may I be of service today?");
    }

    private void reply(String content) {
        String[] strings = content.split("\n");
        System.out.println(bottySymbol + strings[0]);
        for (int i = 1; i < strings.length; i++) {
            System.out.println(bottyIndentation + strings[i]);
        }
    }

}
