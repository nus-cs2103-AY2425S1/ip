import java.util.ArrayList;
import java.util.Scanner;

public class Gravitas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = "Gravitas";
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("____________________________________________________________ \nHello! I'm " + name + "\nWhat can I do for you? \n____________________________________________________________\n");

        while (true) {
            String msg = sc.nextLine();
            Task newTask = new Task(msg);
            String[] msgFrag = msg.split(" ", 2);
            System.out.println("____________________________________________________________");
            if (msg.equals("bye")) {
                sc.close(); //close scanner
                System.out.println("Bye. Hope to see you again soon! \n____________________________________________________________");
                break;
            } else if (msg.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                Task currentTask;
                for (int i = 0; i < tasks.size(); i++) {
                    currentTask = tasks.get(i);
                    System.out.println((i + 1) + ". [" + currentTask.getStatusIcon() + "] " + currentTask.description);
                }
            } else if (msgFrag[0].equals("mark")) {
                int index = Integer.parseInt((msgFrag[1]));
                Task t = tasks.get(index - 1);
                t.isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t[" + t.getStatusIcon() + "] " + t.description);
            } else if (msgFrag[0].equals("unmark")) {
                int index = Integer.parseInt((msgFrag[1]));
                Task t = tasks.get(index - 1);
                t.isDone = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("\t[" + t.getStatusIcon() + "] " + t.description);
            } else {
                tasks.add(newTask);
                System.out.println("added: " + msg);
            }
            System.out.println("____________________________________________________________\n");
        }
    }
}
