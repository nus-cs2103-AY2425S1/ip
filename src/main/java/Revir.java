import java.util.ArrayList;

public class Revir {
    public static void main(String[] args) {
        String name = "Revir";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        ArrayList<Task> userInputList =  new ArrayList<Task>();
        while (true) {
            String input = System.console().readLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                // Print the user input history
                System.out.println("History:");
                for (Task userInput : userInputList) {
                    System.out.println(userInput.toString());
                }
            } else if (input.startsWith("mark ")) {
                // Mark a task as completed
                String taskIndexStr = input.substring(5);
                try {
                    int taskIndex = Integer.parseInt(taskIndexStr);
                    if (taskIndex >= 1 && taskIndex <= userInputList.size()) {
                        Task task = userInputList.get(taskIndex - 1);
                        task.setCompleted(true);
                        System.out.println("Task marked as completed: " + task.toString());
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task index.");
                }
            } else if (input.startsWith("unmark ")) {
                // Unmark a completed task
                String taskIndexStr = input.substring(7);
                try {
                    int taskIndex = Integer.parseInt(taskIndexStr);
                    if (taskIndex >= 1 && taskIndex <= userInputList.size()) {
                        Task task = userInputList.get(taskIndex - 1);
                        task.setCompleted(false);
                        System.out.println("Task unmarked as completed: \n" + task.toString());
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task index.");
                }
            } else {
                // Add user input to the list
                userInputList.add(new Task(input));
                // Differentiate the print from the user input
                System.out.println("Task added: " + input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
