import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int taskCounter = 0;
        Task[] tasks = new Task[100];

        System.out.println("------------------------------------------");
        System.out.println("Hello! I'm Bob!\nHow can I help you today?");
        System.out.println("------------------------------------------");

        while (!input.equals("bye")) {
            input = scanner.nextLine().trim(); // trim to remove any whitespace before input
            String[] inputParts = input.split(" ", 2); // split input and store into array
            String command = inputParts[0].toLowerCase(); // lowercase for comparison
            String taskDescription = (inputParts.length <= 1) ? "" : inputParts[1];

            switch (command) {
                case "list":
                    // print tasks
                    System.out.println("------------------------------------------");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCounter; i++) {
                        int j = i + 1;
                        System.out.println(j + ". " + tasks[i].toString());
                    }
                    System.out.println("------------------------------------------");
                    break;

                case "mark":
                    int taskIndexMark = Integer.parseInt(taskDescription) - 1;
                    if (taskIndexMark < taskCounter && taskIndexMark >= 0) {
                        tasks[taskIndexMark].mark();
                        System.out.println("------------------------------------------");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("[" + tasks[taskIndexMark].getStatusIcon() + "] " + tasks[taskIndexMark].getDescription());
                        System.out.println("------------------------------------------");
                    } else {
                        System.out.println("Invalid index..");
                    }
                    break;

                case "unmark":
                    int taskIndexUnmark = Integer.parseInt(taskDescription) - 1;
                    if (taskIndexUnmark < taskCounter && taskIndexUnmark >= 0) {
                        tasks[taskIndexUnmark].unmark();
                        System.out.println("------------------------------------------");
                        System.out.println("Ok, I've marked this task as not done yet:");
                        System.out.println("[" + tasks[taskIndexUnmark].getStatusIcon() + "] " + tasks[taskIndexUnmark].getDescription());
                        System.out.println("------------------------------------------");
                    } else {
                        System.out.println("Invalid index..");
                    }
                    break;

                case "todo":
                    tasks[taskCounter] = new Todo(taskDescription);
                    taskCounter++;
                    System.out.println("------------------------------------------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCounter - 1].toString());
                    System.out.println("Now you have " + taskCounter + " task(s) in the list.");
                    System.out.println("------------------------------------------");
                    break;

                case "deadline":
                    String[] dlParts = taskDescription.split(" /by ");
                    tasks[taskCounter] = new Deadline(dlParts[0], dlParts[1]);
                    taskCounter++;
                    System.out.println("------------------------------------------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCounter - 1].toString());
                    System.out.println("Now you have " + taskCounter + " task(s) in the list.");
                    System.out.println("------------------------------------------");
                    break;

                case "event":
                    String[] eventParts = taskDescription.split(" /from | /to ");
                    tasks[taskCounter] = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    taskCounter++;
                    System.out.println("------------------------------------------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCounter - 1].toString());
                    System.out.println("Now you have " + taskCounter + " task(s) in the list.");
                    System.out.println("------------------------------------------");
                    break;

                case "bye":
                    // exit
                    System.out.println("------------------------------------------");
                    System.out.println("Bye, see you again :)");
                    System.out.println("------------------------------------------");
                    scanner.close();
                    return;

                default:
                    // add task to tasks array
                    tasks[taskCounter] = new Task(input);
                    taskCounter++;

                    System.out.println("------------------------------------------");
                    System.out.println("added: " + input);
                    System.out.println("------------------------------------------");
                    break;
            }
        }
    }
}


