public class DeadlineException extends Exception {
    @Override
    public String toString() {
        return "You have provided an incorrect deadline input!!! Correct format is deadline (name) /by (date)";
    }

}
