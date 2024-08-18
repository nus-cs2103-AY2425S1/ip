import java.util.Scanner;

public class Boss {
    public static void main(String[] args) {
        String[] tasks = new String[100];
        int numTasks = 0;

        System.out.println("Hello! I'm the boss.");
        System.out.println("What can I do for you?");
        Scanner myObj = new Scanner(System.in);

        String task = myObj.nextLine();
        do {
            if (task.equals("list")) {
                int i = 1;
                while (i < numTasks + 1) {
                    System.out.println(i + ". " + tasks[i-1]);
                    i++;
                }
            }
            else {
                System.out.println("added: " + task);
                tasks[numTasks] = task;
                numTasks++;
            }
            task = myObj.nextLine();
        } while(!task.equals("bye") && numTasks < 100);

        System.out.println("Bye. Hope to see you again soon!");

    }
}
