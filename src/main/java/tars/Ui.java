package tars;
/**
 * Class represents a User interface which handles the entry and exit messages for the User
 *
 * @author csk
 * @version 1
 */
public class Ui {
    static String line = "    _____________________________________________";

    public Ui(){

    }

    /**
     * Prints message to be displayed to user when first starting Tars chatbot or re-starting the Tars chatbot
     */
    public void welcome(){
        System.out.println(line + "\n" + "    Hello! I'm Tars\n" + "    What can I do for you"
                + "\n" + "You can add Tasks and I will help manage them for you!" + "\n" + line);
    }

    /**
     * Prints message to be displayed to user when exiting Tars chatbot
     * This command is executed after the "bye" input, which is handled in Tars application
     */
    public void bye(){
        System.out.println(line + "\n" + "    Bye. Hope to see you again soon!" + "\n" + line);
    }
}
