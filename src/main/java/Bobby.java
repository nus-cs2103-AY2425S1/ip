import java.util.ArrayList;
import java.util.Scanner;

public class Bobby {
    private final Scanner input;
    private ArrayList<Task> tasks;
    private boolean isRunning;

    public Bobby() {
        this.input = new Scanner(System.in);
        this.tasks = new ArrayList<>();
        this.isRunning = true;
    }

    public String getInput() {
        return this.input.nextLine();
    }

    public void printAddSuccess(Task newTask) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    public void processInput(String s) {
        String[] inputArr = s.split(" ", 2);
        String command = inputArr[0];
        if (command.equals("bye")) {
            this.isRunning = false;
            System.out.println("Bye. Hope to see you again soon!");
        } else if (command.equals("list")) {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
        } else if (command.equals("mark")) {
            int idx = Integer.parseInt(inputArr[1]) - 1;
            this.tasks.get(idx).setIsDone(true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(this.tasks.get(idx));
        } else if (command.equals("unmark")) {
            int idx = Integer.parseInt(inputArr[1]) - 1;
            this.tasks.get(idx).setIsDone(false);
            System.out.println("Ok, I've marked this task as not done yet: ");
            System.out.println(this.tasks.get(idx));
        } else if (command.equals("todo")) {
            Task newTask = new Todo(inputArr[1]);
            this.tasks.add(newTask);
            this.printAddSuccess(newTask);
        } else if (command.equals("deadline")) {
            String[] args = inputArr[1].split(" /by ", 2);
            String name = args[0];
            String deadline = args[1];
            Task newTask = new Deadline(name, deadline);
            this.tasks.add(newTask);
            this.printAddSuccess(newTask);
        } else if (command.equals("event")) {
            String[] args = inputArr[1].split(" /from ", 2);
            String name = args[0];
            String[] fromTo = args[1].split(" /to ", 2);
            String from = fromTo[0];
            String to = fromTo[1];
            Task newTask = new Event(name, from, to);
            this.tasks.add(newTask);
            this.printAddSuccess(newTask);
        } else {
            System.out.println("Invalid command");
        }
    }

    public void sayHi() {
        System.out.println("Hello, I'm Bobby");
        System.out.println("What can I do for you?");
    }

    public static void main(String[] args) {
        Bobby chatBot = new Bobby();
        chatBot.sayHi();
        while (chatBot.isRunning) {
            chatBot.processInput(chatBot.getInput());
        }
    }
}
