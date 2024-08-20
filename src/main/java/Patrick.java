import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Patrick {
    static String horizontalLine = "____________________________________________________________\n";
    static String greetingMsg = horizontalLine + "Hello! I'm Patrick, Spongebob bestie\nHow can I help you?\n" + horizontalLine;
    static String exitMsg = horizontalLine + "Bye. Hope to see you again soon!\n" + horizontalLine;
    static String taskMsg = "Got it. I've added this task:\n";
    static String numTaskMsg1 = "Now you have ";
    static String numTaskMsg2 = " tasks in the list.\n";
    static String input;
    static ArrayList list = new ArrayList();
    static Task task;

    public static void main(String[] args) {
        Scanner inputMsg = new Scanner(System.in);
        System.out.println(greetingMsg);
        do {
            input = inputMsg.nextLine();
            if (input.equals("list")) {
                System.out.println(horizontalLine + "Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    Task curr = (Task) list.get(i - 1);
                    System.out.println(i + "." + curr.toString());
                }
                System.out.println(horizontalLine);
            }
            else if (input.equals("bye")) {
                break;
            }
            else if (input.startsWith("mark")) {
                try {
                    Mark(input);
                } catch (PatrickException e) {
                    System.out.print(horizontalLine + e.toString() + "\n" + horizontalLine);
                }
            }
            else if (input.startsWith("unmark")) {
                try {
                    Unmark(input);
                } catch (PatrickException e) {
                    System.out.println(horizontalLine + e.toString() + "\n" + horizontalLine);
                }
            }
            else if (input.startsWith("todo")) {
                try {
                    ToDoTask(input);
                } catch (PatrickException e) {
                    System.out.print(horizontalLine + e.toString() + "\n" + horizontalLine);
                }
            }
            else if (input.startsWith("deadline")) {
                try {
                    DeadlineTask(input);
                } catch (PatrickException e) {
                    System.out.print(horizontalLine + e.toString() + "\n" + horizontalLine);
                }
            }
            else if (input.startsWith("event")) {
                try {
                    EventTask(input);
                } catch (PatrickException e) {
                    System.out.print(horizontalLine + e.toString() + "\n" + horizontalLine);
                }
            }
            else {
                System.out.println(horizontalLine + "What are you trying to say man. Re-enter your command \n" + horizontalLine);
            }
        } while (true);
        System.out.println(exitMsg);
    }

    private static void ToDoTask(String input) throws PatrickException {
        String taskDescription = input.replace("todo", "");
        if (taskDescription.isEmpty()) {
            throw new PatrickException("Description of a todo cannot be empty!!");
        } else {
            task = new ToDo(taskDescription);
            list.add(task);
            System.out.println(horizontalLine + taskMsg + task.toString() + "\n" + numTaskMsg1
                    + list.size() + numTaskMsg2 + horizontalLine);
        }
    }

    private static void DeadlineTask(String input) throws PatrickException {
        String newInput = input.replace("deadline", "");
        if (newInput.isEmpty()) {
            throw new PatrickException("Deadline Task Details cannot be empty!!");
        }
        else if (!newInput.contains("/by")) {
            throw new PatrickException("You are missing a '/by' in ur details!!");
        }
        else {
            String taskDescription = newInput.substring(0, newInput.indexOf("/") - 1);
            if (taskDescription.isEmpty()) {
                throw new PatrickException("Deadline Task Description cannot be empty!!");
            }
            else {
                String deadline = newInput.substring(newInput.indexOf("/by")).replace("/by", "");
                if (deadline.isEmpty()) {
                    throw new PatrickException("Deadline Task deadline cannot be empty!!");
                }
                else {
                    task = new Deadline(taskDescription, deadline);
                    list.add(task);
                    System.out.println(horizontalLine + taskMsg + task.toString() + "\n" + numTaskMsg1
                            + list.size() + numTaskMsg2 + horizontalLine);
                }
            }
        }
    }

    private static void EventTask(String input) throws PatrickException {
        String newInput = input.replace("event", "");
        if (newInput.isEmpty()) {
            throw new PatrickException("Event Task Details cannot be empty!!");
        } else if (!newInput.contains("/from")) {
            throw new PatrickException("You are missing a '/from' in your details!!");
        } else if (!newInput.contains("/to")) {
            throw new PatrickException("You are missing a 'to' in your details!!");
        } else {
            String taskDescription = newInput.substring(0, newInput.indexOf("/from") - 1);
            if (taskDescription.isEmpty()) {
                throw new PatrickException("Event Task Description cannot be empty!!");
            } else {
                String from = newInput.substring(newInput.indexOf("/from"), newInput.indexOf("/to") - 1).replace("/from", "");
                String to = newInput.substring(newInput.indexOf("/to")).replace("/to", "");
                if (from.isEmpty()) {
                    throw new PatrickException("You are missing 'from' information from your details!!");
                } else if (to.isEmpty()) {
                    throw new PatrickException("You are missing 'to' information from your details!!");
                } else {
                    task = new Event(taskDescription, from, to);
                    list.add(task);
                    System.out.println(horizontalLine + taskMsg + task.toString() + "\n" + numTaskMsg1
                            + list.size() + numTaskMsg2 + horizontalLine);
                }
            }
        }
    }

    private static void Mark(String input) throws PatrickException {
        String taskNo = input.replace("mark", "").trim();
        if (taskNo.isEmpty()) {
            throw new PatrickException("Task Number cannot be empty!!");
        } else {
            int num = Integer.parseInt(taskNo);
            if (num > list.size()) {
                throw new PatrickException("Invalid Task Number!!");
            } else {
                Task curr = (Task) list.get(num - 1);
                if (curr.isDone) {
                    throw new PatrickException("You cannot mark a completed task!!");
                } else {
                    curr.markAsDone();
                    System.out.println(horizontalLine + "Nice! I've marked this task as done:\n  "
                            + curr.toString() + "\n" + horizontalLine);
                }
            }
        }
    }

    private static void Unmark(String input) throws PatrickException {
        String taskNo = input.replace("unmark", "").trim();
        if (taskNo.isEmpty()) {
            throw new PatrickException("Task Number cannot be empty!!");
        } else {
            int num = Integer.parseInt(taskNo);
            if (num > list.size()) {
                throw new PatrickException("Invalid Task Number!!");
            } else {
                Task curr = (Task) list.get(num - 1);
                if (!curr.isDone) {
                    throw new PatrickException("You cannot unmark an incomplete task!!");
                } else {
                    curr.markAsUndone();
                    System.out.println(horizontalLine + "Nice! I've marked this task as as not done yet:\n  "
                            + curr.toString() + "\n" + horizontalLine);
                }
            }
        }
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsUndone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "]" + this.description;
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
            return "[D]" + super.toString() + " (by:" + this.by + ")";
        }
    }

    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Event extends Task {
        protected String from, to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from:" + this.from + " to:" + this.to + ")";
        }
    }

    public static class PatrickException extends Exception {
        String str;
        public PatrickException(String str) {
            super(str);
        }
    }
}
