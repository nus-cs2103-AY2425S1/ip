public class SpikeException extends Exception {
    public SpikeException(String message) {
        super("     _________________________________________________________\n"
                + "     " + message
                + "\n     _________________________________________________________");
    }
}