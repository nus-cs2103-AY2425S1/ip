
public class ListCommand extends Command {
    private TaskList taskList;

    @Override
    public void execute() {
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
