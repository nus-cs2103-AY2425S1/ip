public class EmptyArgumentException extends Exception {

    public EmptyArgumentException(String arg, String taskType) {
        super(String.format("OOPS!!! The \"%s\" field of a %s cannot be empty.", arg, taskType));
    }
}
