import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Scanner;

public class Lumina {

    private static final String ECHO_EXIT_STRING = "bye";
    private static final String ECHO_LIST_STRING = "list";
    private static final String ECHO_MARK_TASK_STRING = "mark";
    private static final String ECHO_UNMARK_TASK_STRING = "unmark";
    private static final int indentWidth = 2;

    // task description and whether the task is done
    private ArrayList<Task> tasks;
    // Cosntructor
    public Lumina() {
        tasks = new ArrayList<>();
    }

    private String indentMessage(String msg) {
        String[] lines = msg.split("\n");

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < indentWidth; j++) {
                builder.append(" ");
            }
            builder.append(lines[i]);
            if (i < lines.length - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }

    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    private void printMessage(String msg) {
        this.printSeparator();
        System.out.println(indentMessage(msg));
        this.printSeparator();
    }

    private void greet() {
        this.printMessage("Hello! I'm Lumina\n" +
                "What can I do for you?");
    }

    private void exit() {
        this.printMessage("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    private void addTask(String desc) {
        this.tasks.add(new Task(desc));
    }

    private void listTasks() {
        StringBuilder listedTaskMessage = new StringBuilder();
        listedTaskMessage.append("Here are the tasks in your list:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            listedTaskMessage.append(Integer.toString(i + 1)).append(".");
            listedTaskMessage.append(this.tasks.get(i));
            if (i < this.tasks.size() - 1) {
                listedTaskMessage.append("\n");
            }
        }
        this.printMessage(listedTaskMessage.toString());
    }

    private void markTaskDone(int index) {
        if (index < 0 || index >= this.tasks.size()) {
            throw new IllegalArgumentException("Task index out of bounds");
        }
        StringBuilder taskDoneMessage = new StringBuilder();
        taskDoneMessage.append("Nice! I've marked this task as done:\n");
        this.tasks.get(index).markAsDone();
        taskDoneMessage.append(indentMessage(this.tasks.get(index).toString()));
        this.printMessage(taskDoneMessage.toString());
    }

    private void markTaskNotDone(int index) {
        if (index < 0 || index >= this.tasks.size()) {
            throw new IllegalArgumentException("Task index out of bounds");
        }
        StringBuilder taskNotDoneMessage = new StringBuilder();
        taskNotDoneMessage.append("OK, I've marked this task as not done yet:\n");
        this.tasks.get(index).markAsNotDone();
        taskNotDoneMessage.append(indentMessage(this.tasks.get(index).toString()));
        this.printMessage(taskNotDoneMessage.toString());
    }

    private void echo(Scanner sc) {
        String msg;
        while(true) {
            System.out.println();
            msg = sc.nextLine();
            if (msg.equals(Lumina.ECHO_EXIT_STRING)) {
                this.exit();
                break;
            }
            if (msg.equals(Lumina.ECHO_LIST_STRING)) {
                this.listTasks();
                continue;
            }
            // first check unmark since mark contains unmark
            if (msg.contains(Lumina.ECHO_UNMARK_TASK_STRING)) {
                try {
                    String[] msgSplit = msg.split(" ");
                    if (msgSplit.length == 2) {
                        int taskIndex = Integer.parseInt(msgSplit[1]) - 1; // 0 indexed
                        this.markTaskNotDone(taskIndex);
                    } else {
                        throw new IllegalArgumentException("Wrong number of parameters found");
                    }
                } catch(IllegalArgumentException e) {
                    this.printMessage(e.getMessage() + ". Try again");
                }
                continue;
            }
            if (msg.contains(Lumina.ECHO_MARK_TASK_STRING)) {
                try {
                    String[] msgSplit = msg.split(" ");
                    if (msgSplit.length == 2) {
                        int taskIndex = Integer.parseInt(msgSplit[1]) - 1; // 0 indexed
                        this.markTaskDone(taskIndex);
                    } else {
                        throw new IllegalArgumentException("Wrong number of parameters found");
                    }
                } catch(IllegalArgumentException e) {
                    this.printMessage(e.getMessage() + ". Try again");
                }
                continue;
            }
            this.addTask(msg);
            this.printMessage("added: " + msg);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Lumina lumina = new Lumina();
        lumina.greet();
        lumina.echo(sc);
    }
}
