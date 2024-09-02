package parser;

public class KeywordNotRecognisedException extends RuntimeException {
    public KeywordNotRecognisedException(String keyword) {
        super(String.format("The keyword %s is not recognised.", keyword));
    }
}
