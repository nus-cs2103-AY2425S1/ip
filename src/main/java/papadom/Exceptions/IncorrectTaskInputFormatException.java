package papadom.Exceptions;

public class IncorrectTaskInputFormatException extends Exception {
    public IncorrectTaskInputFormatException () {
        super(" Please enter the correct format!\n" +
                "  For todo tasks: todo [task]\n" +
                "  For deadline tasks: deadline [task] /by yyyy-mm-dd OR yyyy-mm-dd hh-mm\n" +
                "  For event tasks: event [task] /from [date & time] /to [date & time]\n" +
                "  To find specific events with keyword: find [keyword]");
    }
}
