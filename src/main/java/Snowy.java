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

    private static String[] tasks = new String[100];

    private static int numOfTasks;

    private static boolean isRunning = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(GREETING);
        while (isRunning) {
            lastCommand = scanner.nextLine();

            switch (lastCommand.toLowerCase()) {
                case "bye":
                    isRunning = false;
                    break;

                case "list":
                    for (int i = 0; i < numOfTasks; i++) {
                        System.out.printf("%d. %s\n", i + 1, tasks[i]);
                    }
                    break;

                default:
                    tasks[numOfTasks] = lastCommand;
                    numOfTasks++;
                    System.out.println("added: " + lastCommand);
                    break;
            }

            System.out.print(LINE);

        }


        System.out.print(ENDING);
    }
}
