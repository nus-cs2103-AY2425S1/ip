import java.util.Scanner;

public class Orion {
    static Scanner scanner = new Scanner(System.in);
    static String horizontalLine = "────────────────────────────────────────";
    static Task[] tasks = new Task[100];
    static int taskCount = 0;

    public static void main(String[] args) {
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Orion");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        label:
        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);

            switch (parts[0]) {
                case "bye":
                    break label;
                case "list":
                    System.out.println(horizontalLine);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                    System.out.println(horizontalLine);
                    break;
                case "mark": {
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    tasks[taskNumber].markAsDone();
                    System.out.println(horizontalLine);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[taskNumber]);
                    System.out.println(horizontalLine);
                    break;
                }
                case "unmark": {
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    tasks[taskNumber].markAsNotDone();
                    System.out.println(horizontalLine);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[taskNumber]);
                    System.out.println(horizontalLine);
                    break;
                }
                case "todo":
                    tasks[taskCount] = new Todo(parts[1]);
                    taskCount++;
                    System.out.println(horizontalLine);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println(horizontalLine);
                    break;
                case "deadline":
                    String[] words = parts[1].split(" ");
                    StringBuilder description = new StringBuilder();
                    StringBuilder by = new StringBuilder();
                    boolean foundBy = false;

                    for (String word : words) {
                        if (word.equals("by")) {
                            foundBy = true;
                        } else if (foundBy) {
                            by.append(word).append(" ");
                        } else {
                            description.append(word).append(" ");
                        }
                    }

                    tasks[taskCount] = new Deadline(description.toString().trim(), by.toString().trim());
                    taskCount++;
                    System.out.println(horizontalLine);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println(horizontalLine);
                    break;
                case "event":
                    String[] eventWords = parts[1].split(" ");
                    StringBuilder eventDescription = new StringBuilder();
                    StringBuilder from = new StringBuilder();
                    StringBuilder to = new StringBuilder();
                    boolean foundFrom = false;
                    boolean foundTo = false;

                    for (String word : eventWords) {
                        if (word.equals("from")) {
                            foundFrom = true;
                        } else if (word.equals("to")) {
                            foundTo = true;
                        } else if (foundTo) {
                            to.append(word).append(" ");
                        } else if (foundFrom) {
                            from.append(word).append(" ");
                        } else {
                            eventDescription.append(word).append(" ");
                        }
                    }

                    tasks[taskCount] = new Event(eventDescription.toString().trim(), from.toString().trim(), to.toString().trim());
                    taskCount++;
                    System.out.println(horizontalLine);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println(horizontalLine);
                    break;
                default:
                    System.out.println(horizontalLine);
                    System.out.println("I'm sorry, I don't understand that command.");
                    System.out.println(horizontalLine);
                    break;
            }
        }

        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}

