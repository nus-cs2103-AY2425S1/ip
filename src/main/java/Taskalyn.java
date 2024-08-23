import jdk.jfr.Event;

import java.util.Scanner;

public class Taskalyn {
    public static void main(String[] args) {

        // Initialising task manager
        TaskManager taskManager = new TaskManager();

        // Initialising scanner
        Scanner scanner = new Scanner(System.in);

        // Greeting user
        taskManager.printLines("Hey! I'm Taskalyn, your personal Task Manager :)\n" +
                "    What can I do for you?");

        // Conditions
        while (true) {
            String input = scanner.nextLine().trim();

            String[] completeString = input.split(" ", 2);

            String command = completeString[0];

            switch (command) {
                case "bye":
                    taskManager.printLines("Bye. Hope to see you again soon!");
                    break;

                case "list":
                    taskManager.listTasks();
                    break;

                case "mark":
                    if (completeString.length == 2) {
                        try {
                            Integer i = Integer.parseInt(completeString[1]);
                            if (i <= taskManager.getTaskSize() + 1) {
                                taskManager.completeTask(i);
                            }
                            else {
                                taskManager.printLines("Aw, that task doesn't exist. Try again!");
                            }
                        } catch (NumberFormatException e) {
                            taskManager.printLines("Aw... mark command must be followed by an integer");
                        }
                    }
                    else {
                        taskManager.printLines("Aw... mark command must have just 2 arguments: the command, and the task number.");
                    }
                    break;

                case "unmark":
                    if (completeString.length == 2) {
                        try {
                            Integer i = Integer.parseInt(completeString[1]);
                            if (i <= taskManager.getTaskSize() + 1) {
                                taskManager.incompleteTask(i);
                            }
                            else {
                                taskManager.printLines("Aw, that task doesn't exist. Try again!");
                            }
                        } catch (NumberFormatException e) {
                            taskManager.printLines("Aw... unmark command must be followed by an integer");
                        }
                    }
                    else {
                        taskManager.printLines("Aw... unmark command must have just 2 arguments: the command, and the task number.");
                    }
                    break;

                case "todo":
                    if (completeString.length == 2) {
                        taskManager.addTask(new TodoTask(completeString[1]));
                    }
                    else {
                        taskManager.printLines("Aw... task command must contain 2 arguments: todo and the task at hand!");
                    }
                    break;

                case "deadline":
                    if (completeString.length == 2) {
                        if (completeString[1].contains("/by")) {
                            String[] deadlineString = completeString[1].split(" /by ", 2);
                            if (deadlineString.length == 2) {
                                taskManager.addTask(new DeadlineTask(deadlineString[0], deadlineString[1]));
                            }
                            else {
                                taskManager.printLines("Aw... deadline command must contain just the task, /by, and the deadline after deadline command!");
                            }
                        }
                        else {
                            taskManager.printLines("Aw... your deadline commands doesn't have a deadline date set!");
                        }
                    }
                    else {
                        taskManager.printLines("Aw... your deadline command is incomplete. Try this: deadline {task} /by {deadline}");
                    }
                    break;

                case "event":
                    if (completeString.length == 2) {
                        if (completeString[1].contains("/from")) {
                            String[] eventString = completeString[1].split(" /from ", 2);
                            if (eventString.length == 2) {
                                String taskString = eventString[0];
                                if (eventString[1].contains("/to")) {
                                    String[] dates = eventString[1].split(" /to ", 2);
                                    if (dates.length == 2) {
                                        String fromDate = dates[0];
                                        String toDate = dates[1];
                                        taskManager.addTask(new EventTask(taskString, fromDate, toDate));
                                    }
                                }
                            }
                        }
                    }
                    break;

                default:
                    taskManager.printLines("Sorry bro, no clue what you're saying!");
                    break;
            }

            if (command.equals("bye")) {
                break;
            }
        }

        scanner.close();

    }
}
