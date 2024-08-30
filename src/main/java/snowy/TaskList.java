package snowy;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<String> lines) {
        for (String line : lines) {
            initializeTask(line);
        }
    }

    public TaskList(){

    }

    private void initializeTask(String description) {
        String status;
        String name;

        String[] dataArray = description.split("[|]");
        String type = dataArray[0];
        switch (type) {
            case "T":
                status = dataArray[1];
                name = dataArray[2];
                Task newToDo = new ToDo(name);
                tasks.add(newToDo);
                if (status.equals("1")) {
                    newToDo.markComplete();
                }
                break;

            case "D":
                status = dataArray[1];
                name = dataArray[2];
                String dueDate = dataArray[3];
                try {
                    Task newDateline = new Deadline(name, dueDate);
                    tasks.add(newDateline);
                    if (status.equals("1")) {
                        newDateline.markComplete();
                    }
                } catch (SnowyException ignored) {

                }


                break;

            case "E":
                status = dataArray[1];
                name = dataArray[2];
                String fromDate = dataArray[3];
                String toDate = dataArray[4];
                try {
                    Task newEvent = new Event(name, fromDate, toDate);
                    tasks.add(newEvent);
                    if (status.equals("1")) {
                        newEvent.markComplete();
                    }
                } catch (SnowyException ignored) {

                }

                break;

            default:
                System.out.println("Error: task type not found");
        }
    }

    public  void addToDo(String description) throws SnowyException{
        if (description.isEmpty()) {
            throw new SnowyException("Invalid input for Todo");
        }
        Task newTask = new ToDo(description);
        tasks.add(newTask);
        System.out.println("New todo task added:\n" + newTask);
    }

    public void addDeadline(String description) throws SnowyException{
        if (description.isEmpty()) {
            throw new SnowyException("Invalid input for Deadline");
        }

        int byIndex = description.indexOf("/by ");

        if (byIndex == -1) {
            throw new SnowyException("Invalid input for Deadline");
        }
        String deadlineName = description.substring(0, byIndex).trim();
        String date = description.substring(byIndex + 4);

        if (deadlineName.isEmpty()) {
            throw new SnowyException("Invalid input for Deadline");
        }

        if (date.isEmpty()) {
            throw new SnowyException("Invalid input for Deadline");
        }
        Task newTask = new Deadline(deadlineName, date);
        tasks.add(newTask);
        System.out.println("New Deadline task added:\n" + newTask);
    }

    public void addEvent(String description) throws SnowyException{
        if (description.isEmpty()) {
            throw new SnowyException("Invalid input for Event");
        }
        int fromIndex = description.indexOf("/from ");
        int toIndex = description.indexOf("/to ");

        if (toIndex == -1 || fromIndex == -1) {
            throw new SnowyException("Invalid input for Event");
        }

        String eventName = description.substring(0, fromIndex);
        String fromDate = description.substring(fromIndex + 6, toIndex).trim();
        String toDate = description.substring(toIndex + 4);
        if (eventName.isEmpty()) {
            throw new SnowyException("Invalid input for Event");
        }
        if (fromDate.isEmpty()) {
            throw new SnowyException("Invalid input for Event");
        }
        if (toDate.isEmpty()) {
            throw new SnowyException("Invalid input for Event");
        }
        Task newTask = new Event(eventName, fromDate, toDate);
        tasks.add(newTask);
        System.out.println("New Event task added:\n " + newTask);
    }

    public Task deleteTask(int index) throws SnowyException {
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new SnowyException("Invalid index input");
        }
    }

    public Task markTask(int index) throws SnowyException {
        try {
            tasks.get(index - 1).markComplete();
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new SnowyException("Invalid index input");
        }
    }

    public Task unmarkTask(int index) throws SnowyException {
        try {
            tasks.get(index - 1).markIncomplete();
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new SnowyException("Invalid index input");
        }
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String temp = String.format("%d. %s\n", i + 1, tasks.get(i));
            text.append(temp);
        }
        return text.toString();
    }

    public String toSaveString() {
        StringBuilder text = new StringBuilder();
        for (Task task : tasks) {
            text.append(task.toFileStorage()).append("\n");
        }
        return text.toString();
    }

}
