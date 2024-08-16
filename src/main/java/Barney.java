import java.util.Scanner;

public class Barney {
    private static String LONG_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        // Welcome text
        String welcomeText = "Hello, I am Barney <RAWR>, what can I do for you?";
        System.out.println(welcomeText);
        System.out.println(LONG_LINE);

        Scanner SCANNER = new Scanner(System.in);
        String input;

        Boolean isChatting = true;

        Task[] taskList = new Task[100];
        int listLength = 0;

        while (isChatting) {
            System.out.println(">>>");
            input = SCANNER.nextLine().trim();
            System.out.println("<<<");

            String[] inputArray = input.split(" ");
            String keyword = inputArray[0];

            switch (keyword) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listLength; i++) {
                        System.out.println(String.format("%d. %s", i + 1, taskList[i].toString()));
                    }
                    break;
                case "mark":
                    String markStr = inputArray[1];
                    if (!markStr.matches("\\d+")) {
                        System.out.println("Invalid task number");
                        break;
                    }

                    int markIndex = Integer.parseInt(markStr) - 1;

                    if (markIndex >= listLength || markIndex < 0) {
                        System.out.println("Invalid task number");
                        break;
                    }

                    taskList[markIndex].mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskList[markIndex].toString());
                    break;
                case "unmark":
                    String unmarkStr = inputArray[1];
                    if (!unmarkStr.matches("\\d+")) {
                        System.out.println("Invalid task number");
                        break;
                    }

                    int unmarkIndex = Integer.parseInt(unmarkStr) - 1;
                    if (unmarkIndex >= listLength || unmarkIndex < 0) {
                        System.out.println("Invalid task number");
                        break;
                    }

                    taskList[unmarkIndex].unmark();
                    System.out.println("OK, I've unmarked this task as not done yet:");
                    System.out.println(taskList[unmarkIndex].toString());
                    break;
                case "bye":
                    isChatting = false;
                    break;
                default:
                    taskList[listLength] = new Task(input);
                    listLength++;
                    System.out.println("added: " + input);
                    System.out.println(LONG_LINE);

            }
        }

        // Ending text
        String endingText = "Goodbye, I am Barney <RAWR>, see you next time!";
        System.out.println(endingText);
        System.out.println(LONG_LINE);

        SCANNER.close();
    }
}
