import java.util.Scanner;

public class Gumball {
    public static void main(String[] args) {
        Gumball chat = new Gumball();
        chat.start();

    }

    public String command;
    public Scanner input = new Scanner(System.in);

    public Gumball() {

    }

    public void start() {
        intro();
        while (true){
            command = input.nextLine();
            if(command.equals("bye")) break;
            print(command);
        }
        outro();
    }


    public void intro() {
        String str = "Hello! I'm Gumball \n"
                + "What can I do for you?";
        print(str);
    }

    public void outro() {
        String str ="Bye. Hope to see you again soon!";
        print(str);
    }

    public void print(String out) {
        String str ="____________________________________________________________";
        System.out.println(str);
        System.out.println(out);
        System.out.println(str);

    }
}
