import java.util.Scanner;
public class R2D2 {
    public static void main(String[] args) {
        String hline = "____________________________________________________________";
        System.out.println(hline);
        System.out.println("Hello! I'm R2D2! *Beep* *Boop*");
        System.out.println("What can I do for you?");
        System.out.println(hline);


        Scanner reader = new Scanner(System.in);
        String userInput = reader.next();
        while (!userInput.equals("bye")) {
            System.out.println(hline);
            System.out.println(userInput);
            System.out.println(hline);
            userInput = reader.next();
        }
        System.out.println(hline);
        System.out.println("Bye. Hope to see you again soon! *bzzzt*");
        System.out.println(hline);
    }
}
