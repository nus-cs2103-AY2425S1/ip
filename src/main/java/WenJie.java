import java.util.Scanner;

public class WenJie {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean active = true;

        String greeting =
                "   ____________________________________________________________\n" +
                "   Eh wasusp la bro, my name is Wen Jie.\n" +
                "   What you want?\n" +
                "   ____________________________________________________________\n";

        System.out.println(greeting);

        while(active) {
            String input = scanner.nextLine();

            switch(input) {
                case "bye": {
                    active = false;
                    String farewell =
                            "____________________________________________________________\n" +
                            " Paiseh bro I zao liao, see you around ah bro.\n" +
                            "____________________________________________________________\n";
                    System.out.println(farewell);
                    break;
                }

                default :
                    String output =
                            "____________________________________________________________\n" +
                            input + "\n" +
                            "____________________________________________________________\n";
                    System.out.println(output);
                    break;
            }
        }



    }
}
