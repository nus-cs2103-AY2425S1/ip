import com.sun.source.util.TaskListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;

public class Snowy {
    static String line ="____________________________________________________________";

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getDescription() {
            return this.description;
        }

        public void toggleStatus() {
            this.isDone = !this.isDone;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s",this.getStatusIcon(), this.getDescription());
        }

    }

    public static class ToDo extends Task {

        public ToDo(String description) {
            super(description);
        }
        @Override
        public String toString() {
            return "[E]" + super.toString();
        }
    }

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {
        protected String from;
        protected String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }

    public static class ToDoList {
        private ArrayList<Task> taskList;
        public ToDoList() {
            taskList = new ArrayList<Task>();
        }

        public void addTask(String d) {
            taskList.add(new Task(d));
        }

        public boolean isTaskDone(int i) {
            Task task = taskList.get(i);
            return task.isDone;
        }

        public void toggleTask(int i) {
            if (i >= 0 && i < taskList.size()) {
                Task task = taskList.get(i);
                task.toggleStatus();
                String str = String.format("%d. %s", i+1, task);
                System.out.println(str + "\n" + line + "\n");
            } else {
                System.out.println("Invalid task index.");
            }
        }
        public void displayList() {
            System.out.println(line+"\nYour to-do list");
            if (taskList.isEmpty()) {
                System.out.println("No tasks, make a to-do list first");
            }
            for (int i = 0; i < taskList.size(); i++) {
                String str = String.format("%d. %s",i+1, taskList.get(i));
                System.out.println(str + "\n");
            }
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        System.out.println(line + "\n Hello I'm Snowy\n What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        ToDoList taskList = new ToDoList();
        boolean isBye = false;

        while (!isBye) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                isBye = true;

            } else if (input.equalsIgnoreCase("list")) {
                taskList.displayList();

            } else if (input.split(" ")[0].equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]);

                if (!taskList.isTaskDone(index - 1)) {
                    System.out.println(line + "\nMarked as done\n");
                    taskList.toggleTask(index - 1);

                } else {
                    System.out.println("Task is already done");

                }
            } else if (input.split(" ")[0].equalsIgnoreCase("unmark")){
                int index = Integer.parseInt(input.split(" ")[1]);

                if (taskList.isTaskDone(index - 1)) {
                    System.out.println(line + "\nUnmarked task\n");
                    taskList.toggleTask(index - 1);

                } else {
                    System.out.println("Cannot unmark task as it is not done");

                }
            } else {
                System.out.println(line + "\nYou said: " + input + "\n" + line);
                taskList.addTask(input);

            }
        }
        System.out.println("Bye. See you next time!");
    }
}
