package nerf.io;

/**
 * Class for handling of user interface.
 */

public class Ui {
    private final int LINE_LENGTH = 60;
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

    public Ui(){
        this.parser = new Parser();
    }

    /**
     * Prints out initial welcome banner.
     */
    public void greetings(){
        printDivider();
        System.out.println("Hello there! I am Nerf, your personal chatbot assistant.\n");
        printLogo();
        System.out.println("How may I help you today?");
        printDivider();
    }

    /**
     * Prints out the nerf logo.
     */
    public void printLogo(){
        System.out.println(this.LOGO);
    }

    /**
     * Prints out divider line.
     */
    public void printDivider(){
        System.out.println("_".repeat(this.LINE_LENGTH));
    }

    /**
     * Prints out warning regarding file I/O issues.
     */
    public void showLoadingError(){
        System.out.println("Unable to open save file, starting application with empty database");
    }

    /**
     * Returns user input using Parser class.
     * 
     * @return user input string.
     */
    public String getInput(){
        return this.parser.getInput();
    }

    /**
     * Prints out goodbye banner.
     */
    public void exit(){
        System.out.println("Goodbye. Hope to see you soon!");
        printDivider();
    }

}
