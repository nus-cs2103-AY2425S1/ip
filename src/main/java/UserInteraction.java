import java.util.Scanner;
public class UserInteraction {


    public static void interact() {
        Scanner scannerObject = new Scanner(System.in);


        while (true) {
            String instruction = scannerObject.nextLine();
            System.out.println(Meeju.LINE_BREAK);
            if (instruction.equals("bye")) {
                break;
            } else {
                System.out.println("\t" + instruction);
            }
            System.out.println(Meeju.LINE_BREAK);
        }
    }
}
