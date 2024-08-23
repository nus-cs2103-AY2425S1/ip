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
                return "[" + (isDone ? "X" : " ") + "] " + description + "\n";
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
            } else {
                Task newTask = new Task(input);
                tasklist.addTask(newTask);
                output = "Added " + input + " to your list~";
                System.out.println(lines + "\n" + output + "\n" + lines + "\n");
            }
        }

        System.out.println(lines + "\n Aww, going already? Don't miss me too much, ok? \n" + lines);
    }
}
