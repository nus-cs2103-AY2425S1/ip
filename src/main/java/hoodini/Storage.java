package hoodini;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which stores the tasklist and methods
 * to handle tasks.
 * Has a method to write tasks to a text file
 */
public class Storage {
    private ArrayList<Input> input;
    private Ui ui;
    private static int counter = 0;

    /**
     * Constructor for a new Storage object. 
     * Takes in a UI object to handle messages
     * @param ui Handles messages to the user.
     */
    public Storage(Ui ui) {
        this.input = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Stores the input object into the Tasklist
     * Takes in the input object to be stored
     * @param input Object to be stored in the task list
     */
    public void store(Input input) {
        if(input.empty()) {
            ui.invalidTask();
        } else {
            this.input.add(input);
            counter++;
            ui.store(input, counter);
        }


    }

    /**
     * Adding items to the tasklist without a message
     * Handles input objects read from file
     * @param input Task which is read from the file
     */
    public void add(Input input) {
        this.input.add(input);
        counter++;
    }

    /**
     * Method to write the tasklist to a local text file
     * @param filename Name of file
     * @throws IOException Invalid file name
     */
    public void writeToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Input i : input) {
                writer.write(i.toString());
                writer.newLine();
            }
        }
    }


    /**
     * Method to list out tasks to the user.
     * No params required.
     */
    public void output() {
        System.out.println("Here are the list of tasks " +
                "that needs to be completed: ");
        for (int i = 0; i < counter; i++) {
            System.out.println((i+1) + ". " + input.get(i));
        }
    }

    /**
     * Method to mark the task as done
     * @param str String from user on which task to mark
     */
    public void mark(String str) {
        int i = Integer.parseInt(str.substring(5));
        if (i > counter) {
            ui.invalidInput();
        } else {
            input.get(i-1).done();

        }


    }

    /**
     * Method to delete the task
     * @param str String from user on which task to delete
     */
    public void delete(String str) {
        int i = Integer.parseInt(str.substring(7));
        if (i > counter) {
            ui.invalidInput();
        } else {
            Input input1 = input.get(i-1);
            input.remove(i-1);
            counter--;
            ui.delete(input1, counter);

        }
    }



    /**
     * Method to unmark the task
     * @param str String from user on which task to unmark
     */
    public void unmark(String str) {
        int i = Integer.parseInt(str.substring(7));
        if (i > counter) {
            ui.invalidInput();
        } else {
            input.get(i-1).unDone();

        }

    }

    /**
     * Method uses regex to find patterns within the string
     * in order to find matching tasks in task list
     * @param str string which user wants to find
     */
    public void find(String str) {
        String regex = str.substring(5);
        Pattern pattern = Pattern.compile(regex);
        ArrayList<Input> arr = new ArrayList<>();
        for(int i = 0; i < counter; i++) {
            Input found = input.get(i);
            Matcher matcher = pattern.matcher(found.toString());
            if(matcher.find()) {
                arr.add(found);
            }

        }
        System.out.println("Here are the list of tasks found: ");
        for (int i = 0; i < arr.size(); i++) {
            System.out.println((i+1) + ". " + arr.get(i).toString());
        }
    }



}
