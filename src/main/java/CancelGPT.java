import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CancelGPT {
    private final String CHATBOT_NAME;
    private final List<Task> TASKS_LIST;

    public CancelGPT() {
        this.CHATBOT_NAME = "CancelGPT";
        this.TASKS_LIST = new ArrayList<>();
    }
    public static void main(String[] args) {
        CancelGPT cancelGPT = new CancelGPT();
        cancelGPT.run();
    }

    public void run() {
        String horizontalLine = "____________________________________________________________";
        Scanner sc = new Scanner(System.in);

        System.out.println(horizontalLine);
        greet();
        System.out.println(horizontalLine);

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(horizontalLine);
            handleCommand(command);
            System.out.println(horizontalLine);
            command = sc.nextLine();
        }

        sc.close();
        System.out.println(horizontalLine);
        exit();
        System.out.println(horizontalLine);
    }

    public void greet() {
        System.out.println("Hello! I am " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
    }

    public void exit() {
        System.out.println("Good bye. Hope to see you again soon!");
    }

    public void handleCommand(String command) {
        if (command.equals("list")) {
            displayTasksList();
        } else {
            String text = addToTaskList(command);
            System.out.println("added: " + text);
        }
    }

    public String addToTaskList(String text) {
        Task task = new Task(text);
        this.TASKS_LIST.add(task);
        return task.toString();
    }

    public void displayTasksList() {
        for (int i = 0; i < this.TASKS_LIST.size(); i++) {
            System.out.println(i + 1 + ". " + this.TASKS_LIST.get(i));
        }
    }
}
