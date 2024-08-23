public class CommandNotFoundException extends Exception {

    @Override
    public String toString() {
        return "command not found in lib";
    }
}
