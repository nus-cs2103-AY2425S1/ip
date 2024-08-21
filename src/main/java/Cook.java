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
        ArrayList<Task> taskList = new ArrayList<Task>();

        while (true) {
            // Solution below inspired by https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            String[] commands = userInput.split(" ");
            // Breaks loop
            if (userInput.equalsIgnoreCase("bye")) {
                formatting("Bye. Hope to see you again soon!");
                break;
            }
            // Prints out to do list
            else if (userInput.equalsIgnoreCase("list")) {
                StringBuilder taskListString = new StringBuilder();
                for (int i = 0; i < taskList.size(); i++) {
                    int taskNo = i + 1;
                    Task task = taskList.get(i);
                    taskListString.append(taskNo).append(".").append(task.toString()).append("\n");
                }
                formatting(taskListString.toString().stripTrailing());
            }
            // Mark/Unmark tasks
            else if (commands.length == 2 && (commands[0].equalsIgnoreCase("mark"))) {
                try {
                    int taskIndex = Integer.parseInt(commands[1]) - 1;
                    Task taskToMark = taskList.get(taskIndex);
                    boolean toMark = commands[0].equalsIgnoreCase("mark");
                    boolean success = taskToMark.mark(toMark);
                    String done = toMark ? "done" : "not done";
                    if (success) {
                        formatting("Alright, I've marked this task as " + done + ":\n   " + taskToMark.toString());
                    } else {
                        formatting("Oh no! The task is already marked as " + done + ":\nDid you intend to do something else?");
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    formatting("Oh no! I can't find the task you are referring to... Sorry!");
                }
            }
            // Tasks
            else {
                if (commands[0].equalsIgnoreCase("todo")) {
                    Task newTask = new ToDo(commands[1]);
                    taskList.add(newTask);
                }
                else if (commands[0].equalsIgnoreCase("deadline")) {
                    int indexOfBy = findFirstCommand(commands, "/by");
                    if (indexOfBy == -1) {
                        formatting("Oh no! I can't see the deadline of the task... Perhaps you can try again?");
                        continue;
                    }
                    StringBuilder deadlineDesc = new StringBuilder();
                    for (int i = 1; i < indexOfBy; i++) {
                        deadlineDesc.append(commands[i]).append(" ");
                    }
                    deadlineDesc.deleteCharAt(deadlineDesc.length() - 1);

                    StringBuilder deadlineDate = new StringBuilder();
                    for (int i = indexOfBy + 1; i < commands.length; i++) {
                        deadlineDate.append(commands[i]).append(" ");
                    }
                    deadlineDate.deleteCharAt(deadlineDate.length() - 1);

                    Task newTask = new Deadline(deadlineDesc.toString(), deadlineDate.toString());
                    taskList.add(newTask);
                }
                else if (commands[0].equalsIgnoreCase("event")) {
                    int indexOfFrom = findFirstCommand(commands, "/from");
                    int indexOfTo = findFirstCommand(commands, "/to");
                    if (indexOfFrom == -1 || indexOfTo == -1) {
                        formatting("Oh no! I don't know when the event is held... Perhaps you can try again?");
                        continue;
                    }
                    StringBuilder eventDesc = new StringBuilder();
                    for (int i = 1; i < indexOfFrom; i++) {
                        eventDesc.append(commands[i]).append(" ");
                    }
                    eventDesc.deleteCharAt(eventDesc.length() - 1);

                    StringBuilder startDateTime = new StringBuilder();
                    for (int i = indexOfFrom + 1; i < indexOfTo; i++) {
                        startDateTime.append(commands[i]).append(" ");
                    }
                    startDateTime.deleteCharAt(startDateTime.length() - 1);

                    StringBuilder endDateTime = new StringBuilder();
                    for (int i = indexOfTo + 1; i < commands.length; i++) {
                        endDateTime.append(commands[i]).append(" ");
                    }
                    endDateTime.deleteCharAt(endDateTime.length() - 1);

                    Task newTask = new Event(eventDesc.toString(), startDateTime.toString(), endDateTime.toString().stripTrailing());
                    taskList.add(newTask);
                }
                else {
                    formatting("I don't understand what you mean... Could you say it again?");
                    continue;
                }
                formatting("I've added the " + commands[0] + " to your task list!\nYou now have " + taskList.size() + " tasks in the list!");
            }
        }
    }

    public static void formatting(String text) {
        System.out.println("____________________________________________________________");
        System.out.println(text);
        System.out.println("____________________________________________________________");
    }

    public static int findFirstCommand(String[] commands, String command) {
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].equalsIgnoreCase(command)) {
                return i;
            }
        }
        return -1;
    }
}
