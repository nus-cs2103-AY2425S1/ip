import java.util.ArrayList;
import java.util.Scanner;

public class Sigma {
    public static void main(String[] args) {
        String name = "SIGMA";
        Scanner input = new Scanner(System.in);
        ArrayList<String> items = new ArrayList<>();

        print("Hello! I'm " + name + "\nLooking forward to slaying with you!\nWhat do you need today?");
        String userPrompt = input.nextLine();
        while (!userPrompt.isEmpty()) {
            switch (userPrompt) {
                case "bye":
                    exit();
                    System.exit(0);
                    break;
                case "list":
                    StringBuilder s = new StringBuilder();
                    int i = 1;
                    for (String item : items) {
                        s.append(i + ". " + item + "\n");
                        i++;
                    }
                    print(s.toString());
                    break;
                default:
                    items.add(userPrompt);
                    print("Added: " + userPrompt);
                    break;
            }

            userPrompt = input.nextLine();
        }
        exit();
    }

    private static void exit() {
        print("What the sigma? You're leaving so soon? Bye chat, see you again!");
    }

    private static void print(String message) {
        line();
        System.out.println(message);
        line();
    }
    private static void line() {
        String line = "----------------------------------------------------------------------------------------------";
        System.out.println(line);
    }
}
