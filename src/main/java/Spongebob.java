import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Spongebob {

    final static String LINE = "____________________________________________________________\n";
    final static String lOGO = "▕╮╭┻┻╮╭┻┻╮╭▕╮╲\n" +
                        "▕╯┃╭╮┃┃╭╮┃╰▕╯╭▏\n" +
                        "▕╭┻┻┻┛┗┻┻┛ ╰▏\n" +
                        "▕╰━━━┓┈┈┈╭╮▕╭╮▏\n" +
                        "▕╭╮╰┳┳┳┳╯╰╯▕╰╯▏\n" +
                        "▕╰╯┈┗┛┗┛┈╭╮▕╮┈▏";

    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);

        String greetings = "Hello! I'm Spongebob! \nWhat can I do for you?\n";
        String goodbye =  "Bye. Hope to see you again soon!\n";

        System.out.println(LINE + lOGO + "\n" + greetings + LINE);

        String usrInput = scanner.nextLine();

        while (!Objects.equals(usrInput, "bye")) {
            System.out.println(LINE + usrInput + "\n" +LINE);
            usrInput = scanner.nextLine();
        }
        System.out.println(LINE + goodbye + LINE);
    }
}
