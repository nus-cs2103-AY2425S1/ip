import java.util.Objects;
import java.util.Scanner;
public class BitBot {
    public static void main(String[] args) {
        String inputData = "";
        String intro = " ____________________________________ \n Hello! I'm BitBot \n What can I do for you? \n ____________________________________";
        String conclusion = " ____________________________________ \n Bye. Hope to see you again soon! \n ____________________________________ \n ";
        String initialSkeletal = intro + conclusion;

        //printing out the intro
        System.out.println(intro);
        //creating a scanner
        Scanner sc = new Scanner(System.in);

        //listening to the user's input & only terminating when the user says "bye"
        while (true) {
            inputData = sc.nextLine();
            // if the user keys in "bye" break out of the while loop and print out the conclusion.
            if (Objects.equals(inputData, "bye")) {
                break;
            }
            System.out.println(" ____________________________________ \n " + inputData + "\n ____________________________________");
        }

        System.out.println(conclusion);


    }
}
