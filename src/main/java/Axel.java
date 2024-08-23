import java.util.Scanner;
public class Axel {
    private static final int MAX_TASKS = 100;
    private static Task[] taskList = new Task[MAX_TASKS];
    private static int taskListCount = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        //Greeting message
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Axel");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        //Repeatedly prompts for user's input
        while (true) {
            //Reads user's input
            userInput = scanner.nextLine();
            //Display tasks
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                displayTasks();
                System.out.println("____________________________________________________________");
            //Mark task as done
            } else if (userInput.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                markTaskAsDone(taskIndex);
            //Mark task as not done
            } else if (userInput.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                markTaskAsNotDone(taskIndex);
            //Exit message
            } else if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else {
                //Adds tasks
                addTask(userInput);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }

    private static void addTask(String taskName) {
        if (taskListCount < MAX_TASKS) {
            taskList[taskListCount] = new Task(taskName);
            taskListCount++;
        } else {
            System.out.println("Task list is full!");
        }
    }
    private static void displayTasks() {
        if (taskListCount == 0) {
            System.out.println("No tasks in the list!");
        } else {
            for (int i = 0; i < taskListCount; i++) {
                System.out.println((i + 1) + ". " + taskList[i]);
            }
        }
    }
    //Mark as done
    private static void markTaskAsDone(int index) {
        if (index >= 0 && index < taskListCount) {
            taskList[index].markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + taskList[index]);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("No such task.");
        }
    }
    //Mark as not done
    private static void markTaskAsNotDone(int index) {
        if (index >= 0 && index < taskListCount) {
            taskList[index].markAsNotDone();
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + taskList[index]);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("No such task.");
        }
    }
}
