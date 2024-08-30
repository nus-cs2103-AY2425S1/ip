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

    /**
     * Checks if the given task index is valid.
     *
     * This method verifies whether the provided index is within the valid range of
     * task indices in the task list. If the index is greater than the size of the task list
     * or is zero, it throws a {@code MentosException} indicating that no such task exists.
     *
     * @param index The index of the task to check.
     * @return {@code true} if the index is valid.
     * @throws MentosException if the index is out of bounds or zero.
     */
    public boolean checkIndex(int index) throws MentosException {
        if (index > tasks.size() || index == 0){
            throw new MentosException("No Such Tasks!");
        }
        return true;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * This method checks if the given index is valid and then marks the task at
     * that index as completed. The index provided is 1-based, so the method
     * internally adjusts for zero-based indexing.
     *
     * @param index The 1-based index of the task to mark as done.
     * @throws MentosException if the index is invalid.
     */
    public void markTask(int index) throws MentosException {
        if (checkIndex(index)){
            this.tasks.get(index-1).markAsDone();
        }
    }

    /**
     * Unmarks the task at the specified index as not done.
     *
     * This method checks if the given index is valid and then marks the task at
     * that index as not completed. The index provided is 1-based, so the method
     * internally adjusts for zero-based indexing.
     *
     * @param index The 1-based index of the task to unmark as not done.
     * @throws MentosException if the index is invalid.
     */
    public void unmarkTask(int index) throws MentosException {
        if (checkIndex(index)){
            this.tasks.get(index-1).markAsNotDone();
        }
    }

    /**
     * Deletes the task at the specified index.
     *
     * This method checks if the given index is valid and then removes the task
     * at that index from the task list. The index provided is 1-based, so the method
     * internally adjusts for zero-based indexing.
     *
     * @param index The 1-based index of the task to delete.
     * @throws MentosException if the index is invalid.
     */
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

