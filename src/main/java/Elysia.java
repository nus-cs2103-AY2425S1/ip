import java.util.Objects;
import java.util.Scanner;

public class Elysia {


    public static void main(String[] args) {

        class Task {
            protected String description;
            protected boolean isDone;

            public Task(String description) {
                this.description = description;
                this.isDone = false;
            }
            public void updateStatus(boolean status) {
                isDone = status;
            }

            @Override
            public String toString() {
                return "[" + (isDone ? "X" : " ") + "] " + description;
            }
        }

        class Todo extends Task {
            public Todo(String description) {
                super(description);
            }

            @Override
            public String toString() {
                return "[T]" + super.toString() + "\n";
            }
        }

        class Deadline extends Task {
            protected String by;
            public Deadline(String description, String by) {
                super(description);
                this.by = by;
            }

            @Override
            public String toString() {
                return "[D]" + super.toString() + " (by: " + by + ")\n";
            }
        }

        class Event extends Task {
            protected String from;
            protected String to;

            public Event(String description, String from, String to) {
                super(description);
                this.from = from;
                this.to = to;
            }

            @Override
            public String toString() {
                return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")\n";
            }
        }

        class TaskList {
            private Task[] list;
            private int listPointer = 0;

            public TaskList(int size) {
                list = new Task[size];
            }

            public void addTask(Task task) {
                list[listPointer] = task;
                listPointer++;
            }

            public void markTask(int taskNumber) {
                list[taskNumber-1].updateStatus(true);
            }

            public void unmarkTask(int taskNumber) {
                list[taskNumber-1].updateStatus(false);
            }

            public String printTask(int taskNumber) {
                return list[taskNumber-1].toString();
            }

            public String size() {
                return "" + listPointer;
            }

            @Override
            public String toString() {
                String output = "";
                for (int i = 0; i < listPointer; i++) {
                    output += (i+1) + "." + list[i].toString();
                }
                return output;
            }
        }

        String lines = "________________________________________________________________________________________";

        System.out.println(lines);
        System.out.println("Hi~! I'm Elysia! As you can see, I'm a girl as beautiful as as flower!");
        System.out.println("How can I help you today? I'm all ears! \n" + lines + "\n");

        Scanner command = new Scanner(System.in);
        String input = "";
        String output = "";

        TaskList tasklist = new TaskList(100);

        while (!Objects.equals(input, "bye")) {
            input = command.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                output = "Here's your list! \n";
                output += tasklist.toString();
                System.out.println(lines + "\n" + output + lines + "\n");
            } else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.substring(5));
                tasklist.markTask(taskNumber);
                output = "Amazing! You've completed this task! \n";
                output += tasklist.printTask(taskNumber);
                System.out.println(lines + "\n" + output + lines + "\n");
            } else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.substring(7));
                tasklist.unmarkTask(taskNumber);
                output = "Making a pretty girl undo her work is not good for her health! \n";
                output += tasklist.printTask(taskNumber);
                System.out.println(lines + "\n" + output + lines + "\n");
            } else if (input.startsWith("todo")) {
                Todo newTodo = new Todo(input);
                tasklist.addTask(newTodo);
                output = "Added the task below to your list~\n" + newTodo.toString();
                output += "Wow! You now have " + tasklist.size() + " tasks in your list!\n";
                System.out.println(lines + "\n" + output + lines + "\n");
            } else if (input.startsWith("deadline")) {
                int index = input.indexOf("/");
                Deadline newDeadline = new Deadline(input.substring(9,index),
                        input.substring(index + 4));
                tasklist.addTask(newDeadline);
                output = "Added the task below to your list~\n" + newDeadline.toString();
                output += "Wow! You now have " + tasklist.size() + " tasks in your list!\n";
                System.out.println(lines + "\n" + output + lines + "\n");
            } else if (input.startsWith("event")) {
                int index0 = input.indexOf("/");
                int index1 = input.lastIndexOf("/");
                Event newEvent = new Event(input.substring(6,index0),
                        input.substring(index0 + 6, index1),
                        input.substring(index1+4));
                tasklist.addTask(newEvent);
                output = "Added the task below to your list~\n" + newEvent.toString();
                output += "Wow! You now have " + tasklist.size() + " tasks in your list!\n";
                System.out.println(lines + "\n" + output + lines + "\n");
            } else {
                output = "something went wrong :(";
                System.out.println(lines + "\n" + output + "\n" + lines + "\n");
            }
        }

        System.out.println(lines + "\n Aww, going already? Don't miss me too much, ok? \n" + lines);
    }
}
