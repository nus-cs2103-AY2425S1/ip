package kita.exceptions;

/**
 * General parent error class
 */
public class KitaError extends RuntimeException {
    @Override
    public String toString() {
        return "Kita.Kita Error";
    }
}
