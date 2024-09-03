package nerf.io;

/**
 * Class for handling of user interface.
 */
public class Ui {
    private static final int LINE_LENGTH = 60;
    private final String LOGO = """
                                ##  ##   ######   #####    ######
                                ### ##   ##       ##  ##   ##    
                                ######   ##       ##  ##   ##    
                                ######   ####     #####    ####  
                                ## ###   ##       ####     ##    
                                ##  ##   ##       ## ##    ##    
                                ##  ##   ######   ##  ##   ##    
                                """;
    private final Parser parser;                                   
    public Ui() {
        this.parser = new Parser();
    }

    /**
     * Prints out initial welcome banner.
     * 
     * @return welcome banner.
     */
    public String greetings() {
       
        String res = "Hello there! I am Nerf, your personal chatbot assistant.\n" 
                + "How may I help you today?";

        return res;
    }

    /**
     * Prints out the nerf logo.
     */
    public String printLogo() {
        return this.LOGO;
    }

    /**
     * Prints out divider line.
     */
    public static void printDivider() {
        System.out.println("_".repeat(Ui.LINE_LENGTH));
    }

    /**
     * Prints out warning regarding file I/O issues.
     */
    public void showLoadingError() {
        System.out.println("Unable to open save file, starting application with empty database");
    }

    /**
     * Returns user input using Parser class.
     * 
     * @return user input string.
     */
    public String getInput() {
        return this.parser.getInput();
    }

    /**
     * Prints out goodbye banner.
     * 
     * @return goodbye string.
     */
    public String exit() {
        return "Goodbye. Hope to see you soon!";
    }

}
