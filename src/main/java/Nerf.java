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
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        greetings();
        echo();
    }

    private static void addToList(Task input){
        listings.add(input);
        System.out.println("Understood. I've added the following task:");
        System.out.println("  " + input);
        
        System.out.println(String.format("You now have %d task(s) in total.",listings.size()));
    }
    private static void printList(){
        System.out.println("Here are the task(s) in your list:");
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
                    try {
                        if (input.startsWith("mark")) {
                            markTask(input);
                        } else if (input.startsWith("unmark")) {
                            unmarkTask(input);
                        } else if (input.startsWith("todo")){
                            addTodo(input);
                        } else if (input.startsWith("deadline")){
                            addDeadline(input);
                        } else if (input.startsWith("event")){
                            addEvent(input);
                        } else if (input.startsWith("delete")){
                            deleteTask(input);
                        } else {
                            System.out.println("""
                                            Sorry, I dont understand what u are asking of me.
                                            You may use list/bye or mark/unmark/todo/deadline/event/delete + required syntax
                                            """);
                        }
                    } catch (InvalidDataException e) {
                        System.out.println("Sorry, your input is seems to be missing some data.");
                    }
                }
            }
        } while (!input.equals("bye"));
    }

    private static void deleteTask(String input){
        String number = input.substring(6).trim();
        try {
            int num = Integer.parseInt(number);
            String taskDeleted = listings.get(num-1).toString();
            listings.remove(num-1);
            System.out.println(String.format("""
                              Noted, removing:
                                 %s
                              You now have %d task(s) in total.
                               """, taskDeleted,listings.size()));
            printDivider();
        } catch (NumberFormatException e) {
            System.out.println("Oops! That does not seem like a number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Please specific a number within the list.");
        }
    }

    private static void addTodo(String input) throws InvalidDataException{
        String taskDesc = input.substring(4).trim();
        if (taskDesc.equals("")) {
            throw new InvalidDataException();
        } else {
            addToList(new ToDos(taskDesc)); 
        }
        
    }

    private static void addDeadline(String input) throws InvalidDataException{
        input = input.substring(8).trim();
        String[] parts = input.split("/by",2);
        if (parts.length != 2) {
            System.out.println("Syntax: deadline <taskname> /by <datetime>");
        } else {
            String taskDesc = parts[0].trim();
            String deadline = parts[1].trim();
            if (taskDesc.equals("") || deadline.equals("")){
                throw new InvalidDataException();
            }
            addToList(new Deadlines(taskDesc,deadline));
        }
        
         
    }
    private static void addEvent(String input) throws InvalidDataException{
        input = input.substring(5).trim();
        String[] part1 = input.split("/from",2);
        if (part1.length != 2) {
            System.out.println("Syntax: event <taskname> /from <datetime> /to <datetime>");
        }
        else {
            String[] part2 = part1[1].split("/to",2);
            if (part2.length != 2) {
                System.out.println("Syntax: event <taskname> /from <datetime> /to <datetime>");
            } else {
                String taskDesc = part1[0].trim();
                String from = part2[0].trim();
                String to = part2[1].trim();
                if (taskDesc.equals("") || from.equals("") || to.equals("")){
                    throw new InvalidDataException();
                }
                addToList(new Events(taskDesc,from,to));
            }
        }
        
        
    }

    private static void markTask(String input){
        String number = input.substring(4).trim();
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
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Please specific a number within the list.");
        }
    }

    private static void unmarkTask(String input){
        String number = input.substring(6).trim();
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
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Please specific a number within the list.");
        }
    }

    private static String userInput(){
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
