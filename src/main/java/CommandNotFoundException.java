public class CommandNotFoundException extends Exception {
    CommandNotFoundException() {

    }

    @Override
    public String toString() {
        return "command not found in lib";
    }
}
