import java.util.Scanner;

import java.util.NoSuchElementException;

public class YihuiBot {
    private static final String NAME = "YihuiBot";

    public static void main(String[] args) {
        greetings();

        Scanner userInput = new Scanner(System.in);
        try {
            while (true) {
                String input = userInput.nextLine();
                if (input.equals("bye")) {
                    break;
                }
                callSuitableFunction(input);
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("An error has occured.");
        }

        exit();
    }

    private static void callSuitableFunction(String input) {
        switch (input) {
        case "bye":
            break;
        default:
            echo(input);
        }
    }

    private static String wrapStringWithHorizontalLines(String s) {
        String horizontalLine = "-----------------------------------------------------";
        return horizontalLine + "\n" + s + "\n" + horizontalLine;
    }

    private static void greetings() {
        String s = String.format(
            "Hello! I am %s!\nWhat can I do for you?",
            NAME
        );
        String wrapped = wrapStringWithHorizontalLines(s);
        System.out.println(wrapped);
    }

    private static void exit() {
        String s = "Bye. Hope to see you again soon!";
        String wrapped = wrapStringWithHorizontalLines(s);
        System.out.println(wrapped);
    }

    private static void echo(String input) {
        String wrapped = wrapStringWithHorizontalLines(input);
        System.out.println(wrapped);
    }
}
