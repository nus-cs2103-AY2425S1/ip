import java.util.Arrays;
import java.util.Scanner;

public class FRIDAY {
    private Boolean isActive;
    private String userInput;
    private String output;
    private final String greeting, exitMessage;
    private String divider;
    private final Task[] storage = new Task[100];
    private int storagePointer = 0;

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

        //start the bot
        isActive = true;

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
                        if (taskDetails.isEmpty()) {
                            throw new FRIDAYException("ERROR: Please input the task that you are trying to mark as complete");
                        }
                        storage[Integer.parseInt(parts[1]) - 1].check();
                        String marked = divider + "Nice! I've marked this task as done\n" + storage[Integer.parseInt(parts[1]) - 1] + "\n" + divider;
                        System.out.println(marked);
                        break;
                    case ("unmark"):
                        if (taskDetails.isEmpty()) {
                            throw new FRIDAYException("ERROR: Please input the task that you are trying to mark as incomplete");
                        }
                        storage[Integer.parseInt(parts[1]) - 1].uncheck();
                        String unmarked = divider + "Ok, I've marked this task as not done yet\n" + storage[Integer.parseInt(parts[1]) - 1] + "\n" + divider;
                        System.out.println(unmarked);
                        break;
                    case ("todo"):
                        if (taskDetails.isEmpty()) {
                            throw new FRIDAYException("ERROR: Please note that the description of a task cannot be left empty");
                        }
                        //create new to do task
                        Task newToDo = new ToDo(taskDetails);
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
                        Task newDeadline = new Deadline(deadlineDescription, deadlineDeadline);
                        add(newDeadline);
                        break;
                    case ("event"):
                        if (taskDetails.isEmpty()) {
                            throw new FRIDAYException("ERROR: Please note that the description of a task cannot be left empty");
                        }
                        //create new event task
                        String[] eventDetails = taskDetails.split("/");
                        String eventDescription = eventDetails[0];
                        String eventStart = eventDetails[1];
                        String eventEnd = eventDetails[2];
                        Task newEvent = new Event(eventDescription, eventStart, eventEnd);
                        add(newEvent);
                        break;
                    case ("bye"):
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
                        throw new FRIDAYException("It appears that you have attempted to log an unrecognized task type. Please try again");
                }
            }
            catch(FRIDAYException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //function to add string to storage array
    public void add(Task input) {
        //add the input to the array
        storage[storagePointer] = input;
        storagePointer += 1;
        output = divider + "Got it. I've added this task:\n " + input.getDescription() + "\nNow you have " + storagePointer + " tasks in your list\n" + divider;
        System.out.println(output);
    }

    //function to display storage in list format
    public void display() {
        String displayList = divider;
        for(int i = 1; i <= storagePointer; i++) {
            displayList += i + ". " + storage[i - 1] + "\n";
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
