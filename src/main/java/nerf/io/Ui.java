package nerf.io;

/**
 * Class for handling of user interface.
 */
public class Ui {
    private static final int LINE_LENGTH = 60;
    private static final String LOGO = """
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
     * Print initial welcome banner.
     */
    public static void printGreetings() {
        System.out.println("Hello there! I am Nerf, your personal chatbot assistant.");
        System.out.println(printLogo());
        System.out.println("How may I help you today?");
    }

    /**
     * Returns initial welcome banner.
     * 
     * @return welcome banner.
     */
    public String getGreetings() {
        return "Hello there! I am Nerf, your personal chatbot assistant.\nHow may I help you today?";
    }

    /**
     * Returns the nerf logo.
     * 
     * @return nerf logo.
     */
    public static String printLogo() {
        return Ui.LOGO;
    }

    /**
     * Returns divider line.
     * 
     * @return divider line.
     */
    public static String printDivider() {
        return "_".repeat(Ui.LINE_LENGTH);
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
    public String getExit() {
        return "Goodbye. Hope to see you soon!";
    }

}
