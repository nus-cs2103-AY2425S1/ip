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

    private static void addToList(Task input){
        listings.add(input);
        System.out.println("Understood. I've added the following task:");
        System.out.println("  " + input);
        
        System.out.println(String.format("You now have %d tasks in total.",listings.size()));
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
                    } else if (input.startsWith("todo ")){
                        addTodo(input);
                    } else if (input.startsWith("deadline ")){
                        addDeadline(input);
                    } else if (input.startsWith("event ")){
                        addEvent(input);
                    }
                }
            }
        } while (!input.equals("bye"));
    }

    private static void addTodo(String input){
        addToList(new ToDos(input.substring(5).trim())); 
    }

    private static void addDeadline(String input){
        input = input.substring(9).trim();
        String[] parts = input.split("/by",2);
        addToList(new Deadlines(parts[0].trim(),parts[1].trim()));
         
    }
    private static void addEvent(String input){
        input = input.substring(6).trim();
        String[] part1 = input.split("/from",2);
        String[] part2 = part1[1].split("/to",2);
        addToList(new Events(part1[0].trim(),part2[0].trim(),part2[1].trim()));
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
            System.out.println("Oops! That does not seem like a number.");
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
            System.out.println("Oops! That does not seem like a number.");
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
