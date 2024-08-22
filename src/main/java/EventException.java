public class EventException extends Exception {
    @Override
    public String toString() {
        return "You have provided an incorrect event input!!! Correct format is event (name) /from (date) /to (date)";
    }

}
