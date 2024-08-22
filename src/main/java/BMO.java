import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BMO {

    private Storage storage;
    private String line = "____________________________________________________________\n";

    public BMO(Storage storage) {
        this.storage = storage;
    }

    //Logic for running the chatbot for every command input
    public String commandLoop(String commandInput) throws BMOException {
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

                if (index >= storage.getSize() || index <= 0) {
                    throw new BMOException("\nOh no! The task number you specified does not exist!");
                }
                Task taskToMark = storage.getTask(index);
                taskToMark.mark();
                return "\nNice! I've marked this task as done:\n" + taskToMark.toString() + "\n" + line;

            //Unmarks a completed task as incomplete
            case "unmark":
                if (words.length < 2) {
                    throw new BMOException("\nOh no! Please specify the task number you want to unmark!");
                }
                //Parses the second word as the index of the task to unmark
                int index2 = Integer.parseInt(words[1]) - 1;

                if (index2 >= storage.getSize() || index2 <= 0) {
                    throw new BMOException("\nOh no! The task number you specified does not exist!");
                }
                Task taskToUnmark = storage.getTask(index2);
                taskToUnmark.unmark();
                return "\nOK, I've marked this task as not done yet:\n" + taskToUnmark.toString() + "\n" + line;

            //Adds a todo task (no deadline) to the list 
            case "todo":
                if (description.equals("")) {
                    throw new BMOException("\nOh no! The description of a todo cannot be empty!");
                }
                Task todo = new ToDo(description);
                storage.addTask(todo);
                return "\nGot it. I've added this task:\n" + todo.toString() + "\n" + storage.getNumOfTasks() + "\n" + line;

            //Adds a deadline task (with deadline) to the list
            case "deadline":
                if (description.equals("")) {
                    throw new BMOException("\nOh no! The description of a deadline cannot be empty!");
                }

                //Splits the description into task description and deadline
                String[] deadlineDetails = description.split(" /by ");
                Task deadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                storage.addTask(deadline);
                return "\nGot it. I've added this task:\n" + deadline.toString() + "\n" + storage.getNumOfTasks() + "\n" + line;

            //Adds an event task (with start and end timings) to the list
            case "event":
                if (description.equals("")) {
                    throw new BMOException("\nOh no! The description of an event cannot be empty!");
                }

                //Splits the description into task description and event timings
                String[] eventDetails = description.split(" /from ");
                String[] eventTimings = eventDetails[1].split(" /to ");
                Task event = new Event(eventDetails[0], eventTimings[0], eventTimings[1]);
                storage.addTask(event);
                return "\nGot it. I've added this task:\n" + event.toString() + "\n" + storage.getNumOfTasks() + "\n" + line;

            //Deletes a task from the list
            case "delete":
                if (words.length < 2) {
                    throw new BMOException("\nOh no! Please specify the task number you want to delete!");
                }
                //Parses the second word as the index of the task to delete
                int index3 = Integer.parseInt(words[1]) - 1;

                if (index3 >= storage.getSize() || index3 <= 0) {
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
        //Initialise storage, scanner and BMO chatbot
        Storage storage = new Storage();
        BMO bmo = new BMO(storage);
        Scanner sc = new Scanner(System.in);
        System.out.println(bmo.welcomeMessage());

        //Awaits user input
        String command = sc.nextLine();

        try {
            //Loop to read user inputted commands
            while (!command.equals("bye")) {
                System.out.println(bmo.commandLoop(command));
                command = sc.nextLine();
            }        
            sc.close();
            System.out.println(bmo.exitMessage());
        } catch (BMOException e) {
            System.out.println(e.getMessage());
        } 
    }
}
