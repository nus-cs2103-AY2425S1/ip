public class NoSuchCommandException extends Exception {

    NoSuchCommandException(String input) {
        super(String.format("%s is not a valid command. Please try again with: \ntodo \nevent \ndeadline \nlist \nbye", input));
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
