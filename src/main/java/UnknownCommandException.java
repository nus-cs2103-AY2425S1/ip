public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("I do not recognise that command."
                + "\nHere are the commands I recognise:"
                + "\nadd: add a new item to your list."
                + "\ncheck: check off an item to your list."
                + "\nuncheck: uncheck an item to your list."
                + "\ndelete: delete an item from your list"
                + "\nlist: print out your list."
                + "\nbye: bid me farewell."
        );
    }
}
