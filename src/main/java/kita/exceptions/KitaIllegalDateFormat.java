package kita.exceptions;

/**
 * An error for when the date entered is not of the expected format
 */
public class KitaIllegalDateFormat extends KitaError {
    @Override
    public String toString() {
        return "Please state any dates in the form of yyyy-mm-dd";
    }
}
