import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;

import java.util.ArrayList;
import java.util.Scanner;

public class Blitz{
    public static void main(String[] args) {
        System.out.println("---------------\n" +
                "Hello! I'm BLITZ \n" +
                "What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> inputHistory = new ArrayList<>();

        while (true) {
            String userInput = scanner.nextLine();
            String strippedInput = userInput.trim().toLowerCase();
            if (strippedInput.equals("bye")) {
                System.out.println("----------------\n" +
                        "Till we meet again, GOODBYE!");
                break;
            } else {
                actionBasedOnInput(inputHistory, userInput);
            }
        }
    }

    public static void actionBasedOnInput(ArrayList<Task> inputHistory, String userInput) {
        String strippedInput = userInput.toLowerCase().trim();
        if (strippedInput.isEmpty()) {
            return;
        }

        String[] words = strippedInput.split(" ");
        if (strippedInput.equals("list")) {
            displayList(inputHistory);
        } else if (strippedInput.startsWith("mark") || strippedInput.startsWith("unmark")){
            try {
                int taskNumber = Integer.parseInt(words[1]);
                changeTaskStatus(words[0], inputHistory.get(taskNumber - 1));
            } catch (NumberFormatException e) {
                System.out.println("Come on, that is not a number bro. Don't worry, try again.");
            }
        } else if (strippedInput.startsWith("delete")) {
            int deleteIndex = Integer.parseInt(words[1]);
            Task taskToDelete = inputHistory.get(deleteIndex - 1);
            inputHistory.remove(taskToDelete);
            System.out.println("----------------\n" +
                    "WOOHOO! The following task has been ELIMINATED:\n " +
                    taskToDelete + "\n" +
                    "HUH you still have " + inputHistory.size() + " tasks remaining??\n" +
                    "----------------\n");
        } else {
            try {
                Task newTask = Task.createTask(userInput);
                inputHistory.add(newTask);
                System.out.println("----------------\n" +
                        "Alrighty! The following task has been added:\n " +
                        newTask + "\n" +
                        "Oh my goodness you have " + inputHistory.size() + " tasks remaining\n" +
                        "----------------\n");
            } catch (InvalidTaskException e) {
                System.out.println("THAT IS AN INVALID TASK LAH");
            } catch (NoTaskDescriptionException e) {
                System.out.println("Wah, no description then I record what?");
            }
        }
    }

    public static void changeTaskStatus(String action, Task task) {
        System.out.println("---------------");
        if (action.equals("mark")) {
            task.changeStatus(true);
            System.out.println("GOOD RIDDANCE! Finally, this task is done:\n" +
                    task);
        } else {
            task.changeStatus(false);
            System.out.println("Alright, this task is not done yet faster finish leh:\n" +
                    task);
        }
        System.out.println("---------------");
    }

    public static void displayList(ArrayList<Task> inputHistory) {
        System.out.println("---------------");
        inputHistory.forEach(task -> System.out.println((inputHistory.indexOf(task) + 1) +
                ". " +
                task));
        System.out.println("---------------\n");
    }
}
