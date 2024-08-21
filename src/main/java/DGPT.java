import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The DGPT class represents a simple task management system.
 * It allows users to add tasks, mark them as done, unmark them, and view the list of tasks.
 */
public class DGPT {

    private final List<Task> list;

    /**
     * Constructs a DGPT instance with an empty task list.
     */
    public DGPT() {
        list = new ArrayList<>();
    }

    private void addToDoToList(String text) {
        Task newTask = new ToDo(text);
        this.list.add(newTask);
        System.out.println("-----------------------");
        System.out.println("DGPT> Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.printf("Now you have %d tasks in the list.%n", this.list.size());
        System.out.println("-----------------------");
    }

    private void addDeadlineToList(String text) {
        String[] parts = text.split(" /by ");
        Task newTask = new Deadline(parts[0], parts[1]);
        this.list.add(newTask);
        System.out.println("-----------------------");
        System.out.println("DGPT> Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.printf("Now you have %d tasks in the list.%n", this.list.size());
        System.out.println("-----------------------");
    }

    private void addEventToList(String text) {
        String[] parts = text.split(" /");
        Task newTask = new Event(parts[0],parts[1].substring(5), parts[2].substring(3));
        this.list.add(newTask);
        System.out.println("-----------------------");
        System.out.println("DGPT> Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.printf("Now you have %d tasks in the list.%n", this.list.size());
        System.out.println("-----------------------");
    }

    private void showList() {
        int numOfItems = this.list.size();
        System.out.println("-----------------------");
        System.out.println("DGPT> Here are the tasks in your list:");
        for (int i = 1; i <= numOfItems; i++) {
            Task currTask = this.list.get(i - 1);
            System.out.println(i + "." + currTask.toString());
        }
        System.out.println("-----------------------");
    }

    private void markTask(int index) {
        this.list.get(index).mark();
        System.out.println("-----------------------");
        System.out.println("DGPT> Nice! I've marked this task as done: ");
        System.out.println(this.list.get(index).toString());
        System.out.println("-----------------------");
    }

    private void unmarkTask(int index) {
        this.list.get(index).unmark();
        System.out.println("-----------------------");
        System.out.println("DGPT> OK, I've marked this task as not done yet:");
        System.out.println(this.list.get(index).toString());
        System.out.println("-----------------------");
    }

    /**
     * Main method to start the program and interact with the user.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DGPT dgpt = new DGPT();

        // Starting Message
        System.out.println("-----------------------");
        System.out.println("DGPT> Hello! I'm DGPT");
        System.out.println("DGPT> What can I do for you?");
        System.out.println("-----------------------");

        String input;
        do {
            System.out.print("User> ");
            input = scanner.nextLine();
            String[] parts = input.split(" ", 2);


            if (input.equals("list")) {
                dgpt.showList();
            } else if (parts[0].equals("mark")) {
                dgpt.markTask(Integer.parseInt(parts[1]) - 1);
            } else if (parts[0].equals("unmark")) {
                dgpt.unmarkTask(Integer.parseInt(parts[1]) - 1);
            } else if (parts[0].equals("todo")) {
                dgpt.addToDoToList(parts[1]);
            } else if (parts[0].equals("deadline")) {
                dgpt.addDeadlineToList(parts[1]);
            } else if (parts[0].equals("event")) {
                dgpt.addEventToList(parts[1]);
            }
        } while (!input.equals("bye"));

        scanner.close();

        // Closing Message
        System.out.println("-----------------------");
        System.out.println("DGPT> Bye. Hope to see you again soon!");
        System.out.println("-----------------------");
    }
}
