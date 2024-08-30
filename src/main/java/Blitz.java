import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;

import java.io.*;
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
        loadData();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Input you next task! Please give dates in d/M/yyyy format! : ");
            String userInput = scanner.nextLine();
            String strippedInput = userInput.trim().toLowerCase();
            if (strippedInput.equals("bye")) {
                System.out.println("----------------\n" +
                        "Till we meet again, GOODBYE!");
                break;
            } else {
                actionBasedOnInput(userInput);
                writeToData();
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


    public String formatInput(String input) {
        input = input.substring(input.indexOf("["));
        char taskType = input.charAt(1);
        int descriptionStartIndex = input.indexOf("] ", input.indexOf("]") + 1) + 2;
        String description;
        String timeDetails;
        switch (taskType) {
        case 'T':
           return "todo " + input.substring(descriptionStartIndex);
        case 'E':
            int endIndex = input.indexOf("(");
            description = input.substring(descriptionStartIndex, endIndex).trim();

            int timeIndex = input.indexOf("(from");
            timeDetails = input.substring(timeIndex).trim();
            timeDetails = timeDetails.replace("(from:", "/from");
            timeDetails = timeDetails.replace("to:", "/to");
            timeDetails = timeDetails.replace(")", "");
            return "event " + description + " " + timeDetails;
        default:
            int descriptionEndIndex = input.indexOf("(");
            description = input.substring(descriptionStartIndex, descriptionEndIndex).trim();
            int deadlineIndex = input.indexOf("(by");
            timeDetails = input.substring(deadlineIndex);
            timeDetails = timeDetails.replace("(by", "/by");
            timeDetails = timeDetails.replace(")", "");
            return "deadline " + description + " " + timeDetails;
        }
    }

    public void loadData() {
        String path = "src/main/data/blitz.txt";
        checkAndCreateFile(path);
        try {
            File dataFile = new File(path);

            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    System.out.println("This is the formatted task: " + formatInput(line));
                    Task newTask = Task.createTask(formatInput(line));
                    inputHistory.add(newTask);
                } catch (InvalidTaskException e) {
                    System.out.println("DATA CONTAINS INVALID TASK LAH");
                } catch (NoTaskDescriptionException e) {
                    System.out.println("Wah, no description then I record what?");
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading from file");
        }
    }

    public void writeToData() {
        String path = "src/main/data/blitz.txt";
        checkAndCreateFile(path);

        try {

            File dataFile = new File(path);

            FileWriter writer = new FileWriter(dataFile);

            for (int i = 0; i < inputHistory.size(); i++) {
                Task task = inputHistory.get(i);
                writer.write((i + 1) + ". " + task + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file");
            e.printStackTrace();
        }
    }

    public static void checkAndCreateFile(String path) {
        File file = new File(path);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            directory.mkdir();
        }

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("error when creating file");
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Blitz blitz_jr = new Blitz();
        blitz_jr.start();
    }
}
