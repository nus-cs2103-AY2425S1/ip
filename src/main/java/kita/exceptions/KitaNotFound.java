package kita.exceptions;

/**
 * An error for when an unknown command is entered
 */
public class KitaNotFound extends KitaError {
    @Override
    public String toString() {
        return "Kita does not understand what you just entered {{{(>_<)}}}. "
                + "It does not match any of my commands."; // get exception msg
    }
}
