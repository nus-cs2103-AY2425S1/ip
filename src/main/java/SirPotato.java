import java.util.Scanner;  // For user inputs
import java.util.ArrayList; //To create the to-do list
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
* SirPotato is the name of the chatbot 
* that we will be using in this project
* @author Rahul Agarwal
*/
public class SirPotato {

    private Scanner scanner;
    private String logo = "Sir Potato";
    private String horizontal_line = "___________________________ \n";
    private String indent = "   ";
    private ArrayList<Task> toDoList;
    DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Constructor for the chatbot, named SirPotato
     * Initialises the scanner and the toDoList.
     */
    public SirPotato() {
        this.scanner = new Scanner(System.in);
        this.toDoList = new ArrayList<Task>();
    }

    private LocalDate parseData(String dateToParse) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateToParse, formatter);
    }

    /**
     * Displays the welcome message when the user first starts the chat
     */
    public void displayWelcomeMessage() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do ya for?\n" + horizontal_line);
    }

    /**
     * Displays the bye message when the user types 'bye'.
     */
    public void displayByeMessage() {
        System.out.println("Bye mate, see you around.");
    }

    /**
     * Displays the message that a task has been added to the list
     * @param task the task that is being added to the toDoList.
     */
    public void displayAddedTask(Task task) {
        System.out.println(horizontal_line);
        System.out.println(indent + "Got it. I've added this task for ya.");
        System.out.println(indent + task);
        System.out.println(indent + "Now you've got " + toDoList.size() + " tasks in the list mate");
        System.out.println(horizontal_line);
    }

    private void validateDateFormat(String date) throws DukeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Mate, the date must be in the format dd-MM-yyyy.");
        }
    }

    /**
     * Validates user input
     * @param userInput The user's input to the chatbot
     */
    public void checkForErrors(String userInput) throws DukeException {
        if (userInput.startsWith("todo")) {
            if (userInput.length() <= 5 || userInput.substring(5).isEmpty()) {
                throw new DukeException("Mate, you have got to give us a description of the task");
            }
        } else if (userInput.startsWith("deadline")) {
            String[] sectionedString = userInput.split("/by ");
            if (sectionedString.length < 2 || 
                sectionedString[0].substring(9).isEmpty() || 
                sectionedString[1].isEmpty()) {
                throw new DukeException("Mate, a deadline must include a task, and the deadline");
            }
            validateDateFormat(sectionedString[1].trim());
        } else if (userInput.startsWith("event")) {
            String[] sectionedString = userInput.split(" /from | /to ");
            if (sectionedString.length < 3 || sectionedString[0].substring(6).isEmpty() ||
                sectionedString[1].isEmpty() || sectionedString[2].isEmpty()) {
                throw new DukeException("Mate, an event should have the description, the start, and the end.");
            }
            validateDateFormat(sectionedString[1].trim());
            validateDateFormat(sectionedString[2].trim());
        } else if (userInput.startsWith("delete")) {
            if (userInput.length() <= 7) {
                throw new DukeException("You need to say which item to delete");
            }
        } else if (!userInput.equals("bye") && !userInput.equals("list") && !userInput.startsWith("mark") && !userInput.startsWith("unmark")) {
            throw new DukeException("I'm sorry, that is not a valid input");
        }
    }

    /**
     * Prints out the deletion message 
     */

    public void displayDeletionMessage(Task task) {
        System.out.println(horizontal_line);
        System.out.println("Gotcha mate, I've deleted the following task: ");
        System.out.println(task);
        System.out.println("Now you've got " + (toDoList.size() - 1) + " tasks left.");
        System.out.println(horizontal_line);
    }

    /**
     * Populates the toDoList using data file 
     * @param filePath the path of the data file to read
     */
    private void readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] sectionedString = line.split(" \\| ");
            String type = sectionedString[0];
            boolean isDone = sectionedString[1].equals("1");
            String description = sectionedString[2];
            Task task = null;
            if (type.equals("T")) {
                task = new Todo(description);
            } else if (type.equals("D")) {
                LocalDate dl = parseData(sectionedString[3]);
                task = new Deadline(description, dl);
            } else if (type.equals("E")) {
                LocalDate from = parseData(sectionedString[3]);
                LocalDate to = parseData(sectionedString[4]);
                task = new Event(description, from, to);
            }
            System.out.println(task);
            toDoList.add(task);
        }
        
    }

    /**
     * Saves the toDoList and writes it to the data file
     * @param filePath the path of data file to write to
     * @param toDoList the toDoList that will be saved 
     */
    private void writeToFile(String filePath, ArrayList<Task> toDoList) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (Task task : toDoList) {
            String type = task instanceof Todo ? "T" : task instanceof Deadline ? "D" : "E";
            String isDone = task.isDone ? "1" : "0";
            String textToAdd = type + " | " + isDone + " | " + task.description;
            if (task instanceof Deadline) {
                textToAdd += " | " + ((Deadline) task).by.format(saveFormatter);
            } else if (task instanceof Event) {
                textToAdd += " | " + ((Event) task).from.format(saveFormatter) + " | " + 
                ((Event) task).to.format(saveFormatter);
            }
            fw.write(textToAdd + System.lineSeparator());
        }
        fw.close();
    }


    /**
     * Starts the chat with the bot. Keeps accepting user input until the user types 'bye'.
     * Saves the data to a file as you go / creates a file if one doesn't exist 
     */
    public void startChat() {

        //Check to see if the data.txt file exists 
        //If it doesn't, it will create the file and inform the user
        try {
            File f = new File("../../../data/list.txt"); 
            if (f.createNewFile()) {
                System.out.println("Creating your data file");
            } else {
                System.out.println("Initializing your data file");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //Use the data file to populate the toDoList.
        try {
            System.out.println("Loading your data file");
            readFile("../../../data/list.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        displayWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine();
            try {
                checkForErrors(userInput);
                if (userInput.equals("bye")) {
                    try {
                        writeToFile("../../../data/list.txt", toDoList);
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    System.out.println(horizontal_line + "\n" + indent + "Bye. Thanks mate." + "\n" + horizontal_line + "\n");
                    return;
                } else if (userInput.equals("list")) {
                    System.out.println(horizontal_line);
                    System.out.println(indent + "Here are the tasks in your list:");
                    System.out.println(indent + "List: ");
                    for (int i = 0; i < toDoList.size(); i++) {
                        System.out.println(indent + (i+1) + ". " + toDoList.get(i));
                    }
                    System.out.println(horizontal_line + "\n");
                } else if (userInput.length() >= 4 && userInput.substring(0, 4).equals("mark")) {
                    String[] commandParts = userInput.split(" ");
                    int itemNumber = Integer.parseInt(commandParts[1]) - 1;
                    toDoList.get(itemNumber).setCompletion(true);
                    System.out.println(horizontal_line);
                    System.out.println(indent + "Good on ya! I've marked it done:");
                    System.out.println(indent + indent + toDoList.get(itemNumber));
                    System.out.println(horizontal_line);
                } else if (userInput.length() >= 6 && userInput.substring(0, 6).equals("unmark")) {
                    String[] commandParts = userInput.split(" ");
                    int itemNumber = Integer.parseInt(commandParts[1]) - 1;
                    toDoList.get(itemNumber).setCompletion(false);
                    System.out.println(indent + "What's happened here mate? I've unmarked it for ya.");
                    System.out.println(indent + indent + toDoList.get(itemNumber));
                    System.out.println(horizontal_line);
                } else if (userInput.startsWith("todo")) {
                    String description = userInput.substring(5); 
                    Todo todo = new Todo(description);
                    toDoList.add(todo);
                    displayAddedTask(todo);
                } else if (userInput.startsWith("deadline")) {
                    String[] sectionedString = userInput.split("/by ");
                    String description = sectionedString[0].substring(9);
                    LocalDate by = parseData(sectionedString[1]);
                    Deadline dl = new Deadline(description, by);
                    toDoList.add(dl);
                    displayAddedTask(dl);
                } else if (userInput.startsWith("event")) {
                    String[] sectionedString = userInput.split(" /from | /to ");
                    String description = sectionedString[0].substring(6); 
                    LocalDate from = parseData(sectionedString[1]); 
                    LocalDate to = parseData(sectionedString[2]); 
                    Event ev = new Event(description, from, to);
                    toDoList.add(ev);
                    displayAddedTask(ev);
                } else if (userInput.startsWith("delete")) {
                    String[] sectionedString = userInput.split(" ");
                    int itemToDelete = Integer.parseInt(sectionedString[1]) - 1;
                    displayDeletionMessage(toDoList.get(itemToDelete));
                    toDoList.remove(itemToDelete);
                } else {
                    throw new DukeException("That is not valid input mate. Please have another go.");
                }
            } catch (DukeException de) {
                System.out.println(horizontal_line);
                System.out.println(indent + de.getMessage());
                System.out.println(horizontal_line);
            }
        }
    }

    public static void main(String[] args) {

        SirPotato potato = new SirPotato();
        potato.startChat();

    }
}