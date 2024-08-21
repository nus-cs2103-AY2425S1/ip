import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> toDoList = new ArrayList<Task>();
    private static int counter = 1;

    private static void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < toDoList.size(); i++){
            int serial = i+1;
            Task task = toDoList.get(i);
            System.out.println(serial + "." + task.getStatusIcon() + " " + task.toString());
        }
    }

    private static void mark(String command) {
        String[] getIndex = command.split(" ",2);
        int index = Integer.parseInt(getIndex[1]);
        Task task = toDoList.get(index-1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.getStatusIcon() + " " + task.toString());
    }

    private static void unmark(String command) {
        String[] getIndex = command.split(" ",2);
        int index = Integer.parseInt(getIndex[1]);
        Task task = toDoList.get(index-1);
        task.unmarkAsUndone();
        System.out.println("Ok! I've marked this task as not done yet:");
        System.out.println(task.getStatusIcon() + " " + task.toString());
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String command;
        while (sc.hasNext()) {
            command = sc.nextLine();
            if (command.indexOf("mark") == 0) {
                mark(command);
            } else if (command.indexOf("unmark") == 0) {
                unmark(command);
            } else if (command.equals("list")) {
                printList();
            } else if (command.equals("bye")) {
                break;
            } else {
                Task newTask = new Task(command);
                toDoList.add(newTask);
                counter += 1;
                System.out.println("added: " + command);
            }
        }
        sc.close();
        bye();
    }
}
