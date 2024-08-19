public class MeejuException extends Exception{

    private String message;
    public MeejuException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return "Meow!! =^..^= " + message;
    }
}
