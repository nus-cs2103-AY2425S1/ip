package storage;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> parent;

    public TaskList(ArrayList<Task> list) {
        this.parent = list;
    }

    public void addTask(String task) {
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                Choose a task type (1, 2 or 3):
                1. Todo - No end date
                2. tasks.Deadline - Has end date
                3. Event - Has start and end date
                """);
        switch(getInputFromUser(sc, "(1, 2 or 3) > ")) {
            case "1":
                this.parent.add(new Todo(task));
                System.out.println("Friday > Okay, I've added a todo: " + task);
                break;
            case "2":
                System.out.println("What is the deadline?");
                this.parent.add(new Deadline(task, getInputFromUser(sc, "tasks.Deadline > ")));
                System.out.println("Friday > Okay, I've added a deadline: " + task);
                break;
            case "3":
                System.out.println("What is the start date?");
                String start = getInputFromUser(sc, "Start Date > ");
                System.out.println("What is the end date?");
                this.parent.add(new Event(task, start, getInputFromUser(sc, "End Date > ")));
                System.out.println("Friday > Okay, I've added an event: " + task);
                break;
            default:
                System.out.println("Invalid task type! Try adding again.");
        }
    }

    public void removeTask(int task) {
        Task temp = parent.get(task);
        this.parent.remove(task);

        System.out.println("Friday > Successfully removed: " + temp.getName());
        System.out.println("Friday > You now have " + getSize() + " total tasks left.");
    }

    @Override
    public String toString() {
        String ans = "";
        ans += String.format("Completed: %d tasks | Incomplete: %d tasks | Total: %d tasks%n%n", countCompleted(true), countCompleted(false), parent.size());
        for (int i = 1; i <= parent.size(); i++) {
            ans += String.format("%d: %s%n", i, parent.get(i-1).toString());
        }
        return ans;
    }

    public int getSize() {
        return this.parent.size();
    }

    public ArrayList<Task> getParent() {
        return this.parent;
    }

    public void doneTask(String action, int task) {
        Task temp = parent.get(task);
        if (action.equals("mark") || action.equals("Mark")) {
            if (!temp.isDone()) {
                temp.setDone();
            }
            System.out.println("Friday > Good job! Marked as done :)");
        } else {
            if (temp.isDone()) {
                this.parent.get(task).setUndone();
            }
            System.out.println("Friday > Oh man! Marked as undone :(");
        }
    }

    public String getInputFromUser(Scanner sc, String template) {
        while (true) {
            System.out.print(template);
            String str = sc.nextLine();
            if (str.isEmpty()) {
                System.out.println("Friday > Invalid input! Did you make sure to type something?");
            } else {
                return str.trim();
            }
        }
    }

    public int countCompleted(boolean test) {
        int count = 0;
        if (test) {
            for (Task task : this.parent) {
                if (task.isDone()) {
                    count++;
                }
            }
        } else {
            for (Task task : this.parent) {
                if (!task.isDone()) {
                    count++;
                }
            }
        }
        return count;
    }
}