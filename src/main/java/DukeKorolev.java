import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class DukeKorolev {
    private static int findIndex(String input) {
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        if (m.find()) {
            return Integer.parseInt(m.group());
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String newLogo = "Hello! I'm DukeKorolev\n"
                + "What can I do for you?\n";
        String input = "";
        String end = "Bye. Hope to see you again soon!";
        String divider = "--------------------";



        KorolevList repo = new KorolevList();

        System.out.println(newLogo);
        repo.loadEvent();
        while (true) {
            input = scanner.nextLine();
            String[] target = input.split("\\s");
            System.out.println(divider);
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(repo.displayList());
            } else if (target[0].equals("unmark")) {
                try {
                    repo.unmarkEvent(Integer.parseInt(target[1]) - 1);
                } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Error:" + e.getMessage());
                }
            } else if (target[0].equals("mark")) {
                try {
                    repo.markEvent(Integer.parseInt(target[1]) - 1);
                } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Error:" + e.getMessage());
                }
            } else if (target[0].equals("delete")) {
                try {
                    repo.removeEvent(Integer.parseInt(target[1]) - 1);
                } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Error:" + e.getMessage());
                }
            } else {
                try {
                    repo.addEvent(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            repo.saveEvent();
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
