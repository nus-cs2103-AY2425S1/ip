import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Julie {
    private static final String NAME = "Julie";
    private static boolean run = true;
    private static final List<Task> taskList = new ArrayList<>();
    public enum Command {
        MARK, UNMARK, LIST, DELETE,
        DEADLINE, TODO, EVENT,
        BYE;

        public static Command fromString(String s) throws InvalidCommandException {
            try {
                return Command.valueOf(s.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new InvalidCommandException(s);
            }
        }
    }
    public static void main(String[] args) {
        Storage.start();
        Storage.load(taskList);
        CommonFunctions.wrappedLinePrint("Hi!! My name is: " + NAME + "!\nHow may I help?");
        Scanner sc = new Scanner(System.in);
        while (run) {
            String input = sc.nextLine();
            try {
                String commandToken = CommonFunctions.getFirstWord(input);
                Command cmd = Command.fromString(commandToken);
                Task t = null;
                int x = 0;
                String[] tokens = null;

                switch (cmd) {
                    case BYE:
                        run = false;
                        break;
                    case LIST:
                        CommonFunctions.printList(taskList);
                        break;
                    case MARK:
                        tokens = input.split(" ");
                        x = Integer.parseInt(tokens[1]) - 1;
                        t = taskList.get(x);
                        t.mark();
                        CommonFunctions.wrappedLinePrint(String.format("Ooh, this task is done!\n%s",t));
                        break;
                    case UNMARK:
                        tokens = input.split(" ");
                        x = Integer.parseInt(tokens[1]) - 1;
                        t = taskList.get(x);
                        t.unmark();
                        CommonFunctions.wrappedLinePrint(String.format("Oop, this task is not yet done\n%s",t));
                        break;
                    case DELETE:
                        tokens = input.split(" ");
                        x = Integer.parseInt(tokens[1]) - 1;
                        t = taskList.get(x);
                        taskList.remove(x);
                        CommonFunctions.wrappedLinePrint(String.format("Okay, I'll remove this task from the list!\n    %s\n You still have %d tasks left!!",t, taskList.size()));
                        break;
                    case TODO:
                        t = new ToDo(input.substring(5));
                        // Fallthrough
                    case DEADLINE:
                        if (t == null) {
                            tokens = input.split(" /by ");
                            if (tokens.length != 2) {
                                throw new InvalidInputException(commandToken);
                            }
                            t = new Deadline(tokens[0].substring(9), tokens[1]);
                        }
                        // Fallthrough
                    case EVENT:
                        if (t == null) {
                            tokens = input.split(" /from | /to ");
                            if (tokens.length != 3) {
                                throw new InvalidInputException(commandToken);
                            }
                            t = new Event(tokens[0].substring(6), tokens[1], tokens[2]);
                        }
                        taskList.add(t);
                        CommonFunctions.addedPrompt(t, taskList);
                        Storage.save(t);
                        break;
                }

            } catch (JulieException e) { // The command is not recognised
                CommonFunctions.wrappedLinePrint(e.getMessage());
            }
        }
        CommonFunctions.wrappedLinePrint("Bye!! See you soon!!");
    }
}
