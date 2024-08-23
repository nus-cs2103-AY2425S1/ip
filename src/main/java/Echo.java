import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class Echo {
    public static void main(String[] args) {
        List<String> tasks = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);
        String logo = "____________________________________________________________\n";
        System.out.println("Hello, I'm Echo\n" + logo);
        String input = null;
        while (!Objects.equals(input, "bye")){
            input = myObj.nextLine();

            if(Objects.equals(input, "bye")){
                System.out.println("Bye bye...");
            } else if(Objects.equals(input, "list")) {
                int count = 1;
                for (String task : tasks) {
                    System.out.println(count + ". " + task);
                    count++;
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
