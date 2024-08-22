public class MissingArgumentException extends Exception {

    public MissingArgumentException(String missing, String taskType) {
        super(String.format("OOPS!!! A %s requires a \"%s\" field.", taskType, missing));
    }
}
