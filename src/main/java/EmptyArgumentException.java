public class EmptyArgumentException extends BottyException {
    public EmptyArgumentException(String flag) {
        super("The following argument is empty: " + flag);
    }
}
