public class DudeNullDateTimeException extends DudeException{
    public DudeNullDateTimeException(String s){
        super("You need to add date or/and time for \"" + s + "\".");
    }
}
