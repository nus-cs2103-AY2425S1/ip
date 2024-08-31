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

public class TaskList {
    private ArrayList<Task> myList;
    private int noOfTask;

    public TaskList() {
        this.myList = new ArrayList<>();
        Storage.loadFromFile(myList);
        this.noOfTask = myList.size();
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
                // Parse the date string into LocalDateTime using the correct format
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
            // Assuming the times are also in the "d/M/yyyy HHmm" format, adjust the parsing accordingly
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime fromTime = LocalDateTime.parse(timeParts[0].trim(), formatter);
                LocalDateTime toTime = LocalDateTime.parse(timeParts[1].trim(), formatter);
                task = new EventTask(descParts[0].trim(), fromTime.toString(), toTime.toString());
            } catch (DateTimeParseException e) {
                throw new InputErrorException("The date format must be 'd/M/yyyy HHmm'. Example: '2/12/2019 1800'");
            }
        } else {
            throw new InputErrorException("Unknown task type.");
        }

        myList.add(task);
        noOfTask++;
        Storage.saveToFile(this.myList); // Save to file after adding a task
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

        Storage.saveToFile(this.myList);
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

        Storage.saveToFile(this.myList);
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

        Storage.saveToFile(myList);
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