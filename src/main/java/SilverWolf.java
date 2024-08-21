import java.util.ArrayList;
import java.util.Scanner;

public class SilverWolf {
    // using arraylist
    private static ArrayList<Task> list = new ArrayList<>();
    // prints the line
    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }
    // output the list
    private static void outputList(){
        int index = 1;
        for(Task t : list){
            System.out.println(index + t.toString());
            index++;
        }
    }

    private static void showConfirmation(){
        printDivider();
        System.out.println("Got it. I've added this task:");
        System.out.println(list.get(list.size()-1));
        showTotalTask();
        printDivider();
        System.out.print("\n");
    }

    private static void showTotalTask(){
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }
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

    private static void handleBye() {
        printDivider();
        System.out.println(" Till we meet again! May this journey lead us starward!");
        printDivider();
    }

    private static void handleList() {
        printDivider();
        System.out.println("Here are the tasks in your list:");
        outputList();
        printDivider();
        System.out.print("\n");
    }

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

    private static void handleWrongInput() throws SilverWolfException{
        throw new SilverWolfException("Sorry what are you trying to say????");
    }

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
