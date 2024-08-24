import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Ynch chatbot = new Ynch();
        System.out.println(chatbot.greet());
        while (true) {
            userInput = scanner.nextLine();
            
            if (userInput.equals("bye")) {
                System.out.println(chatbot.exit());
                break;
            } 
            if (userInput.equals("list")) {
                System.out.println(chatbot.list());
            } else {
                System.out.println(chatbot.addTask(userInput));
            }
            
            
        }
        scanner.close();  
    }
}