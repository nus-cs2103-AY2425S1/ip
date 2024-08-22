import java.util.ArrayList;
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
    private static ArrayList<String> taskList = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(openingMessage);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        
        // User input cycle is here.

        while (!input.equals("bye")) {
            outputTask(input);
            input = sc.nextLine();
        }

        System.out.println("Fine just leave me like everyone does hmph");
    }

    private static void outputTask(String input) {
        if (input.equals("list")) {
            displayList();
        } else {
            addTask(input);
        }
    }
    private static void addTask(String task) {
        taskList.add(task);
        System.out.println("_____________________________________________________________________\n   Meow has added a task:\n     " + task + "\n_____________________________________________________________________");
    }

    private static void displayList() {
        System.out.println("_____________________________________________________________________\n   Meow here is your task list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("    " + i + ". " + taskList.get(i));

        }
        System.out.println("_____________________________________________________________________");
    }
}
