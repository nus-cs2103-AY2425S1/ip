import com.sun.source.util.TaskListener;

import java.sql.SQLOutput;
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

        public void addTask(Task t) {
            taskList.add(t);
            String str = String.format(" %s\nNow you have %d task(s) in your list\n" + line, t, this.taskList.size());
            System.out.println(str);
        }

        public boolean isTaskDone(int i) {
            Task task = taskList.get(i);
            return task.isDone;
        }

        public void toggleTask(int i) throws SnowyException {
            if (i >= 0 && i < taskList.size()) {
                Task task = taskList.get(i);
                task.toggleStatus();
                String str = String.format("%d. %s", i+1, task);
                System.out.println(str + "\n" + line + "\n");
            } else {
                throw new SnowyException("Invalid index.");
            }
        }
        public void displayList() throws SnowyException {
            if (taskList.isEmpty()) {
                throw new SnowyException("No tasks, make a list first.");
            }
            System.out.println(line+"\nYour list of tasks");
            for (int i = 0; i < taskList.size(); i++) {
                String str = String.format("%d. %s",i+1, taskList.get(i));
                System.out.println(str);
            }
            System.out.println(line);
        }

        public void deleteTask(int index) throws SnowyException {
            if (taskList.isEmpty()) {
                throw new SnowyException("No tasks in list.");
            }
            if (index >=0 && index < taskList.size()) {
                Task task = taskList.get(index - 1);
                System.out.println("Removed task:\n " + task);
                taskList.remove(index - 1);
                System.out.printf("\nNow you have %d task(s) in your list.\n", this.taskList.size());

            } else {
                throw new SnowyException("Invalid index.");
            }
        }

    }

    public static class SnowyException extends Exception {
        protected String formatted;
        public SnowyException(String message) {
            super(message);
            formatted = line + "\nmessage\n" + line;
        }
    }

    public static void main(String[] args) {
        System.out.println(line + "\n Hello I'm Snowy\n What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        ToDoList taskList = new ToDoList();
        boolean isBye = false;

        while (!isBye) {
            try {
                String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                isBye = true;

            } else if (input.equalsIgnoreCase("list")) {
                taskList.displayList();

            } else if (input.split(" ")[0].equalsIgnoreCase("mark")) {
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                    throw new SnowyException("Please provide a task number");
                }
                int index = Integer.parseInt(parts[1]);
                if (taskList.isTaskDone(index - 1)) {
                    throw new SnowyException("Task is already done");

                }
                System.out.println(line + "\nMarked as done\n");
                taskList.toggleTask(index - 1);

            } else if (input.split(" ")[0].equalsIgnoreCase("unmark")){
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                    throw new SnowyException("Please provide a task number");
                }
                int index = Integer.parseInt(parts[1]);
                if (!taskList.isTaskDone(index - 1)) {
                    throw new SnowyException("Cannot unmark task as it is not done");

                }
                System.out.println(line + "\nUnmarked task\n");
                taskList.toggleTask(index - 1);

            } else if (input.startsWith("todo")){
                String description = input.substring(4).trim();
                System.out.println(line);
                if (description.isEmpty() || description.equals(" ")) {
                    throw new SnowyException("Please provide a description");

                }
                ToDo todo = new ToDo(description);
                System.out.println("Added a to do to your list of tasks");
                taskList.addTask(todo);

            }  else if (input.startsWith("deadline")) {
                String after = input.substring(8);
                boolean hasDate = input.contains("/by");
                System.out.println(line);

                if (!hasDate) {
                    throw new SnowyException("Please provide a deadline");
                }

                String[] parts = after.split(" /by ", 2);

                if (parts.length < 2) {
                    throw new SnowyException("Please provide a deadline.");
                }

                String description = parts[0].trim();
                String date = parts[1].trim();


                if (description.isEmpty()) {
                    throw new SnowyException("Please provide a description");
                }
                if (date.isEmpty()) {
                    throw new SnowyException("Please provide a deadline");
                }

                Deadline deadline = new Deadline(description, date);
                System.out.println("Added a task with deadline to your list of tasks");
                taskList.addTask(deadline);

            } else if (input.startsWith("event")) {
                String after = input.substring(5);
                boolean hasFrom = input.contains("/from");
                boolean hasTo = input.contains("/to");

                System.out.println(line);

                if (!hasFrom || !hasTo) {
                    throw new SnowyException("Please provide both from and to");
                }

                String description = after.split(" /from ", 2)[0];
                String afterDesc = after.split(" /from ", 2)[1];

                String from = afterDesc.split("/to", 2)[0].trim();
                String to = afterDesc.split("/to", 2)[1].trim();

                if (description.isEmpty() || description.equals(" ")) {
                    throw new SnowyException("Please provide a description");
                }
                if (from.isEmpty() || from.equals(" ")) {
                    throw new SnowyException("Please provide a from");
                }
                if (to.isEmpty() || to.equals(" ")) {
                    throw new SnowyException("Please provide a to");
                }

                Event event = new Event(description, from, to);
                System.out.println("Added an event to your list of tasks");
                taskList.addTask(event);

            } else if (input.startsWith("delete")) {
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                    throw new SnowyException("Please provide a task number.");
                }
                int index = Integer.parseInt(parts[1]);
                taskList.deleteTask(index);
                System.out.println(line);
            } else {
                throw new SnowyException("Sorry, I do not understand.");
            }
        } catch (SnowyException e) {
                System.out.println(e.getMessage());
            }
        }  System.out.println("Bye. See you next time!");
    }
}
