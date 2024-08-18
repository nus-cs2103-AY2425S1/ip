import java.util.Scanner;

public class Infinity {

    public static final String BOTNAME = "Infinity";
    public static final String BREAKLINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public static String botReply(String input) {
        return String.format("%s: %s", BOTNAME, input);
    }

    public static void main(String[] args) {
        Scanner userInputs = new Scanner(System.in);

        System.out.println(BREAKLINE);
        System.out.println(botReply(String.format("Hello, I'm a dummy bot called %s", BOTNAME)));
        System.out.println(botReply("What can I not do for you?"));
        System.out.println(BREAKLINE);

        while (true) {
            String currentInput = userInputs.nextLine();
            System.out.println(BREAKLINE);
            if (currentInput.equals("bye")) {
                System.out.println(botReply("Well, if you are leaving, then I must be infinitely too dumb :("));
                System.out.println(BREAKLINE);
                userInputs.close();
                System.exit(0);
            } else {
                System.out.println(botReply(currentInput));
                System.out.println(BREAKLINE);
            }
        }
    }

}
