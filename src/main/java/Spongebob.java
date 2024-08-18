import java.util.*;

public class Spongebob {

    static List<String> cache = new ArrayList<>();

    final static String LINE = "____________________________________________________________\n";
    final static String lOGO = "▕╮╭┻┻╮╭┻┻╮╭▕╮╲\n" +
                        "▕╯┃╭╮┃┃╭╮┃╰▕╯╭▏\n" +
                        "▕╭┻┻┻┛┗┻┻┛ ╰▏\n" +
                        "▕╰━━━┓┈┈┈╭╮▕╭╮▏\n" +
                        "▕╭╮╰┳┳┳┳╯╰╯▕╰╯▏\n" +
                        "▕╰╯┈┗┛┗┛┈╭╮▕╮┈▏";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String greetings = "Hello! I'm Spongebob! \nWhat can I do for you?\n";
        String goodbye =  "Bye. Hope to see you again soon!\n";

        System.out.println(LINE + lOGO + "\n" + greetings + LINE);

        String usrInput = scanner.nextLine();

        while (!usrInput.equals("bye")) {
            System.out.println(LINE + "\n");

            if (usrInput.equals("list")) {
                ListIterator<String> iter = cache.listIterator();

                while (iter.hasNext()) {
                    System.out.println(iter.nextIndex() + ". " + iter.next());
                }

            } else {
                System.out.println("added: " + usrInput + "\n");
                cache.add(usrInput);
            }

            System.out.println(LINE + "\n");
            usrInput = scanner.nextLine();

        }
        System.out.println(LINE + goodbye + LINE);
    }
}
