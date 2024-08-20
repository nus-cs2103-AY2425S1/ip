import java.util.Scanner;

public class SlothingWaffler {
    public static void main(String[] args) {
        System.out.println("""
                Hello! I'm the Slothing Waffler!
                Let's stop slothing and get cracking!""");

        Task[] tasks = new Task[100];
        int tasksCount = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {

            try {
                String input = scanner.nextLine();
                String[] split = input.split(" ", 2);

                if (split[0].strip().equals("bye")) {
                    System.out.println("See you next time! Remember to get a waffle!");
                    break;
                }
                switch (split[0].strip()) {
                    case "list" -> displayTaskList(tasks, tasksCount);
                    case "mark" -> markTask(tasks, Integer.parseInt(split[1]) - 1);
                    case "todo" -> {
                        if (split.length < 2 || split[1].strip().isEmpty()) {
                            throw new SlothingWafflerException("The description of a Todo Task cannot be empty!");
                        }
                        tasks[tasksCount] = new Todo(split[1]);
                        tasksCount++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[tasksCount - 1].toString());
                        System.out.println("Now you have " + tasksCount + " tasks in the list.");
                    }
                    case "deadline" -> {
                        String[] desc = split[1].split(" /by ", 2);
                        tasks[tasksCount] = new Deadline(desc[0], desc[1]);
                        tasksCount++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[tasksCount - 1].toString());
                        System.out.println("Now you have " + tasksCount + " tasks in the list.");
                    }
                    case "event" -> {
                        String[] desc = split[1].split(" /from | /to ");
                        tasks[tasksCount] = new Event(desc[0], desc[1], desc[2]);
                        tasksCount++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[tasksCount - 1].toString());
                        System.out.println("Now you have " + tasksCount + " tasks in the list.");
                    }
                    default -> throw new SlothingWafflerException("The Waffler will continuing slothing!! " +
                            "Please give me instructions that I can understand :(");
                }
            } catch (SlothingWafflerException e) {
                System.out.println("OOPS!! " + e.getMessage());
            } finally {
                System.out.println("The Waffler is ready for your next command!");
            }
        }
        scanner.close();
    }

    private static void displayTaskList(Task[] tasks, int tasksCount) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasksCount; i++) {
            System.out.println((i) + "." + tasks[i - 1].toString());
        }
    }

    private static void markTask(Task[] tasks, int taskNum) {
        tasks[taskNum].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[taskNum].toString());
    }
}
