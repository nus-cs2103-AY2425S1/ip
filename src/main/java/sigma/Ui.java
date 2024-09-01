package sigma;

/**
 * Class for handling interactions with the user
 *
 * @author Qiao Yi
 */
public class Ui {

    // just use default constructor

    // welcome message

    /**
     * Greets the user upon running the program
     * @return The welcome message
     */
    public String welcome() {
        return "Hello! I'm Sigma \nWhat can I do for you? \n";
    }


    // goodbye message
//    public void goodbye() {
//        System.out.println("leaving so soon? goodbye!");
//    }
    // don't recognise command message

    /**
     * Alerts the user that there is no recognised command
     */
    public void dontRecognise() {
        System.out.println( "erm, what the sigma? i don't recognise that command.");
    }

}
