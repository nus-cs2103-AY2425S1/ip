package Mentos;

import Mentos.MentosException.MentosException;
import Mentos.components.ActionTaskIndexTuple;
import Mentos.components.Parser;
import Mentos.components.Storage;
import Mentos.components.Ui;
import Mentos.task.TaskList;

public class Mentos
{
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Mentos(String FILE_PATH){
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();
        try{
            this.tasks = new TaskList(storage.loadTasksFromFile());
        } catch (MentosException error){
            System.out.println(error);
            tasks = new TaskList();
        }
    }

    public boolean checkIndex(int index) throws MentosException {
        if (index > tasks.size() || index == 0){
            throw new MentosException("No Such Tasks!");
        }
        return true;
    }

    public void markTask(int index) throws MentosException {
        if (checkIndex(index)){
            this.tasks.get(index-1).markAsDone();
        }
    }
    public void unmarkTask(int index) throws MentosException {
        if (checkIndex(index)){
            this.tasks.get(index-1).markAsNotDone();
        }
    }
    public void deleteTask(int index) throws MentosException{
        if (checkIndex(index)){
            this.tasks.remove(index);
        }
    }

    public void run(){
        ui.startConversation();
        boolean isExit = false;
        Parser parser = new Parser();
        while (!isExit){
            try{
                String command = ui.readCommand();
                if (command.equals("bye")){
                    ui.endConversation();
                    break;
                }
                ui.print_line();
                ActionTaskIndexTuple actionTaskIndexTuple = parser.taskHandler(command);
                if (actionTaskIndexTuple == null){
                    continue;
                }
                String action = actionTaskIndexTuple.getAction();
                if (action.equals("list")){
                    ui.displayTasks(this.tasks);
                } else if (action.equals("mark")) {
                    markTask(actionTaskIndexTuple.getIndex());
                    ui.markEvent(this.tasks.get(actionTaskIndexTuple.getIndex()-1));
                } else if (action.equals("unmark")){
                    unmarkTask(actionTaskIndexTuple.getIndex());
                    ui.unmarkEvent(this.tasks.get(actionTaskIndexTuple.getIndex()-1));
                } else if (action.equals("delete")){
                    deleteTask(actionTaskIndexTuple.getIndex());
                    ui.deleteEvent(actionTaskIndexTuple.getTask(), this.tasks.size());
                } else{
                    this.tasks.add(actionTaskIndexTuple.getTask());
                    ui.print_event(action,actionTaskIndexTuple.getTask(),this.tasks.size());
                }
                storage.saveTasksToFile(this.tasks);

            } catch (MentosException err){
                System.out.println(err);
            }
        }

    }

    public static void main(String[] args) {
        new Mentos("/data/Mentos.txt").run();
    }























}

