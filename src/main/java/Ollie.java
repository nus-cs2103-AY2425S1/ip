import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;
import java.util.ArrayList;

public class Ollie {
    // Private Types
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Greet
        Ollie.greet();

        String input = scanner.nextLine();
        while(!input.matches("bye")) {
            try {
                // Input parser
                if (input.matches("list")) {
                    Ollie.list();
                } else if (input.matches("^mark.*")) {
                    Ollie.mark(input);
                } else if (input.matches("^unmark.*")) {
                    Ollie.unmark(input);
                } else if (input.matches("^(deadline|event|todo).*")) {
                    Ollie.add(input);
                } else if (input.matches("^delete.*")) {
                    Ollie.delete(input);
                } else {
                    throw new OllieException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (OllieException e) {
                Ollie.printResponse(e.getMessage());
            } finally {
                input = scanner.nextLine();
            }
        }

        // Exit
        Ollie.exit();
    }

    // Private Methods
    private static void greet() {
        Ollie.printResponse("Hello! I'm Ollie!\nWhat can I do for you?");
    }

    private static void exit() {
        // Exit
        Ollie.printResponse("Bye. Hope to see you again soon!");
    }

    private static void add(String s) throws OllieException {
        Task task;

        // Input parser:
        if (s.matches("^deadline.*$")){
            if (!s.contains("/by")) {
                throw new OllieException("Use deadline with a \"/by\" keyword and a date/time.");
            }
            String[] splitString = s.split("/by", 2);

            String desc = splitString[0].replaceFirst("deadline","").trim();
            if (desc.isEmpty()) {
                throw new OllieException("Description of deadline cannot be empty!");
            }

            String by = splitString[1].trim();
            if (by.isEmpty()) {
                throw new OllieException("Date/Time of deadline cannot be empty!");
            }

            task = new Deadline(desc, by);
        } else if(s.matches("^event.*")) {
            if (!s.contains("/from")) {
                throw new OllieException("Use deadline with a \"/from\" keyword and a date/time.");
            }
            if (!s.contains("/to")) {
                throw new OllieException("Use deadline with a \"/to\" keyword and a date/time.");
            }
            if (!s.matches(".*/from.*/to.*")) {
                throw new OllieException("\"/from\" keyword must come before \"/to\" keyword.");
            }
            String[] splitString = s.split("/from|/to", 3);

            String desc = splitString[0].replaceFirst("event","").trim();
            if (desc.isEmpty()) {
                throw new OllieException("Description of event cannot be empty!");
            }

            String from = splitString[1].trim();
            if (from.isEmpty()) {
                throw new OllieException("Date/Time after /from cannot be empty!");
            }

            String to = splitString[2].trim();
            if (to.isEmpty()) {
                throw new OllieException("Date/Time after /to cannot be empty!");
            }

            task = new Event(desc, from, to);
        } else {
            String desc = s.replaceFirst("todo","").trim();
            if (desc.isEmpty()) {
                throw new OllieException("Description of todo cannot be empty!");
            }

            task = new Todo(desc);
        }
        tasks.add(task);
        Ollie.printResponse("Got it. I've added this task:\n  "
                + task.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
    private static void list() throws OllieException {
        if (tasks.isEmpty()) {
            throw new OllieException("Your list is empty!");
        }
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i++) {
            list.add(String.format("%d.%s%s", i + 1,tasks.get(i),i == tasks.size() - 1 ? "": "\n"));
        }
        Ollie.printResponse(String.join("",list));
    }

    private static void mark(String input) throws OllieException{
        int index = Ollie.getIndex(input);
        Task task = tasks.get(index);
        task.markAsDone();

        Ollie.printResponse("Nice! I've marked this task as done:\n  " + task.toString());
    }

    private static void unmark(String input) throws OllieException{
        int index = Ollie.getIndex(input);
        Task task = tasks.get(index);
        task.markAsUndone();

        Ollie.printResponse("OK, I've marked this task as not done yet:\n  " + task.toString());
    }

    private static void delete(String input) throws OllieException {
        int index = Ollie.getIndex(input);
        Task task = tasks.get(index);
        tasks.remove(index);

        Ollie.printResponse("Noted. I've removed this task:\n  "
                + task.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void echo(String s) {
        Ollie.printResponse(s);
    }

    private static int getIndex(String s) throws OllieException {
        if (!s.matches(".* \\d+")) {
            throw new OllieException("Missing Serial Number after command.");
        }
        int index = Integer.parseInt(s.replaceAll("\\D+", "")) - 1;

        if (index < 0 || index >= tasks.size()) {
            throw new OllieException("Invalid Serial Number!");
        }
        return index;
    }

    private static void printResponse(String s) {
        System.out.println("____________________________________________________________\n"
                + s
                + "\n____________________________________________________________");
    }
}
