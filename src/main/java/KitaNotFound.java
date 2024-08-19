public class KitaNotFound extends RuntimeException {
    @Override
    public String toString() {
        return "Kita does not understand what you just entered {{{(>_<)}}}. It does not match any of my commands."; // get exception msg
    }
}
