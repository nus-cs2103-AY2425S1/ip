
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

    private static final List<Task> listings = new ArrayList<>();

    public static void main(String[] args) {
        greetings();
        echo();
    }

    private static void addToList(String input){
        listings.add(new Task(input));
        System.out.println("added: " + input);
    }
    private static void printList(){
        System.out.println("Here are the tasks in your list:");
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
                default -> {
                    if (input.startsWith("mark ")) {
                        markTask(input);
                    } else if (input.startsWith("unmark ")) {
                        unmarkTask(input);
                    }
                    else {
                        addToList(input);
                    }
                }
            }
        } while (!input.equals("bye"));
    }
    private static void markTask(String input){
        String number = input.substring(5).trim();
        try {
            int num = Integer.parseInt(number);
            listings.get(num-1).setDone();
            System.out.println("""
                               Wow! Congrats on finishing another task!
                               I've marked this task as done:
                               """ + listings.get(num-1));
            printDivider();
        } catch (NumberFormatException e) {
            addToList(input); // might be the task is to mark something
        }
    }
    private static void unmarkTask(String input){
        String number = input.substring(7).trim();
        try {
            int num = Integer.parseInt(number);
            listings.get(num-1).setUndone();
            System.out.println("""
                               Ok noted!
                               I've marked this task as undone:
                               """ + listings.get(num-1));
            printDivider();
        } catch (NumberFormatException e) {
            addToList(input); // might be the task is to unmark something
        }
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
