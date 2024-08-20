import java.util.ArrayList;
import java.util.Scanner;

public class Delta {
    private static ArrayList<String> list = new ArrayList<String>();

    public static String sayHello() {
        return "\t____________________________________________________________\n"
                + "\t Hello! I'm Delta\n"
                + "\t What can I do for you?\n"
                + "\t____________________________________________________________";
    }

    public static String sayBye() {
        return "\t____________________________________________________________\n"
                + "\t Bye. Hope to see you again soon!\n"
                + "\t_____________________________________________________________";
    }

    public static String add(String phrase) {
        list.add(phrase);
        return "\t____________________________________________________________\n"
                + "\t added: " + phrase + "\n"
                + "\t_____________________________________________________________";
    }

    public static String echo(String phrase) {
        return "\t____________________________________________________________\n"
                + "\t " + phrase + "\n"
                + "\t_____________________________________________________________";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(sayHello());

        while (sc.hasNextLine()) {
            String phrase = sc.nextLine();
            if (phrase.equals("bye")) {
                System.out.println(sayBye());
                break;
            } else {
                System.out.println(echo(phrase));
            }
        }

    }
}
