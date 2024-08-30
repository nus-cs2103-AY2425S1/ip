package main.java;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FRIDAY {
    private String userInput;
    private String output;
    private final String greeting, exitMessage;
    private String divider;
    private final ArrayList<Task> storage = new ArrayList<>();
    private boolean isActive = true;

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
        //load user data from storage, or create a blank storage file
        this.loadDataFromStorage();
        this.activateBot();
        while(isActive && scanner.hasNextLine()) {
            try {
                //collect user input
                userInput = scanner.nextLine();
                String[] parts = userInput.split(" ");
                String keyword = parts[0];
                //taskDetails is the user input without the keyword
                String taskDetails = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
                this.performAction(keyword, taskDetails);
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

    /**
     * activates the bot
     */
    public void activateBot() {
        this.isActive = true;
        System.out.println(greeting);
    }

    /**
     * deactivates the bot, printing an error message and exiting the program
     */
    public void deactivateBot() {
        System.out.println(exitMessage);
        this.isActive = false;
    }

    /**
     * updates the storage by writing all current elements in the programs memory to specified location in the computer's hard drive
     */
    public void updateStorage() {
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
    }

    /**
     * causes the bot to perform the action corresponding to the user input.
     * storage is updated after every performed action
     *
     * @param keyword indicates the type of action that the bot should perform
     * @param taskDetails contains miscellaneous information about the task, if applicable
     * @throws FRIDAYException exception is thrown when the type of action to be performed is not recognized
     */
    public void performAction(String keyword, String taskDetails) throws FRIDAYException {
        //keywords trigger respective actions
        switch (keyword) {
            //keywords
            case ("mark"):
                storage.get(Integer.parseInt(taskDetails.substring(0, 1)) - 1).check();
                String marked = divider + "Nice! I've marked this task as done\n" + storage.get(Integer.parseInt(taskDetails.substring(0, 1)) - 1) + "\n" + divider;
                System.out.println(marked);
                updateStorage();
                break;
            case ("unmark"):
                storage.get(Integer.parseInt(taskDetails.substring(0, 1)) - 1).uncheck();
                String unmarked = divider + "Ok, I've marked this task as not done yet\n" + storage.get(Integer.parseInt(taskDetails.substring(0, 1)) - 1) + "\n" + divider;
                System.out.println(unmarked);
                updateStorage();
                break;
            case ("todo"):
                if (taskDetails.isEmpty()) {
                    throw new FRIDAYException("ERROR: Please note that the description of a task cannot be left empty");
                }
                //create new to do task
                Task newToDo = new ToDo(taskDetails.strip(), 0);
                add(newToDo);
                updateStorage();
                break;
            case ("deadline"):
                if (taskDetails.isEmpty()) {
                    throw new FRIDAYException("ERROR: Please note that the description of a task cannot be left empty");
                }
                //create new deadline task
                String[] deadlineDetails = taskDetails.split("/");
                String deadlineDescription = deadlineDetails[0].strip();
                String deadlineDeadline = deadlineDetails[1].strip();
                Task newDeadline = new Deadline(deadlineDescription, deadlineDeadline, 0);
                add(newDeadline);
                updateStorage();
                break;
            case ("event"):
                if (taskDetails.isEmpty()) {
                    throw new FRIDAYException("ERROR: Please note that the description of a task cannot be left empty");
                }
                //assignment /from 2pm to 5pm
                //create new event task
                //split the input with the whitespace, "from" and "to" delimiters
                String[] eventDetails = taskDetails.split("/from");
                String[] startEnd = eventDetails[1].split("to");
                String eventDescription = eventDetails[0].trim();
                String eventStart = startEnd[0].trim();
                String eventEnd = startEnd[1].trim();
                Task newEvent = new Event(eventDescription, eventStart, eventEnd, 0);
                add(newEvent);
                updateStorage();
                break;
            case("delete"):
                if (taskDetails.isEmpty()) {
                    throw new FRIDAYException("ERROR: Please note that the description of a task cannot be left empty");
                }
                storage.remove(Integer.parseInt(taskDetails.substring(0, 1)) - 1);
                String removed = divider + "Noted. I've removed this task:\n" + storage.get(Integer.parseInt(taskDetails.substring(0, 1)) - 1) + "\n" + "\nNow you have " + storage.size() + " tasks in your list\n" + divider;
                System.out.println(removed);
                updateStorage();
                break;

            case ("bye"):
                updateStorage();
                deactivateBot();
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

    /**
     * method to read data from the storage file
     * if such a storage file fails to exist, ie the first time using the program, then the method creates a blank storage file to log user data
     */
    public void loadDataFromStorage() {
        String fileName = "./storage/FRIDAY.txt";
        //check if the storage file exists on the computer, if it doesnt then attempt to create a new one
        try {
            File storageFile = new File(fileName);
            if (!storageFile.exists()) {
                //attempt to create blank storage file
                if(!storageFile.createNewFile()) {
                    System.out.println("error creating file");
                };
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                writer.write("");
                writer.close();
            }

            Scanner fileScanner = new Scanner(storageFile);
            while(fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                populateProgramMemory(data);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * method to populate the program storage with data read from the hard drive
     *
     * @param data string to represent all the details of the task, including its type and other miscellaneous details
     */
    public void populateProgramMemory(String data) {
        String[] taskElements = data.split("\\|");
        String taskType = taskElements[0].trim();
        int status = Integer.parseInt(taskElements[1].trim());
        String taskDesc = taskElements[2].trim();
        switch(taskType) {
            case("T") :
                Task toDo = new ToDo(taskDesc, status);
                storage.add(toDo);
                break;
            case("E") :
                String duration = taskElements[3].trim();
                System.out.println(duration);
                String[] startEnd = duration.split("from |to ");
                String start = startEnd[1].trim();
                String end = startEnd[2].trim();
                Task event = new Event(taskDesc, start, end, status);
                storage.add(event);
                break;
            case("D") :
                String taskDeadline = taskElements[3];
                Task deadline = new Deadline(taskDesc, taskDeadline, status);
                storage.add(deadline);
                break;
            default:
                System.out.println("Encountered unrecognizable task type");

            int k = 0;

        }
    }

    public static void main(String[] args) {
        FRIDAY bot = new FRIDAY();
        bot.start();
    }
}
