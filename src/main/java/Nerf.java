
import java.util.ArrayList;
import java.util.List;
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

    private static final List<String> listings = new ArrayList<>();

    public static void main(String[] args) {
        greetings();
        echo();
    }

    private static void addToList(String input){
        listings.add(input);
        System.out.println("added: " + input);
    }
    private static void printList(){
        for (int i = 0; i < listings.size();i++){
            System.out.println(String.format("%d. %s",i+1,listings.get(i)));
        }
        printDivider();
    }

    private static void echo(){
        String input;
        do {
            input = userInput();
            switch(input){
                case "bye" -> exit();
                case "list" -> printList();
                default -> addToList(input);
            }
        } while (!input.equals("bye"));
    }

    private static String userInput(){
        Scanner scanner = new Scanner(System.in);
        String res = scanner.nextLine();
        printDivider();
        return res;
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
