public class NonExistentTaskException extends Exception {
    public NonExistentTaskException(int index) {
        super("Sumo cannot find task #"
                + index + ".\n"
                + "Can you check your index by checking \"list\" pleaseeeeeeeeee.....");
    }
}
