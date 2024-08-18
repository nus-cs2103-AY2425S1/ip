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

    public static String formatTaskListing(int i, String task) {
        return KHEE_BOT.SPACING + " " + i + ". " + task + "\n";
    }

    public static String formatTaskAddition(String input) {
        return (
            KHEE_BOT.SPACING + KHEE_BOT.BORDER +
            KHEE_BOT.SPACING + " " + "added: " +  input + "\n" +
            KHEE_BOT.SPACING + KHEE_BOT.BORDER
        );
    }

    public String[] tasks;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KHEE_BOT kheeBot = new KHEE_BOT();
        kheeBot.tasks = new String[100];
        int taskCounter = 0;

        System.out.println(KHEE_BOT.GREETING);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                System.out.print(KHEE_BOT.SPACING + KHEE_BOT.BORDER);
                for (int i = 0; i < taskCounter; i++) {
                    System.out.print(KHEE_BOT.formatTaskListing(i + 1, kheeBot.tasks[i]));
                }
                System.out.print(KHEE_BOT.SPACING + KHEE_BOT.BORDER);
            } else {
                kheeBot.tasks[taskCounter++] = userInput;

                String formattedTask = KHEE_BOT.formatTaskAddition(userInput);
                System.out.println(formattedTask);
            }
        }

        System.out.println(KHEE_BOT.GOODBYE);
    }
}
