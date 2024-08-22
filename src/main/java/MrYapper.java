import java.util.Scanner;

public class MrYapper {
    private static void say(String message) {
        System.out.println("____________________________________________________________\n"
                + message
                + "\n____________________________________________________________");
    }

    public static void main(String[] args) {
        String greeting = " Hello! I'm MrYapper\n"
                + " What can I do for you?";
        String goodbye = " Bye. Hope to see you again soon!";
        say(greeting);
        boolean sayGoodbye = false;
        Scanner userInputReader = new Scanner(System.in);

        while (!sayGoodbye) {
            String userInput = userInputReader.nextLine();
            if (userInput.equals("bye")) {
                sayGoodbye = true;
                userInputReader.close();
                say(goodbye);
            } else {
                say(" " + userInput);
            }
        }
    }
}
