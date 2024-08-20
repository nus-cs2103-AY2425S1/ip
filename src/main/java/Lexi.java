import java.util.Scanner;

public class Lexi {
    static String[] tasks = new String[100];
    static int count = 0;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        greet();
        String response = userInput.nextLine();
        while (!response.equals("bye")) {
            if(response.equals("list")) {
                listTasks();
            } else {
                addTask(response);
            }
            response = userInput.nextLine();
        }
        bye();
    }

    public static void listTasks() {
        System.out.println("____________________________________________________________");
        for(int i = 0;i<count;i++) {
            String currTask = tasks[i];
            System.out.printf(" %d. %s%n", i+1, currTask);
        }
        System.out.println("____________________________________________________________");
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
    public static void addTask(String task) {
        tasks[count] = task;
        count++;
        System.out.println("____________________________________________________________");
        System.out.printf(" added: %s%n", task);
        System.out.println("____________________________________________________________\n");
    }

}
