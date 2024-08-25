package sumode.util;

import sumode.exception.*;
import sumode.task.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SumoTaskList {

    private final List<Task> tasks;
    private final Storage storage;
    private Ui ui;

    public SumoTaskList(Storage storage, Ui ui) throws IOException {
        //initialising
        this.tasks = new ArrayList<>();
        this.storage = storage;
        this.ui = ui;

        //adding tasks based on data
        String[] datas = storage.load();
        for (int i = 0; i < datas.length; i++) {
            try {
                tasks.add(Task.createFromData(datas[i]));
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                ui.corruptedSaveFile(i+1);
            }
        }


    }

    public SumoTaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.storage = null;
        this.ui = ui;
    }

    public boolean execute(Command command, String item)
            throws NonExistentTaskException, UnknownCommandException,
            WrongSyntaxForCommandException, AlreadyMarkedException,
            AlreadyUnmarkedException {
        switch(command) {
            case BYE:
            case EXIT:  // added this to allow flexibility though not required by qn
                return true;
            case LIST:
                this.ui.printTask(this.tasks);
                break;
            case FIND:
                List<Task> filtered = this.tasks.stream()
                        .filter(task -> task.toString().contains(item))
                        .toList();
                this.ui.printTask(filtered);
                break;
            case MARK:
            {
                int index;
                try {
                    index = Integer.parseInt(item);
                } catch (IllegalArgumentException e) {
                    throw new WrongSyntaxForCommandException(command);
                }

                if (index > tasks.size() || index <= 0) {
                    throw new NonExistentTaskException(index);
                }
                tasks.get(index - 1).mark();
                ui.mark(tasks.get(index - 1));

            }
            if (storage != null) {
                storage.save(this.tasks);
            }
            break;
            case UNMARK:
            {
                int index;
                try {
                    index = Integer.parseInt(item);
                } catch (IllegalArgumentException e) {
                    throw new WrongSyntaxForCommandException(command);
                }

                if (index > tasks.size() || index <= 0) {
                    throw new NonExistentTaskException(index);
                }
                tasks.get(index - 1).unmark();
                ui.unmark(tasks.get(index - 1));
            }
            if (storage != null) {
                storage.save(this.tasks);
            }

            break;
            case DELETE:
            {
                int index;
                try {
                    index = Integer.parseInt(item);
                } catch (IllegalArgumentException e) {
                    throw new WrongSyntaxForCommandException(command);
                }
                if (index > tasks.size() || index <= 0) {
                    throw new NonExistentTaskException(index);
                }
                ui.removeTask(tasks.get(index - 1), tasks.size() - 1);
                tasks.remove(index - 1);
            }
            if (storage != null) {
                storage.save(this.tasks);
            }
            break;
            case TODO:
            case DEADLINE:
            case EVENT:
                Task newlyAdded = Task.of(command, item);
                tasks.add(newlyAdded);  // used factory method to be more neat and OOP
                ui.addTask(newlyAdded, tasks.size());
                if (storage != null) {
                    storage.save(this.tasks);
                }
                break;
            default:
                throw new UnknownCommandException(command);
        }
        return false;
    }
}
