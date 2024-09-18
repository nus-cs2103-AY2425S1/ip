package tasks;

public class ToDo extends Task {
    //private String input;

    public ToDo(String taskName) {
        super(taskName);
    }

    public static String extractName(String input) {
        String[] strings = input.split("\\s+", 2);
        //
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
        //
        return strings[1];
    }
    @Override
    public String displayTask() {
        String cross = super.displayDone();
        String exclamationMark = super.displayPriority();
        return exclamationMark + "[T]" + cross + " " + super.getInput() + "\n";
    }
}
