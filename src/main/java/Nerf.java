
import java.util.Scanner;

public class Nerf {
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

    public static void main(String[] args) {
        greetings();
        echo();
    }

    private static void echo(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        printDivider();
        while (!input.equals("bye")){
            System.out.println(input);
            printDivider();
            input = scanner.nextLine();
            printDivider();
        }
        exit();

    }

    private static void printLogo(){
        System.out.println(Nerf.LOGO);
    }
    private static void printDivider(){
        System.out.println("_".repeat(Nerf.LINE_LENGTH));
    }

    public static void greetings(){
        printDivider();
        System.out.println("Hello there! I am Nerf, your personal chatbot assistant.\n");
        printLogo();
        System.out.println("How may I help you today?");
        printDivider();
    }

    public static void exit(){
        System.out.println("Goodbye. Hope to see you soon!");
        printDivider();
    }
}
