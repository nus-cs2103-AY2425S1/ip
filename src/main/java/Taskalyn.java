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

            try {
                switch (command) {
                    case "bye":
                        taskManager.printLines("Bye. Hope to see you again soon!");
                        return;

                    case "list":
                        taskManager.listTasks();
                        break;

                    case "mark":
                        if (completeString.length != 2) {
                            throw new CommandFormatException("Aw... mark command must have just 2 arguments: the command, and the task number.");
                        }
                        try {
                            Integer i = Integer.parseInt(completeString[1]);
                            if (i <= taskManager.getTaskSize() + 1) {
                                taskManager.completeTask(i);
                            } else {
                                throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
                            }
                        } catch (NumberFormatException e) {
                            throw new CommandFormatException("Aw... mark command must be followed by an integer");
                        } catch (IndexOutOfBoundsException e) {
                            throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
                        } catch (Exception e) {
                            throw new CommandFormatException("Aw... mark command must have just 2 arguments: the command, and the task number.");
                        }
                        break;

                    case "unmark":
                        if (completeString.length != 2) {
                            throw new CommandFormatException("Aw... unmark command must have 2 arguments: the command and the task number.");
                        }
                        try {
                            Integer i = Integer.parseInt(completeString[1]);
                            if (i <= taskManager.getTaskSize() + 1) {
                                taskManager.incompleteTask(i);
                            } else {
                                throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
                            }
                        } catch (NumberFormatException e) {
                            throw new CommandFormatException("Aw... unmark command must be followed by an integer");
                        } catch (IndexOutOfBoundsException e) {
                            throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
                        } catch (Exception e) {
                            throw new CommandFormatException("Aw... unmark command must have just 2 arguments: the command, and the task number.");
                        }
                        break;

                    case "todo":
                        if (completeString.length != 2) {
                            throw new CommandFormatException("Aw... todo command must contain 2 arguments: todo and the task at hand!");
                        }
                        try {
                            taskManager.addTask(new TodoTask(completeString[1]));
                        } catch (Exception e) {
                            throw new CommandFormatException("Aw... todo command must contain 2 arguments: todo and the task at hand!");
                        }
                        break;

                    case "deadline":
                        if (completeString.length != 2) {
                            throw new CommandFormatException("Aw... your deadline command is incomplete. Try this: deadline {task} /by {deadline}");
                        }
                        try {
                            if (completeString[1].contains("/by")) {
                                String[] deadlineString = completeString[1].split(" /by ", 2);
                                if (deadlineString.length == 2) {
                                    taskManager.addTask(new DeadlineTask(deadlineString[0], deadlineString[1]));
                                } else {
                                    throw new CommandFormatException("Aw... your deadline command must contain the task, /by, and the deadline.");
                                }
                            } else {
                                throw new CommandFormatException("Aw... your deadline command doesn't have a deadline date set!");
                            }
                        } catch (Exception e) {
                            throw new CommandFormatException("Aw... your deadline command is incorrect. Try this: deadline {task} /by {deadline}");
                        }
                        break;

                    case "event":
                        if (completeString.length != 2) {
                            throw new CommandFormatException("Aw your event command is incomplete. Try this: event {event} /from {from} /to {to}");
                        }
                        try {
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
                                        else {
                                            throw new CommandFormatException("Aw... you might be missing a from or to date!");
                                        }
                                    }
                                    else {
                                        throw new CommandFormatException("Aw... you might be missing the to date!");
                                    }
                                }
                                else {
                                    throw new CommandFormatException("Aw... you might be missing the task description and /from date!");
                                }
                            }
                            else {
                                throw new CommandFormatException("Aw... you might be missing the /from date!");
                            }
                        } catch (Exception e) {
                            throw new CommandFormatException("Aw... your event command might be incorrect. Try this: event {event} /from {from} /to {to}");
                        }
                        break;

                    default:
                        taskManager.printLines("Sorry bro, no clue what you're saying!");
                        break;
                }
            } catch (NoSuchTaskException | CommandFormatException e) {
                taskManager.printLines(e.getMessage());
            }
        }
    }
}
