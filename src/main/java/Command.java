public class Command {
    private TaskList tasklist;
    private Ui ui;
    private FileStorage storage;

    public Command(TaskList tasklist, Ui ui, FileStorage storage) {
        this.tasklist = tasklist;
        this.ui = ui;
        this.storage = storage;
    }

//    public void execute(TaskList t, Ui ui, FileStorage s) {
//        tasklist.addTask();
//        ui.showSuccessMsg(t.size());
//        s.writeToTxt(t.toString());
//    }

//    public boolean isExit() {
//        return
//    }

}
