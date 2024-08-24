public class AlreadyUnmarkedException extends Exception {
    public AlreadyUnmarkedException(Task task) {
        super("Sumo confused. This task is not completed in the first place!\n"
                + "But SUMO will mark it as NOT done again!\n" + task);
    }
}
