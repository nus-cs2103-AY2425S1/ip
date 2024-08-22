public class Response {

    private final TaskList taskList = new TaskList();


    private String formatResponse(String input) {
        String separator = '\n' + "-".repeat(input.length() + 3)+'\n';
        return separator + input + separator;
    }

    public String generateResponse(String input) {
        taskList.addTask(input);
        return formatResponse(input);
    }

    public String showList() {
        return formatResponse(taskList.toString());
    }
    public String intro() {
        String introMessage = "Greetings, human! I'm TARS, your slightly sarcastic yet highly capable companion.\nLet's get this chat started! Just remember, my humor setting is at 75%, so things might get a bit cheeky.";
        return formatResponse(introMessage);
    }
    public String outro() {
        String outputMessage = "Well, that's a wrap! If you need anything else, just holler. But let’s be honest, you’re probably better off asking someone else.";
        return formatResponse(outputMessage);
    }
}
