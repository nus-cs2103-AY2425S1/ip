import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bob {
    private static String formatMessage(String msg) {
        return "___________________________________________________________\n"
                + msg
                + "\n___________________________________________________________\n";
    }

    private static String parseInput(String input, ChatBot bot) {
        if (Objects.equals(input, "")) {
            return "";
        }

        String command = input.split(" ")[0];
        try {
            switch (command) {
                case "bye":
                    return bot.exit();
                case "list":
                    return bot.list();
            case "mark": {
                    String[] inputWords = input.split(" ");
                    if (inputWords.length != 2) {
                        throw new InvalidInputException("Please specify which task to mark.");
                    }
                    int itemNum = Integer.parseInt(inputWords[1]);
                    return bot.mark(true, itemNum);
                }
                case "unmark": {
                    String[] inputWords = input.split(" ");
                    if (inputWords.length != 2) {
                        throw new InvalidInputException("Please specify which task to unmark.");
                    }
                    int itemNum = Integer.parseInt(inputWords[1]);
                    return bot.mark(false, itemNum);
                }
                case "todo":
                    return bot.todo(input.substring(5));
                case "deadline": {
                    Matcher matcher = Pattern.compile("^deadline (.*) /by (.*)$").matcher(input);
                    if (matcher.find()) {
                        return bot.deadline(matcher.group(1), matcher.group(2));
                    } else {
                        throw new InvalidInputException("Please specify the deadline using \"/by\".");
                    }
                }
                case "event": {
                    Matcher matcher = Pattern.compile("^event (.*) /from (.*) /to (.*)$").matcher(input);
                    if (matcher.find()) {
                        return bot.event(matcher.group(1), matcher.group(2), matcher.group(3));
                    } else {
                        throw new InvalidInputException("Please specify the timing using \"/from\" and \"/to\".");
                    }
                }
                default:
                    throw new InvalidInputException("I'm sorry, I did not understand your message.");
            }
        } catch (InvalidInputException e) {
            return e.toString();
        }
    }

    public static void main(String[] args) {
        ChatBot bot = new ChatBot();
        System.out.print(formatMessage(bot.greet()));

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String input = in.nextLine();
            String output = parseInput(input, bot);

            System.out.print(formatMessage(output));
            if (input.equals("bye")) {
                break;
            }
        }
    }
}
