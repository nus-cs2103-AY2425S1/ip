public class DevonReadDatabaseException extends DevonException {

    @Override
    public String toString() {
        return super.toString() + " Unable to read database!";
    }
}
