import java.util.Scanner;

public class Snowy {


    private static final String LINE = "-----------------------------------\n";
    private static final String GREETING = LINE
            + "Hello! My name is Snowy \n"
            + "What can I do for you? \n"
            + LINE;

    private static final String ENDING = "Bye! Hope to see you again soon! \n"
            + LINE;

    private static Task[] tasks = new Task[100];

    private static int numOfTasks;

    private static boolean isRunning = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(GREETING);
        while (isRunning) {
            String lastInput = scanner.nextLine();

            String[] words = lastInput.split(" ");

            if (words.length == 0) {
                continue;
            }

            String lastCommand = words[0];

            switch (lastCommand.toLowerCase()) {
                case "bye":
                    isRunning = false;
                    break;

                case "list":
                    for (int i = 0; i < numOfTasks; i++) {
                        System.out.printf("%d. %s\n", i + 1, tasks[i]);
                    }
                    break;

                case "mark":
                    try {
                        int index = Integer.parseInt(words[1]);
                        tasks[index - 1].markComplete();
                        System.out.println("Nice! I've marked this task as done:\n  "
                                + tasks[index - 1].toString());
                    }catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid index provided. Please try again");
                    }
                    break;

                case "unmark":
                    try {
                        int index = Integer.parseInt(words[1]);
                        tasks[index - 1].markIncomplete();
                        System.out.println("Ok, I've marked this task as not done yet:\n  "
                                + tasks[index - 1].toString());
                    }catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid index provided. Please try again");
                    }
                    break;
                default:
                    tasks[numOfTasks] = new Task(lastInput);
                    numOfTasks++;
                    System.out.println("added: " + lastInput);
                    break;
            }

            System.out.print(LINE);

        }


        System.out.print(ENDING);
    }
}
