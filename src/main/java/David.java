import Task.Task;

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
        this.tasks = new ArrayList<>();
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
                default:
                    addToTask(inputString);
            }
        }
    }

    public void addToTask(String s) {
        Task t = new Task(s);
        tasks.add(t);
        System.out.println(
                "____________________________________________________________\n" +
                        "added: " + inputString + "\n" +
                        "____________________________________________________________\n");
    }

    public void markTaskAsDone(String s) {
        try {
            String index = StringParser.parseStringToArguments(s);
            Task t = tasks.get(Integer.parseInt(index) -1);
            t.markAsDone();
            System.out.println(
                    "____________________________________________________________\n" +
                            "Nice! I've marked this task as done: \n" +
                            "[X] " + t.getTask() + "\n" +
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
                            "Okay, I've marked this task as not done yet: \n" +
                            "[ ] " + t.getTask() + "\n" +
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
