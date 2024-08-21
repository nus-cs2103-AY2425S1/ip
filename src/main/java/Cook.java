import java.util.Scanner;
import java.util.ArrayList;

public class Cook {
    public static void main(String[] args) {
        // Solution below adapted from https://www.patorjk.com/software/taag/#p=author&v=0&f=Avatar&t=Cook
        String logo = """ 
                          ____  ____  ____  _  __
                         /   _\\/  _ \\/  _ \\/ |/ /
                         |  /  | / \\|| / \\||   /\s
                         |  \\__| \\_/|| \\_/||   \\\s
                         \\____/\\____/\\____/\\_|\\_\\
                                                \s
                         """;
        System.out.print(logo);
        formatting("Hello, I'm Cook!\nWhat can I do for you?");

        // Solution below inspired by https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html
        ArrayList<Task> toDoList = new ArrayList<Task>();

        while (true) {
            // Solution below inspired by https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            String[] commands = userInput.split(" ");
            // Breaks loop
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
            // Prints out to do list
            else if (userInput.equalsIgnoreCase("list")) {
                StringBuilder taskList = new StringBuilder();
                for (int i = 0; i < toDoList.size(); i++) {
                    int taskNo = i + 1;
                    Task task = toDoList.get(i);
                    taskList.append(taskNo).append(".[").append(task.getStatusIcon()).append("] ").append(task.toString()).append("\n");
                }
                formatting(taskList.toString().stripTrailing());
            }
            // Mark/Unmark tasks
            else if (commands.length == 2 && (commands[0].contains("mark"))) {
                try {
                    int taskIndex = Integer.parseInt(commands[1]) - 1;
                    Task taskToMark = toDoList.get(taskIndex);
                    boolean toMark = commands[0].equalsIgnoreCase("mark");
                    boolean success = taskToMark.mark(toMark);
                    String done = toMark ? "done" : "not done";
                    if (success) {
                        formatting("Alright, I've marked this task as " + done + ":\n   [" + taskToMark.getStatusIcon() + "] " + taskToMark.toString());
                    }
                    else {
                        formatting("Oh no! The task is already marked as " + done + ":\nDid you intend to do something else?");
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    formatting("Oh no! I can't find the task you are referring to... Sorry!");
                }
            }
            else {
                Task newTask = new Task(userInput);
                toDoList.add(newTask);
                formatting("I've added " + userInput + " to your to do list!");
            }
        }

        formatting("Bye. Hope to see you again soon!");
    }

    public static void formatting(String text) {
        System.out.println("____________________________________________________________");
        System.out.println(text);
        System.out.println("____________________________________________________________");
    }
}
