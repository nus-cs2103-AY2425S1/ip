public class DeadlineException extends KittyException{
    @Override
    public String toString() {
        return "Invalid input. Follow this structure:\n" + "    deadline <name> /by <time>";
    }
}
