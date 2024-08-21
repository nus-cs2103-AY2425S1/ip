import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Will {

    static class Task {
        final String desc;
        boolean isDone;

        Task(String desc) {
            this.desc = desc;
            this.isDone = false;
        }

        void markDone() {
            this.isDone = true;
        }

        void markNotDone() {
            this.isDone = false;
        }

        String getType() {
            return "[ ]";
        }

        @Override
        public String toString() {
            return getType() + (isDone ? "[X] " : "[ ] ") + desc;
        }
    }

    static class ToDo extends Task {
        ToDo(String desc) {
            super(desc);
        }

        @Override
        String getType() {
            return "[T]";
        }
    }

    static class Deadline extends Task {
        String by;

        Deadline(String desc, String by) {
            super(desc);
            this.by = by;
        }

        @Override
        String getType() {
            return "[D]";
        }

        @Override
        public String toString() {
            return super.toString() + " (by: " + by + ")";
        }
    }

    static class Event extends Task {
        String from;
        String to;

        Event(String desc, String from, String to) {
            super(desc);
            this.from = from;
            this.to = to;
        }

        @Override
        String getType() {
            return "[E]";
        }

        @Override
        public String toString() {
            return super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }

    static void printTask(ArrayList<Task> tasks) {
        System.out.println("_____________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("_____________________________________");
    }

    static void markTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markDone();
            System.out.println("_____________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(index - 1));
        } else {
            System.out.println("No Task Found");
        }
        System.out.println("_____________________________________");
    }

    static void unmarkTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markNotDone();
            System.out.println("_____________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(index - 1));
        } else {
            System.out.println("No Task Found");
        }
        System.out.println("_____________________________________");
    }

    static void todoTask(ArrayList<Task> tasks, String desc){
        tasks.add(new ToDo(desc));
        System.out.println("_____________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("_____________________________________");
    }

    static void deadTask(ArrayList<Task> tasks, String desc, String by){
        tasks.add(new Deadline(desc, by));
        System.out.println("_____________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("_____________________________________");
    }

    static void eventTask(ArrayList<Task> tasks, String desc, String from, String to){
        tasks.add(new Event(desc, from, to));
        System.out.println("_____________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("_____________________________________");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String logo = "WILL";
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
        System.out.println("_____________________________________");

        while (true) {
            String userInput = scanner.nextLine();
            System.out.println("_____________________________________");

            if (Objects.equals(userInput, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (Objects.equals(userInput, "list")) {
                printTask(tasks);
                continue;
            }

            if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(5).trim());
                markTask(tasks, taskNumber);
                continue;
            }

            if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(7).trim());
                unmarkTask(tasks, taskNumber);
                continue;
            }

            if (userInput.startsWith("todo ")) {
                String desc = userInput.substring(5).trim();
                todoTask(tasks, desc);
                continue;
            }

            if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ");
                String desc = parts[0].trim();
                String by = parts[1].trim();
                deadTask(tasks, desc, by);
                continue;
            }

            if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ");
                String desc = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                eventTask(tasks, desc, from, to);
                continue;
            }

            tasks.add(new Task(userInput));
            System.out.println("added: " + userInput);
            System.out.println("_____________________________________");
        }
    }
}
