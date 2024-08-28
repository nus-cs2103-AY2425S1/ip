import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BMO {

    private Storage storage;
    private String line = "____________________________________________________________\n";

    public BMO(Storage storage, FileWriter writer) {
        this.storage = storage;
    }

    //Logic for running the chatbot for every command input
    public String commandLoop(String commandInput) throws Exception {
        //Stores the input line as an array of words
        String[] words = commandInput.split(" ");

        //Extracts the first word as the command word
        String commandWord = words[0];
        //Extracts the rest of the words as the description
        String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));

        //Switch statement to handle different commands
        switch (commandWord) {
        
        //Lists all the tasks in the list
        case "list":
            return "\nHere are the tasks in your list:\n" + storage.getTasks() + line;

        //Marks a task as completed
        case "mark":
            if (words.length < 2) {
                throw new BMOException("\nOh no! Please specify the task number you want to mark as done!");
            }
            //Parses the second word as the index of the task to mark
            int index = Integer.parseInt(words[1]) - 1;
            System.out.println(index);

            if (index >= storage.getSize() || index < 0) {
                throw new BMOException("\nOh no! The task number you specified does not exist!");
            }
            storage.markTask(index);

            return "\nNice! I've marked this task as done:\n" + storage.getTask(index).toString() + "\n" + line;

        //Unmarks a completed task as incomplete
        case "unmark":
            if (words.length < 2) {
                throw new BMOException("\nOh no! Please specify the task number you want to unmark!");
            }
            //Parses the second word as the index of the task to unmark
            int index2 = Integer.parseInt(words[1]) - 1;

            if (index2 >= storage.getSize() || index2 < 0) {
                throw new BMOException("\nOh no! The task number you specified does not exist!");
            }
            storage.unmarkTask(index2);
            return "\nOK, I've marked this task as not done yet:\n" + storage.getTask(index2).toString() + "\n" + line;

        //Adds a todo task (no deadline) to the list 
        case "todo":
            if (description.equals("")) {
                throw new BMOException("\nOh no! The description of a todo cannot be empty!");
            }
            storage.addTodo(description);
            return "\nGot it. I've added this task:\n" + storage.getRecentTask().toString() + "\n" + storage.getNumOfTasks() 
                + "\n" + line;

        //Adds a deadline task (with deadline) to the list
        case "deadline":
            if (description.equals("")) {
                throw new BMOException("\nOh no! The description of a deadline cannot be empty!");
            }

            //Splits the description into task description and deadline
            String[] deadlineDetails = description.split(" /by ");
            storage.addDeadline(deadlineDetails[0], deadlineDetails[1]);
            return "\nGot it. I've added this task:\n" + storage.getRecentTask().toString() 
                + "\n" + storage.getNumOfTasks() + "\n" + line;

        //Adds an event task (with start and end timings) to the list
        case "event":
            if (description.equals("")) {
                throw new BMOException("\nOh no! The description of an event cannot be empty!");
            }

            //Splits the description into task description and event timings
            String[] eventDetails = description.split(" /from ");
            String[] eventTimings = eventDetails[1].split(" /to ");
            storage.addEvent(eventDetails[0], eventTimings[0], eventTimings[1]);
            return "\nGot it. I've added this task:\n" + storage.getRecentTask().toString() 
                + "\n" + storage.getNumOfTasks() + "\n" + line;

        //Deletes a task from the list
        case "delete":
            if (words.length < 2) {
                throw new BMOException("\nOh no! Please specify the task number you want to delete!");
            }
            //Parses the second word as the index of the task to delete
            int index3 = Integer.parseInt(words[1]) - 1;

            if (index3 >= storage.getSize() || index3 < 0) {
                throw new BMOException("\nOh no! The task number you specified does not exist!");
            }
            Task taskToDelete = storage.getTask(index3);
            storage.deleteTask(index3);
            return "\nNoted. I've removed this task:\n" + taskToDelete.toString() + "\n" + storage.getNumOfTasks() + "\n" + line;

        //Catches unknown commands
        default:
            throw new BMOException("\nOh no! I do not recognise that command, please try again!");
        }
    }

    public String welcomeMessage() {
        return "Hello! I'm BMO!\nWhat can I do for you?\n" + line;
    }

    public String exitMessage() {
        return "Bye. Hope to see you again soon!\n" + line;
    }
    public static void main(String[] args) {   
        BMO bmo;
        FileWriter writer;
        Storage storage;
        Scanner sc;
        String FILEPATH = "ip/data/BMO.txt";
        
        //Initialise storage, scanner, file writer and BMO chatbot    
        try {
            //Creates storage log for task list
            File file = new File(FILEPATH);
            System.out.println("Attempting to create file at: " + file.getAbsolutePath());

            //Debugging for file creation
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create the directory if it doesn’t exist
                file.createNewFile(); // Create the file if it doesn’t exist
            }

            storage = new Storage(); 
            writer = new FileWriter(FILEPATH, true);
            Storage.setWriter(writer);
            bmo = new BMO(storage, writer);
            sc = new Scanner(System.in);
            
            storage.readStorageFile(FILEPATH);
            System.out.println(bmo.welcomeMessage());

            //Awaits user input
            String command = sc.nextLine();

            //Loop to read user inputted commands
            while (!command.equals("bye")) {
                System.out.println(bmo.commandLoop(command));
                command = sc.nextLine();
            }   

            Storage.closeWriter(); 
            sc.close();
            System.out.println(bmo.exitMessage());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
