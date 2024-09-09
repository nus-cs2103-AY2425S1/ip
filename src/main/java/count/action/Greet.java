package count.action;

/**
 * The Greet class is used to give a welcome message
 */
public class Greet extends Action {

    /**
     * The run method returns the String for Count's UI to print
     * @return String for Count's UI to print
     */
    @Override
    public String run() {
        return "Hello! I'm Count Borg!\nWhat can I do for you?";
    }
}
