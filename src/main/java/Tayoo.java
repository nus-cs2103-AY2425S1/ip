import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Tayoo {
    private static final ArrayList<Task> tasklist = new ArrayList<Task>(100);
    private static final String[] exitCodes = {"EXIT", "BYE", "GOODBYE", "CLOSE"};
    public static void main(String[] args) {
        String name = "Tayoo";
        Scanner scanner = new Scanner(System.in);

        //Introduce self
        printText("Hello! I'm " + name + "\nWhat can I do for you?\n");

        //Await command
        awaitCommand(scanner);



        //Exit programme
        scanner.close();
        System.exit(0);
    }
    private static void printHoriLine() {
        System.out.println("\t_______________________________________");
    }

    private static void printText(String text) {
        printHoriLine();
        System.out.println(text);
        printHoriLine();
        System.out.println("\n");
    }

    private static void exitBot(Scanner scanner) {
        printText("\tBye. Hope to see you again soon!\n");
        scanner.close();
        System.exit(0);
    }

    private static void awaitCommand(Scanner scanner) {
        while(true) {
            String command = scanner.nextLine().trim();
            String input = command.toUpperCase();

            if (Arrays.asList(exitCodes).contains(input)) {
                exitBot(scanner);
            } else if (input.equals("LIST")) {
                printTaskList();
            } else if (input.startsWith("MARK ")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(5).trim()) - 1;
                    try {
                        if (tasklist.get(taskNumber).markAsDone()) {
                            printText("Nice! I've marked this task as done:\n" + tasklist.get(taskNumber));
                        } else {
                            printText("Hey! You've done that one already!\n" + tasklist.get(taskNumber));
                        }
                    } catch (IndexOutOfBoundsException e) {
                        if (taskNumber < 0) {
                            System.out.println("Dude, your task list starts from 1! Input a number that's above 0!");
                        } else if (taskNumber > 100) {
                            System.out.println("My task list can't go that high! Try a smaller number");
                        } else {
                            System.out.println("Hmm... my task list doesn't contain that number... try again");
                        }
                    } catch (NumberFormatException e) {
                        printText("Hey, that's not a task number! Give me a number please!");
                    }
                } catch (NumberFormatException e) {
                    printText("Hey, that's not a task number! Give me a number please!");
                }
            } else if (input.startsWith("UNMARK ")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
                    try {
                        if (tasklist.get(taskNumber).unmark()) {
                            printText("OK, I've marked this task as not done yet:\n" + tasklist.get(taskNumber));
                        } else {
                            printText("Hey! You haven't even done that one yet!\n" + tasklist.get(taskNumber));
                        }
                    } catch (IndexOutOfBoundsException e) {
                        if (taskNumber <= 0) {
                            System.out.println("Dude, your task list starts from 1! Input a number that's above 0!");
                        } else if (taskNumber > 100) {
                            System.out.println("My task list can't go that high! Try a smaller number");
                        } else {
                            System.out.println("Hmm... my task list doesn't contain that number... try again");
                        }
                    } catch (NumberFormatException e) {
                        printText("Hey, that's not a task number! Give me a number please!");
                    }
                } catch (NumberFormatException e) {
                    printText("Hey, that's not a task number! Give me a number please!");
                }
            } else if (input.startsWith("TODO")){
                try {
                    addTask(new ToDo(command.substring(5).trim()));
                } catch (IndexOutOfBoundsException e) {
                    printText("Add a description to your todo!");
                }
            } else if (input.startsWith("DEADLINE")) {
                try {
                    int deadlineIndex = input.indexOf("/BY ");

                    if (deadlineIndex < 9) {
                        printText("Deadline format incorrect. Format: \"deadline [taskName] /by [deadline]\"." +
                                " Try again please");
                        continue;
                    }
                    if (deadlineIndex == 9) {
                        printText("I see the deadline but no task :(");
                        continue;
                    }

                    addTask(new Deadline(command.substring(9, deadlineIndex - 1).trim(),
                            command.substring(deadlineIndex + 4).trim()));
                } catch (IndexOutOfBoundsException e) {
                    printText("You've made a fatal error! Report it to the developer or face eternal DOOM!!");
                }
            } else if (input.startsWith("EVENT")) {
                try {
                    int startIndex = input.indexOf("/FROM ");
                    int endIndex = input.indexOf("/TO ");
                    String parsedStart = command.substring(startIndex + 5, endIndex - 1).trim();
                    String parsedEnd = command.substring(endIndex + 4).trim();
                    addTask(new Event(command.substring(6, startIndex - 1), parsedStart, parsedEnd));
                } catch (IndexOutOfBoundsException e) {
                    printText("Event format incorrect. Format: \"Event [taskName] /from [start] /to [end]\". " +
                            "Try again please");
                }
            } else if (input.startsWith("DELETE") || input.startsWith("REMOVE")) {
                try {
                    if (input.substring(7).trim().equals("ALL")) {
                        deleteAll();
                        continue;
                    }

                    int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
                    deleteTask(tasklist.get(taskNumber));
                } catch (IndexOutOfBoundsException e) {
                    printText("Hey, that task doesn't exist for me to delete!");
                } catch (NumberFormatException e) {
                    printText("Hey, that's not a task number! Give me a number please!");
                }
            } else {
                printText("I'm not sure what that means :(");
            }
        }
    }

    private static void addTask(Task task) {
        if (tasklist.size() >= 100) {
            printText("Too many tasks! Complete some first! >:( ");
            return;
        }
        tasklist.add(task);

        String toPrint = "Got it. I've added this task: \n" + task.toString();

        if (tasklist.size() > 1) {
            toPrint += "\n Now you have " + tasklist.size() + " tasks in your list";
        } else {
            toPrint += "\n Now you have " + tasklist.size() + " task in your list";
        }

        printText(toPrint);
    }

    private static void deleteTask(Task task) {
        tasklist.remove(task);
        String toPrint = "Noted. I've removed this task:\n" + task;

        if (tasklist.size() > 1) {
            toPrint += "\n Now you have " + tasklist.size() + " tasks in your list";
        } else {
            toPrint += "\n Now you have " + tasklist.size() + " task in your list";
        }

        printText(toPrint);
    }

    private static void deleteAll() {
        int length = tasklist.size();
        printText("Removing all tasks");
        for (int i = 0; i < length; i++) {
            tasklist.remove(i);
        }
    }

    private static void printTaskList() {
        StringBuilder toPrint = new StringBuilder("Here are the tasks in your list: \n");
        int length = tasklist.size();

        for (int i = 0; i < length; i++) {
            toPrint.append(i+1 + ". " + tasklist.get(i) + "\n");
        }

        printText(toPrint.toString());
    }

}
