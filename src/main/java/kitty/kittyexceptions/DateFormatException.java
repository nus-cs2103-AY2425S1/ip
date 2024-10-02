package kitty.kittyexceptions;

public class DateFormatException extends KittyException {
    @Override
    public String toString() {
        return "Invalid input. Please input time in this format:\n" + "    \"yyyy/MM/dd HH:mm\"";
    }
}
