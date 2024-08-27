package Kita.Exceptions;

public class KitaNotFound extends KitaError {
    @Override
    public String toString() {
        return "Kita.Kita does not understand what you just entered {{{(>_<)}}}. It does not match any of my commands."; // get exception msg
    }
}
