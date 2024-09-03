package Nave;

public class Ui {
    public void greet() {
        System.out.println(formatResponse("Hello! :) I'm Nave.Nave, your personal task management assistant.\n" +
                "What can I do for you today?"));
    }

    public void sayFarewell() {
        System.out.println(formatResponse("Goodbye :( Come visit me again"));
    }

    public void helpMessage() {
        System.out.println(formatResponse("""
            /help: shows all available commands
            list: shows all tasks
            todo [name]: adds a todo with associated name
            deadline [name] /by [date]: adds a deadline with associated name and date
            event [name] /from [date] /to [date]: adds an event with associated name,
                start date and end date
            bye: ends the Nave.Nave chatbot"""));
    }

    public void unsureMessage() {
        System.out.println(formatResponse("I'm not sure what you want me to do! try /help"));
    }

    public void showResponse(String response) {
        System.out.println(formatResponse(response));
    }

    public static String formatResponse(String input) {
        return "-----------------------------------------------------------------\n"
                + input + "\n"
                + "-----------------------------------------------------------------";
    }
}
