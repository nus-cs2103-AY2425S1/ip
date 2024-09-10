package kita.exceptions;

/**
 * An error for when the selected index is out of bounds
 */
public class KitaOutOfBounds extends KitaError {

    private int index;

    public KitaOutOfBounds(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Task ID: " + this.index + " is out of bounds";
    }
}
