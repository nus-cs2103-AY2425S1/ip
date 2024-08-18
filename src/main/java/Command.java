// solution below inspired by https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
public class Command {
    private enum CommandsEnum {
        list, blah, bye
    }
    private CommandsEnum command;
    private static final Message message = new Message("");

    public Command(String command) {
        switch (command) {
            case "list":
                this.command = CommandsEnum.list;
                break;
            case "blah":
                this.command = CommandsEnum.blah;
                break;
            case "bye":
                this.command = CommandsEnum.bye;
                break;
            default:
                break;
        }
    }

    public void action() {
        switch (command) {
            case list:
                Command.message.text("list").print();
                break;
            case blah:
                Command.message.text("blah").print();
                break;
            case bye:
                Command.message.text("Bye. Hope to see you again soon!").print();
                System.exit(0);
                break;
        }
    }
}
