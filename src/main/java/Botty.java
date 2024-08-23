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

    public static void main(String[] args) {
        Botty botty = new Botty();

        botty.beginInteraction();
    }

    public void beginInteraction() {
        inputScanner = new Scanner(System.in);
        taskManager = new TaskManager();

        displayIntroduction();
        boolean exitFlag = false;
        while (!exitFlag) {
            try {
                System.out.println();

                String userInput = inputScanner.nextLine();

                ParsedInput parsedInput = ParsedInput.parse(userInput);

                switch (parsedInput.getCommand()) {
                    case "bye":
                        exitFlag = true;
                        break;
                    case "list":
                        handleList();
                        break;
                    case "mark":
                        handleMark(parsedInput);
                        break;
                    case "unmark":
                        handleUnmark(parsedInput);
                        break;
                    case "todo":
                        handleTodo(parsedInput);
                        break;
                    case "deadline":
                        handleDeadline(parsedInput);
                        break;
                    case "event":
                        handleEvent(parsedInput);
                        break;
                    case "delete":
                        handleDelete(parsedInput);
                        break;
                    default:
                        throw new UnknownCommandException(parsedInput.getCommand());
                }
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

    private void addToTaskList(Task task) {
        taskManager.addTask(task);

        reply("I have added the following task to the list!\n" +
                task + "\n" +
                "You now have " + taskManager.size() + " tasks.");

    }

    // Adapted from https://stackoverflow.com/a/39402538
    private boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    private void handleList() throws TaskListEmptyException {
        reply("Here you go!\n" + taskManager.list());
    }
    private void handleMark(ParsedInput parsedInput) throws BottyException {
        String argument = parsedInput.getArgument("main");
        if (argument == null || !isNumber(argument)) {
            throw new BottyException("I don't quite know what you want me to do. " +
                    "Do indicate which task to mark with its number!");
        }

        int taskIndex = Integer.parseInt(argument) - 1;
        Task task = taskManager.markTask(taskIndex);

        reply("Congrats on completing that! Let me just mark that as done for you.\n" + task);
    }
    private void handleUnmark(ParsedInput parsedInput) throws BottyException {
        String argument = parsedInput.getArgument("main");
        if (argument == null || !isNumber(argument)) {
            throw new BottyException("I don't quite know what you want me to do. " +
                    "Do indicate which task to unmark with its number!");
        }
        int taskIndex = Integer.parseInt(argument) - 1;
        Task task = taskManager.unmarkTask(taskIndex);

        reply("It's okay, we can get that done later. I'll mark that as undone for you.\n" + task);
    }
    private void handleTodo(ParsedInput parsedInput) throws BottyException {
        try {
            Todo todo = new Todo(parsedInput.getArgument("main"));
            addToTaskList(todo);
        } catch (ArgumentNotFoundException | EmptyArgumentException ex) {
            throw new BottyException("I am unable to add that todo! Please ensure that the description is not blank");
        }
    }
    private void handleDeadline(ParsedInput parsedInput) throws BottyException {
        try {
            Deadline deadline = new Deadline(parsedInput.getArgument("main"), parsedInput.getArgument("by"));
            addToTaskList(deadline);
        } catch (ArgumentNotFoundException | EmptyArgumentException ex) {
            throw new BottyException("I am unable to add that deadline! Please provide details " +
                    "in the following format: [description] /by [deadline]");
        }
    }
    private void handleEvent(ParsedInput parsedInput) throws BottyException {
        try {
            Event event = new Event(parsedInput.getArgument("main"),
                    parsedInput.getArgument("from"),
                    parsedInput.getArgument("to"));
            addToTaskList(event);
        } catch (ArgumentNotFoundException | EmptyArgumentException ex) {
            throw new BottyException("I am unable to add that event! Please provide details in " +
                    "the following format: [description] /from [start] /to [end]");
        }
    }
    private void handleDelete(ParsedInput parsedInput) throws BottyException {
        String argument = parsedInput.getArgument("main");
        if (argument == null || !isNumber(argument)) {
            throw new BottyException("I don't quite know what you want me to do. " +
                    "Do indicate which task to delete with its number!");
        }
        int taskIndex = Integer.parseInt(argument) - 1;

        Task task = taskManager.deleteTask(taskIndex);
        reply("Got it! I have removed the following task:\n" +
                task + "\n" +
                "You have " + taskManager.size() + " tasks left!");
    }
}
