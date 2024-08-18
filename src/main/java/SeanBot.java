import java.util.Scanner;
public class SeanBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm SeanBot\n" + //
                            "What can I do for you?");
        
        Scanner scanner = new Scanner(System.in);
        while(true){
            String userInput = scanner.nextLine();
            System.out.println(userInput);
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }         
        }
        scanner.close();
    }
}
