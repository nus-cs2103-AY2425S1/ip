import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class BitBot {
    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();

        String inputData;
        String intro = "          ____________________________________ \n          Hello! I'm BitBot \n          What can I do for you? \n          ____________________________________";
        String conclusion = "          ____________________________________ \n          Bye. Hope to see you again soon! \n          ____________________________________ \n ";

        //printing out the intro
        System.out.println(intro);
        //creating a scanner
        Scanner sc = new Scanner(System.in);

        //listening to the user's input & only terminating when the user says "bye"
        while (true) {
            inputData = sc.nextLine();
            // i am using a switch and case method to deal with the user input
            // this is because the user can key in list / bye / any other input which
            // the scanner will read.
            switch (inputData) {
                case "bye":
                    break;
                case "list":
                    System.out.println("          ____________________________________");
                    for (int i = 1; i < arrayList.size() + 1; i++) {
                        System.out.println("          " + i + ". " + arrayList.get(i - 1));
                    }
                    System.out.println("          ____________________________________ \n");
                    break;
                default:
                    // if the user adds in anything else that is not either list or bye,
                    // it will add it into the list.
                    arrayList.add(inputData);
                    System.out.println("          ____________________________________ \n " + "          added: " + inputData + "\n          ____________________________________");
                    break;

            }
            // if the user keys in "bye" break out of the while loop and print out the conclusion.
            if (Objects.equals(inputData, "bye")) {
                break;
            }
        }

        System.out.println(conclusion);


    }
}
