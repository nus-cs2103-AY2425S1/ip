import java.util.Scanner;
public class Bob {
    private static String formatMessage(String msg) {
        return "___________________________________________________________\n"
                + msg
                + "\n___________________________________________________________\n";
    }

    private static String parseInput(String input, ChatBot bot) {
        String[] inputWords = input.split(" ");
        if (inputWords.length == 0) {
            return "";
        }

        String command = input.split(" ")[0];
        switch (command) {
            case "bye":
                return bot.exit();
            case "list":
                return bot.list();
            case "mark": {
                try {
                    if (inputWords.length != 2) {
                        throw new InvalidInputException("Task number not found.");
                    }
                    int itemNum = Integer.parseInt(inputWords[1]);
                    return bot.mark(true, itemNum);
                } catch (InvalidInputException e) {
                    return e.toString();
                }
            }
            case "unmark": {
                try {
                    if (inputWords.length != 2) {
                        throw new InvalidInputException("Task number not found.");
                    }
                    int itemNum = Integer.parseInt(inputWords[1]);
                    return bot.mark(false, itemNum);
                } catch (InvalidInputException e) {
                    return e.toString();
                }
            }
            default:
                return bot.add(input);
        }
    }

    public static void main(String[] args) {
        ChatBot bot = new ChatBot();
        System.out.println(formatMessage(bot.greet()));

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String input = in.nextLine();
            String output = parseInput(input, bot);

            System.out.println(formatMessage(output));
            if (input.equals("bye")) {
                break;
            }
        }
    }
}
