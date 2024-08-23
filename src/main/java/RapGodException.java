public class RapGodException extends Exception{
    public RapGodException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

}
