import java.util.ArrayList;
import java.util.Scanner;
public class Patrick {
    public static void main(String[] args) {
        String horizontalLine = "-----------------------------------------------------------------------\n";
        String greetingMsg = horizontalLine + "Hello! I'm Patrick, Spongebob bestie\nHow can I help you?\n" + horizontalLine;
        String exitMsg = horizontalLine + "Bye. Hope to see you again soon!\n" + horizontalLine;
        String taskMsg = "Got it. I've added this task:\n";
        String numTaskMsg1 = "Now you have ";
        String numTaskMsg2 = " tasks in the list.\n";
        String input;
        ArrayList list = new ArrayList();
        Task task;

        Scanner inputMsg = new Scanner(System.in);
        System.out.println(greetingMsg);
        do {
            input = inputMsg.nextLine();
            if (input.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    Task curr = (Task) list.get(i - 1);
                    System.out.println(i + ". " + curr.toString());
                }
            }
            else if (input.equals("bye")) {
                break;
            }
            else if (input.startsWith("mark")) {
                String taskNo = input.replace("mark", "").trim();
                int num = Integer.parseInt(taskNo);
                Task curr = (Task) list.get(num - 1);
                curr.markAsDone();

                System.out.println(horizontalLine + "Nice! I've marked this task as done:\n  "
                + curr.toString() + "\n" + horizontalLine);
            }
            else if (input.startsWith("unmark")) {
                String taskNo = input.replace("unmark", "").trim();
                int num = Integer.parseInt(taskNo);
                Task curr = (Task) list.get(num - 1);
                curr.markAsUndone();

                System.out.println(horizontalLine + "Nice! I've marked this task as not done yet:\n  "
                        + curr.toString() + "\n" + horizontalLine);
            }
            else if (input.startsWith("todo")) {
                String taskDescription = input.replace("todo ", "");
                task = new ToDo(taskDescription);
                list.add(task);
                System.out.println(horizontalLine + taskMsg + task.toString() + "\n" + numTaskMsg1
                        + list.size() + numTaskMsg2 + horizontalLine);
            }
            else if (input.startsWith("deadline")) {
                String newInput = input.replace("deadline ", "");
                String taskDescription = newInput.substring(0, newInput.indexOf("/") - 1);
                String deadline = newInput.substring(newInput.indexOf("/by ")).replace("/by ", "");
                task = new Deadline(taskDescription, deadline);
                list.add(task);
                System.out.println(horizontalLine + taskMsg + task.toString() + "\n" + numTaskMsg1
                        + list.size() + numTaskMsg2 + horizontalLine);
            }
            else if (input.startsWith("event")) {
                String newInput = input.replace("event ", "");
                String taskDescription = newInput.substring(0, newInput.indexOf("/from") - 1);
                String from = newInput.substring(newInput.indexOf("/from"), newInput.indexOf("/to") - 1).replace("/from", "");
                String to = newInput.substring(newInput.indexOf("/to ")).replace("/to ", "");
                task = new Event(taskDescription, from, to);
                list.add(task);
                System.out.println(horizontalLine + taskMsg + task.toString() + "\n" + numTaskMsg1
                        + list.size() + numTaskMsg2 + horizontalLine);
            }
            else {
                System.out.println(horizontalLine + "Wrong command. Please re-enter. \n" + horizontalLine);
            }
        } while (true);
        System.out.println(exitMsg);
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
            return "[" + getStatusIcon() + "] " + this.description;
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
            return "[D]" + super.toString() + " (by: " + this.by + ")";
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
            return "[E]" + super.toString() + " (from:" + this.from + " to: " + this.to + ")";
        }
    }
}
