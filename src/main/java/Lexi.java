import java.util.Scanner;

public class Lexi {
    static Task[] tasks = new Task[100];
    static int count = 0;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        greet();
        String response = userInput.nextLine();
        while(!response.equals("bye")) {
            if(response.contains("mark") || response.contains("unmark")) {
                String[] parts = response.split(" ");
                int taskNumber =  Integer.parseInt(parts[1]) - 1;
                Task taskToBeMarked = tasks[taskNumber];
                if (response.contains("unmark")) {
                    unmarkTask(taskToBeMarked);
                } else {
                    markTask(taskToBeMarked);
                }

            } else if(response.equals("list")) {
                listTasks();
            } else {
                addTask(response);
            }
            response = userInput.nextLine();
        }
        userInput.close();
        bye();
    }

    private static void unmarkTask(Task taskToBeMarked) {
        taskToBeMarked.undoTask();
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + taskToBeMarked);
        System.out.println("____________________________________________________________\n");
    }

    public static void markTask(Task taskToBeMarked) {
        taskToBeMarked.doTask();
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskToBeMarked);
        System.out.println("____________________________________________________________\n");
    }


    public static void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for(int i = 0;i<count;i++) {
            Task currTask = tasks[i];
            System.out.printf("  %d. %s%n", i+1, currTask);
        }
        System.out.println("____________________________________________________________\n");
    }

    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Lexi\n What can I do for you?");
        System.out.println("____________________________________________________________\n");
    }
    public static void bye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }
    public static void addTask(String taskName) {
        tasks[count] = new Task(taskName);
        count++;
        System.out.println("____________________________________________________________");
        System.out.printf(" added: %s%n", taskName);
        System.out.println("____________________________________________________________\n");
    }

}
