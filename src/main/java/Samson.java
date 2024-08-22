import java.util.Scanner;
public class Samson {
    public static void main(String[] args) {
        final String chatBoxName = "Samson";
        Greeting greeting = new Greeting(chatBoxName);
        TaskManager taskManager =  new TaskManager();

        greeting.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                greeting.exit();
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                taskManager.listTask();
            } else if (userInput.startsWith("mark")) {
                String[] chunk = userInput.split(" ");
                int taskNum = Integer.parseInt(chunk[1]);
                taskManager.markTask(taskNum);
            } else if (userInput.startsWith("unmark")) {
                String[] chunk = userInput.split(" ");
                int taskNum = Integer.parseInt(chunk[1]);
                taskManager.unmarkTask(taskNum);
            } else {
                taskManager.addTask(userInput);
            }
        }

        scanner.close();

    }
}
