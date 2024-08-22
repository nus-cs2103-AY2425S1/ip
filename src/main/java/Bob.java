import java.util.Scanner;
import java.util.ArrayList;

/**
 * This is a chatbot class named Bob.
 */
public class Bob {
    private ArrayList<Task> records;
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
        String input = scanner.nextLine().trim();

        while (!input.equals("bye")) {
            String[] inputWords = input.split("\s+");
            String keyword = inputWords[0];

            switch (keyword) {
                case "list":
                    bob.listRecords();
                    break;
                case "mark":
                    bob.updateMark(inputWords, true);
                    break;
                case "unmark":
                    bob.updateMark(inputWords, false);
                    break;
                default:
                    bob.addTask(input);
            }

            input = scanner.nextLine().trim();
        }
        printLines("Bye. Hope to see you again soon!");
    }

    /**
     * Updates whether the task in the record is completed or not completed.
     * @param inputWords String array of the words given as input.
     * @param isCompleted Whether the task is marked as completed or incompleted.
     */
    public void updateMark(String[] inputWords, Boolean isCompleted) {
        if (inputWords.length == 1) {
            System.out.println("Please input which item number you want to mark.");
        } else if (this.records.size() < Integer.valueOf(inputWords[1]) || Integer.valueOf(inputWords[1]) <= 0) {
            System.out.println("Item index out of range.");
        } else {
            Task currTask = this.records.get(Integer.parseInt(inputWords[1]) - 1);
            if (isCompleted) {
                currTask.markTask(true);
            } else {
                currTask.markTask(false);
            }
        }
    }

    /**
     * Adds a task to records
     * @param input Input given by a user.
     */
    public void addTask(String input) {
        String immediateAdd = "added: " + input;
        Bob.printLines(immediateAdd);
        Task newTask = new Task(input);
        this.records.add(this.counter, newTask);
        this.setCounter(this.counter + 1);
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
                allRecords += num + "." + currTask.getTaskListItem();
            } else {
                allRecords += num + "." + currTask.getTaskListItem() + "\n\t";
            }
        }
        Bob.printLines(allRecords);
    }
}
