import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    void add(Task task) {
        this.tasks.add(task);
        this.ui.showAdditionToList(task, this.tasks.size());
    }

    void delete(int index) throws RizzlerException {
        if (this.tasks.isEmpty()) {
            throw new RizzlerException("No tasks to delete");
        }
        try {
            Task removedTask = this.tasks.remove(index);
            this.ui.showRemovalFromList(removedTask, this.tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new RizzlerException("Please put task number that is actually in the list");
        }
    }

    void list() {
        ui.showList(this.tasks);
    }

    ArrayList<Task> getListToSave() {
        return this.tasks;
    }

    void mark(int index) throws RizzlerException {
        if (this.tasks.isEmpty()) {
            throw new RizzlerException("No tasks to mark");
        }
        try {
            this.tasks.get(index).markAsDone();
            this.ui.showMarking(this.tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new RizzlerException("Please put task number that is actually in the list");
        }
    }

    void unmark(int index) throws RizzlerException {
        if (this.tasks.isEmpty()) {
            throw new RizzlerException("No tasks to unmark");
        }
        try {
            this.tasks.get(index).unmark();
            this.ui.showUnmarking(this.tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new RizzlerException("Please put task number that is actually in the list");
        }
    }
}
