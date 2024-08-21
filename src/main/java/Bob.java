import java.util.Scanner;
import java.util.ArrayList;

/**
 * This is a chatbot class named Bob.
 */
public class Bob {
    // Stores all user inputs.
    private ArrayList<String> records;
    // Keeps the count of the number of user inputs stored.
    private int counter;

    /**
     * Initialises an instance of Bob.
     */
    public Bob() {
        this.records = new ArrayList<>();
        counter = 0;
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
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                bob.listRecords();
                input = scanner.nextLine();
                continue;
            }
            String immediateAdd = "added: " + input;
            Bob.printLines(immediateAdd);
            bob.records.add(bob.getCounter(), input);
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
            if (num == records.size()) {
                allRecords += num + ". " + records.get(i);
            } else {
                allRecords += num + ". " + records.get(i) + "\n\t";
            }
        }
        Bob.printLines(allRecords);
    }
}
