import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Slave {
    private static String user;
    private static boolean hasMoreInputs = true;
    private static LinkedList<Task> list = new LinkedList<>();

    public static void main(String[] args) {
        welcome();
        do {
            getUserInput();
        } while (hasMoreInputs);
        goodbye();

    }

    /**
     * Tries to query the user's name from username.txt if it exists
     * if it does not / user has not specified their name, set user's name as "slave driver"
     *
     * Prints the greeting message
     */
    private static void welcome() {
        tryGetUser();
        pageBreakLine();
        // @@author Fluffykan-reused
        // logo sourced from: https://www.askapache.com/online-tools/figlet-ascii/
        String logo
                = "_______        _______ _    _ _______\n"
                + "|______ |      |_____|  \\  /  |______\n"
                + "______| |_____ |     |   \\/   |______\n";
        System.out.println("Ugh... why did you wake me up...\nGuess I am now your personal\n" + logo);
        System.out.println("What do you want from me? Say it now, I don't have all day...");
        pageBreakLine();
    }

    /**
     * used to separate the different print statements
     */
    private static void pageBreakLine() {
        System.out.println("_______________________________________________________________________________________");
    }

    /**
     *  Prints the goodbye message in response to the user inputting "bye"
     */
    private static void goodbye() {
        System.out.println("Good riddance " + user + ", try not to bother me in the future...");
        pageBreakLine();
    }

    /**
     * Creates a new scanner object to get the user's input
     * Slave will echo the user's input, and respond accordingly
     */
    private static void getUserInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        echo(input);
        Scanner inputScanner = new Scanner(input);
        String command = inputScanner.next();
        String body = "";
        if (inputScanner.hasNextLine()) {
            body = inputScanner.nextLine().substring(1);
        }
        inputScanner.close();
        switch (command) {
            case "bye":
                hasMoreInputs = false;
                break;
            case "list":
                listItems();
                break;
            case "mark":
                markAsDone(body);
                break;
            case "unmark":
                markAsIncomplete(body);
                break;
            case "todo":
                addToList(0, body);
                break;
            case "deadline":
                addToList(1, body);
                break;
            case "event":
                addToList(2, body);
                break;
            default:
                System.out.println("You're spouting gibberish...");
                break;
        }
        pageBreakLine();
    }

    /**
     * Prints out the items in the list of items provided by the user
     */
    private static void listItems() {
        System.out.println("Can you not even remember the things you need to do? That should be your job, not mine!");
        if (list.isEmpty()) {
            System.out.println("You don't have anything on your list, and you can't even remember that?");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i).toString());
        }
    }

    /**
     * adds the item specified by the user to the list
     *
     * @param s is te item to be added
     */
    private static void addToList(int i, String s) {
        /*
        0 - Todo
        1 - Deadline
        2 - Event
         */
        boolean insert = true;
        try {
            switch (i) {
                case 0:
                    // todo
                    if (s.isEmpty()) {
                        throw new InvalidTaskFormatException("No task descriptor");
                    }
                    list.add(new Todo(s));
                    break;
                case 1:
                    // deadline
                    String[] arr = s.split(" /by ");
                    if (arr.length == 1) {
                        throw new InvalidTaskFormatException("Error with input string format");
                    }
                    list.add(new Deadline(arr[0], arr[1]));
                    break;
                case 2:
                    // event
                    String[] eventArr = s.split(" /from ");
                    if (eventArr.length == 1) {
                        throw new InvalidTaskFormatException("Error with input string format");
                    }
                    String[] startEndDate = eventArr[1].split(" /to ");
                    list.add(new Event(eventArr[0], startEndDate[0], startEndDate[1]));
                    break;
                default:
                    System.out.println("invalid Task code");
                    insert = false;
                    break;
            }
        } catch (InvalidTaskFormatException e) {
            System.out.println("Can you not even tell me all the details for your event? Do you even want my help?");
            insert = false;
        } finally {
            if (insert) {
                System.out.println("Hey maybe try using some of that memory of yours to remember these things...");
                System.out.println("added: " + list.get(list.size() - 1));
            }
        }

    }

    private static void markAsDone(String s) {
        try {
            int i = Integer.parseInt(s);

            if (i < 1 || i > list.size()) {
                System.out.println("You don't have a task number " + i);
                return;
            }
            Task t = list.get(i - 1);
            t.completed();
            System.out.println("Finally doing something useful with your life eh...");
            System.out.println(t);
        } catch (NumberFormatException nfe) {
            System.out.println("That's not a task number");
        }
    }

    private static void markAsIncomplete(String s) {
        try {
            int i = Integer.parseInt(s);
            if (i < 1 || i > list.size()) {
                System.out.println("You don't have a task number " + i);
                return;
            }
            Task t = list.get(i - 1);
            t.incomplete();
            System.out.println("Slacking off now, are you?");
            System.out.println(t);
        } catch (NumberFormatException nfe) {
            System.out.println("That's not a task number");
        }
    }

    /**
     * Prints the user's input
     *
     * @param s is the user's input
     */
    private static void echo(String s) {
        System.out.println(s);
        pageBreakLine();
    }

    /**
     * Clears the user's saved name from username.txt
     * user's name will now revert to the default "slave driver"
     */
    private static void deleteUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("username.txt"));
            writer.write("");
        } catch (IOException e) {
            System.out.println("Why are you still haunting me even when I'm trying to forget you...");
            System.exit(1);
        }
    }

    /**
     * used to check if the user has specified their name previously at the start of the program
     */
    private static void tryGetUser() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("username.txt"));
            user = reader.readLine();
            reader.close();
            if (user == null) {
                throw new NoUserDataException("No username found");
            }
        } catch (IOException | NoUserDataException e) {
            user = "slave driver";
        }
    }

    /**
     * used to save the user's name if the user has not already defined it
     * will not allow the user to change their name otherwise
     */
    private static void getUser() {
        if (!user.equals("slave driver")) {
            System.out.println("I already know your name, " + user + ", quit wasting my time...");
        } else { // FileNotFoundException is a subclass of IOException
            boolean confirmed = false;
            do {
                Scanner sc = new Scanner(System.in);
                System.out.println("What do I call you, dear slave driver?");
                user = sc.nextLine();
                if (user.isEmpty()) {
                    System.out.println("Come on, if you want to boss me around, at least tell me your name\n");
                    continue;
                }
                System.out.println("You are " + user + ", confirm? (Y/N)");
                confirmed = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } while (!confirmed);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("username.txt"));
                writer.write(user);
                writer.close();
            } catch (IOException ioe) {
                System.out.println("Something went wrong, seems like the world doesn't want me to remember your name... Hope to see you never...");
                System.exit(1);
            }
        }
    }

}
