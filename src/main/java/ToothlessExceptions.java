public class ToothlessExceptions extends Exception {

    public ToothlessExceptions(String message) {
        super("Toothless:\nOops! Some error has occured! X.X\n\n" + message);
    }
}
