public class Response {

    Command markCommand = new MarkCommand();
    Command deleteCommand = new DeleteCommand();
    Command addCommand = new AddCommand();
    private final static String LOGO = """
                ________________ __________  _________
                \\__    ___/  _  \\\\______   \\/   _____/
                  |    | /  /_\\  \\|       _/\\_____  \\
                  |    |/    |    \\    |   \\/        \\
                  |____|\\____|__  /____|_  /_______  /
                                \\/       \\/        \\/\s
                """.trim();
    private void formatResponse(String input) {

        String separator = '\n' + "-".repeat(Math.min(input.length() + 5, 110))+'\n';
        System.out.println(separator + input + separator);
    }

    public void generateResponse(String input, TaskList tasks) {
        if (input.contains("mark")) {
            try {
                formatResponse(markCommand.execute(input, tasks));
            } catch (TarsException e) {
                formatResponse(e.getMessage());
            }

        } else if (input.contains("delete")) {
            try {
                formatResponse(deleteCommand.execute(input, tasks));
            } catch (TarsException e) {
                formatResponse(e.getMessage());
            }

        } else {
            try {
                formatResponse(addCommand.execute(input, tasks));
            } catch (TarsException e) {
                formatResponse(e.getMessage());

            }
        }

    }

    public void showList(TaskList tasks) {
        formatResponse(tasks.toString());
    }
    public void intro() {
        String introMessage = "\nGreetings, human! I'm TARS, your slightly sarcastic yet highly capable companion.\nLet's get this chat started! Just remember, my humor setting is at 75%, so things might get a bit cheeky.";
        formatResponse(LOGO + introMessage);
    }
    public void outro() {
        String outputMessage = "Well, that's a wrap! If you need anything else, just holler.\nBut let’s be honest, you’re probably better off asking someone else.";
        formatResponse(outputMessage);
    }

}
