import java.util.Scanner;
import java.util.ArrayList;

public class Park {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> arr = new ArrayList<String>();
        System.out.println("""
                Hello! I'm Park
                What can I do for you?""");
        while (sc.hasNext()) {
            String s = sc.next();
            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                System.exit(0);
            } else if (s.equals("list")){
                int n = 1;
                for (String text : arr) {
                    System.out.print(n);
                    System.out.println(". " + text);
                    n++;
                }
            } else {
                arr.add(s);
                System.out.println("added: " + s);
            }
        }
    }
}
