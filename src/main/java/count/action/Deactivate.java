package count.action;

/**
 * The Deactivate class is used to turn the Count program off
 */
public class Deactivate extends Action {

    /**
     * Returns the String for Count's UI to print
     * @return String for Count's UI to print
     */
    @Override
    public String run() {
        return "Bye. Hope to see you again soon!";
    }
}
