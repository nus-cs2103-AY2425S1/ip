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

    private static final String bottySymbol = "|┐∵|┘: ";
    private static final String bottyIndentation = "       ";

    private static int currentIndex = 0;
    private static Task[] taskList;

    public static void main(String[] args) {
        System.out.println(logo);

        System.out.println();
        System.out.println(bottySymbol + "Hello, I am Botty the Bot, how may I be of service today?");

        Scanner inputScanner = new Scanner(System.in);

        taskList = new Task[100];

        boolean exitFlag = false;

        while (!exitFlag) {
            System.out.println();

            String userInput = inputScanner.nextLine();

            String[] splitInput = userInput.split(" ");
            String command = splitInput[0];

            boolean hasIntegerArgument = splitInput.length > 1 && isNumber(splitInput[1]);

            switch (command) {
                case "bye":
                    exitFlag = true;
                    break;
                case "list":
                    System.out.println(bottySymbol + "Here you go!");
                    for (int i = 1; i < currentIndex + 1; i++) {
                        System.out.println(bottyIndentation + "  " + i + ". " + taskList[i - 1]);
                    }
                    break;
                case "mark":
                    if (hasIntegerArgument) {
                        setTaskCompletion(true, Integer.parseInt(splitInput[1]) - 1);
                    } else {
                        System.out.println(bottySymbol +
                                "I don't quite know what you want me to do. " +
                                "Do indicate which task to mark with its number!");
                    }
                    break;
                case "unmark":
                    if (hasIntegerArgument) {
                        setTaskCompletion(false, Integer.parseInt(splitInput[1]) - 1);
                    } else {
                        System.out.println(bottySymbol +
                                "I don't quite know what you want me to do. " +
                                "Do indicate which task to unmark with its number!");
                    }
                    break;
                default:
                    if (currentIndex < taskList.length) {
                        taskList[currentIndex] = new Task(userInput);
                        currentIndex++;
                        System.out.println(bottySymbol + "I have added \"" + userInput + "\" to the list!");
                    } else {
                        System.out.println(bottySymbol +
                                "I have run out of space, sorry! Here's a cookie \uD83C\uDF6A");
                    }
            }

        }

        inputScanner.close();
        System.out.println(bottySymbol + "Thank you for your continued patronage. Goodbye!");
    }

    private static boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch(NumberFormatException ex) {
            return false;
        }
    }
    private static void setTaskCompletion(boolean completion, int taskIndex) {
        if (taskIndex >= 0 && taskIndex <= currentIndex - 1) {
            System.out.println(bottySymbol +
                    (completion
                            ? "Congrats on completing that! Let me just mark that as done for you."
                            : "It's okay, we can get that done later. I'll mark that as undone for you."));
            taskList[taskIndex].setCompleted(completion);
            System.out.println(bottyIndentation + "  " + taskList[taskIndex]);
        } else {
            System.out.println(bottySymbol + "I don't see a task with that number! Try a number from 1 to " +
                    currentIndex);
        }
    }
}
