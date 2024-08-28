public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("I do not recognise that command."
                + "\nHere are the commands I recognise:"
                + "\nadd: add a new item to your list of tasks."
                + "\ncheck: check off an item to your list of tasks."
                + "\nuncheck: uncheck an item to your list of tasks."
                + "\ndelete: delete an item from your list of tasks"
                + "\nlist: print out your list of tasks."
                + "\ndate: print out deadlines and events that occur on a specific date."
                + "\nbye: bid me farewell."
        );
    }
}
