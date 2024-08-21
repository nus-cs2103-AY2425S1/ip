import java.util.Scanner;

public class Prince {

    private static String LINE = "    ___________________________________________";

    // flag to control input printing when running automated tests
    private static boolean DEBUG = false;

    public static void main(String[] args) throws PrinceException {

        // check if debug argument is passed during automated text ui testing
        if (args.length > 0 && args[0].equals("debug")) {
            DEBUG = true;
        }

        // initialise scanner for user input
        Scanner sc = new Scanner(System.in);

        // initialise array for input storage
        Task[] tasksArray = new Task[100];

        printline();
        System.out.println("    Hello! I'm Prince");
        System.out.println("    What can I do for you?");
        printline();

        while (true) {
            // get input from the user
            String input = sc.nextLine();

            // prints input if in automated testing mode
            if (DEBUG) {
                System.out.println(input);
            }

            printline();

            // input equals bye
            // input equals list
            // input contains unmark
            // input contains mark
            // all other event inputs

            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    printList(tasksArray);
                    printline();
                } else if (input.contains("unmark")) {
                    // extra check to make sure the start of input is "unmark"
                    String checkMark = input.substring(0, 6);
                    if (checkMark.equals("unmark")) {
                        int index = getIndex(input);
                        Task task = tasksArray[index];
                        task.markAsNotDone();
                        System.out.println("      " + task.toString());
                    }
                    printline();
                } else if (input.contains("mark")) {
                    // extra check to make sure the start of input is "mark"
                    String checkMark = input.substring(0, 4);
                    if (checkMark.equals("mark")) {
                        int index = getIndex(input);
                        Task task = tasksArray[index];
                        task.markAsDone();
                        System.out.println("      " + task.toString());
                    }
                    printline();
                } else if (input.equals("todo") || input.equals("deadline") ||
                        input.equals("event")) {
                    throw new PrinceException("    Please describe your '" + input + "' task in more detail!");
                } else if (input.contains("todo") || input.contains("deadline") ||
                        input.contains("event")) {
                    if (input.contains("todo")) {
                        handleTodo(input, tasksArray);
                    }
                    if (input.contains("deadline")) {
                        handleDeadline(input, tasksArray);
                    }
                    if (input.contains("event")) {
                        handleEvent(input, tasksArray);
                    }
                    printline();
                } else {
                    throw new PrinceException("    Sorry, I am not sure what '" + input +
                            "' means. Please try again!");
                }
            } catch (PrinceException err) {
                System.out.println(err.toString());
                printline();
            }
        }

        // close and cleanup scanner
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    /*
     * HELPER FUNCTIONS
     */

    // method to print a line
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

    /*
     * Methods related to printing out list format of tasks
     */

    // get length of task array
    private static int getTaskArrayLength(Task[] arr) {
        int x = 0;
        for (int i = 0; i < 100; i++) {
            if (arr[i] != null) {
                x++;
            }
        }
        return x;
    }

    private static void printList(Task[] tasksArray) {
        System.out.println("    Here are the tasks in your list:");

        int length = tasksArray.length;
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
    }

    /*
     * Methods related to handling TODo tasks
     */

    // method to get description of the todo input
    private static String getTodo(String input) {
        String[] arr = input.split("todo");
        String todo = arr[1].trim();
        return todo;
    }

    private static void handleTodo(String input, Task[] tasksArray) {
        System.out.println("    Got it. I've added this task:");
        String desc = getTodo(input);
        Todo todo = new Todo(desc);
        int todoID = todo.getTaskID();
        tasksArray[todoID] = todo;
        System.out.println("      " + todo.toString());
        System.out.println("    Now you have " + getTaskArrayLength(tasksArray) +
                " tasks in the list.");
    }

    /*
     * Methods related to handling DEADLINE tasks
     */

    // method to get description of the deadline input
    private static String getDeadline(String input) {
        String[] arr = input.split("deadline|/by");
        String deadline = arr[1].trim();
        return deadline;
    }

    // method to get the deadline of deadline task input
    private static String getBy(String input) {
        String[] arr = input.split("/by");
        String by = arr[1].trim();
        return by;
    }

    private static void handleDeadline(String input, Task[] tasksArray) {
        System.out.println("    Got it. I've added this task:");
        String desc = getDeadline(input);
        String by = getBy(input);
        Deadline deadlineTask = new Deadline(desc, by);
        int deadlineID = deadlineTask.getTaskID();
        tasksArray[deadlineID] = deadlineTask;
        System.out.println("      " + deadlineTask.toString());
        System.out.println("    Now you have " + getTaskArrayLength(tasksArray) +
                " tasks in the list.");
    }

    /*
     * Methods related to handling EVENT tasks
     */

    // method to get description of the event input
    private static String getEvent(String input) {
        String[] arr = input.split("event|/from|/to");
        String event = arr[1].trim();
        return event;
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

    private static void handleEvent(String input, Task[] tasksArray) {
        System.out.println("    Got it. I've added this task:");
        String desc = getEvent(input);
        String from = getFrom(input);
        String to = getTo(input);
        Event event = new Event(desc, from, to);
        int eventID = event.getTaskID();
        tasksArray[eventID] = event;
        System.out.println("      " + event.toString());
        System.out.println("    Now you have " + getTaskArrayLength(tasksArray) +
                " tasks in the list.");
    }
}
