class AddCommand extends Command {
    private Task.TaskType type;
    private String description;

    public AddCommand(Task.TaskType type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws StreamsException {
        if (description.isEmpty()) {
            throw new StreamsException("the description of a task cannot be empty");
        }

        Task newTask;
        switch (type) {
            case TODO:
                newTask = new ToDoTask(description);
                break;
            case DEADLINE:
                String[] deadlineParts = description.split(" /by ");
                if (deadlineParts.length != 2) {
                    throw new StreamsException("the format for deadlines is 'deadline [description] /by [date]'");
                }
                newTask = new DeadlineTask(deadlineParts[0], deadlineParts[1]);
                break;
            case EVENT:
                String[] eventParts = description.split(" /from ");
                if (eventParts.length != 2) {
                    throw new StreamsException("the format for events is 'event [description] /from [date] /to [date]'");
                }
                String[] timeParts = eventParts[1].split(" /to ");
                if (timeParts.length != 2) {
                    throw new StreamsException("the format for events is 'event [description] /from [date] /to [date]'");
                }
                newTask = new EventTask(eventParts[0], timeParts[0], timeParts[1]);
                break;
            default:
                throw new StreamsException("unknown task type");
        }

        tasks.addTask(newTask);
        storage.save(tasks.getTasks());
        return "added " + type.toString().toLowerCase() + ": " + newTask;
    }
}