import java.util.Scanner;
import java.util.ArrayList;

public class Prince {

    private static String LINE = "--------------------------------------";
    private static boolean TOGGLER = true;

    public static void main(String[] args) {

        // initialise scanner for user input
        Scanner sc = new Scanner(System.in);

        // initialise arr for input storage
        ArrayList<Task> arr = new ArrayList<Task>();

        printline();
        System.out.println("Hello! I'm Prince");
        System.out.println("What can I do for you?");
        printline();

        while (TOGGLER) {
            // get input from the user
            String input = sc.nextLine();
            printline();

            if (!input.equals("bye") && !input.equals("list")
                    && !input.contains("mark") && !input.contains("unmark")) {

                System.out.println("added: " + input);
                printline();

                // create a new task
                Task task = new Task(input);
                // add the task to the arraylist
                arr.add(task);

            } else if (input.equals("list")) {

                int length = arr.size();

                // print the list of inputs
                for (int i = 0; i < length; i++) {
                    // get the task
                    Task task = arr.get(i);

                    // get description of task
                    String desc = task.getDescription();

                    // get status of task
                    String status = task.getStatusIcon();

                    // formatting for numbering of list
                    int listNum = i + 1;
                    String numDot = listNum + ".";

                    System.out.println(numDot + status + desc);
                }

                printline();

            } else if (input.contains("unmark")) {

                // extra check to make sure the start of input is "unmark"
                String checkMark = input.substring(0, 6);
                if (checkMark.equals("unmark")) {
                    // get input of index
                    String indexAsString = input.substring(7);
                    // convert to arr index
                    int index = Integer.valueOf(indexAsString) - 1;
                    // mark task as undone
                    Task task = arr.get(index);
                    task.markAsNotDone();

                    // get description of task
                    String desc = task.getDescription();

                    // get status of task
                    String status = task.getStatusIcon();

                    System.out.println(status + desc);
                }

                printline();
               
            } else if (input.contains("mark")) {

                String checkMark = input.substring(0, 4);
                if (checkMark.equals("mark")) {
                    // get input of index
                    String indexAsString = input.substring(5);
                    // convert to arr index
                    int index = Integer.valueOf(indexAsString) - 1;
                    // mark task as undone
                    Task task = arr.get(index);
                    task.markAsDone();

                    // get description of task
                    String desc = task.getDescription();

                    // get status of task
                    String status = task.getStatusIcon();

                    System.out.println(status + desc);
                }

                printline();
              
            } else {
                TOGGLER = false;
            }
        }

        // close and cleanup scanner
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");

    }

    private static void printline() {
        System.out.println(LINE);
    }
}
