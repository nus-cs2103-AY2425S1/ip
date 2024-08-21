import java.util.Scanner;

public class Botty {
    public static void main(String[] args) {
        String logo = " ________  ________  _________  _________    ___    ___                 \n" +
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

        String bottySymbol = "|┐∵|┘: ";
        String bottyIndentation = "       ";

        System.out.println(logo);

        System.out.println();
        System.out.println(bottySymbol + "Hello, I am Botty the Bot, how may I be of service today?");

        Scanner inputScanner = new Scanner(System.in);

        String[] dataList = new String[100];
        int currentIndex = 0;

        boolean exitFlag = false;

        while (!exitFlag) {
            System.out.println();

            String userInput = inputScanner.nextLine();

            switch (userInput) {
                case "bye":
                    exitFlag = true;
                    break;
                case "list":
                    System.out.println(bottySymbol + "Here you go!");
                    for (int i = 1; i < currentIndex + 1; i++) {
                        System.out.println(bottyIndentation + "  " + i + ". " + dataList[i - 1]);
                    }
                    break;
                default:
                    if (currentIndex < dataList.length) {
                        dataList[currentIndex] = userInput;
                        currentIndex++;
                        System.out.println(bottySymbol + "I have added \"" + userInput + "\" to the list!");
                    } else {
                        System.out.println(bottySymbol + "I have run out of space, sorry! Here's a cookie \uD83C\uDF6A");
                    }
            }

        }

        inputScanner.close();
        System.out.println(bottySymbol + "Thank you for your continued patronage. Goodbye!");
    }
}
