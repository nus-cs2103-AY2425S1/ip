import java.util.ArrayList;
import java.util.Scanner;

/**
 * The SilverWolf class represents a chat bot application.
 * It allows users to add tasks, display the list of tasks, and show task statistics.
 */
public class SilverWolf {

    // List to store the tasks
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * Prints a divider line to the console for visual separation.
     */
    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Outputs the list of tasks to the console with their corresponding indices.
     * Each task is printed on a new line.
     */
    private static void outputList(){
        int index = 1;
        for(Task t : list){
            System.out.println(index + t.toString());
            index++;
        }
    }


    /**
     * Outputs the list of tasks to the console with their corresponding indices.
     * Each task is printed on a new line.
     */
    private static void showConfirmation(){
        printDivider();
        System.out.println("Got it. I've added this task:");
        System.out.println(list.get(list.size()-1));
        showTotalTask();
        printDivider();
        System.out.print("\n");
    }

    /**
     * Displays the total number of tasks currently in the list.
     */
    private static void showTotalTask(){
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * The main method serves as the entry point for the SilverWolf chat bot.
     * It displays a welcome message and processes user commands to manage tasks.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        // initialize the scanner to read user input

        String logo = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡴⠶⠒⠋⠉⠉⠉⠛⠓⠶⡶⠶⠒⠒⠲⠶⠶⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡴⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⢶⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠾⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠶⠦⢤⣀⡀⠉⠻⢦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠳⢦⣄⠙⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠟⠁⢀⡤⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⢿⡿⣷⡂⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠁⢀⡔⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢾⣷⡤⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠟⠀⢠⠏⠀⠀⠀⠀⡄⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣿⡀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠏⠀⢰⠃⠀⠀⠀⢠⣾⠁⠀⠀⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡀⠀⠀⠀⠀⠀⠀⠀⢄⠀⠀⠀⠀⠀⠀⠀⠘⢷⡀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡟⠀⢠⡇⠀⠀⠀⣰⢿⠇⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡄⠀⠀⠀⠀⢧⠀⠀⠀⠀⠀⠀⠀⠈⣧⠀⠀⠀⠀⠀⠀⠀⠈⢿⡄⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠁⠀⣾⠀⠀⠀⢠⢏⡏⢀⡆⠀⢀⡴⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢱⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⢸⡀⠀⠀⠀⠀⠀⠀⠀⠈⣷⠀⠀⠀⠀\n" +
                "⣿⣿⣶⡤⣀⠀⠀⢀⡴⣺⣿⡀⠀⡇⠀⠀⠀⡟⢸⣇⡞⠀⣴⠏⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡆⠀⢸⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠈⡇⠀⠀⠀⠀⠀⠀⠀⠀⠘⣧⠀⠀⠀\n" +
                "⠻⣿⣎⠙⠳⢯⣟⡵⠛⠉⠛⢷⣼⣃⡀⠀⢸⠁⢸⡝⢠⡾⢥⣄⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⡄⢸⠀⢸⠃⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡀⠀⠀\n" +
                "⠀⠈⢿⣷⡀⠀⠀⠀⠀⢸⠀⠈⠻⣿⡄⠀⣾⠀⣿⢠⠏⠀⡄⠉⠻⢾⡆⠀⠀⠀⡄⠀⠀⠀⠀⠀⠀⣿⢳⡟⠀⣾⠀⠀⠀⡁⠀⠀⠀⠀⠀⠁⠀⡄⠀⠀⠀⠀⢶⠀⠀⠘⣇⠀⠀\n" +
                "⠀⠀⠀⠹⣿⣦⠀⠀⣠⣾⣶⣦⣄⡈⢣⡀⣿⠀⣷⣯⣤⣾⣿⣄⡀⠀⢿⡀⠀⠸⣅⠀⠀⠀⠀⠀⠀⢹⣼⠃⢠⡏⠀⠀⠀⡄⠀⠀⠀⠀⠀⡆⠀⡇⠀⠀⠀⠀⢸⣧⠀⠀⣿⠀⠀\n" +
                "⠀⠀⠀⠀⠈⢺⣷⣞⡿⣿⢀⠀⢸⠺⡅⠓⣿⠀⣿⣿⣿⣿⣿⣿⣿⣷⣜⢷⡀⠀⣿⣆⠀⠀⠀⠀⠀⢸⡟⠀⡾⣇⠀⠀⢀⡇⠀⠀⠀⠀⠀⡇⢸⠁⢀⣠⣤⡀⢸⡿⣇⠀⢸⡄⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠈⠉⠀⣿⢸⣆⠈⣇⣷⠀⢹⠀⡿⠋⠁⢰⠟⠀⣾⠙⢻⣷⡻⣦⣻⡙⢷⣄⠀⣀⠀⡼⠁⣼⠃⢹⡆⠀⣼⣧⠀⠀⠀⢀⣤⣇⣯⣴⡿⠛⣾⠁⢸⠃⣿⠀⢸⡇⠀\n" +
                "⠀⠀⠀⠀⠀⢠⣤⣖⣲⣿⠟⢈⣙⣻⢹⣇⠈⣇⣷⠀⠀⣿⠀⠁⢿⣀⣸⢻⠁⠈⠉⠁⠙⠛⠳⢽⡿⢁⣾⣁⣴⣿⣧⣾⣥⣿⠀⠀⢀⣞⡼⠫⢚⣉⡀⠀⠿⠶⠾⠿⠿⢶⣾⣷⣏\n" +
                "⠀⠀⠀⠀⠀⠈⠻⣿⣦⣄⣀⡿⠉⣿⠈⠹⣆⠹⣿⡄⠀⢹⡄⠒⠀⠉⠡⡿⠀⠀⠀⠀⠀⠀⢀⣾⣷⡿⠿⠿⠿⠿⣿⣿⣿⣿⠀⢀⠎⢸⡧⠉⢩⣽⠇⠀⠀⣀⣤⣴⣾⣿⢹⡇⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢠⡿⠉⠙⠁⠀⡟⢧⣀⠙⢷⣹⣷⡀⠀⠛⠒⠦⠖⠚⠁⢀⣀⣘⣀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠄⣀⠙⡋⣇⣴⠋⢀⡾⠁⠀⣼⣛⣤⠶⠛⣽⡿⠋⠀⣿⣼⠁⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢀⡾⠁⠀⠀⠀⡼⣱⢰⣿⣿⣟⣿⡛⠃⠀⠀⠀⠀⠀⠀⠀⠀⣏⠉⠈⠉⢹⠀⠀⠀⠀⠀⠐⠃⠜⠡⠞⠀⢩⡏⣠⠟⠀⣠⣾⠙⠋⠀⠀⣰⡏⣧⠀⠀⣿⠟⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢀⡾⠁⠀⠀⢠⡞⣡⠇⠀⠛⠛⠹⣾⣿⡷⣤⣀⠀⠀⠀⠀⠀⠀⠸⡄⠀⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣾⡵⠃⣠⣾⡯⣾⠘⠀⠀⣠⣿⡀⢿⠀⠀⠉⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣼⠁⠀⢀⡴⣻⢠⡯⠖⠛⠉⠉⠛⠺⣿⣿⣆⠉⠛⠷⣶⣤⣤⣤⣀⣻⣄⣠⡇⢀⣀⣀⣀⣀⣠⣤⣤⠶⣻⣟⣫⣴⠟⣹⠀⢉⡟⠀⠀⣰⠟⠀⠙⢾⡆⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢰⡇⠀⡴⠋⢀⣿⠟⢷⣄⣀⠀⠀⠀⠀⠈⠙⢿⣧⠀⠀⠘⡻⣶⢿⠹⠿⣿⣿⣿⢿⣟⣿⣿⡟⠁⠀⠈⠳⣏⠉⢸⣽⢠⠏⠀⣸⠃⣠⣾⠋⠀⠀⠀⠀⠙⠶⣄⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠘⣧⢰⣧⣀⣼⠃⢠⡟⠀⠀⠑⠲⠆⠀⠀⠀⢸⡟⡇⠀⢀⣧⠘⣿⣧⣀⡀⠀⢀⣿⣽⣿⣿⠁⠀⠀⢀⣠⣞⣿⣾⣿⣿⠀⢰⣯⡶⣿⡟⠀⠀⠀⠀⠀⠀⠀⠈⢷⡄⠀\n" +
                "⠀⠀⠀⠀⠀⠹⣦⠇⣼⠃⠀⠸⣦⠀⠀⠀⠀⠀⠀⠀⢀⣾⢧⡇⣠⡾⢹⠀⣿⡉⠁⠳⣿⣿⣽⣿⣿⣿⣿⣄⡴⣫⣿⣿⣿⣽⣿⡿⣗⡮⠥⣾⡿⠁⠀⢰⣤⣀⠀⠀⠀⠀⠀⠹⡆\n" +
                "⠀⠀⠀⠀⠀⠀⠈⢻⠇⠀⠀⠀⠘⢧⣀⡀⠀⠀⣀⣴⢟⡟⢸⣿⡟⢡⣯⠾⠃⠹⣄⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣿⣿⡉⠉⠁⠀⠀⠀⣸⠃⠙⠳⣤⡀⠀⠀⠀⣿\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢸⡄⠀⠀⠀⠀⢸⠻⣍⣩⠽⠋⣡⠏⠀⠸⣿⣇⠀⠈⠳⣶⡶⠈⢦⡀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⡂⠀⠀⠀⣴⠏⠀⠀⠀⣸⠳⡄⠀⢠⠟\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠈⠛⠶⣤⣤⣤⣬⣷⣦⣤⣠⠜⠃⠀⠀⠀⢈⣟⣷⠒⣿⠉⠀⠀⠀⠱⣜⣿⣿⣿⣿⣿⣿⣿⣿⣯⣿⣧⡳⣽⣿⣿⣿⣿⣦⡴⠟⠃⠀⠀⣀⡴⠋⠀⣿⣴⠟⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠷⠤⠤⠤⠤⠴⠟⠛⠛⠛⠛⠛⠶⠶⠾⠭⠿⠿⢿⣿⠿⠟⠻⠿⠿⠟⠋⠻⠟⠛⠛⠛⠉⠉⠁⠀⠀⠀⠀⠀⠙⠛⠛⠛⠉⠀⠀⠀";
        System.out.println(logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Silver Wolf\n" +
                " What can I help you with?\n" +
                "____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                // reading the user input
                String input = scanner.nextLine();

                // checks for commands from input
                if (input.equals("bye")) {
                    handleBye();
                    break;
                } else if (input.equals("list")) {
                    handleList();
                } else if (input.startsWith("mark ")) {
                    handleMark(input);
                } else if (input.startsWith("unmark ")) {
                    handleUnmark(input);
                } else if (input.startsWith("todo")) {
                    handleTodo(input);
                } else if (input.startsWith("deadline")) {
                    handleDeadline(input);
                } else if (input.startsWith("event")) {
                    handleEvent(input);
                } else if (input.startsWith("delete")) {
                    handleDeleteCommand(input);
                } else {
                    handleWrongInput();
                }
            } catch(SilverWolfException e){
                printDivider();
                System.out.println(e.getMessage());
                printDivider();
            } catch (Exception e){
                printDivider();
                System.out.println("An unexpected error occurred: " + e);
                printDivider();
            }
        }
        // close the scanner
        scanner.close();
    }

    /**
     * Handles the 'bye' command from the user, which indicates the user wants to exit the
     * application. Displays a farewell message and terminates the program loop.
     */
    private static void handleBye() {
        printDivider();
        System.out.println(" Till we meet again! May this journey lead us starward!");
        printDivider();
    }

    /**
     * Handles the 'list' command from the user, which requests the application to display
     * all current tasks in the task list. Outputs the list of tasks with appropriate formatting.
     */
    private static void handleList() {
        printDivider();
        System.out.println("Here are the tasks in your list:");
        outputList();
        printDivider();
        System.out.print("\n");
    }

    /**
     * Handles the 'mark' command, which marks a specific task as done based on the provided
     * index. Extracts the task index from the command input, marks the task as completed,
     * and displays confirmation of the action.
     *
     * @param input The user input containing the command and task index.
     * @throws SilverWolfException If the index is out of bounds or invalid.
     */
    private static void handleMark(String input) throws SilverWolfException{
        //extract the input number
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            // retrieve the specific task from the arraylist
            Task specificTask = list.get(index);
            // mark the task as done
            specificTask.markAsDone();
            printDivider();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(specificTask);
            printDivider();
            System.out.print("\n");
        } catch (IndexOutOfBoundsException e){
            System.out.println("Hey! the index you provided is not correct you know");
        }
    }

    /**
     * Handles the 'unmark' command, which marks a specific task as not done based on the
     * provided index. Extracts the task index from the command input, marks the task as
     * incomplete, and displays confirmation of the action.
     *
     * @param input The user input containing the command and task index.
     * @throws SilverWolfException If the index is out of bounds or invalid.
     */
    private static void handleUnmark(String input) throws SilverWolfException{
        //extract the input number
        try {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        // retrieve the specific task from the arraylist
            Task specificTask = list.get(index);
        // mark the task as undone
        specificTask.unmarkTask();
        printDivider();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(specificTask);
        printDivider();
        System.out.print("\n");
    } catch (IndexOutOfBoundsException e){
        System.out.println("Hey! the index you provided is not correct you know");
    }
    }

    /**
     * Handles the 'todo' command, which adds a new Todo task to the task list. Extracts
     * the task description from the command input, creates a new Todo task, and adds it
     * to the list. Provides feedback on successful addition or throws an exception if the
     * description is empty.
     *
     * @param input The user input containing the command and task description.
     * @throws SilverWolfException If the task description is empty or invalid.
     */
    private static void handleTodo(String input) throws SilverWolfException{
        //taking in the input
        try {
            String descrption = input.substring(5);
            if (descrption.isEmpty()) {
                throw new SilverWolfException("Hey! your todo cannot be empty you know");
            }
            Todo newTodo = new Todo(descrption);
            list.add(newTodo);
            showConfirmation();
        } catch (StringIndexOutOfBoundsException e){
            throw new SilverWolfException("Hey! your todo cannot be empty you know");
        }
    }

    /**
     * Handles the 'deadline' command, which adds a new Deadline task to the task list.
     * Extracts the task description and deadline date/time from the command input, creates
     * a new Deadline task, and adds it to the list. Provides feedback on successful addition
     * or throws exceptions for invalid input.
     *
     * @param input The user input containing the command, task description, and deadline.
     * @throws SilverWolfException If the input is incorrectly formatted or missing required information.
     */
    private static void handleDeadline(String input) throws SilverWolfException{
        //taking in the input
        try {
            String[] parts = input.substring(9).split(" /by ");
            Task newDeadline = new Deadline(parts[0], parts[1]);
            list.add(newDeadline);
            showConfirmation();
        } catch (StringIndexOutOfBoundsException e){
            throw new SilverWolfException("Hey! your deadline cannot be empty you know");
        } catch (ArrayIndexOutOfBoundsException e){
            throw new SilverWolfException("Wrong usage. Correct usage: deadline [task in String] /by [date/time] e.g deadline submit report by 11/10/2019 ");
        }
    }

    /**
     * Handles the 'event' command, which adds a new Event task to the task list.
     * Extracts the task description, start date/time, and end date/time from the command
     * input, creates a new Event task, and adds it to the list. Provides feedback on
     * successful addition or throws exceptions for invalid input.
     *
     * @param input The user input containing the command, task description, start and end dates/times.
     * @throws SilverWolfException If the input is incorrectly formatted or missing required information.
     */
    private static void handleEvent(String input) throws SilverWolfException{
        try {
        //taking in the input
        String[] parts = input.substring(6).split(" /from ");
        String[] to = parts[1].split(" /to ");
            Task newEvent = new Event(parts[0],to[0],to[1]);
        list.add(newEvent);
        showConfirmation();
    } catch (StringIndexOutOfBoundsException e){
        throw new SilverWolfException("Hey! your event cannot be empty you know");
    } catch (ArrayIndexOutOfBoundsException e){
        throw new SilverWolfException("Wrong usage. Correct usage: event [task in String] /from [date/time] /to [date/time] e.g event project meeting /from Mon 2pm /to 4pm ");
    }
    }

    /**
     * Handles invalid commands or input that does not match any expected format.
     * Throws a SilverWolfException with a message indicating that the input is not recognized.
     *
     * @throws SilverWolfException Always thrown with a message about the incorrect input.
     */
    private static void handleWrongInput() throws SilverWolfException{
        throw new SilverWolfException("Sorry what are you trying to say????");
    }

    /**
     * Handles the 'delete' command, which removes a specific task from the task list based
     * on the provided index. Extracts the task index from the command input, removes the task
     * from the list, and displays confirmation of the removal.
     *
     * @param input The user input containing the command and task index.
     * @throws SilverWolfException If the index is out of bounds, invalid, or not a number.
     */
    private static void handleDeleteCommand(String input) throws SilverWolfException{
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task removedTask = list.remove(index);
            printDivider();
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            showTotalTask();
            printDivider();
        } catch (IndexOutOfBoundsException e) {
            throw new SilverWolfException("Hey, this index is empty or out of bounds");
        } catch (NumberFormatException e) {
            throw new SilverWolfException("Yo, please provide a valid index");
        }
    }
}
