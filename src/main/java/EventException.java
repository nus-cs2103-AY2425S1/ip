public class EventException extends KittyException{
    @Override
    public String toString() {
        return "Invalid input. Follow this structure:\n" + "    event <name> /from <start_time> /to <end_time>";
    }
}
