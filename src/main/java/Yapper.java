import java.util.Scanner;

public class Yapper {
    public static void main(String[] args) {
        // greeting message
        System.out.println("Hello! I'm Yapper \n What can I do for you? \n");
        
        while (true) {
            Scanner sc = new Scanner(System.in);
            String text = sc.next();
            if (text.equals("bye")) {
                exit();
                break;
            } else {
                echo(text);
            }
        }
    }

    // echos command entered by the user
    public static void echo(String text)
    {
        System.out.println(text);
    }

    // exits when the user types the command "bye"
    public static void exit()
    {
        System.out.println("Yapper shall yap next time!");
    }
}
