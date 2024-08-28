package blitz;

/* My import */
import exception.BlitzException;
import task.Task;

public class Ui {
    private String divider, tab;

    /**
     * Constructs a new Ui object with specified divider and tab.
     *
     * @param divider Divider to be printed to divide the text from each other.
     * @param tab Tab to be printed in front of text as padding.
     */
    public Ui(String divider, String tab) {
        this.divider = divider;
        this.tab = tab;
    }

    /**
     * Prints specified Strings between dividers.
     *
     * @param cont Array of String containing the text to be printed between divider.
     *            Each element will be printed on a new line.
     */
    public void printInDivider(String[] cont) {
        System.out.print(this.divider);
        for (String s : cont) {
            System.out.print(this.tab + s + "\n");
        }
        System.out.print(this.divider);
    }

    /**
     * Prints specified Task object as String between dividers.
     *
     * @param type Type of the Task object.
     * @param size Size of the current TaskList.
     * @param task Task object to be printed.
     */
    public void printTaskAddedWithDivider(String type, int size, Task task) {
        String[] toPrint = {"Got it. I've added this task:", "  [" + type + "][ ] " + task,
                "Now you have " + size + " tasks in the list."};
        printInDivider(toPrint);
    }

    /**
     * Prints specified BlitzException object as String between dividers
     *
     * @param err BlitzException object to be printed.
     */
    public void printError(BlitzException err) {
        String[] toPrint = {err.toString()};
        printInDivider(toPrint);
    }
}
