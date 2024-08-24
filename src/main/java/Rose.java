import java.util.Scanner;
import java.util.ArrayList;

public class Rose {
    static final String horizontal = "____________________________________________________________";
    static final String name = "Rose";
    static ArrayList<Task> tasks = new ArrayList<Task>();

    private static void addTask(String taskType, String taskName) {
        System.out.print(horizontal.indent(4));
        System.out.print("Got it. I've added this task: ".indent(4));
        Task newTask;

        if (taskType.equals("event")) {
            String[] partsA = taskName.split(" /from ");
            String[] partsB = partsA[1].split(" /to ");
            newTask = new Event(partsA[0], partsB[0], partsB[1]);
        } else if (taskType.equals("deadline")) {
            String[] parts = taskName.split(" /by ");
            newTask = new Deadline(parts[0], parts[1]);
        } else {
            newTask = new Todo(taskName);
        }

        tasks.add(newTask);
        System.out.print(newTask.toString().indent(6));
        System.out.print(String.format("Now you have %d task in the list.", tasks.size()).indent(4));

        System.out.print(horizontal.indent(4));
    }

    private static void showList() {
        System.out.println(horizontal.indent(4));
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.print((i + ". " + tasks.get(i - 1).toString()).indent(4));
        }
        System.out.println(horizontal.indent(4));
    }

    private static void markTask(Integer idx) {
        System.out.println(horizontal.indent(4));
        tasks.get(idx - 1).mark();
        //Task updatedTask = tasks.get(idx - 1).mark();
        //tasks.set(idx - 1, updatedTask);
        System.out.println("Marked as done : ".indent(4));
        System.out.println(tasks.get(idx - 1).toString().indent(4));
        System.out.println(horizontal.indent(4));
    }

    private static void unmarkTask(Integer idx) {
        System.out.println(horizontal.indent(4));
        tasks.get(idx - 1).unmark();
        System.out.println("Marked as not done :".indent(4));
        System.out.println(tasks.get(idx - 1).toString().indent(4));
        System.out.println(horizontal.indent(4));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String opening = horizontal + "\nHi gorgeous! I'm " + name + "~~\n"
                + "How can I help you today?\n" + horizontal;
        String closing = horizontal + "\nSee you~~ Don't forget to drink some water ^_^\n" + horizontal;

        System.out.println(opening.indent(4));

        String input = scanner.nextLine();
        String message = "";

        while (!input.equals("bye")) {
            String command = input.split(" ",2)[0];
            if ((input.split(" ", 2).length > 1)) {
                message = input.split(" ", 2)[1];
            }

            if (command.equals("list")) {
                showList();
            } else if (command.equals("mark")) {
                markTask(Integer.valueOf(message));
            } else if (command.equals("unmark")) {
                unmarkTask(Integer.valueOf(message));
            } else if (command.equals("todo") || command.equals("event") || command.equals("deadline")) {
                addTask(command, message);
            } else {
                System.out.println("Oops sry idk what command is that".indent(4));
            }
            input = scanner.nextLine();
        }

        System.out.println(closing.indent(4));
    }
}
