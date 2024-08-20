package hoodini;
public class Ui {
    /**
     * Default Constructor to create new UI object.
     */
    public Ui() {

    }

    /**
     * Handles the welcome message,
     * takes in no parameters,
     * prints the welcome statement.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Hoodini\n" +
                "How may I assist you?");
    }

    /**
     * Handles the goodbye message,
     * takes in no parameters,
     * prints the parting statement.
     */
    public void showGoodbye() {
        System.out.println("Bye! " +
                "Come back to Hoodini soon!");
    }

    /**
     * Handles the no file found message,
     * takes in no parameters,
     * prints the no file statement.
     */
    public void noFileFound() {
        System.out.println("File not found, " +
                "I will create a new file for you!");
    }

    /**
     * Handles storing message,
     * print message when user
     * stores a task
     * @param input Takes in input object
     *              to output the relevant string
     * @param counter Takes in a counter number to determine
     *                number of items in list
     */
    public void store(Input input, int counter) {
        System.out.println("Noted. " +
                "I have added this task:");
        System.out.println(input);
        System.out.println("You have " +
                counter + " tasks in the list.");
    }

    /**
     * Handles delete message
     * prints a message when user
     * deletes a task.
     * @param input Takes in input object
     *              to output the relevant string
     *              of deleted task.
     * @param counter Takes in a counter number to determine
     *                number of items in list.
     *
     */
    public void delete(Input input, int counter) {
        System.out.println("Noted. " +
                "I have deleted this task:");
        System.out.println(input);
        System.out.println("You have " +
                counter + " tasks in the list.");
    }

    /**
     * Handles invalid input message
     */
    public void invalidInput() {
        System.out.println("Invalid number, " +
                "enter a valid number");
    }

    /**
     * Handles invalid task message
     */
    public void invalidTask() {
        System.out.println("Invalid task, " +
                "please enter a valid task");
    }

    /**
     * Handles empty strings
     */
    public void empty() {

        System.out.println("Whoopsie! " +
                "Please enter a task");
    }

}
