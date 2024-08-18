import java.util.Scanner;
public class Downy {

    public static void main(String[] args) {
        String divider = "________________________________________\n";
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        int taskCount = 0;
        System.out.println(divider + "Hello! I'm Downy.\nHow can I help?\n" + divider);

        while (true) {
            String userInput = scanner.nextLine();
            String[] parts = userInput.split(" ", 2);
            String command = parts[0];

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                if (taskCount == 0) {
                    System.out.println(divider + "There are no tasks!\n" + divider);
                    continue;
                }
                System.out.printf(divider);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks.getTask(i));
                }
                System.out.println(divider);
                continue;
            }
            if (command.equals("mark") || command.equals("unmark")) {
                String remainder = parts[1];
                int taskNumber = Integer.parseInt(remainder);
                Task t = tasks.getTask(taskNumber - 1);
                if (command.equals("mark")) {
                    t.completeTask();
                    System.out.printf(divider);
                    System.out.println("Nice! You've completed this task:\n  " + t);
                } else {
                    t.uncompleteTask();
                    System.out.printf(divider);
                    System.out.println("Ok! This task is not complete:\n  " + t);
                }
                System.out.println(divider);
                continue;
            }
            switch (command) {
                case "todo" -> {
                    String remainder = parts[1];
                    tasks.setTask(new Todo(remainder), taskCount);
                    taskCount++;
                    System.out.println(divider + "Okay! Added this task:\n  " + tasks.getTask(taskCount - 1)
                            + "\nNow you have " + taskCount + " tasks in this list\n" + divider);
                    continue;

                }
                case "deadline" -> {
                    String remainder = parts[1];
                    String[] splitParts = remainder.split("/by", 2);
                    String name = splitParts[0];
                    String time = splitParts[1];
                    tasks.setTask(new Deadline(name, time), taskCount);
                    taskCount++;
                    System.out.println(divider + "Okay! Added this task:\n  " + tasks.getTask(taskCount - 1)
                            + "\nNow you have " + taskCount + " tasks in this list\n" + divider);
                    continue;

                }
                case "event" -> {
                    String remainder = parts[1];
                    String[] splitParts = remainder.split("/from", 2);
                    String name = splitParts[0];
                    String[] time = splitParts[1].split("/to", 2);
                    String startTime = time[0];
                    String endTime = time[1];
                    tasks.setTask(new Event(name, startTime, endTime), taskCount);
                    taskCount++;
                    System.out.println(divider + "Okay! Added this task:\n  " + tasks.getTask(taskCount - 1)
                            + "\nNow you have " + taskCount + " tasks in this list\n" + divider);
                    continue;
                }
            }
            System.out.println("Invalid command\n");
        }
        scanner.close();
        System.out.println(divider + "Bye! Yippee!");
    }
}
