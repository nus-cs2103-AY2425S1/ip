import java.util.Scanner;

public class KHEE_BOT {
    public static final String BOT_NAME = "KHEE_BOT";
    public static final String SPACING = "    ";
    public static final String BORDER = "____________________________________________________________\n";
    public static final String GREETING =
            KHEE_BOT.SPACING + KHEE_BOT.BORDER +
            KHEE_BOT.SPACING + " Hello! I'm " + KHEE_BOT.BOT_NAME + ", a talking keyboard!" + "\n" +
            KHEE_BOT.SPACING + " What can I do for you?\n";
    public static final String GOODBYE =
            KHEE_BOT.SPACING + KHEE_BOT.BORDER +
            KHEE_BOT.SPACING + " Bye. Hope to see you again soon!\n" +
            KHEE_BOT.SPACING + KHEE_BOT.BORDER;

    public static String formatOutput(String output) {
        return (
                KHEE_BOT.SPACING + KHEE_BOT.BORDER +
                KHEE_BOT.SPACING + " " + output + "\n" +
                KHEE_BOT.SPACING + KHEE_BOT.BORDER
        );
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(KHEE_BOT.GREETING);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            }

            System.out.println(KHEE_BOT.formatOutput(userInput));
        }

        System.out.println(KHEE_BOT.GOODBYE);
    }
}
