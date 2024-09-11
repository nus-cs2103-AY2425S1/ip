package kitty.kittyexceptions;

public class EventException extends KittyException {
    @Override
    public String toString() {
        return """
                Invalid input. Follow this structure:
                    event <name> /from <start_time> /to <end_time>
                with time format:
                    yyyy/MM/dd HH:mm""";
    }
}
