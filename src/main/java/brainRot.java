import java.util.Scanner;
public class brainRot {
    public static void main(String[] args) {

        //creating an array with a number to memorise how many memory slots have been taken
        String[] arr = new String[100];
        int mem = 0;

        Scanner reader = new Scanner(System.in);

        String greeting = "____________________________________________________________ \n"
        + "Hello! I'm fanumTaxRizzlerOhioSigmaLooksmaxxer\n"
        + "What can I do for you?\n"
        + "____________________________________________________________ \n";

        String goodBye = "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        System.out.println(greeting);
        String answer = reader.nextLine();

        //logic to check if "bye" or "list" has been said
        while(!answer.equals("bye")) {
            if(answer.equals("list")) {
                for(int i = 0 ; i < mem; i ++) {
                    System.out.println((i+1) + "." + arr[i]);
                }
                reader = new Scanner(System.in);
                answer = reader.nextLine();

            } else {
                System.out.println(answer);
                arr[mem] = answer;
                mem++;
                reader = new Scanner(System.in);
                answer = reader.nextLine();
            }

        }
        System.out.println(goodBye);
    }
}
