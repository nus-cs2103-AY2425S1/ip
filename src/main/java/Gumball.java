import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class Gumball {
    public static void main(String[] args) {
        Gumball chat = new Gumball();
        chat.start();

    }

    public String command;
    public Scanner input;
    public TaskList list;


    public Gumball() {
        input = new Scanner(System.in);
        list = new TaskList();
    }

    public void start() {
        intro();
        while (true){
            command = input.nextLine();
            if(command.equals("bye")) break;
            execute(command);
        }
        outro();
    }

    public void execute(String command) {
        if (command.equals("bye")) {

        } else if (command.equals("list")) {
            getList();
        } else if (command.startsWith("mark ")) {
            int num = Integer.parseInt(command.replaceAll("[^0-9]", ""));
            list.mark(num);
            print("Nice! I've marked this task as done:\n" + list.getSpecific(num));
        }
        else {
            addToList(command);
        }
    }

    public void addToList(String str) {
        print("added: " + list.add(str));
    }

    public void getList() {
        print(list.get());
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

    public static void print(String out) {
        String str ="____________________________________________________________";
        System.out.println(str);
        System.out.println(out);
        System.out.println(str);

    }
}
