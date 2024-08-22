import java.util.Scanner;

public class Kayo {

    public static void main(String[] args) {
        Greet();
        Scanner input = new Scanner(System.in);
        String inputString = "";
        while(!inputString.equals("Bye")) {
            inputString = input.nextLine();
            if(inputString.equals("Bye")) {
                break;
            } else {
                System.out.println(inputString);
            }
        }
        Exit();
    }
    public static void Greet() {
        System.out.println("Hello! I'm Kayo! ");
        System.out.println("What can I do for you?");
    }
    public static void Exit(){
        System.out.println("Bye. I hope to see you again soon!");
    }
}
