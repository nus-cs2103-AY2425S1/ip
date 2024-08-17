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
            String[] parts = userInput.split(" ");
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
            } else if (parts.length == 2 && (command.equals("mark") || command.equals("unmark"))) {
                int taskNumber = Integer.parseInt(parts[1]);
                if (command.equals("mark")) {
                    Task t = tasks.getTask(taskNumber - 1);
                    t.completeTask();
                    System.out.println("Nice! You've completed this task:\n" + t);
                    continue;
                } else {
                    Task t = tasks.getTask(taskNumber - 1);
                    t.uncompleteTask();
                    System.out.println("Ok! This task is not complete:\n" + t);
                    continue;
                }
            }
            tasks.setTask(new Task(userInput), taskCount);
            taskCount++;
            System.out.println(divider + "added: " + userInput + "\n" + divider);
        }
        scanner.close();
        System.out.println(divider + "Bye! Yippee!");
    }
}
