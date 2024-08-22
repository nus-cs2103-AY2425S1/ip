public class ListOfTask {
    private String[] tasks;
    private int index;

    public ListOfTask() {
        this.tasks = new String[100];
        this.index = 0;
    }

    public String addTask(String t) {
        this.tasks[index] = t;
        this.index++;
        return "     ____________________________________________________________ \n" +
                "     " + "added: " + t + "\n" +
                "     ____________________________________________________________ \n";
    }

    public String printList() {
        String output = "     ____________________________________________________________ \n";
        for (int i = 0; i < this.index; i++) {
            int label = i + 1;
            output += "     " + label + ". " + this.tasks[i] + "\n";
        }
        return output + "     ____________________________________________________________ \n";
    }
}
