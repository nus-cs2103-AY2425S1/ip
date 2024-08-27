

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

    public void greetings(){
        printDivider();
        System.out.println("Hello there! I am Nerf, your personal chatbot assistant.\n");
        printLogo();
        System.out.println("How may I help you today?");
        printDivider();
    }

    public void printLogo(){
        System.out.println(this.LOGO);
    }

    public void printDivider(){
        System.out.println("_".repeat(this.LINE_LENGTH));
    }

    public void showLoadingError(){
        System.out.println("Unable to open save file, starting application with empty database");
    }

    public String getInput(){
        return this.parser.getInput();
    }

    

    public void exit(){
        System.out.println("Goodbye. Hope to see you soon!");
        printDivider();
    }

}
