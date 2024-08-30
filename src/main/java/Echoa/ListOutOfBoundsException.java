package Echoa;

public class ListOutOfBoundsException extends Exception {
    private int index;

    public ListOutOfBoundsException(int index) {
        super();
        this.index = index;
    }


    @Override
    public String getMessage() {
        return this.index + "is out of bounds. Please check your list again.";
    }
}
