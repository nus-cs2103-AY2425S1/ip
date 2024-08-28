package slaveFiles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser {
    private List<Task> list;
    private Storage storage;

    public Parser(List<Task> list, Storage activeStorage) {
        this.list = list;
        this.storage = activeStorage;
    }

    /**
     * Creates a new scanner object to get the user's input
     * Slave will echo the user's input, and respond accordingly
     */
    public boolean getUserInput(boolean hasMoreInputs) {
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
                case "reload":
                    reload();
                    break;
                case "schedule":
                    scheduleOn(body);
                    break;
                default:
                    System.out.println("You're spouting gibberish...");
                    break;
            }
            pageBreakLine();
            return hasMoreInputs;
        } catch (NoSuchElementException e) {
            // handle empty inputs / only spaces (" ")
            // wait for next user input
            return true;
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
     * used to separate the different print statements
     */
    public static void pageBreakLine() {
        System.out.println("_______________________________________________________________________________________");
    }

    /**
     * Prints out the items in the list of items provided by the user
     */
    private void listItems() {
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
     * marks the task as completed by changing the boolean value to true
     *
     * @param s is the task index in String format
     * @throws IllegalArgumentException in the event that event index is out of the range of the task list
     */
    private void markAsDone(String s) throws IllegalArgumentException {
        try {
            int i = Integer.parseInt(s);

            if (i < 1 || i > list.size()) {
                System.out.println("You don't have a task number " + i);
                return;
            }
            Task t = list.get(i - 1);
            t.completed();
            storage.save();
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
    private void markAsIncomplete(String s) throws IllegalArgumentException {
        try {
            int i = Integer.parseInt(s);
            if (i < 1 || i > list.size()) {
                System.out.println("You don't have a task number " + i);
                return;
            }
            Task t = list.get(i - 1);
            t.incomplete();
            storage.save();
            System.out.println("Slacking off now, are you?");
            System.out.println(t);
        } catch (NumberFormatException nfe) {
            System.out.println("That's not a task number");
        }
    }

    /**
     * adds the item specified by the user to the list
     *
     * @param s is te item to be added
     */
    private void addToList(int i, String s) {
        /*
        0 - Todo
        1 - Deadline
        2 - Event
         */
        boolean didInsert = true;
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
                    // only accepts time in format yyyy-mm-dd
                    LocalDate by = LocalDate.parse(arr[1]);
                    list.add(new Deadline(arr[0], by));
                    break;
                case 2:
                    // event
                    // only accepts time in format yyyy-mm-dd
                    String[] eventArr = s.split(" /from ");
                    if (eventArr.length == 1) {
                        throw new InvalidTaskFormatException("Missing event start date");
                    }
                    String[] startEndDate = eventArr[1].split(" /to ");
                    if (startEndDate.length == 1) {
                        throw new InvalidTaskFormatException("Missing event end date");
                    }
                    LocalDate start = LocalDate.parse(startEndDate[0]);
                    LocalDate end = LocalDate.parse(startEndDate[1]);
                    list.add(new Event(eventArr[0], start, end));
                    break;
                default:
                    System.out.println("invalid Task code");
                    didInsert = false;
                    break;
            }
        } catch (InvalidTaskFormatException e) {
            System.out.println("Can you not even tell me all the details for your event? Do you even want my help?");
            didInsert = false;
        } catch (DateTimeParseException dtpe) {
            didInsert = false;
            System.out.println("Give me the date in yyyy-mm-dd or I won't remember it for you");
        } catch (InvalidChronologicalOrderException icoe) {
            didInsert = false;
            System.out.println("How can your event end before it started?");
        } finally {
            if (didInsert) {
                System.out.println("Hey maybe try using some of that memory of yours to remember these things...");
                System.out.println("added: " + list.get(list.size() - 1));
                storage.save();
            }
        }
    }

    /**
     * removes the Task from list
     * @param s is the index of the Task to be removed
     */
    private void deleteTask(String s) {
        try {
            int i = Integer.parseInt(s);
            if (i < 1 || i > list.size()) {
                throw new IllegalArgumentException("You don't have a task number " + i);
            }
            System.out.println("Good to know that I have less things to remember now...");
            System.out.println("I'll forget about " + list.get(i - 1));
            list.remove(i - 1);
            storage.save();
        } catch (NumberFormatException nfe) {
            System.out.println("That's not a task number");
        } catch (IllegalArgumentException ile) {
            System.out.println(ile.getMessage());
        }
    }


    /**
     * deletes all tasks from the list
     */
    private void clear() {
        list.clear();
        System.out.println("Starting off on a clean slate now are we, " +
                "guess your previous tasks were too much for you to handle");
        storage.save();
    }

    /**
     * clears the list and loads tasks from save file
     */
    private void reload() {
        list.clear();
        storage.load();
        System.out.println("Reloaded");
    }

    /**
     * prints all deadlines / events occuring on the specified date
     * @param s is the date input provided by user
     */
    private void scheduleOn(String s) {
        if (s.isEmpty()) {
            throw new NoSuchElementException();
        }
        System.out.println("Your tasks are :");
        try {
            LocalDate target = LocalDate.parse(s);
            list.forEach(task -> {
                if (task instanceof Event) {
                    if (((Event) task).getRawStart().isBefore(target) && ((Event) task).getRawEnd().isAfter(target)) {
                        System.out.println(task);
                    }
                } else if (task instanceof Deadline) {
                    if (((Deadline) task).getRawDeadline().isAfter(target) || ((Deadline) task).getRawDeadline().isEqual(target)) {
                        System.out.println(task);
                    }
                }
            });
            System.out.println("That's all your tasks for " + target.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
        } catch (DateTimeParseException | NoSuchElementException e) {
            System.out.println("Give me the date in yyyy-mm-dd or I won't check your schedule");
        }
    }
}
