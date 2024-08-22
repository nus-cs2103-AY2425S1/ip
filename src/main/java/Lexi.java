import java.util.Scanner;

public class Lexi {
    static Task[] tasks = new Task[100];
    static int count = 0;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        greet();
        String response = userInput.nextLine();
        while(!response.equals("bye")) {
            String[] parts = response.split(" ");
            if(parts[0].contains("mark")) {
                handleMark(parts);
            } else if(parts[0].equals("todo")) {
                handleTodo(response);
            } else if(parts[0].equals("deadline")){
                handleDeadline(response);
            } else if(parts[0].equals("event")) {
                handleEvent(response);
            } else if(response.equals("list")) {
                listTasks();
            } else {
                addTask(new Task(response));
            }
            response = userInput.nextLine();
        }
        userInput.close();
        bye();
    }

    private static void handleEvent(String response) {
        String[] parts = response.split(" /from ");
        String taskName = parts[0].substring(6);
        String[] range = parts[1].split(" /to ");
        String from  = range[0];
        String to = range[1];
        addTask(new Event(taskName, from, to));
    }

    private static void handleDeadline(String response) {
        String[] parts = response.split(" /by ");
        String taskName = parts[0].substring(9);
        String by = parts[1];
        addTask(new Deadline(taskName, by));
    }

    private static void handleTodo(String response) {
        String taskName = response.substring(5);
        addTask(new Todo(taskName));
    }

    private static void handleMark(String[] parts) {
        int taskNumber =  Integer.parseInt(parts[1]) - 1;
        Task taskToBeMarked = tasks[taskNumber];
        if(parts[0].equals("unmark")) {
            unmarkTask(taskToBeMarked);
        } else {
            markTask(taskToBeMarked);
        }
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
    public static void addTask(Task task) {
        tasks[count] = task;
        count++;
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.printf("   %s%n", task);
        System.out.printf(" Now you have %d task%s in the list.%n", count, count == 1 ? "" : "s");
        System.out.println("____________________________________________________________\n");
    }

}
