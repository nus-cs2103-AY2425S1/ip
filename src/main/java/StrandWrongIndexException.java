public class StrandWrongIndexException extends StrandException{
    protected int size;
    public StrandWrongIndexException(int size) {
        this.size = size;
    }
    @Override
    public String toString() {
        return "Index out of range " + super.toString()
                + "Please input a number within range (minimum 1, maximum " + this.size + ")";
    }
}
