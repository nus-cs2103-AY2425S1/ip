import java.util.Scanner;
public class Julie {
    private static final String NAME = "Julie";
    private static boolean run = true;
    public static void main(String[] args) {
        CommonFunctions.WrappedLinePrint("Hi!! My name is: " + NAME + "!\nHow may I help?");
        Scanner sc = new Scanner(System.in);
        while (run) {
            String input = sc.next();
            if (input.equals("bye")) {
                run = false;
            } else {
                System.out.println(input);
            }
        }
        CommonFunctions.WrappedLinePrint("Bye!! See you soon!!");
    }
}
