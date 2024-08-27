package slaveFiles;

import java.io.*;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Slave {
    private static String user;
    private static boolean hasMoreInputs = true;
    private static LinkedList<Task> list = new LinkedList<>();

    public static void main(String[] args) {
//        load();
        welcome();
        do {
            getUserInput();
        } while (hasMoreInputs);
        goodbye();

    }

    /**
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
        try {
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
                case "delete":
                    deleteTask(body);
                    break;
                case "clear":
                    clear();
                    break;
                default:
                    System.out.println("You're spouting gibberish...");
                    break;
            }
            pageBreakLine();
        } catch (NoSuchElementException e) {
            // handle empty inputs / only spaces (" ")
            // do nothing
        }
    }

    private static void deleteTask(String s) {
        try {
            int i = Integer.parseInt(s);
            if (i < 1 || i > list.size()) {
                throw new IllegalArgumentException("You don't have a task number " + i);
            }
            System.out.println("Good to know that I have less things to remember now...");
            System.out.println("I'll forget about " + list.get(i - 1));
            list.remove(i - 1);
//            save();
        } catch (NumberFormatException nfe) {
            System.out.println("That's not a task number");
        } catch (IllegalArgumentException ile) {
            System.out.println(ile.toString());
        }
    }


    /**
     * Prints out the items in the list of items provided by the user
     */
    private static void listItems() {
        System.out.println("Can you not even remember the things you need to do?" +
                " That should be your job, not mine!");
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
                        throw new InvalidTaskFormatException("Missing event start date");
                    }
                    String[] startEndDate = eventArr[1].split(" /to ");
                    if (startEndDate.length == 1) {
                        throw new InvalidTaskFormatException("Missing event end date");
                    }
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
//                save();
            }
        }

    }

    /**
     * marks the task as completed by changing the boolean value to true
     *
     * @param s is the task index in String format
     * @throws IllegalArgumentException in the event that event index is out of the range of the task list
     */
    private static void markAsDone(String s) throws IllegalArgumentException {
        try {
            int i = Integer.parseInt(s);

            if (i < 1 || i > list.size()) {
                System.out.println("You don't have a task number " + i);
                return;
            }
            Task t = list.get(i - 1);
            t.completed();
//            save();
            System.out.println("Finally doing something useful with your life eh...");
            System.out.println(t);
        } catch (NumberFormatException nfe) {
            System.out.println("That's not a task number");
        }
    }

    /**
     * marks the task as incomplete by changing the boolean value to false
     *
     * @param s is the task index in String format
     * @throws IllegalArgumentException in the event that event index is out of the range of the task list
     */
    private static void markAsIncomplete(String s) throws IllegalArgumentException {
        try {
            int i = Integer.parseInt(s);
            if (i < 1 || i > list.size()) {
                System.out.println("You don't have a task number " + i);
                return;
            }
            Task t = list.get(i - 1);
            t.incomplete();
//            save();
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
     * deletes all tasks from the list
     */
    private static void clear() {
        list.clear();
        System.out.println("Starting off on a clean slate now are we, " +
                "guess your previous tasks were too much for you to handle");
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
                System.out.println("Something went wrong, seems like the world doesn't want me to remember your name..." +
                        " Hope to see you never...");
                System.exit(1);
            }
        }
    }

    /**
     * converts the List<Task> to a string format and writes it to the savefile "./src/main/data/savefile.txt"
     * every line contains only 1 task
     * string format is as per the return value of toString() method of the respective task
     * creates a new file at "./src/main/data" called "savefile.txt" in the event of a missing save file
     */
    private static void save() {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task t : list) {
                sb.append(t);
                sb.append("\n");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/data/savefile.txt"));
            writer.write(sb.toString());
            writer.close();
            System.out.println("saved");
        } catch (IOException ioe) {
            System.out.println("Save failed");
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * prints the contents of a string array
     * used for debugging purposes
     * @param arr is the array to be printed
     */
    private static void printArr(String[] arr) {
        for (String s : arr) {
            System.out.print(s + ",");
        }
        System.out.println("\n");
    }

//    /**
//     * attempts to load the pre saved tasks from the save file "./src/main/data/savefile.txt"
//     * if file does not exist at the path, does nothing
//     * in the event of corrupted save file, skips the line
//     *
//     * save file format: as per toString() function of each task, with one task per line
//     */
//    private static void load() {
//        try {
//            Scanner sc = new Scanner(new File("./src/main/data/savefile.txt"));
//            int success = 0;
//            int failed = 0;
//            while (sc.hasNextLine()) {
//                try {
//                    String task = sc.nextLine();
//                    char taskType = task.charAt(1);
//                    char taskCompleted = task.charAt(4);
//                    int firstSpacePos = task.indexOf(" ");
//                    boolean completed = false;
//                    if (taskCompleted == ']') {
//                        // do nothing
//                    } else if (taskCompleted == 'X') {
//                        completed = true;
//                    } else {
//                        throw new InvalidSaveFileFormatException("invalid completed status");
//                    }
//                    // identify the type of task:
//                    switch (taskType) {
//                        case 'T':
//                            list.add(new Todo(completed, task.substring(firstSpacePos + 1)));
//                            success++;
//                            break;
//                        case 'D':
//                            // not sure how to get rid of error here for string formatting
//                            String[] deadlineArray = task.split(" \\(by: ");
//                            String deadlineName = deadlineArray[0].substring(firstSpacePos + 1);
//                            String by = deadlineArray[1].substring(0, deadlineArray[1].length() - 1);
//                            list.add(new Deadline(completed, deadlineName, by));
//                            success++;
//                            break;
//                        case 'E':
//                            // not sure how to get rid of error here for string formatting
//                            String[] eventArray = task.split(" \\(from: ");
//                            String eventName = eventArray[0].substring(firstSpacePos + 1);
//                            String[] eventDetails = eventArray[1].split(" to: ");
//                            String from = eventDetails[0];
//                            String to = eventDetails[1].substring(0, eventDetails[1].length() - 1);
//                            list.add(new Event(completed, eventName, from, to));
//                            success++;
//                            break;
//                        default:
//                            throw new InvalidSaveFileFormatException("invalid Task type");
//                    }
//                } catch (InvalidSaveFileFormatException | IndexOutOfBoundsException e) {
//                    failed++;
//                    System.out.println("error in event save file format: " + e.getMessage());
//                }
//            }
//            if (success == 0) {
//                System.out.println("No valid save file found");
//            }
//            if (failed > 0) {
//                System.out.println("Deleting buggy save file");
//                try {
//                    BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/data/savefile.txt"));
//                    writer.write("");
//                    writer.close();
//                    System.out.println("Buggy save file deleted");
//                } catch (IOException e) {
//                    System.out.println("Failed to purge buggy save file");
//                }
//            }
//        } catch (FileNotFoundException fnfe) {
//            System.out.println("no save file found");
//        }
//    }

}
