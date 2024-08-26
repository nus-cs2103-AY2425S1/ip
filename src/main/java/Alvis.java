import java.util.Scanner;

public class Alvis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("So you require my assistance?");
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Understood.");
                break;
            } else {
                System.out.println(userInput);
            }
        }
    }
}
