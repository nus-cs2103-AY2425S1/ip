public class AlreadyMarkedException extends Exception {
    public AlreadyMarkedException(Task task) {
        super("Sumo confused. This task is marked as done in the first place!\n"
                + "But SUMO will mark it as done again!\n" + task);
    }
}
