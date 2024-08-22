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
            } else {
                taskManager.addTask(userInput);
            }
        }

        scanner.close();

    }
}
