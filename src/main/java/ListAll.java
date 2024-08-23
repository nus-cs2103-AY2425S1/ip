import java.util.List;
public class ListAll {
    // take in all array list
    private List<Task> allTask;

    // description should be empty
    public ListAll(String description, List<Task> allTask) throws CommandFoundButInvalidException {
        if (!description.isEmpty()) {
            throw new InvalidSyntaxException("list");
        }

        this.allTask = allTask;
    }

    public String message() {
        String s1 = "Here are the tasks in your list:";
        for (int i = 0; i < allTask.size(); i++) {
            String index = String.format("%d", i + 1);
            String curr = "\n" + index + ". " + allTask.get(i).toString();
            s1 = s1.concat(curr);
        }
        return s1;
    }
}
