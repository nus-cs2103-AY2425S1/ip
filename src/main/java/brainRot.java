import java.util.Scanner;
public class brainRot {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        String greeting = "____________________________________________________________ \n"
        + "Hello! I'm fanumTaxRizzlerOhioSigmaLooksmaxxer\n"
        + "What can I do for you?\n"
        + "____________________________________________________________ \n";

        String goodBye = "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        System.out.println(greeting);
        String answer = reader.nextLine();

        while(!answer.equals("bye")) {
            System.out.println(answer);
            reader = new Scanner(System.in);
            answer = reader.nextLine();
        }
        System.out.println(goodBye);
    }
}
