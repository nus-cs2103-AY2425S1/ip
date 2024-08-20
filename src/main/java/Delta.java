import java.util.Scanner;

public class Delta {
    public static String sayHello() {
        return "____________________________________________________________\n"
                + " Hello! I'm Delta\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";
    }

    public static String sayBye() {
        return "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "_____________________________________________________________";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(sayHello());
        System.out.println(sayBye());
    }
}
