import java.util.Scanner;
import java.util.ArrayList;

public class JackBean {
    public static String horizontalLine = "____________________________________________________________";
    public static String greeting = "Hello homie! I'm JackBean, a chatbot designed to help you with your daily tasks!\nHow may I help you today my homie?";
    public static String exitMessage = "Bye homie! Come back if you need anything else. JackBean, signing off!";
    public static ArrayList<Task> taskList = new ArrayList<Task>();

    // Partial solution reused from course website
    // extra comment here to aid with tagging A-Classes
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

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsUndone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + description;
        }
    }

    // this class was generated by github copilot
    public static class Deadline extends Task {
        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D][" + getStatusIcon() + "] " + description + " (by: " + by + ")";
        }
    }

    // this class was generated by github copilot, but had to code from and to functionality
    public static class Event extends Task {
        protected String from;
        protected String to;

        public Event(String description, String at) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E][" + getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
        }
    }

    // this class was generated by github copilot
    public static class Todo extends Task {
        public Todo (String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T][" + getStatusIcon() + "] " + description;
        }
    }

    public static void main(String[] args) {
        System.out.println(horizontalLine);
        System.out.println(greeting);
        System.out.println(horizontalLine);

        Scanner userInput = new Scanner(System.in);

        // GitHub CoPilot helped a lot with auto-complete (except personalisation)
        // it is actually really smart haha
        while (true) {
            String input = userInput.nextLine();

            if (input.equalsIgnoreCase("bye")) { // I added equalsIgnoreCase() by myself
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(horizontalLine);
                System.out.println("Yo homie!, here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
                System.out.println(horizontalLine);
            } else if (input.toLowerCase().startsWith("mark")) {
                // first use split and then parse the integer
                String[] splitInput = input.split(" ");
                int taskNumber = Integer.parseInt(splitInput[1]);

                // check if task is already done
                if (taskList.get(taskNumber - 1).isDone) {
                    System.out.println(horizontalLine);
                    System.out.println("This task is already done my homie:");
                    System.out.println(taskList.get(taskNumber - 1));
                    System.out.println(horizontalLine);
                    continue;
                }

                // now handle implementation
                taskList.get(taskNumber - 1).markAsDone();
                System.out.println(horizontalLine);
                System.out.println("LESGOOO homie! Good job on finishing this task:");
                System.out.println(taskList.get(taskNumber - 1));
                System.out.println(horizontalLine);
            } else if (input.toLowerCase().startsWith("unmark")) {
                // first use split and then parse the integer
                String[] splitInput = input.split(" ");
                int taskNumber = Integer.parseInt(splitInput[1]);

                // check if task is already undone
                if (!taskList.get(taskNumber - 1).isDone) {
                    System.out.println(horizontalLine);
                    System.out.println("This task hasn't been marked done yet my homie:");
                    System.out.println(taskList.get(taskNumber - 1));
                    System.out.println(horizontalLine);
                    continue;
                }

                // now handle implementation
                taskList.get(taskNumber - 1).markAsUndone();
                System.out.println(horizontalLine);
                System.out.println("All good my homie! You've got this, I've undone this task:");
                System.out.println(taskList.get(taskNumber - 1));
                System.out.println(horizontalLine);
            } else {
                taskList.add(new Task(input));
                System.out.println(horizontalLine);
                System.out.println("Got it homie! I've added this task:\n" + input);
                System.out.println("Homie!, you have " + taskList.size() + " task(s) in the list now.");
                System.out.println(horizontalLine);
            }
        }

        System.out.println(horizontalLine);
        System.out.println(exitMessage);
        System.out.println(horizontalLine);
    }
}
