package james;

public class MissingEventTimeException extends JamesException{
    public MissingEventTimeException() {
        super("Oops! looks like you missed out the event time! please add a \"/from\" followed by the starting time" +
                " then a \"/to\" followed by the ending time after the event description");
    }
}
