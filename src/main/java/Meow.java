import java.util.Scanner;

public class Meow {
    static private String logo =  
    "     ___           ___           ___           ___ \n" +     
    "    /  /\\         /  /\\         /  /\\         /  /\\ \n" +   
    "   /  /::|       /  /::\\       /  /::\\       /  /:/_ \n" +  
    "  /  /:|:|      /  /:/\\:\\     /  /:/\\:\\     /  /:/ /\\ \n" +
    " /  /:/|:|__   /  /::\\ \\:\\   /  /:/  \\:\\   /  /:/ /:/_ \n" +
    "/__/:/_|::::\\ /__/:/\\:\\ \\:\\ /__/:/ \\__\\:\\ /__/:/ /:/ /\\ \n" +
    "\\__\\/  /~~/:/ \\  \\:\\ \\:\\_\\/ \\  \\:\\ /  /:/ \\  \\:\\/:/ /:/ \n" +
    "      /  /:/   \\  \\:\\ \\:\\    \\  \\:\\  /:/   \\  \\::/ /:/  \n" +
    "     /  /:/     \\  \\:\\_\\/     \\  \\:\\/:/     \\  \\:\\/:/   \n" +
    "    /__/:/       \\  \\:\\        \\  \\::/       \\  \\::/    \n" +
    "    \\__\\/         \\__\\/         \\__\\/         \\__\\/     ";
    static private String openingMessage = logo + 
    "\n    _____________________________________________________________________" +   
    "\n    Welcome to the Meow Chatbot!!! I love meowing hue hue"
    + "\n    What can I do for you Meow?"
    + "\n    _____________________________________________________________________";
    public static void main(String[] args) {
        System.out.println(openingMessage);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        
        // User input cycle is here.

        while (!input.equals("bye")) {
            System.out.println("    _____________________________________________________________________");
            System.out.println("    " +input);
            System.out.println("    _____________________________________________________________________");
            input = sc.nextLine();
        }

        System.out.println("Fine just leave me like everyone does hmph");
    }
}
