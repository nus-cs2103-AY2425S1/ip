import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class Danny {
    public static void main(String[] args) {
        String seperator = "____________________________________________________________";
        System.out.println(seperator);
        System.out.println("Hello! I'm Danny");
        System.out.println("What can I do for you?");
        System.out.println(seperator);
        Scanner input = new Scanner(System.in);
        List<String> arr = new ArrayList<String>();
        String in = input.nextLine();
        while(!Objects.equals(in, "bye")){
            System.out.println(seperator);
            switch (in.split(" ")[0]){
                case "list":
                    for (int i = 0; i < arr.size(); i++) {
                        System.out.println((i+1) + ". " + arr.get(i));
                    }
                    break;
                case "add":
                    arr.add(in);
                    System.out.println("added: " + in);
                    break;
                default:
                    System.out.println(in);
                    break;
            }
            System.out.println(seperator);
            in = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(seperator);
        return;
    }

}
