import java.util.Scanner;

public class Snowy {


    private static final String LINE = "-----------------------------------\n";
    private static final String GREETING = LINE
            + "Hello! My name is Snowy \n"
            + "What can I do for you? \n"
            + LINE;

    private static final String ENDING = "Bye! Hope to see you again soon! \n"
            + LINE;

    private static String lastCommand;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(GREETING);
        while (true) {
            lastCommand = scanner.nextLine();
            if (lastCommand.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println(lastCommand);
            System.out.print(LINE);

        }


        System.out.print(ENDING);
    }
}
