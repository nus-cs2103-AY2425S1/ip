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
    private TaskManager taskManager;
    private Scanner inputScanner;
    private Map<String, Command> commands;
    public Botty(Map<String, Command> commands) {
        this.commands = commands;
    }
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Map<String, Command> commands = new HashMap<>();
        commands.put("list", new ListCommand(taskManager));
        commands.put("mark", new MarkCommand(taskManager));
        commands.put("unmark", new UnmarkCommand(taskManager));
        commands.put("todo", new TodoCommand(taskManager));
        commands.put("deadline", new DeadlineCommand(taskManager));
        commands.put("event", new EventCommand(taskManager));
        commands.put("delete", new DeleteCommand(taskManager));

        Botty botty = new Botty(commands);

        botty.beginInteraction();
    }

    public void beginInteraction() {
        inputScanner = new Scanner(System.in);
        taskManager = new TaskManager();

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

                reply(commands.get(parsedInput.getCommand()).execute(parsedInput));

            } catch (BottyException exception) {
                reply(exception.getMessage());
            }
        }

        inputScanner.close();
        reply("Thank you for your continued patronage. Goodbye!");
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
