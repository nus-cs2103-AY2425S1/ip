import java.util.Scanner;

public class Sigma {
    public static void main(String[] args) {
        String name = "SIGMA";
        Scanner input = new Scanner(System.in);
        print("Hello! I'm " + name + "\nLooking forward to slaying with you!\nWhat do you need today?");
        String userPrompt = input.nextLine();
        while (!userPrompt.toLowerCase().equals("bye")) {
            print(userPrompt);
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
