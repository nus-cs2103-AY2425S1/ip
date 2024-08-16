import java.util.Scanner;

public class Barney {
    private static String LONG_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        // Welcome text
        String welcomeText = "Hello, I am Barney <RAWR>, what can I do for you?";
        System.out.println(welcomeText);
        System.out.println(LONG_LINE);

        Scanner SCANNER = new Scanner(System.in);
        String command;

        String taskDescription;

        Boolean isChatting = true;

        Task[] taskList = new Task[100];
        int listLength = 0;

        while (isChatting) {
            System.out.println(">>>");
            command = SCANNER.next();
            System.out.println("<<<");

            switch (command) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listLength; i++) {
                        System.out.println(String.format("%d. %s", i + 1, taskList[i].toString()));
                    }
                    System.out.println(LONG_LINE);
                    break;
                case "mark":
                    String markStr = SCANNER.next();
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

                    SCANNER.nextLine();
                    break;
                case "unmark":
                    String unmarkStr = SCANNER.next();
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

                    SCANNER.nextLine();
                    break;
                case "todo":
                    taskDescription = SCANNER.nextLine().trim();
                    taskList[listLength] = new Todo(taskDescription);
                    listLength++;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList[listLength - 1].toString());
                    System.out.println(String.format("Now you have %d tasks in the list.", listLength));
                    System.out.println(LONG_LINE);
                    break;
                case "deadline":
                    String deadlineDescription = "";
                    String deadlineBy = "";
                    String upcoming = SCANNER.next();
                    while (!upcoming.equals("/by")) {
                        deadlineDescription += " " + upcoming;
                        upcoming = SCANNER.next();
                    }

                    deadlineDescription = deadlineDescription.trim();
                    deadlineBy = SCANNER.nextLine().trim();

                    taskList[listLength] = new Deadline(deadlineDescription, deadlineBy);
                    listLength++;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList[listLength - 1].toString());
                    System.out.println(String.format("Now you have %d tasks in the list.", listLength));
                    System.out.println(LONG_LINE);
                    break;
                case "event":
                    String eventDescription = "";
                    String eventAtStr = "";
                    String n = SCANNER.next();
                    while (!n.equals("/from")) {
                        eventDescription += " " + n;
                        n = SCANNER.next();
                    }
                    n = SCANNER.next();
                    while (!n.equals("/to")) {
                        eventAtStr += " " + n;
                        n = SCANNER.next();
                    }
                    String eventToStr = SCANNER.nextLine();

                    eventDescription = eventDescription.trim();
                    eventAtStr = eventAtStr.trim();
                    eventToStr = eventToStr.trim();

                    taskList[listLength] = new Event(eventDescription, eventAtStr, eventToStr);
                    listLength++;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList[listLength - 1].toString());
                    System.out.println(String.format("Now you have %d tasks in the list.", listLength));
                    System.out.println(LONG_LINE);

                    break;
                case "bye":
                    isChatting = false;
                    break;
                default:
                    System.out.println("invalid command");
            }
        }

        // Ending text
        String endingText = "Goodbye, I am Barney <RAWR>, see you next time!";
        System.out.println(endingText);
        System.out.println(LONG_LINE);

        SCANNER.close();
    }
}
