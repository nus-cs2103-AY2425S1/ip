public class NonExistentTaskException extends Exception {
    public NonExistentTaskException(int index) {
        super("SUMO cannot find task #"
                + index + ".\n"
                + "Can you check your index by checking \"list\" pleaseeeeeeeeee.....");
    }
}
