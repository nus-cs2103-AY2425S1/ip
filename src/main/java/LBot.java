import java.util.Scanner;

public class LBot {
    public static void main(String[] args) {
        String greeting = "Hello! I'm LBot, your dedicated personal assistant ;)\nWhat can I do for you?";
        String exitMsg = "Bye. Hope to smell you again!";
        // Initialise Scanner object
        Scanner scanner = new Scanner(System.in);
        System.out.println(greeting);
        String userInput = "";
        while (true){
            userInput = scanner.nextLine();
            if (!userInput.equals("bye")){
                System.out.println(userInput);
            }else{
                System.out.println(exitMsg);
                break;
            }
        }
        scanner.close();
    }
}
