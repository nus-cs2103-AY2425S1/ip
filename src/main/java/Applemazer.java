import java.util.Scanner;
import java.util.ArrayList;

public class Applemazer {

    private static class Task {
        private final String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return isDone ? "[X] " : "[ ] ";
        }

        public void setDone() {
            isDone = true;
        }

        public void setUndone() {
            isDone = false;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    Scanner sc = new Scanner(System.in);
    ArrayList<Task> tasks = new ArrayList<>();

    private void greeting() {
        String greeting = "Hello! I'm Applemazer.\nWhat can I do for you?\n";
        System.out.println(greeting);
    }

    private void farewell() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    private void process() {
        boolean processing = true;
        Task task;
        int taskNumber;
        while (processing) {
            String command = sc.next();
            switch (command) {
                case "bye" :
                    processing = false;
                    break;
                case "list" :
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < tasks.size(); ++i) {
                        task = tasks.get(i);
                        System.out.println((i+1) + "." + task.getStatusIcon() + task);
                    }
                    System.out.println(); // Leave empty line.
                    break;
                case "mark" :
                    taskNumber = sc.nextInt()-1;
                    assert taskNumber < tasks.size(): "Index exceeds number of tasks. ";
                    task = tasks.get(taskNumber);
                    task.setDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("    " + task.getStatusIcon() + task + "\n");
                    break;
                case "unmark" :
                    taskNumber = sc.nextInt()-1;
                    assert taskNumber < tasks.size(): "Index exceeds number of tasks. ";
                    task = tasks.get(taskNumber);
                    task.setUndone();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println("    " + task.getStatusIcon() + task + "\n");
                    break;
                default:
                    command += sc.nextLine(); // Get rest of line if first word not valid command.
                    task = new Task(command);
                    tasks.add(task);
                    System.out.println("added: " + task + "\n");
            }
        }
    }

    public static void main(String[] args) {
        Applemazer chatBot = new Applemazer();
        chatBot.greeting();
        chatBot.process();
        chatBot.farewell();
    }
}
