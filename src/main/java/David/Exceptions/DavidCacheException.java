package David.Exceptions;

public class DavidCacheException extends DavidException{

    public DavidCacheException() {};

    @Override
    public String toString() {
        return "A cache failure occured. Please report this bug and try again later.";
    }
}
