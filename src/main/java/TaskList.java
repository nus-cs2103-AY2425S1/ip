import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TaskList {

    List<Task> cache;

    public TaskList() {
        this.cache = new ArrayList<>();
    }

    public TaskList(Storage storage) throws SpongebobException {
        this.cache = storage.load();
    }

    public void update(int index, Task task) throws IndexOutOfBoundsException{
        cache.set(index, task);
    }

    public void add(Task task) {
        this.cache.add(task);
    }

    public void delete(int index) {
        this.cache.remove(index);
    }

    public int size() {
        return this.cache.size();
    }

    @Override
    public String toString() {
        ListIterator<Task> iter = this.cache.listIterator();
        StringBuilder builder = new StringBuilder();

        while (iter.hasNext()) {
            Task cur = iter.next();
            builder.append((iter.previousIndex() + 1) + "." + cur + "\n");
        }

        return builder.toString();
    }
}
