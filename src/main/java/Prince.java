import java.util.Scanner;

public class Prince {

    private static String LINE = "    --------------------------------------";
    private static boolean TOGGLER = true;

    public static void main(String[] args) {

        // initialise scanner for user input
        Scanner sc = new Scanner(System.in);

        // initialise array for input storage
        Task[] tasksArray = new Task[100];

        printline();
        System.out.println("    Hello! I'm Prince");
        System.out.println("    What can I do for you?");
        printline();

        while (TOGGLER) {
            // get input from the user
            String input = sc.nextLine();
            printline();

            if (!input.equals("bye") && !input.equals("list")
                    && !input.contains("mark") && !input.contains("unmark")) {

                // create a new task
                Task task = new Task(input);
                // get task id to add into array
                int taskID = task.getTaskID();
                // add the task to the arraylist
                tasksArray[taskID] = task;

                System.out.println("    added: " + input);
                printline();
            } else if (input.equals("list")) {

                int length = tasksArray.length;
                System.out.println("    Here are the tasks in your list:");

                // print the list of inputs
                for (int i = 0; i < length; i++) {
                    if (tasksArray[i] != null) {
                        // get the task
                        Task task = tasksArray[i];

                        // formatting for numbering of list
                        int listNum = i + 1;
                        String numDot = listNum + ".";

                        System.out.println("    " + numDot + task.toString());
                    }
                }

                printline();
            } else if (input.contains("unmark")) {

                // extra check to make sure the start of input is "unmark"
                String checkMark = input.substring(0, 6);
                if (checkMark.equals("unmark")) {
                    // get index of input
                    int index = getIndex(input);
                    // mark task as undone
                    Task task = tasksArray[index];
                    task.markAsNotDone();

                    System.out.println("      " + task.toString());
                }

                printline();
            } else if (input.contains("mark")) {

                String checkMark = input.substring(0, 4);
                if (checkMark.equals("mark")) {
                    // get index of input
                    int index = getIndex(input);
                    // mark task as undone
                    Task task = tasksArray[index];
                    task.markAsDone();

                    System.out.println("      " + task.toString());
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

    private static int getIndex(String input) {
        if (input.contains("unmark")) {
            // get character value of index in the input
            String indexAsString = input.substring(7);
            // convert to arr index
            int index = Integer.valueOf(indexAsString) - 1;
            return index;
        } else if (input.contains("mark")) {
            // get character value of index in input
            String indexAsString = input.substring(5);
            // convert to arr index
            int index = Integer.valueOf(indexAsString) - 1;
            return index;
        } else {
            // should not reach here
            return -1;
        }
    }
}
