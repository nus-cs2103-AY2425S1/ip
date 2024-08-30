import Exceptions.EmptyDescriptionException;
import Exceptions.InputErrorException;
import Exceptions.WrongNumberOfItemException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Note {
    private ArrayList<Task> myList;
    private int noOfTask;
    private static final String FILE_PATH = "./data/blue.txt";


    public Note() {
        this.myList = new ArrayList<>();
        this.noOfTask = 0;
        loadFromFile();
    }


    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return; // No file to load, just return
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileString(line);
                myList.add(task);
                noOfTask++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading from file: " + e.getMessage());
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : myList) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }

    public void printNoOfTask() {
        System.out.println(noOfTask);
    }


    public void addToList(String input) throws EmptyDescriptionException, InputErrorException {
        Task task;
        // Handle direct input format (e.g., "todo read book")
            String[] inputParts = input.split(" ", 2);
            String taskType = inputParts[0].trim(); // todo, deadline, or event

            if (taskType.equalsIgnoreCase("todo")) {
                if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                    throw new EmptyDescriptionException("The description of a todo cannot be empty.");
                }
                task = new ToDoTask(inputParts[1].trim());
            } else if (taskType.equalsIgnoreCase("deadline")) {
                String[] descParts = inputParts[1].split(" /by ", 2);
                if (descParts.length < 2) {
                    throw new InputErrorException("The deadline must be specified in the format: 'deadline <description> /by <date>'");
                }
                try {
                    // Parse the date string into LocalDateTime
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                    LocalDateTime deadline = LocalDateTime.parse(descParts[1].trim(), formatter);
                    task = new DeadlineTask(descParts[0].trim(), deadline);
                } catch (DateTimeParseException e) {
                    throw new InputErrorException("The date format must be 'd/M/yyyy HHmm'. Example: '2/12/2019 1800'");
                }
            } else if (taskType.equalsIgnoreCase("event")) {
                String[] descParts = inputParts[1].split(" /from ", 2);
                if (descParts.length < 2 || !descParts[1].contains(" /to ")) {
                    throw new InputErrorException("The event must be specified in the format: 'event <description> /from <start_time> /to <end_time>'");
                }
                String[] timeParts = descParts[1].split(" /to ", 2);
                task = new EventTask(descParts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
            } else {
                throw new InputErrorException("Unknown task type.");
            }

        myList.add(task);
        noOfTask++;
        saveToFile(); // Save to file after adding a task
        System.out.println(task.toString());
    }






    public void mark(int number) throws WrongNumberOfItemException {
        if (number <= 0 || number > noOfTask) {
            throw new WrongNumberOfItemException(number);
        }

        Task currTask = myList.get(number - 1);
        currTask.markAsDone();
        System.out.println("--------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(currTask.toString());
        System.out.println("--------------------------------------------");

        saveToFile();
    }

    public void unmark(int number) throws WrongNumberOfItemException {
        if (number <= 0 || number > noOfTask) {
            throw new WrongNumberOfItemException(number);
        }

        Task currTask = myList.get(number - 1);
        currTask.markAsUnDone();
        System.out.println("--------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(currTask.toString());
        System.out.println("--------------------------------------------");

        saveToFile();
    }

    public void delete(int number) throws WrongNumberOfItemException {
        if (number <= 0 || number > noOfTask) {
            throw new WrongNumberOfItemException(number);
        }

        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(myList.get(number - 1).toString());
        myList.remove(number - 1);
        this.noOfTask--;
        String result = "Now you have " + noOfTask + " tasks in the list.";
        System.out.println(result);
        System.out.println("____________________________________________________________");

        saveToFile();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < noOfTask; i++) {
            String result = i + 1 + ". " + myList.get(i).toString();
            System.out.println(result);
        }
        System.out.println("--------------------------------------------");
    }

    public int getNumberOfTask() {
        return this.noOfTask;
    }

}