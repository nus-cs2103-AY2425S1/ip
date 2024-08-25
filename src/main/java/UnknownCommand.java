public class UnknownCommand extends Exception{
    UnknownCommand() {
        super(" NAH!!! I'm sorry, but I don't know what that means.\n" +
                " Please give me valid command. eg: mark, unmark, list, ...\n");
    }
}
