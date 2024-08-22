import java.util.Scanner;
import java.util.ArrayList;

/**
 * This is a chatbot class named Bob.
 */
public class Bob {
    // Stores all user inputs.
    private ArrayList<Task> records;
    // Keeps the count of the number of user inputs stored.
    private int counter;

    /**
     * Initialises an instance of Bob.
     */
    public Bob() {
        this.records = new ArrayList<>();
        this.counter = 0;
    }


    public static void main(String[] args) {
        String welcome = "Hello! I'm Bob\n"
                + "\tWhat can I do for you?";
        Bob.printLines(welcome);
        Bob bob = new Bob();
        Bob.chat(bob);
    }

    /**
     * This is a chat function by Bob.
     */
    static void chat(Bob bob) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.trim();

        while (!input.equals("bye")) {
            String[] inputWords = input.split("\s+");
            if (inputWords[0].equals("list")) {
                bob.listRecords();
                input = scanner.nextLine();
                continue;
            }
            //Print array loop
            System.out.println("Current words in inputWords");
            for (String x : inputWords) {
                System.out.println(x);
            }

            if (inputWords[0].equals("mark")) {
                if (inputWords.length == 1) {
                    System.out.println("Please input which item number you want to mark.");
                    input = scanner.nextLine();
                    continue;
                } else if ((bob.records == null)) {
                    System.out.println("No record yet.");
                    input = scanner.nextLine();
                    continue;
                } else if (bob.records.size() < Integer.valueOf(inputWords[1]) || Integer.valueOf(inputWords[1]) <= 0) {
                    System.out.println("Item index out of range.");
                    input = scanner.nextLine();
                    continue;
                } else {
                    String currDescription = bob.records.get(Integer.parseInt(inputWords[1]) - 1).getDescription();
                    bob.markRecord(Integer.parseInt(inputWords[1]) - 1, true, currDescription);
                    input = scanner.nextLine();
                    continue;
                }
            }
            if (inputWords[0].equals("unmark")) {
                if (inputWords.length == 1) {
                    System.out.println("Please input which item number you want to unmark.");
                    input = scanner.nextLine();
                    continue;
                } else if ((bob.records == null)) {
                    System.out.println("No record yet.");
                    input = scanner.nextLine();
                    continue;
                } else if (bob.records.size() < Integer.valueOf(inputWords[1]) || Integer.valueOf(inputWords[1]) <= 0) {
                    System.out.println("Item index out of range.");
                    input = scanner.nextLine();
                    continue;
                } else {
                    String currDescription = bob.records.get(Integer.parseInt(inputWords[1]) - 1).getDescription();
                    bob.markRecord(Integer.parseInt(inputWords[1]) - 1, false, currDescription);
                    input = scanner.nextLine();
                    continue;
                }
            }
            String immediateAdd = "added: " + input;
            Bob.printLines(immediateAdd);
            Task newTask = new Task(input);
            bob.records.add(bob.getCounter(), newTask);

            System.out.println("Current tasks in records");
            for (Task x : bob.records) {
                System.out.println(x.getDescription());
            }

            bob.setCounter(bob.getCounter() + 1);
            input = scanner.nextLine();
        }
        printLines("Bye. Hope to see you again soon!bye");
    }

    /**
     * Prints the text input with lines above and below it.
     * @param text The user input into the chatbot
     */
    static void printLines(String text) {
        String textToPrint = "\t____________________________________________________________\n"
                + "\t"
                + text
                + "\n"
                + "\t____________________________________________________________\n";
        System.out.println(textToPrint);
    }

    /**
     * Returns the index of the next available slot in the arraylist of records.
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * Sets the count of the total number of recorded items in records.
     */
    public void setCounter(int x) {
        this.counter = x;
    }

    /**
     * Prints a list of all recorded user inputs.
     */
    public void listRecords() {
        String allRecords = "";
        for (int i = 0; i < records.size(); i++) {
            int num = i + 1;
            Task currTask = records.get(i);
            if (num == records.size()) {
                allRecords += num + ".[" + currTask.getStatusIcon() + "] " + currTask.getDescription();
            } else {
                allRecords += num + ".[" + currTask.getStatusIcon() + "] " + currTask.getDescription() + "\n\t";
            }
        }
        Bob.printLines(allRecords);
    }

    /**
     * Marks the record based on the task.
     * @param index Arraylist index of the task in the records.
     * @param isDone Whether task is completed.
     * @param taskDescription Description of task.
     */
    public void markRecord(int index, Boolean isDone, String taskDescription) {
        try {
            Task currTask = records.get(index);
            String toPrint = currTask.markTask(isDone, taskDescription);
            Bob.printLines(toPrint);
        } catch(Exception e) {
            System.out.println("Invalid marking");
        }
    }
}
