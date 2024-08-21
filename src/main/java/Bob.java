import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
    private static final List<Task> taskList = new ArrayList<>();
    private static int numTasks = 0;

    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob the chatbot!\nHow can I help you?");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            String endKeyword = "bye";
            String listKeyword = "list";
            String markKeyword = "mark";
            String unmarkKeyword = "unmark";

            if (userInput.equalsIgnoreCase(endKeyword)) {
                System.out.println("Bye! Hope to see you again :)");
                break;
            }

            // Get input history
            if (userInput.equalsIgnoreCase(listKeyword)) {
                System.out.println(generateResponse(userInput) + getTaskList());

            } else if (userInput.startsWith(markKeyword)) {
                String[] parts = userInput.split(" ");
                int taskNum = Integer.parseInt(parts[1]);
                Task currTask = taskList.get(taskNum - 1);
                currTask.markAsDone();
                System.out.println(generateResponse(parts[0]) + currTask.getStatusOutput());

            } else if (userInput.startsWith(unmarkKeyword)) {
                String[] parts = userInput.split(" ");
                int taskNum = Integer.parseInt(parts[1]);
                Task currTask = taskList.get(taskNum - 1);
                currTask.markAsUndone();
                System.out.println(generateResponse(parts[0]) + currTask.getStatusOutput());

            } else {
                Task task = new Task(userInput);
                addTask(task);
                System.out.println(generateResponse(userInput) + userInput);
            }
        }

        scanner.close();
    }

    public static void addTask(Task task) {
        taskList.add(task);
        numTasks++;
    }

    public static String generateResponse(String command) {
        if (command.equalsIgnoreCase("list")) {
            return "Your list of tasks:\n";
        } else if (command.equalsIgnoreCase("mark")) {
            return "Good Job! Marking this task as done:\n";
        } else if (command.equalsIgnoreCase("unmark")) {
            return "Okay, marking this task as not done yet:\n";
        } else {
            return "task added: ";
        }
    }

    public static String getTaskList() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 1; i <= numTasks; i++) {
            Task currTask = taskList.get(i - 1);
            if (i == numTasks) {
                tasks.append(i).append(". ").append(currTask.getStatusOutput());
                continue;
            }
            tasks.append(i).append(". ").append(currTask.getStatusOutput()).append("\n");
        }
        return tasks.toString();
    }
}
