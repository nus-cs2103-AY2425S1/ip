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
            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring(5);
                taskManager.addTask(new ToDo(description));
            } else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.split(" /by ");
                String description = parts[0].substring(9);
                String by = parts[1];
                taskManager.addTask(new Deadline(description, by));
            } else if (userInput.startsWith("event")) {
                String[] parts = userInput.split(" /from | /to ");
                String description = parts[0].substring(6);
                String from = parts[1];
                String to = parts[2];
                taskManager.addTask(new Event(description, from, to));
            } else {
                taskManager.addTask(new Task(userInput));
            }
        }

        scanner.close();

    }
}
