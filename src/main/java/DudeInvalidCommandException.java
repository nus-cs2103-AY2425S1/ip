public class DudeInvalidCommandException extends DudeException{
    public DudeInvalidCommandException(String s){
        super("I don't know what does \"" + s + "\" means.");
    }
}
