package main.java;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ArrayIndexOutOfBoundsException;

public class FRIDAY {
    private String userInput;
    private String output;
    private final String greeting, exitMessage;
    private String divider;
    private final ArrayList<Task> storage = new ArrayList<>();

    public FRIDAY() {
        //divider
        divider = "";
        for (int i = 0; i < 40; i++) {
            divider += "-";
        }
        divider += "\n";

        //preset messages
        greeting = divider + "Hello! I'm FRIDAY\nWhat can I do for you?\n" + divider;
        exitMessage = divider + "Bye. Hope to see you again soon!\n" + divider;

        //variable to store user input
        userInput = "";

        //variable to store output
        output = "";
    }

    public void start() {
        //initialize scanner object
        Scanner scanner = new Scanner(System.in);
        try {
            String fileName = "./storage/FRIDAY.txt";
            File file = new File(fileName);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                if (!parentDir.mkdirs()) {
                    System.err.println("Failed to create directory: " + parentDir.getAbsolutePath());
                    return; // Exit if directory creation fails
                }
            }
            // Ensure the file exists
            try {
                if (!file.exists()) {
                    if (!file.createNewFile()) {
                        System.err.println("Failed to create the file: " + file.getAbsolutePath());
                        return; // Exit if file creation fails
                    }
                    System.out.println("File created: " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                e.printStackTrace();
                return; // Exit if an exception occurs during file creation
            }

            Scanner fileScanner = new Scanner(file);
            //read from file
            while(fileScanner.hasNextLine()) {
                try {
                    //data is saved as a string in the format type | completion | desc | deadline/duration
                    //E | 0 | assignment  from  2pm  to  4pm
                    String[] retrievedTaskDetails = fileScanner.nextLine().split(" \\| ");
                    String taskType = retrievedTaskDetails[0];
                    Integer taskStatus = Integer.parseInt(retrievedTaskDetails[1]);
                    String taskDescription = retrievedTaskDetails[2];
                    Task retrievedTask;
                    switch (taskType) {
                        case ("T"):
                            //todo
                            retrievedTask = new ToDo(taskDescription, taskStatus);
                            storage.add(retrievedTask);
                            break;
                        case ("D"):
                            //deadline
                            String retrievedDeadline = retrievedTaskDetails[3];
                            retrievedTask = new Deadline(taskDescription, retrievedDeadline, taskStatus);
                            storage.add(retrievedTask);
                            break;
                        case ("E"):
                            //event
                            //taskDescription = Aug 6th from  2pm  to  4pm
                            //split duration by delimiter from and to get start and end time and date
                            String[] retrievedDuration = retrievedTaskDetails[3].split("from|to");
                            String date = retrievedDuration[0];
                            String start = retrievedDuration[1];
                            String end = retrievedDuration[2];
                            retrievedTask = new Event(taskDescription, start, end, taskStatus);
                            storage.add(retrievedTask);
                            break;
                        default:
                            //throw error
                            System.out.println("heehe");
                            break;
                    }
                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("possible file formatting error");
                }
            }
        } catch(FileNotFoundException f) {
            String outputPath = "./storage/FRIDAY.txt";
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
                    try {
                        writer.write("");
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    //debugging statement to verify state of storage
        //storage.forEach(item -> System.out.println(item));
        //start the bot
        boolean isActive = true;

        //bot running
        System.out.println(greeting);
        while(isActive && scanner.hasNextLine()) {
            try {
                //collect user input
                userInput = scanner.nextLine();
                String[] parts = userInput.split(" ");
                String keyword = parts[0];
                //taskDetails is the user input without the keyword
                String taskDetails = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));

                //keywords trigger respective actions
                switch (keyword) {
                    //keywords
                    case ("mark"):
                        storage.get(Integer.parseInt(parts[1]) - 1).check();
                        String marked = divider + "Nice! I've marked this task as done\n" + storage.get(Integer.parseInt(parts[1]) - 1) + "\n" + divider;
                        System.out.println(marked);
                        break;
                    case ("unmark"):
                        storage.get(Integer.parseInt(parts[1]) - 1).uncheck();
                        String unmarked = divider + "Ok, I've marked this task as not done yet\n" + storage.get(Integer.parseInt(parts[1]) - 1) + "\n" + divider;
                        System.out.println(unmarked);
                        break;
                    case ("todo"):
                        if (taskDetails.isEmpty()) {
                            throw new FRIDAYException("ERROR: Please note that the description of a task cannot be left empty");
                        }
                        //create new to do task
                        Task newToDo = new ToDo(taskDetails, 0);
                        add(newToDo);
                        break;
                    case ("deadline"):
                        if (taskDetails.isEmpty()) {
                            throw new FRIDAYException("ERROR: Please note that the description of a task cannot be left empty");
                        }
                        //create new deadline task
                        String[] deadlineDetails = taskDetails.split("/");
                        String deadlineDescription = deadlineDetails[0];
                        String deadlineDeadline = deadlineDetails[1];
                        Task newDeadline = new Deadline(deadlineDescription, deadlineDeadline, 0);
                        add(newDeadline);
                        break;
                    case ("event"):
                        if (taskDetails.isEmpty()) {
                            throw new FRIDAYException("ERROR: Please note that the description of a task cannot be left empty");
                        }
                        //assignment from 2pm to 5pm
                        //create new event task
                        //split the input with the whitespace, "from" and "to" delimiters
                        String[] eventDetails = taskDetails.split("/");
                        String[] startEnd = eventDetails[1].split("to");
                        String eventDescription = eventDetails[0];
                        String eventStart = startEnd[0];
                        String eventEnd = startEnd[1];
                        Task newEvent = new Event(eventDescription, eventStart, eventEnd, 0);
                        add(newEvent);
                        break;
                    case("delete"):
                        if (taskDetails.isEmpty()) {
                            throw new FRIDAYException("ERROR: Please note that the description of a task cannot be left empty");
                        }
                        storage.remove(Integer.parseInt(parts[1]) - 1);
                        String removed = divider + "Noted. I've removed this task:\n" + storage.get(Integer.parseInt(parts[1]) - 1) + "\n" + "\nNow you have " + storage.size() + " tasks in your list\n" + divider;
                        System.out.println(removed);
                        break;

                    case ("bye"):
                        String outputPath = "./storage/FRIDAY.txt";
                        try {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
                            storage.forEach((task) -> {
                                try {
                                    writer.write(task.storageDisplay() + "\n");
                                } catch (IOException e){
                                    e.printStackTrace();
                                }
                            });
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(exitMessage);
                        isActive = false;
                        break;
                    case ("list"):
                        display();
                        break;
                    //if there is no input then nothing added to list
                    case (""):
                        break;
                    //to handle all normal inputs less empty strings
                    default:
                        throw new FRIDAYException("It appears that you have attempted to log an unrecognized class type. Please try again");
                }
            }
            catch(FRIDAYException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //function to add string to storage array
    public void add(Task input) {
        //add the input to the arraylist
        storage.add(input);
        output = divider + "Got it. I've added this task:\n " + input.getDescription() + "\nNow you have " + storage.size() + " tasks in your list\n" + divider;
        System.out.println(output);
    }

    //function to display storage in list format
    public void display() {
        String displayList = divider;
        for(int i = 1; i <= storage.size(); i++) {
            displayList += i + ". " + storage.get(i - 1) + "\n";
        }
        displayList += divider;
        output = "Here are the tasks in your list:\n" + displayList;
        System.out.println(output);
    }

    public static void main(String[] args) {
        FRIDAY bot = new FRIDAY();
        bot.start();
    }
}
