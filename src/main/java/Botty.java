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

        System.out.println(logo);

        System.out.println();
        System.out.println(bottySymbol + "Hello, I am Botty the Bot, how may I be of service today?");

        System.out.println();

        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            String userInput = inputScanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }

            System.out.println(bottySymbol + userInput);
            System.out.println();
        }

        inputScanner.close();
        System.out.println(bottySymbol + "Thank you for your continued patronage. Goodbye!");
    }
}
