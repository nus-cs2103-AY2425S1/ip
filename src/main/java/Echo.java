import java.util.Objects;
import java.util.Scanner;
public class Echo {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        String logo = "____________________________________________________________\n";
        System.out.println("Hello, I'm Echo\n" + logo);
        String input = null;
        while (!Objects.equals(input, "bye")){
            input = myObj.nextLine();

            if(Objects.equals(input, "bye")){
                System.out.println("Bye bye...");
            } else {
                System.out.println(input);
            }
        }
    }
}
