import java.util.Scanner;
public class Neko {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greetingMessage =  "  ∧,,,∧  \n( ̳̳• · ̳• ) \n づ Meow! I'm Neko\nWhat can I do for you? ";
        String exitMessage = "Bye! Hope to see you again soon meow ฅ ฅ";
        
        System.out.println(greetingMessage);
        scanner.nextLine();
        System.out.println(exitMessage);
    }
}
