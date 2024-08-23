import java.util.Scanner;

public class FlyChat {
    private static final String lineBreak = "\n--------------------\n";
    public static void main(String[] args) {
        start();
        echo();
        end();
    }

    private static void start() {
        System.out.println("--------------------\n");
        System.out.println("Hello! I'm FlyChat\nWhat can I do for you?");
        System.out.println(lineBreak);
    }

    private static void end() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }

    private static void echo() {
        Scanner userInput = new Scanner(System.in);
        String inputString = userInput.nextLine();
        while (!inputString.equals("bye")) {
            System.out.println(lineBreak);
            System.out.println(inputString);
            System.out.println(lineBreak);
            inputString = userInput.nextLine();
        }
        userInput.close();
        System.out.println(lineBreak);
    }

}
