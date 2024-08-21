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
        String[] inputArr = input.split(" ");
        inputArr[0] = inputArr[0].toLowerCase();
        switch (inputArr[0]) {
            case "bye":
                hasMoreInputs = false;
                break;
            case "list":
                listItems();
                break;
            case "mark":
                markAsDone(Integer.parseInt(inputArr[1]));
                break;
            case "unmark":
                markAsIncomplete(Integer.parseInt(inputArr[1]));
                break;
            default:
                addToList(input);
                break;
        }
        pageBreakLine();
    }

    /**
     * Prints out the items in the list of items provided by the user
     */
    private static void listItems() {
        System.out.println("Can you not even remember the things you need to do? That should be your job, not mine!");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i).toString());
        }
    }

    /**
     * adds the item specified by the user to the list
     *
     * @param s is te item to be added
     */
    private static void addToList(String s) {
        System.out.println("Hey maybe try using some of that memory of yours to remember these things...");
        list.add(new Task(s));
        System.out.println("added: " + s);
    }

    private static void markAsDone(int i) {
        Task t = list.get(i - 1);
        t.completed();
        System.out.println("Finally doing something useful with your life eh...");
        System.out.println(t);
    }

    private static void markAsIncomplete(int i) {
        Task t = list.get(i - 1);
        t.incomplete();
        System.out.println("Slacking off now, are you?");
        System.out.println(t);
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
