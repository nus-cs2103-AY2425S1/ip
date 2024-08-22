public class DeadlineNoDateException extends TestamentException {

    public DeadlineNoDateException() {
        super("You have not provided the date for this deadline.\nThe format for deadlines is as follows:\n" +
                "deadline (details) /by (date)");
    }

}
