import java.util.Scanner;

public class Buddy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int tracker = 0;
        System.out.println("    ____________________________________");
        System.out.println("    Hey there! I'm Buddy\n    What do ya need help with?");
        System.out.println("    ____________________________________\n");

        while (true) {
            String userInput = scanner.nextLine().trim();

            System.out.println("\n    ____________________________________");

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("    Bye! See ya soon!");
                System.out.println("    ____________________________________");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                if (tracker == 0) {
                    System.out.println("    List is empty!!");
                } else {
                    System.out.println("    Here are the tasks in your list: ");
                    for (int i = 0; i < tracker; i++) {
                        System.out.printf("    %d. [%s][%s] %s%n", i + 1, list[i].getTaskType(), list[i].getStatusIcon(), list[i].description);
                    }
                }


            } else if (userInput.startsWith("mark ") && userInput.matches("mark \\d+$")) {
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;

                if (taskIndex >= 0 && taskIndex < tracker) {
                    System.out.println("    Nice one buddy! Marked this as done...");
                    list[taskIndex].markAsDone();
                    System.out.printf("    [%s][%s] %s%n", list[taskIndex].getTaskType(), list[taskIndex].getStatusIcon(), list[taskIndex].description);
                } else {
                    System.out.println("    I don't think task is on your list buddy...");
                }
            } else if (userInput.startsWith("unmark ") && userInput.matches("unmark \\d+$")) {
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;

                if (taskIndex >= 0 && taskIndex < tracker) {
                    System.out.println("    Alright buddy, let's give that task another shot!");
                    list[taskIndex].markAsUndone();
                    System.out.printf("    [%s][%s] %s%n", list[taskIndex].getTaskType(), list[taskIndex].getStatusIcon(), list[taskIndex].description);
                } else {
                    System.out.println("I don't think task is on your list buddy...");
                }

            } else if (userInput.isBlank()) {
                System.out.println("    Not too sure about that, buddy");
            } else if (userInput.startsWith("todo ")) {
                String taskDesc = userInput.substring(5);
                Task t = new ToDos(taskDesc);
                list[tracker] = t;
                tracker++;

                System.out.println("    Gotcha! I've added this task: ");
                System.out.printf("     [%s][%s] %s%n", t.getTaskType(), t.getStatusIcon(), t.description);
                System.out.printf("    Now, you have %d tasks in the list!", tracker);

            } else if (userInput.startsWith("deadline ")) {
                String taskDesc = userInput.substring(9);
                String[] parts = taskDesc.split("/by ", 2);

                if (parts.length == 2) {
                    String desc = parts[0].trim();
                    String day = parts[1].trim();


                    Task t = new Deadlines(desc, day);
                    list[tracker] = t;
                    tracker++;

                    System.out.println("    Gotcha! I've added this task: ");
                    System.out.printf("     [%s][%s] %s%n", t.getTaskType(), t.getStatusIcon(), t.description);
                    System.out.printf("    Now, you have %d tasks in the list!", tracker);
                } else {
                    System.out.println("    When do ya need to get it done by? (include '/by' followed by the deadline");
                }
            } else if (userInput.startsWith("event ")) {
                String taskDesc = userInput.substring(6);
                String[] parts = userInput.split("/from ", 2);

                if (parts.length == 2) {
                    String task = parts[0].trim(); // "project meeting"
                    String dateTimeAndEnd = parts[1].trim(); // "Mon 2pm /to 4pm"

                    // Split the second part using the "/to " delimiter
                    String[] dateTimeAndEndParts = dateTimeAndEnd.split("/to ", 2);

                    if (dateTimeAndEndParts.length == 2) {
                        String dateTime = dateTimeAndEndParts[0].trim(); // "Mon 2pm"
                        String endTime = dateTimeAndEndParts[1].trim(); // "4pm"

                        Task t = new Events(task, dateTime, endTime);
                        list[tracker] = t;
                        tracker++;

                        System.out.println("    Gotcha! I've added this task: ");
                        System.out.printf("     [%s][%s] %s%n", t.getTaskType(), t.getStatusIcon(), t.description);
                        System.out.printf("    Now, you have %d tasks in the list!", tracker);
                    } else {
                        System.out.println("    there's no end date?");
                    }
                } else {
                    System.out.println("    there's no start date?");
                }

            } else {
                System.out.println("    you can add tasks by starting these commands: \n    todo\n    deadline\n    event");
            }
            System.out.println("\n    ____________________________________\n");
        }

        scanner.close();
    }

}

