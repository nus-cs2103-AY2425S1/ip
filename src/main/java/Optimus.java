import java.util.Scanner;

public class Optimus {
    private static Task[] taskList = new Task[100]; //stores list of tasks
    private static int taskCount = 0; //tracks number of tasks
    public static void main(String[] args) {
        //greeting for user
        String greeting = "Hello! I'm Optimus.\n" +
                "What can I do for you?\n";
        System.out.println(greeting);
        //Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        //while loop to keep taking in user input
        while (true) {
            try {
                String userInput = scanner.nextLine();

                //exit program if user enters "bye"
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!\n");
                    scanner.close();
                    System.exit(0);
                }

                //if user inputs "list", list out all existing tasks from taskList array
                if (userInput.equals("list")) {
                    listTasks();
                } else if (userInput.startsWith("mark")) { //check if user wants to mark task
                    String[] parts = userInput.split(" ");
                    int taskIndex = Integer.parseInt(parts[1]) - 1; // Get the index (1-based index to 0-based)
                    if (taskIndex + 1 > taskCount) { //check if task user wants to add exists
                        System.out.println("Sorry, you only have up to task number " + taskCount);
                        continue;
                    }
                    markTaskAsDone(taskIndex);
                } else {
                    // else add user task to taskList array and echo "added: <task>"
                    addTask(userInput);
                }
            } catch (OptimusException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //add task to taskList array upon user input
    private static void addTask(String userInput) throws OptimusException {
        Task task;
        //used AI to find how to extract relevant words from user input
        if (userInput.startsWith("todo")) {
            String description = userInput.substring(5).trim();
            if (description.isEmpty()) {
                throw new OptimusException("The description of a todo cannot be empty >:(");
            }
            task = new Todo(description);
        } else if (userInput.startsWith("deadline")) {
            String[] parts = userInput.substring(9).split("/by");
            String description = parts[0].trim();
            String by = parts[1].trim();
            if (description.isEmpty()) {
                throw new OptimusException("The description of a deadline cannot be empty >:(");
            }
            task = new Deadline(description, by);
        } else if (userInput.startsWith("event")) {
            String[] parts = userInput.substring(6).split ("/from|/to");
            String description = parts[0].trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            if (description.isEmpty()) {
                throw new OptimusException("The description of an event cannot be empty >:(");
            }
            task = new Event(description, from, to);
        } else {
            task = new Task(userInput);
        }
        taskList[taskCount] = task;
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    //list out tasks from taskList array
    private static void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + taskList[i].toString());
        }
    }

    private static void markTaskAsDone(int taskIndex) {
        taskList[taskIndex].isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList[taskIndex].toString());
    }
}
