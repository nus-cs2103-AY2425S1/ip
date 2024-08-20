import java.util.*;

public class Count {
    private ArrayList<Task> ls;
    private boolean on;

    public Count() {
        this.ls = new ArrayList<>();
        this.on = true;
    }
    private void reply(String output) {
        String spacer = "\n____________________________________________________________\n";
        System.out.println(spacer + output + spacer);
    }
    private void greet() {
        reply("Hello! I'm Count!\nWhat can I do for you?");
    }

    private void farewell() {
        this.on = false;
        reply("Bye. Hope to see you again soon!");
    }

    private void helpTutorial() {
        reply("1.'hello': Prompts me for a greeting!\n Usecase: hello" +
                "\n\n2.'bye': Closes the program\n Usecase: bye" +
                "\n\n3.'list': Lists all tasks added in the order that they were added in\n Usecase: list" +
                "\n\n4.'todo': Adds a task with no date or time attached\n Usecase: todo bake bread" +
                "\n\n5.'deadline': Adds a task with a date or time to complete by\n Usecase: deadline homework /by Monday" +
                "\n\n6.'event': Adds a task with from a date or time to another date or time\n Usecase: event project meeting /from 3pm /to 6pm" +
                "\n\n7.'mark': Marks specified task as complete\n Usecase: mark 2" +
                "\n\n8.'unmark': Marks specified task as incomplete\n Usecase: unmark 1" +
                "\n\n9.'delete': Deletes specified task number\n Usecase: delete 3");
    }

    private void addTask(Task t) {
        ls.add(t);
        reply("Added the following task:\n" + t.toString() +"\nYou now have " + ls.size() + " task(s) in your list");
    }

    private void listReply() {
        String ans = "Here are the tasks in your list:\n";
        for (int i = 0 ; i < ls.size() ; i++) {
            if (i != ls.size() - 1) {
                ans += (i + 1) + "." + ls.get(i).toString() + "\n";
            } else {
                ans += (i + 1) + "." + ls.get(i).toString();
            }
        }
        reply(ans);
    }

    private void mark(int i) {
        try {
            ls.get(i - 1).setCompletion(true);
            reply("Good job, I have marked this task as complete:\n" + ls.get(i - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            reply("Invalid list index chosen! Choose a number from 1 to " + ls.size() + "\nType 'help' to see correct formatting examples");
        }
    }

    private void unmark(int i) {
        try {
            ls.get(i - 1).setCompletion(false);
            reply("No problem, I have marked this task as incomplete:\n" + ls.get(i - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            reply("Invalid list index chosen! Choose a number from 1 to " + ls.size() + "\nType 'help' to see correct formatting examples");
        }
    }

    private void delete(int i) {
        try {
            Task curr = ls.get(i - 1);
            ls.remove(i - 1);
            reply("Got it, I have removed this task from the list:\n" + curr.toString() + "\nYou now have " + ls.size() + " task(s) in your list");
        } catch (IndexOutOfBoundsException e) {
            reply("Invalid list index chosen! Choose a number from 1 to " + ls.size() + "\nType 'help' to see correct formatting examples");
        }
    }

    // single word command parser
    private void parser(String command) {
        switch (command.toLowerCase()) {
            case "hello":
                greet();
                break;
            case "bye":
                farewell();
                break;
            case "list":
                listReply();
                break;
            case "help":
                helpTutorial();
                break;
            default:
                reply("I'm sorry, I did not understand that, type 'help' for available commands.");
        }
    }

    // multi-world parser
    private void parser(String command, String firstWord) {
        String temp[] = command.split(" ", 2);
        String rest = temp[1];
        try {
            switch (firstWord.toLowerCase()) {
                case "mark":
                    mark(Integer.valueOf(rest));
                    break;
                case "unmark":
                    unmark(Integer.valueOf(rest));
                    break;
                case "delete":
                    delete(Integer.valueOf(rest));
                    break;
                case "todo":
                    addTask(new ToDos(rest));
                    break;
                case "deadline":
                    String commandSplitD[] = rest.split(" /by ", 2);
                    addTask(new Deadlines(commandSplitD[0], commandSplitD[1]));
                    break;
                case "event":
                    String commandSplitE[] = rest.split(" /from ", 2);
                    String startEndTime[] = commandSplitE[1].split(" /to ", 2);
                    addTask(new Events(commandSplitE[0], startEndTime[0], startEndTime[1]));
                    break;
                default:
                    reply("I'm sorry, I did not understand that, could you say that again?");
            }
        } catch (NumberFormatException e) {
            reply("Use a number after mark/unmark/delete to specify the task targeted!\nType 'help' to see correct formatting examples");
        } catch (ArrayIndexOutOfBoundsException e) {
            reply("Invalid format for event or deadline!\nType 'help' to see correct formatting examples");
        }
    }

    public static void main(String[] args) {
        Count c = new Count();
        Scanner sc = new Scanner(System.in);
        c.greet();

        while (c.on) {
            String command = sc.nextLine();

            // If there is more than 1 word,
            if (command.indexOf(" ") > -1) {
                c.parser(command, command.substring(0, command.indexOf(" ")).trim());
            } else {
                c.parser(command);
            }
        }

    }
}
