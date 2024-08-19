import Exceptions.*;
import Task.Task;
import Task.TodoTask;
import Task.DeadlineTask;
import Task.EventTask;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class David {
    private Scanner sc;
    private String inputString;
    private String intro =
            "____________________________________________________________\n" +
            " Hello! I'm David\n" +
            " What can I do for you?\n" +
            "____________________________________________________________";
    private String outro =
            "____________________________________________________________\n" +
            "Bye. Hope to see you again soon!\n" +
            "____________________________________________________________\n";
    public List<Task> tasks;

    //constructor for David
    public David() {
        this.sc = new Scanner(System.in);
        this.inputString = "";
        this.tasks = new ArrayList<Task>();
    };

    //run the chatbot
    public void activateChatBot() {
        System.out.println(this.intro);
        while(true) {
            inputString = sc.nextLine(); //get next input

            if (inputString.equals("bye")) {
                endChatBot();  //end chatbot
                break;
            }
            try {
                switch (StringParser.parseStringToCommand(inputString)) {
                    case "LIST":
                        listTasks();
                        break;
                    case "MARK":
                        markTaskAsDone(inputString);
                        break;
                    case "UNMARK":
                        markTaskAsUnDone(inputString);
                        break;
                    case "TODO":
                        addTodoTask(inputString);
                        break;
                    case "EVENT":
                        addEventTask(inputString);
                        break;
                    case "DEADLINE":
                        addDeadlineTask(inputString);
                        break;
                    default:
                        throw new DavidUnknownActionException();
                }
            } catch (DavidException e) {
                /*
                Catch all exception.
                The actual error message thrown/shown depends on the runtime type of
                the exception thrown. (Polymorphism)
                 */
                System.out.println(e.showErrorMessage());
            }

        }
    }

    /*
    Adds todo task to array list of tasks
     */
    public void addTodoTask(String s) throws DavidInvalidArgumentsException{
        String event = StringParser.parseStringToArguments(s);
        Task t = new TodoTask(event);
        this.tasks.add(t);
        System.out.println(
                "____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        t + "\n" +
                        "     You now have " + this.tasks.size() +  " tasks in the list.\n" +
                        "____________________________________________________________\n");
    }

    /*
    Adds event task to array list of tasks
     */
    public void addEventTask(String s) throws DavidInvalidArgumentsException, DavidInvalidRangeException {
        String event = StringParser.parseStringToArguments(s);
        String[] eventSplit = event.split(" /from", 2);
        String eventName = eventSplit[0];
        if (eventSplit.length <= 1) {
            //"from" field does not exist
            throw new DavidInvalidRangeException();
        }
        String[] eventDetails = eventSplit[1].split(" /to", 2);
        if (eventDetails.length <= 1 || eventDetails[0].trim().equals("") || eventDetails[1].trim().equals("")) {
            //only the "from" field exist
            throw new DavidInvalidRangeException();
        }
        Task t = new EventTask(eventName, eventDetails[0], eventDetails[1]);
        this.tasks.add(t);
        System.out.println(
                "____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        t + "\n" +
                        "     You now have " + this.tasks.size() +  " tasks in the list.\n" +
                        "____________________________________________________________\n");
    }

    /*
    Adds deadline task to array list of tasks
     */
    public void addDeadlineTask(String s) throws DavidInvalidArgumentsException, DavidInvalidDeadlineException {
        String event = StringParser.parseStringToArguments(s);
        String[] eventSplit = event.split(" /by", 2);
        if(eventSplit.length <= 1 || eventSplit[1].trim().equals("")) {
            //deadline is not added to the input string
            throw new DavidInvalidDeadlineException();
        }
        Task t = new DeadlineTask(eventSplit[0], eventSplit[1]);
        this.tasks.add(t);
        System.out.println(
                "____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        t + "\n" +
                        "     You now have " + this.tasks.size() +  " tasks in the list.\n" +
                        "____________________________________________________________\n");
    }

    public void markTaskAsDone(String s) throws DavidInvalidArgumentsException{
        try {
            String index = StringParser.parseStringToArguments(s);
            Task t = tasks.get(Integer.parseInt(index) -1);
            t.markAsDone();
            System.out.println(
                    "____________________________________________________________\n" +
                            "Nice! I've marked this task as done:\n" +
                            t + "\n" +
                            "____________________________________________________________\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No such task! Please enter a valid task.");
        } catch (NumberFormatException e) {
            System.out.println("The number you entered is not a valid number. Please enter a valid number");
        }
    }

    public void markTaskAsUnDone(String s) throws DavidInvalidArgumentsException {
        try {
            String index = StringParser.parseStringToArguments(s);
            Task t = tasks.get(Integer.parseInt(index) - 1);
            t.markAsUnDone();
            System.out.println(
                    "____________________________________________________________\n" +
                            "Okay, I've marked this task as not done yet:\n" +
                            t + "\n" +
                            "____________________________________________________________\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No such task! Please enter a valid task.");
        } catch (NumberFormatException e) {
            System.out.println("The number you entered is not a valid number. Please enter a valid number");
        }
    }

    public void listTasks() {
        for (int i = 0; i<tasks.size(); i++) {
            System.out.println(i+1 + ": " + tasks.get(i));
        }
    }

    public void endChatBot() {
        System.out.println(outro);
    }

    public static void main(String[] args) {
        David chat = new David();
        chat.activateChatBot();
    }
}
