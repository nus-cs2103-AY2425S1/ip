public class InvalidIndexException extends BeeBooExceptions{
    public InvalidIndexException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "Index dont exist. Please input a valid index.You can use the \"list\" to check your list ";
    }
}
