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

            }
        }
    }

    public void addTodoTask(String s) {
        Task t = new TodoTask(s);
        this.tasks.add(t);
        System.out.println(
                "____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        t + "\n" +
                        "     You now have " + this.tasks.size() +  " tasks in the list.\n" +
                        "____________________________________________________________\n");
    }

    public void addEventTask(String s) {
        String event = StringParser.parseStringToArguments(s);
        String[] eventSplit = event.split(" /from", 2);
        String eventName = eventSplit[0];
        String[] eventDetails = eventSplit[1].split(" /to", 2);
        Task t = new EventTask(eventName, eventDetails[0], eventDetails[1]);
        this.tasks.add(t);
        System.out.println(
                "____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        t + "\n" +
                        "     You now have " + this.tasks.size() +  " tasks in the list.\n" +
                        "____________________________________________________________\n");
    }

    public void addDeadlineTask(String s) {
        String event = StringParser.parseStringToArguments(s);
        String[] eventSplit = event.split(" /by", 2);
        Task t = new DeadlineTask(eventSplit[0], eventSplit[1]);
        this.tasks.add(t);
        System.out.println(
                "____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        t + "\n" +
                        "     You now have " + this.tasks.size() +  " tasks in the list.\n" +
                        "____________________________________________________________\n");
    }

    public void markTaskAsDone(String s) {
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
        }
    }

    public void markTaskAsUnDone(String s) {
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
