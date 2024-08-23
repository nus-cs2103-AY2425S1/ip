import java.util.Scanner;

public class Denim {
    public static void main(String[] args) {

        final String TASK_MARK = "mark";
        final String TASK_UNMARK = "unmark";
        final String TASK_LIST = "list";

        String horizontalLine = "____________________________________________________________";
        String chatBotName = "Denim";
        String greetingMessage = String.format("%s%n Hello! I'm %s!%n What can I do for you? %n%s%n",
                horizontalLine, chatBotName, horizontalLine);

        Task[] taskList = new Task[100];
        int taskSize = 0;

        System.out.println(greetingMessage);

        // Scans User Input in the CLI
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] inputComponents;
        String command;
        String argument;



        // Splits the input into its components: an argument and the command and process.
        while (!input.equals("bye")) {
            inputComponents = input.split(" ", 2);
            command = inputComponents[0];
            argument = inputComponents.length > 1 ? inputComponents[1] : "";
            switch (command) {
            case TASK_LIST:
                System.out.println(horizontalLine);
                for (int i = 0; i < taskSize; i++) {
                    System.out.printf("%d. %s\n", i + 1, taskList[i].toString());
                }
                System.out.println(horizontalLine);
                input = sc.nextLine();
                break;
            case TASK_MARK:
                int markTaskIndex = Integer.parseInt(argument) - 1;
                Task markTask = taskList[markTaskIndex];
                markTask.setDone(true);
                String taskMarkMessage = String.format("Okay, I've marked this task as done: \n %s",
                        markTask);
                System.out.println(taskMarkMessage);
                input = sc.nextLine();
                break;
            case TASK_UNMARK:
                int unmarkTaskIndex = Integer.parseInt(argument) - 1;
                Task unmarkTask = taskList[unmarkTaskIndex];
                unmarkTask.setDone(false);
                String taskUnmarkMessage = String.format("Okay, I've marked this task as not done yet: \n %s",
                        unmarkTask);
                System.out.println(taskUnmarkMessage);
                input = sc.nextLine();
                break;
            default:
                Task newTask = new Task(input);
                taskList[taskSize++] = newTask;
                String addMessage = String.format("%s%n added: %s%n%s", horizontalLine, newTask.getDescription(),
                        horizontalLine);
                System.out.println(addMessage);
                input = sc.nextLine();
                break;
            }
        }

        String byeMessage = String.format("%s%n %s%n%s", horizontalLine, "Bye. Hope to see you again soon!",
                horizontalLine);
        System.out.println(byeMessage);
        sc.close();
    }
}


