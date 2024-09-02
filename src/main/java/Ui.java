class Ui {
    private TaskList taskList;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    public void printWelcomeMessage() {
        String hi = "Hello! I'm Foo\n" +
                "What can I do for you?";
        System.out.println(Parser.addHorizontalLinesAndIndentation(hi));
    }

    public void printGoodbyeMessage() {
        System.out.println(Parser
            .addHorizontalLinesAndIndentation("Bye. Hope to see you again soon!"));
    }

    public void displayList() {
        String lString = "";
        int index = 1;
        for (Task task : taskList) {
            lString += String.valueOf(index) + "."  + task.toString();
            if (index == taskList.size()) {
                break;
            }
            lString += "\n";
            index++;
        }
        lString = Parser.addHorizontalLinesAndIndentation(lString);
        System.out.println(lString);
    }


}