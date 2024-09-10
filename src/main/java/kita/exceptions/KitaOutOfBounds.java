package kita.exceptions;

/**
 * An error for when the selected index is out of bounds
 */
public class KitaOutOfBounds extends KitaError {
    @Override
    public String toString() {
        return "The task ID you selected is out of bounds/does not exist :c";
    }
}
