import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int taskCounter = 0;
        Task[] tasks = new Task[100];

        System.out.println("\t------------------------------------------");
        System.out.println("\tHello! I'm Bob!\n\tHow can I help you today?");
        System.out.println("\t------------------------------------------");

        while (!input.equals("bye")) {
            input = scanner.nextLine().trim(); // trim to remove any whitespace before input
            String[] inputParts = input.split(" ", 2); // split input and store into array
            String command = inputParts[0].toLowerCase(); // lowercase for comparison
            String taskDescription = (inputParts.length <= 1) ? "" : inputParts[1];

            switch (command) {
                case "list":
                    // print tasks
                    System.out.println("\t------------------------------------------");
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < taskCounter; i++) {
                        int j = i + 1;
                        System.out.println("\t" + j + ". " + tasks[i].toString());
                    }
                    System.out.println("\t------------------------------------------");
                    break;

                case "mark":
                    int taskIndexMark = Integer.parseInt(taskDescription) - 1;
                    if (taskIndexMark < taskCounter && taskIndexMark >= 0) {
                        tasks[taskIndexMark].mark();
                        System.out.println("\t------------------------------------------");
                        System.out.println("\tNice! I've marked this task as done:");
                        System.out.println("\t\t[" + tasks[taskIndexMark].getStatusIcon() + "] " + tasks[taskIndexMark].getDescription());
                        System.out.println("\t------------------------------------------");
                    } else {
                        System.out.println("\tInvalid index..");
                    }
                    break;

                case "unmark":
                    int taskIndexUnmark = Integer.parseInt(taskDescription) - 1;
                    if (taskIndexUnmark < taskCounter && taskIndexUnmark >= 0) {
                        tasks[taskIndexUnmark].unmark();
                        System.out.println("\t------------------------------------------");
                        System.out.println("\tOk, I've marked this task as not done yet:");
                        System.out.println("\t\t[" + tasks[taskIndexUnmark].getStatusIcon() + "] " + tasks[taskIndexUnmark].getDescription());
                        System.out.println("\t------------------------------------------");
                    } else {
                        System.out.println("\tInvalid index..");
                    }
                    break;

                case "todo":
                    tasks[taskCounter] = new Todo(taskDescription);
                    taskCounter++;
                    System.out.println("\t------------------------------------------");
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t\t" + tasks[taskCounter - 1].toString());
                    System.out.println("\tNow you have " + taskCounter + " task(s) in the list.");
                    System.out.println("\t------------------------------------------");
                    break;

                case "deadline":
                    String[] dlParts = taskDescription.split(" /by ");
                    tasks[taskCounter] = new Deadline(dlParts[0], dlParts[1]);
                    taskCounter++;
                    System.out.println("\t------------------------------------------");
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t\t" + tasks[taskCounter - 1].toString());
                    System.out.println("\tNow you have " + taskCounter + " task(s) in the list.");
                    System.out.println("\t------------------------------------------");
                    break;

                case "event":
                    String[] eventParts = taskDescription.split(" /from | /to ");
                    tasks[taskCounter] = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    taskCounter++;
                    System.out.println("\t------------------------------------------");
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t\t" + tasks[taskCounter - 1].toString());
                    System.out.println("\tNow you have " + taskCounter + " task(s) in the list.");
                    System.out.println("\t------------------------------------------");
                    break;

                case "bye":
                    // exit
                    System.out.println("\t------------------------------------------");
                    System.out.println("\tBye, see you again :)");
                    System.out.println("\t------------------------------------------");
                    scanner.close();
                    return;

                default:
                    // add task to tasks array
                    tasks[taskCounter] = new Task(input);
                    taskCounter++;

                    System.out.println("\t------------------------------------------");
                    System.out.println("\tadded: " + input);
                    System.out.println("\t------------------------------------------");
                    break;
            }
        }
    }
}


