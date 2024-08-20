import javax.swing.*;
import java.util.Objects;
import java.util.Scanner;
public class Optimus {
    boolean isLive;
    Task[] storage;
    Integer numOfTasksStored;
    static String linebreak = "____________________________";

    public Optimus() {
        this.isLive = true;
        this.storage = new Task[100];
        this.numOfTasksStored = 0;
        this.greet();
    }

    public boolean getStatus() {
        return this.isLive;
    }

    private void addTask(String[] commands) {
        StringBuilder desc = new StringBuilder();
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        switch (commands[0]) {
            case "todo":
                for (int i = 1; i < commands.length; i++){
                    desc.append(commands[i]);
                    desc.append(" ");
                }
                this.storage[numOfTasksStored] = new ToDoTask(desc.toString());
                this.numOfTasksStored += 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(this.storage[numOfTasksStored - 1]);
                System.out.printf("Now you have %d tasks in the list%n", this.numOfTasksStored);
                break;
            case "deadline":
                boolean addDeadline = false;
                for (int i = 1; i < commands.length; i++) {
                    if (Objects.equals(commands[i], "/by")) {
                        addDeadline = true;
                        continue;
                    }
                    if (addDeadline) {
                        end.append(commands[i]);
                        end.append(" ");
                    } else {
                        desc.append(commands[i]);
                        desc.append(" ");
                    }
                }
                if (!addDeadline || Objects.equals(end.toString(), "")) {
                    System.out.println("Deadline Tasks must have a deadline specified");
                } else {
                    this.storage[numOfTasksStored] = new DeadlineTask(desc.toString(), end.toString());
                    this.numOfTasksStored += 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(this.storage[numOfTasksStored - 1]);
                    System.out.printf("Now you have %d tasks in the list%n", this.numOfTasksStored);
                }
                break;
            case "event":
                boolean addStart = false;
                boolean addEnd = false;
                for (int i = 1; i < commands.length; i++) {
                    if (Objects.equals(commands[i], "/from")) {
                        addStart = true;
                        continue;
                    }
                    if (Objects.equals(commands[i], "/to")) {
                        addEnd = true;
                        continue;
                    }

                    if (addStart && !addEnd) {
                        start.append(commands[i]);
                        start.append(" ");
                    } else if (addEnd){
                        end.append(commands[i]);
                        end.append(" ");
                    } else {
                        desc.append(commands[i]);
                        desc.append(" ");
                    }
                }
                if (!addStart || Objects.equals(start.toString(), "") || !addEnd || Objects.equals(end.toString(), "") ) {
                    System.out.println("Events must have a start and end specified");
                } else {
                    this.storage[numOfTasksStored] = new EventTask(desc.toString(), start.toString(), end.toString());
                    this.numOfTasksStored += 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(this.storage[numOfTasksStored - 1]);
                    System.out.printf("Now you have %d tasks in the list%n", this.numOfTasksStored);
                }
        }
    }

    private void markTask(String[] commands, boolean markComplete) {
        if (commands.length != 2) {
            System.out.println("Invalid command");
            return;
        }

        try {
            int taskNum = Integer.parseInt(commands[1]);
            if (taskNum <= 0 || taskNum > this.numOfTasksStored) {
                System.out.println("Invalid task number.");
                return;
            }

            Task task = this.storage[taskNum - 1];
            if (markComplete) {
                task.markAsComplete();
                System.out.println("Nice! I've marked this task as complete:");
            } else {
                task.markAsIncomplete();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println(task);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + e.getMessage());
        }
    }

    public void markTaskAsDone(String[] commands) {
        markTask(commands, true);
    }

    public void markTaskAsIncomplete(String[] commands) {
        markTask(commands, false);
    }

    public void printAllTasks() {
        for (int i = 1; i <= this.numOfTasksStored; i++){
            String out = String.format("%d. %s", i, this.storage[i-1]);
            System.out.println(out);
        }
    }

    private void greet() {
        System.out.println("Hello! I'm Optimus\nWhat can I do for you?");
        System.out.println(Optimus.linebreak);
    }

    private void leave() {
        this.isLive = false;
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Optimus.linebreak);
    }

    public static void main(String[] args) {
        Optimus optimus = new Optimus();
        Scanner scanner = new Scanner(System.in);
        
        while (optimus.getStatus()) {
            String input = scanner.nextLine();
            String[] commands = input.split(" ");
            String command = commands[0];

            switch (command) {
                case "bye":
                    optimus.leave();
                    break;
                case "list":
                    optimus.printAllTasks();
                    break;
                case "mark":
                    optimus.markTaskAsDone(commands);
                    break;
                case "unmark":
                    optimus.markTaskAsIncomplete(commands);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    optimus.addTask(commands);
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }

        }
    }
}
