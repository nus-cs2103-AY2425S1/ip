import java.sql.Array;
import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList; // import the ArrayList class

public class Tuesday {
    private static ArrayList<Task> tasksArray = new ArrayList<>();
    private static int count = 0;

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
        for (int n = 0; n < count; n++) {
            message += (n+1) + ".[" +tasksArray.get(n).getDone() + "] " + tasksArray.get(n).getTask() + "\n";
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
            message = "Nice! I've marked this task as done: \n  [X] ";
        else
            message = "OK, I've marked this task as not done yet: \n  [ ] ";

        message += tasksArray.get(task_num - 1).getTask();

        System.out.println("_______________________________\n"
                + message
                + "\n_______________________________");
    }

    private static void add_stuff(String item) {
        Task taskItem = new Task(item);
        tasksArray.add(taskItem);
        count++;

        System.out.println("_______________________________\n"
                + "added: " + item
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
            }
            else {
                add_stuff(userInput);
            }
        }
        Msg_bye();
    }
}
