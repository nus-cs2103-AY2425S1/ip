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

                System.out.println("    Got it. I've added this task:");

                if (input.contains("todo")) {
                    String desc = getTodo(input);
                    Todo todo = new Todo(desc);
                    int todoID = todo.getTaskID();
                    tasksArray[todoID] = todo;
                    System.out.println("      " + todo.toString());
                }
                if (input.contains("deadline")) {
                    String desc = getDeadline(input);
                    String by = getBy(input);
                    Deadline deadlineTask = new Deadline(desc, by);
                    int deadlineID = deadlineTask.getTaskID();
                    tasksArray[deadlineID] = deadlineTask;
                    System.out.println("      " + deadlineTask.toString());
                }
                if (input.contains("event")) {
                    String desc = getEvent(input);
                    String from = getFrom(input);
                    String to = getTo(input);
                    Event event = new Event(desc, from, to);
                    int eventID = event.getTaskID();
                    tasksArray[eventID] = event;
                    System.out.println("      " + event.toString());
                }

                System.out.println("Now you have " + getTaskArrayLength(tasksArray) +
                        " tasks in the list.");
                printline();
            } else if (input.equals("list")) {

                int length = tasksArray.length;
                System.out.println("    Here are the tasks in your list:");

                // print the list of inputs
                for (int i = 0; i < length; i++) {
                    if (tasksArray[i] != null) {
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

    // method to get the integer when inputting mark or unmark
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

    // method to get length of task array
    private static int getTaskArrayLength(Task[] arr) {
        int x = 0;
        for (int i = 0; i < 100; i++) {
            if (arr[i] != null) {
                x++;
            }
        }
        return x;
    }

    /*
     * Mehthods to get descriptions of each respective task
     */
    // method to get description of the todo input
    private static String getTodo(String input) {
        String[] arr = input.split("todo");
        String todo = arr[1].trim();
        return todo;
    }

    // method to get description of the deadline input
    private static String getDeadline(String input) {
        String[] arr = input.split("deadline|/by");
        String deadline = arr[1].trim();
        return deadline;
    }

    // method to get description of the event input
    private static String getEvent(String input) {
        String[] arr = input.split("event|/from|/to");
        String event = arr[1].trim();
        return event;
    }
    

    /*
     * Methods to get dates of respective inputs
     */
    // method to get the deadline of deadline task input
    private static String getBy(String input) {
        String[] arr = input.split("/by");
        String by = arr[1].trim();
        return by;
    }

    // method to get from of the event input
    private static String getFrom(String input) {
        String[] arr = input.split("/from|/to");
        String from = arr[1].trim();
        return from;
    }

    // method to get to of the event input
    private static String getTo(String input) {
        String[] arr = input.split("/from|/to");
        String to = arr[2].trim();
        return to;
    }
}
