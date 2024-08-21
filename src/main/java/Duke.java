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
            System.out.println(serial + "." + task.toString());
        }
    }

    private static void mark(int index) {
        Task task = toDoList.get(index-1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    private static void unmark(int index) {
        Task task = toDoList.get(index-1);
        task.unmarkAsUndone();
        System.out.println("Ok! I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String command;
        while (sc.hasNext()) {
            command = sc.nextLine();
            String[] getInstr = command.split(" ", 2);
            String instr = getInstr[0];
            if (instr.equals("mark")) {
                int index = Integer.parseInt(getInstr[1]);
                mark(index);
            } else if (instr.equals("unmark")) {
                int index = Integer.parseInt(getInstr[1]);
                unmark(index);
            } else if (instr.equals("list")) {
                printList();
            } else if (instr.equals("bye")) {
                break;
            } else if (instr.equals("todo")) {
                Task task = new Todo(getInstr[1]);
                toDoList.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println(task.toString());
                System.out.println("Now you have " + counter + " tasks in the list.");
                counter += 1;
            } else if (instr.equals("deadline")) {
                String[] getBy = getInstr[1].split(" /by ", 2);
                Task task = new Deadline(getBy[0], getBy[1]);
                toDoList.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println(task.toString());
                System.out.println("Now you have " + counter + " tasks in the list.");
                counter += 1;
            } else if (instr.equals("event")) {
                String[] getTime = getInstr[1].split(" /", 3);
                Task task = new Event(getTime[0], getTime[1], getTime[2]);
                toDoList.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println(task.toString());
                System.out.println("Now you have " + counter + " tasks in the list.");
                counter += 1;
            }

        }
        sc.close();
        bye();
    }
}
