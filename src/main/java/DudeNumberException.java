public class DudeNumberException extends DudeException{
    public DudeNumberException(String s){
        super("\"" + s + "\" is not a valid number.");
    }
}
