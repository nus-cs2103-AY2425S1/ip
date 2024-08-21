import java.util.ArrayList;
import java.util.Scanner;

public class Tako {
    public static void main(String[] args) {
        String name = "Tako";
        ArrayList<Task> listOfTask = new ArrayList<Task>();

        Scanner input = new Scanner(System.in);

        System.out.println("Hello! I'm " + name + "\n" +
                           "What can I do for you?\n");

        String command = input.nextLine();

        while(!command.equals("bye")) {
            if (command.equals("list")) {
                listStorage(listOfTask);
            } else if (command.length() > 4 && command.substring(0, 5).equals("mark ")) {
                int taskNumber = Integer.parseInt(command.substring(5));
                markTask(listOfTask, taskNumber);
            } else if (command.length() > 6 && command.substring(0, 7).equals("unmark ")) {
                int taskNumber = Integer.parseInt(command.substring(7));
                unmarkTask(listOfTask, taskNumber);
            } else {
                System.out.println("added: " + command);
                Task newTask = new Task(command);
                addStorage(listOfTask, newTask);
            }
            command = input.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void listStorage(ArrayList<Task> listOfTask) {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < listOfTask.size(); i++) {
            System.out.println((i + 1) + ".[" + listOfTask.get(i).getStatusIcon() +
                               "] " + listOfTask.get(i).getDescription());
        }
    }

    public static void addStorage(ArrayList<Task> listOfTask, Task newTask) {
        listOfTask.add(newTask);
    }

    public static void markTask(ArrayList<Task> listOfTask, int taskNumber) {
        listOfTask.get(taskNumber - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" +
                           "  [" + listOfTask.get(taskNumber - 1).getStatusIcon() +
                           "] " + listOfTask.get(taskNumber - 1).getDescription());
    }

    public static void unmarkTask(ArrayList<Task> listOfTask, int taskNumber) {
        listOfTask.get(taskNumber - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" +
                           "  [" + listOfTask.get(taskNumber - 1).getStatusIcon() +
                           "] " + listOfTask.get(taskNumber - 1).getDescription());
    }
}
