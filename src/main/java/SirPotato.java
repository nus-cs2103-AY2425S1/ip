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

    public SirPotato() {
        this.scanner = new Scanner(System.in);
        this.toDoList = new ArrayList<Task>();
    }

    public void displayWelcomeMessage() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do ya for?\n" + horizontal_line);
    }

    public void displayByeMessage() {
        System.out.println("Bye mate, see you around.");
    }

    public void displayAddedTask(Task task) {
        System.out.println(horizontal_line);
        System.out.println(indent + "Got it. I've added this task for ya.");
        System.out.println(indent + task);
        System.out.println(indent + "Now you've got " + toDoList.size() + " tasks in the list mate");
        System.out.println(horizontal_line);
    }



    public void startChat() {
        displayWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine();
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
            } else {
                toDoList.add(new Task(userInput));
                System.out.println(horizontal_line + "\n" + indent + 
                            "Added to list: " + userInput + "\n" + horizontal_line + "\n");
            }
        }
    }

    public static void main(String[] args) {
        
        SirPotato potato = new SirPotato();
        potato.startChat();
    }
}