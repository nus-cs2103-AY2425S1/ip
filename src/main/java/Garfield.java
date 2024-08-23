import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Garfield {

    private static ArrayList<String> taskList = new ArrayList<>();
    public static void main(String[] args) {
        String logo = """
                 ██████╗  █████╗ ██████╗ ███████╗██╗███████╗██╗     ██████╗
                ██╔════╝ ██╔══██╗██╔══██╗██╔════╝██║██╔════╝██║     ██╔══██╗
                ██║  ███╗███████║██████╔╝█████╗  ██║█████╗  ██║     ██║  ██║
                ██║   ██║██╔══██║██╔══██╗██╔══╝  ██║██╔══╝  ██║     ██║  ██║
                ╚██████╔╝██║  ██║██║  ██║██║     ██║███████╗███████╗██████╔╝
                 ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝╚══════╝╚═════╝
                """;

        String initialGreeting = "Hey. I'm\n\n" + logo
                + "\nLet's get this over with. What do you want?";
        Garfield.speak(initialGreeting);

        // Loop to get user input
        Scanner inputScanner = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = inputScanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                if (taskList.isEmpty()) {
                    Garfield.speak("Your list is empty. Just like my lasagna pan. "
                            + "Are we done here, or are you going to add something?");
                } else {
                    StringBuilder listSummary = new StringBuilder("Ugh, here's what you've got so far:\n\n");
                    for (int i = 0; i < taskList.size(); i++) {
                        listSummary.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");

                    }
                    listSummary.append("\nCan we be done now?");
                    Garfield.speak(listSummary.toString());
                }
                continue;
            }

            Garfield.speak("Fine. I'll add '" + userInput + "' to the list.");
            taskList.add(userInput);

        }

        Garfield.speak("Finally. Try not to come back too soon.");
    }

    private static void speak(String message) {
        Garfield.line(70);
        System.out.println(message);
        Garfield.line(70);
    }

    private static void line(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }
}