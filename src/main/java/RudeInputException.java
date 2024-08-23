public class RudeInputException extends RuntimeException{
    public RudeInputException() {
        super("That's a very rude thing to say!");
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

}
