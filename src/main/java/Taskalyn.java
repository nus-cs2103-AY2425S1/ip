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

            String[] completeString = input.split(" ");

            String command = completeString[0];

            boolean isError = false;

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
                                isError = true;
                            }
                        } catch (NumberFormatException e) {
                            isError = true;
                        }
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
                                isError = true;
                            }
                        } catch (NumberFormatException e) {
                            isError = true;
                        }
                    }
                    break;

                default:
                    taskManager.addTask(new Task(input));
                    break;
            }

            if (isError) {
                taskManager.addTask(new Task(input));
                isError = false;
            }

            if (command.equals("bye")) {
                break;
            }
        }

        scanner.close();

    }
}
