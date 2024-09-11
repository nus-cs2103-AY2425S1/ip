package kitty.kittyexceptions;

public class DeadlineException extends KittyException {
    @Override
    public String toString() {
        return """
                Invalid input. Follow this structure:
                    deadline <name> /by <time>
                with time format:
                    yyyy/MM/dd HH:mm""";
    }
}
