package ip.derrick ;
public class Parser {

    public CommandHandler returnCommand(String input) {
        String instructions = input.split(" ")[0];
        return new CommandHandler(Commands.fromString(instructions));
    }

}
