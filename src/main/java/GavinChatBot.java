import java.util.Scanner;

public class GavinChatBot{
    // array to hold Task objects
    static Task[] tasks = new Task[100];
    static int taskCount = 0;
    public static void main(String[] args) {

        String horizontalLine = "_________________________________\n";

        // print welcome message
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Gavin\n");
        System.out.println("What can I do for you?\n");
        System.out.println(horizontalLine);


        // create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        String input;

        // echo input by user until user types bye
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                // print exit message
                System.out.println(horizontalLine);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                // print out list of items
                System.out.println(horizontalLine);
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    System.out.println(i+1 + ". " + "[" + tasks[i].getStatusIcon() + "]" + " " + tasks[i].description);
                }
                System.out.println(horizontalLine);
                continue;
            } else if (input.startsWith("mark")) {
                // mark task as done
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println(horizontalLine);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + tasks[taskNumber].getStatusIcon() + "] " + tasks[taskNumber].description);
                System.out.println(horizontalLine);
                continue;
            } else if (input.startsWith("unmark")) {
                // mark task as undone
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNumber].markAsNotDone();
                System.out.println(horizontalLine);
                System.out.println("OK, I've marked this task as not done yet!");
                System.out.println("[" + tasks[taskNumber].getStatusIcon() + "]" + " " + tasks[taskNumber].description);
                System.out.println(horizontalLine);
                continue;
            } else if (input.startsWith("todo")) {
                String[] inputParts = input.split(" ", 2);
                String taskDescription = inputParts[1];
                tasks[taskCount] = new ToDos(taskDescription);
                taskCount++;
                printAddTaskMessage(tasks[taskCount - 1], taskCount);
                continue;
            } else if (input.startsWith("deadline")) {
                String[] inputParts = input.split("/by", 2); // ["deadline return book" , "Sunday"]
                String taskDescription = inputParts[0].substring(9); // skip first 9 chars, which is "deadline "
                String deadlineDay = inputParts[1];
                tasks[taskCount] = new Deadline(taskDescription, deadlineDay);
                taskCount++;
                printAddTaskMessage(tasks[taskCount - 1], taskCount);
                continue;
            } else if (input.startsWith("event")) {
                String[] inputParts = input.split("/from", 2); //["event project meeting" , "Mon 2pm /to 4pm"]
                String taskDescription = inputParts[0].substring(6);
                String[] timeParts = inputParts[1].split("/to", 2); //["Mon 2pm" , "4pm"]
                String fromTime = timeParts[0];
                String toTime = timeParts[1];
                tasks[taskCount] = new Event(taskDescription, fromTime, toTime);
                taskCount++;
                printAddTaskMessage(tasks[taskCount - 1], taskCount);
                continue;
            }

            System.out.println(horizontalLine);
            tasks[taskCount] = new Task(input);
            taskCount++;
            System.out.println("added: " + input);
            System.out.println(horizontalLine);
        }
    }
    private static void printAddTaskMessage(Task task, int taskCount) {
        String horizontalLine = "_________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(horizontalLine);

    }
}
