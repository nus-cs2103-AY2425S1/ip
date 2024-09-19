package fred.Exceptions;

public class EmptyKeywordsException extends FredException {
    public EmptyKeywordsException() {
        super("OOPS!!! Looks like you forgot to specify the keywords to look for");
    }
}
