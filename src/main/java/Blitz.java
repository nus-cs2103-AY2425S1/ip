import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;

import java.util.ArrayList;
import java.util.Scanner;

public class Blitz{

    ArrayList<Task> inputHistory;

    public Blitz() {
        inputHistory = new ArrayList<>();
    }

    public void start() {
        System.out.println("---------------\n" +
                "Hello! I'm BLITZ \n" +
                "What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            String strippedInput = userInput.trim().toLowerCase();
            if (strippedInput.equals("bye")) {
                System.out.println("----------------\n" +
                        "Till we meet again, GOODBYE!");
                break;
            } else {
                actionBasedOnInput(userInput);
            }
        }
    }

    public void actionBasedOnInput(String userInput) {
        String strippedInput = userInput.toLowerCase().trim();
        if (strippedInput.isEmpty()) {
            return;
        }

        String[] words = strippedInput.split(" ");
        if (strippedInput.equals("list")) {
            displayList();
        } else if (strippedInput.startsWith("mark") || strippedInput.startsWith("unmark")){
            try {
                int taskNumber = Integer.parseInt(words[1]);
                changeTaskStatus(words[0], inputHistory.get(taskNumber - 1));
            } catch (NumberFormatException e) {
                System.out.println("Come on, that is not a number bro. Don't worry, try again.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("A valid index has not been given!");
            }
        } else if (strippedInput.startsWith("delete")) {
            try {
                int deleteIndex = Integer.parseInt(words[1]);
                Task taskToDelete = inputHistory.get(deleteIndex - 1);
                inputHistory.remove(taskToDelete);
                System.out.println("----------------\n" +
                        "WOOHOO! The following task has been ELIMINATED:\n " +
                        taskToDelete + "\n" +
                        "HUH you still have " + inputHistory.size() + " tasks remaining??\n" +
                        "----------------\n");
            } catch (NumberFormatException e) {
                System.out.println("Come on, that is not a number bro. Don't worry, try again.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("A valid index has not been given!");
            }
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

    public void changeTaskStatus(String action, Task task) {
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

    public void displayList() {
        System.out.println("---------------");
        inputHistory.forEach(task -> System.out.println((this.inputHistory.indexOf(task) + 1) +
                ". " +
                task));
        System.out.println("---------------\n");
    }

    public static void main(String[] args) {
        Blitz blitz_jr = new Blitz();
        blitz_jr.start();
    }
}
