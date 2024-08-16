import java.util.Scanner;
import java.util.ArrayList;

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

        ArrayList<Task> taskList = new ArrayList<Task>();

        while (isChatting) {
            System.out.println(">>>");
            command = SCANNER.next();
            System.out.println("<<<");

            switch (command) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(String.format("%d. %s", i + 1, taskList.get(i).toString()));
                    }
                    System.out.println(LONG_LINE);
                    break;
                case "mark":
                    String markStr = SCANNER.nextLine().trim();
                    if (!markStr.matches("\\d+")) {
                        System.out.println("Invalid task number: Please add in a number from 1 to " + taskList.size());
                        System.out.println(markStr);
                        break;
                    }

                    int markIndex = Integer.parseInt(markStr) - 1;

                    if (markIndex >= taskList.size() || markIndex < 0) {
                        System.out.println(
                                "Task number out of range. Please choose a number from 1 to " + taskList.size());
                        break;
                    }

                    taskList.get(markIndex).mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskList.get(markIndex).toString());

                    SCANNER.nextLine();
                    break;
                case "unmark":
                    String unmarkStr = SCANNER.nextLine().trim();
                    if (!unmarkStr.matches("\\d+")) {
                        System.out.println("Invalid task number: Please add in a number from 1 to " + taskList.size());
                        break;
                    }

                    int unmarkIndex = Integer.parseInt(unmarkStr) - 1;

                    if (unmarkIndex >= taskList.size() || unmarkIndex < 0) {
                        System.out.println(
                                "Task number out of range. Please choose a number from 1 to " + taskList.size());
                        break;
                    }

                    taskList.get(unmarkIndex).unmark();
                    System.out.println("OK, I've unmarked this task as not done yet:");
                    System.out.println(taskList.get(unmarkIndex).toString());
                    break;
                case "todo":
                    taskDescription = SCANNER.nextLine().trim();
                    if (taskDescription.equals("")) {
                        System.out.println("Empty task description: Please add in a task description");
                        System.out.println(LONG_LINE);
                        break;
                    }

                    taskList.add(new Todo(taskDescription));

                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                    System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
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
                    if (deadlineDescription.equals("")) {
                        System.out.println("Empty task description: Please add in a description");
                        System.out.println(LONG_LINE);
                        break;
                    }

                    deadlineBy = SCANNER.nextLine().trim();
                    if (deadlineBy.equals("")) {
                        System.out.println("Empty deadline: Please add in a deadline");
                        System.out.println(LONG_LINE);
                        break;
                    }

                    taskList.add(new Deadline(deadlineDescription, deadlineBy));

                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                    System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
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
                    if (eventDescription.equals("")) {
                        System.out.println("Empty task description: Please add in a time");
                        System.out.println(LONG_LINE);
                        break;
                    }

                    eventAtStr = eventAtStr.trim();
                    if (eventAtStr.equals("")) {
                        System.out.println("Empty /from time: Please add in a time");
                        System.out.println(LONG_LINE);
                        break;
                    }

                    eventToStr = eventToStr.trim();
                    if (eventToStr.equals("")) {
                        System.out.println("Empty /to time: Please add in a time");
                        System.out.println(LONG_LINE);
                        break;
                    }

                    taskList.add(new Event(eventDescription, eventAtStr, eventToStr));

                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                    System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
                    System.out.println(LONG_LINE);

                    break;
                case "delete":
                    String deleteStr = SCANNER.nextLine().trim();
                    if (!deleteStr.matches("\\d+")) {
                        System.out.println("Invalid task number: Please add in a number from 1 to " + taskList.size());
                        break;
                    }

                    int deleteIndex = Integer.parseInt(deleteStr) - 1;

                    if (deleteIndex >= taskList.size() || deleteIndex < 0) {
                        System.out.println(
                                "Task number out of range. Please choose a number from 1 to " + taskList.size());
                        break;
                    }

                    Task deletedTask = taskList.remove(deleteIndex);

                    System.out.println("Noted. I've removed this task:");
                    System.out.println(deletedTask.toString());
                    System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));

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
