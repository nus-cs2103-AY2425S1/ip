package hoodini;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Stores the tasklist and methods
 * to handle tasks.
 * Has a method to write tasks to a text file
 */
public class Storage {
    private static int counter = 0;
    private ArrayList<Input> inputs;
    private Ui ui;

    /**
     * Creates a new Storage object.
     * Takes in a UI object to handle messages
     * @param ui Handles messages to the user.
     */
    public Storage(Ui ui) {
        this.inputs = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Stores the input object into the Tasklist
     * Takes in the input object to be stored
     * @param input Object to be stored in the task list
     */
    public String store(Input input) {
        if (input.empty()) {
            return ui.invalidTask();
        } else {
            this.inputs.add(input);
            counter++;
            return ui.store(input, counter);
        }


    }

    /**
     * Adds items to the tasklist without a message
     * Handles input objects read from file
     * @param input Task which is read from the file
     */
    public void add(Input input) {
        this.inputs.add(input);
        counter++;
    }

    /**
     * Writes the tasklist to a local text file
     * @param filename Name of file
     * @throws IOException Invalid file name
     */
    public void writeToFile(String filename) throws IOException {
        try (BufferedWriter writer = new
                BufferedWriter(new FileWriter(filename))) {
            for (Input i : inputs) {
                writer.write(i.toString());
                writer.newLine();
            }
        }
    }


    /**
     * Lists out tasks to the user.
     * @return String representation of the tasks
     */
    public String output() {
        StringBuilder sb = new StringBuilder("Here are the list of tasks "
                + "that needs to be completed: \n");
        ArrayList<Input> unique = new ArrayList<>();

        for (int i = 0; i < counter; i++) {
            if (unique.contains(this.inputs.get(i))) {
                continue;
            } else {
                unique.add(inputs.get(i));
                sb.append(i + 1).append(". ").append(inputs.get(i)).append("\n");
            }
        }
        return sb.toString();

    }

    /**
     * Marks the task as done
     * @param str String from user on which task to mark
     * @return String representation of the task marked
     */
    public String mark(String str) {
        int i = Integer.parseInt(
                str.substring(5));
        if (i > counter) {
            return ui.invalidInput();
        } else {
            return inputs.get(i - 1).done();

        }


    }

    /**
     * Deletes the task
     * @param str String from user on which task to delete
     * @return String representation of the task deleted
     */
    public String delete(String str) {
        int i = Integer.parseInt(str.substring(7));
        if (i > counter) {
            return ui.invalidInput();
        } else {
            Input input1 = inputs.get(i - 1);
            inputs.remove(i - 1);
            counter--;
            return ui.delete(input1, counter);

        }
    }



    /**
     * Unmarks the task
     * @param str String from user on which task to unmark
     * @return String representation of the task unmarked
     */
    public String unmark(String str) {
        int i = Integer.parseInt(str.substring(7));
        if (i > counter) {
            return ui.invalidInput();
        } else {
            return inputs.get(i - 1).unDone();

        }

    }

    /**
     * Returns matching tasks to the input of the user
     * @param str string which user wants to find
     * @return String representation of the tasks found
     */
    public String find(String str) {
        String regex = str.substring(5);
        Pattern pattern = Pattern.compile(regex);
        ArrayList<Input> arr = new ArrayList<>();
        for (int i = 0; i < counter; i++) {
            Input found = inputs.get(i);
            Matcher matcher = pattern.matcher(found.toString());
            if (matcher.find()) {
                arr.add(found);
            }

        }
        String out = "Here are the list "
                + "of tasks found: \n";
        for (int i = 0; i < arr.size(); i++) {
            out = out + (i + 1) + ". "
                    + arr.get(i).toString() + "\n";
        }
        return out;
    }



}
