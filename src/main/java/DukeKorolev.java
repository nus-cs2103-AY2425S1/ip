import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class DukeKorolev {
    private static int findIndex(String input) {
        String regex = "\\d";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        if (m.find()) {
            return Integer.parseInt(m.group());
        }
        return -1;
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        String newLogo = "Hello! I'm DukeKorolev\n"
                + "What can I do for you?\n";
        String input = "";
        String end = "Bye. Hope to see you again soon!";
        String divider = "--------------------";

        String listNotice = "Here are the tasks in your list:";
        String markNotice = "Nice! I've marked this task as done:";
        String unmarkNotice = "OK, I've marked this task as not done yet:";
        String deleteNotice = "Noted. I've removed this task:";

        KorolevList repo = new KorolevList();

        System.out.println(newLogo);
        while (true) {
            input = scanner.nextLine();
            System.out.println(divider);
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(listNotice);
                repo.displayList();
            } else if (input.contains("unmark")) {
                System.out.println(unmarkNotice);
                repo.unmarkEvent(DukeKorolev.findIndex(input) - 1);
            } else if (input.contains("mark")) {
                System.out.println(markNotice);
                repo.markEvent(DukeKorolev.findIndex(input) - 1);
            } else if (input.contains("delete")) {
                System.out.println(deleteNotice);
                repo.removeEvent(DukeKorolev.findIndex(input) - 1);
            } else {
                try {
                    repo.addEvent(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(divider);
        }
        System.out.println(end);
    }

    public static class ParseException extends Exception{

        public ParseException(String msg) {
            super("Fail to extract information: " + msg);
        }
    }
}
