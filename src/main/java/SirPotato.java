/**
* SirPotato is the name of the chatbot 
* that we will be using in this project
* @author Rahul Agarwal
*/


import java.util.Scanner;  // For user inputs
import java.util.ArrayList; //To create the to-do list

public class SirPotato {

    private Scanner scanner;
    private String logo = "Sir Potato";
    private String horizontal_line = "___________________________ \n";
    private String indent = "   ";
    private ArrayList<Task> toDoList;

    /**
     * Constructor for the chatbot, named SirPotato
     * Initialises the scanner and the toDoList.
     */
    public SirPotato() {
        this.scanner = new Scanner(System.in);
        this.toDoList = new ArrayList<Task>();
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
        } else if (userInput.startsWith("event")) {
            String[] sectionedString = userInput.split(" /from | /to ");
            if (sectionedString.length < 3 || sectionedString[0].substring(6).isEmpty() ||
                sectionedString[1].isEmpty() || sectionedString[2].isEmpty()) {
                throw new DukeException("Mate, an event should have the description, the start, and the end.");
            }
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
     * Starts the chat with the bot. Keeps accepting user input until the user types 'bye'.
     */
    public void startChat() {
        displayWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine();
            try {
                checkForErrors(userInput);
                if (userInput.equals("bye")) {
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
                    String by = sectionedString[1];
                    Deadline dl = new Deadline(description, by);
                    toDoList.add(dl);
                    displayAddedTask(dl);
                } else if (userInput.startsWith("event")) {
                    String[] sectionedString = userInput.split(" /from | /to ");
                    String description = sectionedString[0].substring(6); 
                    String from = sectionedString[1]; 
                    String to = sectionedString[2]; 
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