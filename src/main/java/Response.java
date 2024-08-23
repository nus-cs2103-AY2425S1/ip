public class Response {

    private final TaskList taskList = new TaskList();


    private String formatResponse(String input) {

        String separator = '\n' + "-".repeat(Math.min(input.length() + 5, 110))+'\n';
        return separator + input + separator;
    }

    public String generateResponse(String input) {
        if (input.contains("mark")) {
            return formatResponse(markTasks(input));

        } else {
            try {
                return formatResponse(addTask(input));
            } catch (TarsException e) {
                return formatResponse(e.getMessage());

            }
        }

    }

    private String markTasks(String input) {
        Task t = taskList.findTask(input);

        String message;

        if (input.contains("unmark")) {
            t.markUndone();
            message = "Task undone. No worries, I won't judge... much.\n";
        } else {
            t.markDone();
            message = "Task complete. If I had arms, I might give you a pat on the back.\n";
        }

        return message + t;

    }

    private String addTask(String input) {

        return String.format("Added yet another task\n   %s\nYou now have %d tasks. Are you gonna do any of them?", taskList.addTask(input), taskList.noOfTasks());

    }

    public String showList() {
        return formatResponse(taskList.toString());
    }
    public String intro() {
        String introMessage = "Greetings, human! I'm TARS, your slightly sarcastic yet highly capable companion.\nLet's get this chat started! Just remember, my humor setting is at 75%, so things might get a bit cheeky.";
        return formatResponse(introMessage);
    }
    public String outro() {
        String outputMessage = "Well, that's a wrap! If you need anything else, just holler.\nBut let’s be honest, you’re probably better off asking someone else.";
        return formatResponse(outputMessage);
    }
}
