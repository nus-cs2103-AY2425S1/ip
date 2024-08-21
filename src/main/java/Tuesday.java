import java.sql.Array;
import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList; // import the ArrayList class

public class Tuesday {
    private static ArrayList<Task> tasksArray = new ArrayList<>();

    private static void Msg_welcome() {
        System.out.println("_______________________________\n"
                + "Hello! I'm Tuesday, a randomly created bot.\n"
                + "What can I do for you?\n"
                + "_______________________________");
    }
    private static void Msg_bye() {
        System.out.println("_______________________________"
                + "\nBye bye. Hope to see you again soon!\n"
                + "_______________________________");
    }
    private static void Msg_list() {
        String message = "Here are the tasks in your list:\n";
        for (int n = 0; n < Task.count; n++) {
            message += (n+1) + "." + tasksArray.get(n).toString() + "\n";
        }
        System.out.println("_______________________________\n"
                + message
                + "_______________________________");
    }
    private static void Msg_blah() {
        System.out.println("_______________________________\n"
                + "blah\n"
                + "_______________________________");
    }

    private static void comm_mark(int task_num, boolean state) {
        tasksArray.get(task_num - 1).changeDone(state);
        String message = "";
        if (state)
            message = "Nice! I've marked this task as done: \n  ";
        else
            message = "OK, I've marked this task as not done yet: \n  ";

        message += tasksArray.get(task_num - 1).toString();

        System.out.println("_______________________________\n"
                + message
                + "\n_______________________________");
    }

    private static void comm_todo(String title) {
        ToDo taskItem = new ToDo(title);
        tasksArray.add(taskItem);

        System.out.println("_______________________________\n"
                + "Got it. I've added this task:\n  "
                + tasksArray.get(Task.count - 1).toString()
                + "\nNow you have " + Task.count + " task(s) in the list."
                + "\n_______________________________");
    }

    private static void comm_deadline(String title, String by_msg) {
        Deadline deadlineItem = new Deadline(title, by_msg);
        tasksArray.add(deadlineItem);

        System.out.println("_______________________________\n"
                + "Got it. I've added this task:\n  "
                + tasksArray.get(Task.count - 1).toString()
                + "\nNow you have " + Task.count + " task(s) in the list."
                + "\n_______________________________");
    }

    private static void comm_event(String title, String from_msg, String to_msg) {
        Event eventItem = new Event(title, from_msg, to_msg);
        tasksArray.add(eventItem);

        System.out.println("_______________________________\n"
                + "Got it. I've added this task:\n  "
                + tasksArray.get(Task.count - 1).toString()
                + "\nNow you have " + Task.count + " task(s) in the list."
                + "\n_______________________________");
    }

    public static void main(String[] args) {
        Msg_welcome();
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String userInput = "";
        String[] userInputArr;

        while (true) {
            userInput = myObj.nextLine();  // Read user input
            userInputArr = userInput.split(" ", 2);
            if (userInputArr[0].equals("bye"))
                break;

            if (userInput.equals("list")) {
                Msg_list();
            } else if (userInput.equals("blah")) {
                Msg_blah();
            } else if (userInputArr[0].equals("mark")) {
                try {
                    comm_mark(Integer.parseInt(userInputArr[1]), true);
                } catch(Exception e){
                    continue;
                }
            } else if (userInputArr[0].equals("unmark")) {
                try {
                    comm_mark(Integer.parseInt(userInputArr[1]), false);
                } catch(Exception e){
                    continue;
                }
            } else if (userInputArr[0].equals("todo")) {
                try {
                    comm_todo(userInputArr[1]);
                } catch(Exception e){
                    continue;
                }
            } else if (userInputArr[0].equals("deadline")) {
                try {
                    String[] msg_split_by = userInputArr[1].split("/by ", 2);
                    comm_deadline(msg_split_by[0], msg_split_by[1]);
                } catch(Exception e){
                    continue;
                }
            } else if (userInputArr[0].equals("event")) {
                try {
                    String[] msg_split_from = userInputArr[1].split("/from ", 2);
                    String[] msg_split_to = msg_split_from[1].split(" /to ", 2);
                    comm_event(msg_split_from[0], msg_split_to[0], msg_split_to[1]);
                } catch(Exception e){
                    continue;
                }
            } else {
                comm_todo(userInput);
            }
        }
        Msg_bye();
    }
}
