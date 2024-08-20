public class MelException extends Exception {
    public MelException(String str) {
        super(str);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
