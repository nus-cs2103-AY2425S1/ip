import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BabyGronk {
    private final static String SEPERATOR =  "💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬\n";
    private static List<Task>  tasks = new ArrayList<>();

    private static void logOff() {
        String goodByeMessage = SEPERATOR +
                """
                Smell ya later.
                """ +
                SEPERATOR;
        System.out.println(goodByeMessage);
        System.exit(0);
    }

    private static void greet() {
        String welcomeMessage = SEPERATOR +
                """ 
                What's up ohio? I'm BabyGronk, let's see how sigma you are
                How much aura do you have?
                """ +
                SEPERATOR;
        System.out.println(welcomeMessage);
    }

    private static String   handleInput(String input) throws EmptyInputException {
        if (input == null || input.isEmpty()) {
            throw new EmptyInputException ("What bro? You're not skibidi enough\n");
        }
        if (input.equals("bye")) {
            logOff();
        }
        if (input.equals("list")) {
            return (listTasks());
        }
        if (input.startsWith("unmark") || input.startsWith("mark")) {
            String[] args = input.split(" ");
            if (args.length != 2) {
                return ("invalid mark/unmark command\n");
            }
            if (input.startsWith("unmark")) {
                return (markTask(args[1], false));
            } else {
                return (markTask(args[1], true));
            }
        }
        if (input.startsWith("delete")) {
            String[] args = input.split(" ");
            if (args.length != 2) {
                return ("invalid delete command\n");
            }
            return (deleteTask(args[1]));
        }
        return (addTask(input));
    }

    private static String deleteTask(String input) {
        int toDelete = Integer.parseInt(input);
        if (toDelete < 1 || toDelete > tasks.size()) {
            return ("are u acoustic?!! pick within the range 1 - " + tasks.size() + "\n");
        }
        Task deleted = tasks.remove(toDelete - 1);
        return (deleted + "\n was sent to the shadow realm\n" + tasks.size() + " tasks remain\n");
    }

    private static String addTask(String input) {
        String[] args = input.split(" ");
        if (args.length < 2) {
            return ("invalid task format\n");
        }
        if (args[0].startsWith("todo")) {
            StringBuilder task = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                task.append(args[i]).append(" ");
            }
            task.deleteCharAt(task.length() - 1);
            tasks.add(new ToDo(task.toString()));
        } else if (args[0].startsWith("deadline")) {
            StringBuilder task = new StringBuilder();
            int i = 1;
            for (; i < args.length; i++) {
                if (args[i].equals("/by"))
                    break;
                task.append(args[i]).append(" ");
            }
            if (i == args.length || i + 1 == args.length) {
                return ("no deadline!\n");
            }
            tasks.add(new Deadline(task.toString(), args[i + 1]));
        } else if (args[0].equals("event")) {
            StringBuilder task = new StringBuilder();
            int i = 1;
            for (; i < args.length; i++) {
                if (args[i].equals("/from"))
                    break;
                task.append(args[i]).append(" ");
            }
            if (i == args.length || i + 1 == args.length) {
                return ("no event start time!\n");
            }
            StringBuilder from = new StringBuilder();
            for (i = i + 1; i < args.length; i++) {
                if (args[i].equals("/to")) {
                    from.deleteCharAt(from.length() - 1);
                    break;
                }
                from.append(args[i]).append(" ");
            }
            if (i == args.length || i + 1 == args.length) {
                return ("no event end time!\n");
            }
            StringBuilder to = new StringBuilder();
            for (i = i + 1; i < args.length; i++) {
                to.append(args[i]).append(" ");
            }
            to.deleteCharAt(to.length() - 1);
            tasks.add(new Event(task.toString(), from.toString(), to.toString()));
        } else {
            return ("invalid task type\n");
        }
        return ("added task: " + tasks.get(tasks.size() - 1).toString() + "\n" +
                tasks.size() + " task(s) in the list rn\n");
    }

    private static String   listTasks() {
        StringBuilder hist = new StringBuilder("To do list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            hist.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return hist.toString();
    }

    private static String   markTask(String input, boolean status) {
        if (input == null || input.isEmpty()) {
            return ("invalid task\n");
        }
        int toMark = Integer.parseInt(input);
        if (toMark > tasks.size() || toMark < 1) {
            return ("invalid task\n");
        }
        Task task = tasks.get(toMark - 1);
        task.setDone(status);
        if (status) {
            return ("Let's go! +100 aura points!\n" + task + "\n");
        } else {
            return ("Bruh, you're cooked, -500 aura points\n" + task + "\n");
        }
    }

    public static void  main(String[] args) {
        String logo = """
                ⣠⣀⣤⣶⣶⣶⣶⣤⣤⣤⣤⣄⡀⠀⠀⠀⢀⣀⣀⣤⣤⣤⣶⣶⣶⣶⣬⣒⢦⡀
                ⡾⠛⠉⠉⢀⣀⣈⣉⣉⣉⣻⠛⠁⠀⠀⠀⠀⠙⢛⣛⣉⣉⣉⣉⣀⠀⠉⠙⠻⢮
                ⠀⠀⣀⠴⢲⣶⣶⣶⠶⡦⠄⢷⡄⠀⠀⠀⠀⣼⠃⠴⡶⢶⣶⣶⢶⠲⢤⡀⠀⠀
                ⠀⠘⠓⠤⠼⠿⠿⠿⠥⠽⠄⠘⠀⠀⠀⠀⠀⠘⠂⠼⠥⠽⠿⠿⠿⠤⠖⠛⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡟⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠀⠀⡞⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⣤⣀⣀⣀⣀⣀⣀⣀⣀⣀⣠⡤⠤⠶⠞⠋⠁⠀⠀⣸⠁⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀""";
        System.out.println("Up your rizz\n" + logo);
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextLine()) {
                try {
                    String answer = handleInput(scanner.nextLine());
                    System.out.println(SEPERATOR + answer + SEPERATOR);
                } catch (EmptyInputException e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
            } else {
                scanner.close();
                break;
            }
        }
    }
}
