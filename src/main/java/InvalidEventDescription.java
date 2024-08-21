public class InvalidEventDescription extends Exception {
    public InvalidEventDescription() {
        super("Sorry traveller. The event description must have 2 '/', with the first part being the description," +
                "the second part being from what time and the third part being to what time");
    }
}
