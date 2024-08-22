import java.util.ArrayList;
import java.util.Scanner;

public class Buddy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("    ---------------------------------------------------------");
        System.out.println("    Hey there! I'm Buddy\n    What do ya need help with?");
        System.out.println("    ---------------------------------------------------------\n");

        while (true) {
            String userInput = scanner.nextLine().trim();

            try {
                System.out.println("\n    ---------------------------------------------------------");

                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("    Bye! See ya soon!");
                    System.out.println("    ---------------------------------------------------------");
                    break;

                } else if (userInput.equalsIgnoreCase("list")) {
                    if (list.isEmpty()) {
                        System.out.println("    List is empty!!");
                    } else {
                        System.out.println("    Here are the tasks in your list: ");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.printf("    %d. [%s][%s] %s%n", i + 1, list.get(i).getTaskType(), list.get(i).getStatusIcon(), list.get(i).description);
                        }
                    }

                } else if (userInput.startsWith("mark") && userInput.matches("mark \\d+$")) {
                    int taskIndex = Integer.parseInt(userInput.substring(4).trim()) - 1;

                    if (taskIndex >= 0 && taskIndex < list.size()) {
                        if (list.get(taskIndex).isDone) {
                            System.out.println("    Uhh, its already been marked buddy!");
                        } else {
                            list.get(taskIndex).markAsDone();
                            System.out.println("    Nice one buddy! Marked this as done...");
                            System.out.printf("    [%s][%s] %s%n", list.get(taskIndex).getTaskType(), list.get(taskIndex).getStatusIcon(), list.get(taskIndex).description);
                        }
                    } else {
                        throw new BuddyException("I don't think task is on your list buddy...");
                    }

                } else if (userInput.startsWith("unmark") && userInput.matches("unmark \\d+$")) {
                    int taskIndex = Integer.parseInt(userInput.substring(6).trim()) - 1;

                    if (taskIndex >= 0 && taskIndex < list.size()) {
                        if (!list.get(taskIndex).isDone) {
                            System.out.println("    Uhh, its already been unmarked buddy!");
                        } else {
                            System.out.println("    Alright buddy, let's give that task another shot!");
                            list.get(taskIndex).markAsUndone();
                            System.out.printf("    [%s][%s] %s%n", list.get(taskIndex).getTaskType(), list.get(taskIndex).getStatusIcon(), list.get(taskIndex).description);
                        }
                    } else {
                        throw new BuddyException("I don't think task is on your list buddy...");
                    }

                } else if (userInput.isBlank()) {
                    throw new BuddyException("The description cannot be empty.");

                } else if (userInput.startsWith("todo")) {
                    String taskDesc = userInput.substring(4).trim();
                    if (taskDesc.isEmpty()) {
                        throw new BuddyException("The description of a todo cannot be empty.");
                    }
                    Task t = new ToDos(taskDesc);
                    list.add(t);

                    System.out.println("    Gotcha! I've added this task: ");
                    System.out.printf("         [%s][%s] %s%n", t.getTaskType(), t.getStatusIcon(), t.description);
                    System.out.printf("    Now, you have %d tasks in the list!", list.size());

                } else if (userInput.startsWith("deadline")) {
                    String taskDesc = userInput.substring(8).trim();
                    if (taskDesc.isEmpty()) {
                        throw new BuddyException("The description of a deadline cannot be empty.");
                    }
                    String[] parts = taskDesc.split("/by ", 2);

                    if (parts.length == 2) {
                        String desc = parts[0].trim();
                        String day = parts[1].trim();
                        Task t = new Deadlines(desc, day);
                        list.add(t);

                        System.out.println("    Gotcha! I've added this task: ");
                        System.out.printf("         [%s][%s] %s%n", t.getTaskType(), t.getStatusIcon(), t.description);
                        System.out.printf("    Now, you have %d tasks in the list!", list.size());
                    } else {
                        throw new BuddyException("When do ya need to get it done by?\n    (include '/by' followed by the deadline)");
                    }

                } else if (userInput.startsWith("event")) {
                    String taskDesc = userInput.substring(5).trim();
                    if (taskDesc.isEmpty()) {
                        throw new BuddyException("The description of an event cannot be empty.");
                    }
                    String[] parts = taskDesc.split("/from ", 2);

                    if (parts.length == 2) {
                        String task = parts[0].trim();
                        String dateTimeAndEnd = parts[1].trim();

                        String[] dateTimeAndEndParts = dateTimeAndEnd.split("/to ", 2);

                        if (dateTimeAndEndParts.length == 2) {
                            String dateTime = dateTimeAndEndParts[0].trim();
                            String endTime = dateTimeAndEndParts[1].trim();

                            Task t = new Events(task, dateTime, endTime);
                            list.add(t);

                            System.out.println("    Gotcha! I've added this task: ");
                            System.out.printf("     [%s][%s] %s%n", t.getTaskType(), t.getStatusIcon(), t.description);
                            System.out.printf("    Now, you have %d tasks in the list!", list.size());
                        } else {
                            throw new BuddyException("There's no end date?\n    (include '/to' after start date)");
                        }
                    } else {
                        throw new BuddyException("There's no start date?\n    (include '/from' after your description)");
                    }

                } else if (userInput.startsWith("delete") && userInput.matches("delete \\d+$")) {
                    int taskIndex = Integer.parseInt(userInput.substring(6)) - 1;

                    if (taskIndex >= 0 && taskIndex < list.size()) {
                        Task removedTask = list.remove(taskIndex);
                        System.out.println("    Noted. I've removed this task:");
                        System.out.printf("      [%s][%s] %s%n", removedTask.getTaskType(), removedTask.getStatusIcon(), removedTask.description);
                        System.out.printf("    Now you have %d tasks in the list.%n", list.size());
                    } else {
                        throw new BuddyException("I don't think task is on your list buddy...");
                    }

                } else {
                    throw new BuddyException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (BuddyException e) {
                System.out.println("    OOPS!!! " + e.getMessage());
            }

            System.out.println("\n    ---------------------------------------------------------\n");
        }

        scanner.close();
    }
}

