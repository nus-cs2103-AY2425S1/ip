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
//        while (true) {
//            String input = scanner.nextLine();
//            if (input.equals("bye")) {
//                taskManager.printLines("Bye. Hope to see you again soon!");
//                break;
//            } else if (input.equals("list")) {
//                taskManager.listTasks();
//            } else if (input.startsWith("mark ")) {
//                String[] completeString = input.split(" ");
//
//                if (completeString.length == 2 && isInteger completeString[1].) {
//
//                }
//            } else if () {
//
//            } else {
//                taskManager.addTask(new Task(input));
//                taskManager.printLines(input);
//            }
//        }
        while (true) {
            String input = scanner.nextLine().trim();

            String[] completeString = input.split(" ");

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
                        } catch (NumberFormatException e) {
                            taskManager.printLines("Oops, there's an error.");
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
                        } catch (NumberFormatException e) {
                            taskManager.printLines("Oops, there's an error.");
                        }
                    }
                    break;

                default:
                    taskManager.addTask(new Task(input));
                    break;
            }

            if (command.equals("bye")) {
                break;
            }
        }

        scanner.close();

    }
}
