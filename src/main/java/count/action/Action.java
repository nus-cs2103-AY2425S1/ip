package count.action;
import count.exception.CountException;
public abstract class Action {

    public abstract String run() throws CountException;

}
