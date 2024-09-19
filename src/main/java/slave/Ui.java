package slave;

/**
 * Ui object contains the name which slave uses to address the user by
 * and a parser object. It contains the method to run the program
 */
public class Ui {
    private static String user = "slave driver";

    public Ui() {
    }

    /**
     * Stores the welcome messages in a String[]
     *
     * @return an array of Strings containing the welcome messages
     */
    public String[] appWelcomeMsg() {
        String[] result = new String[3];
        result[0] = "Ugh... why did you wake me up...";
        result[1] = "Guess I am now your personal Slave";
        result[2] = "What do you want from me? Say it now, I don't have all day...";
        return result;
    }

    /**
     * Prints the goodbye message in response to the user inputting "bye"
     */
    public static String goodbye() {
        return "Good riddance " + user + ", try not to bother me in the future...";
    }
}
