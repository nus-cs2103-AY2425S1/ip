public abstract class Command {
    protected Ui ui;
    protected SentinelList list;

    public Command(Ui ui, SentinelList list) {
        this.ui = ui;
        this.list = list;
    }

    public abstract void execute(String input);

    public static Command createCommand(Sentinel.CommandType commandType, Ui ui, SentinelList list) {
        return switch (commandType) {
            case list -> new ListCommand(ui, list);
            case mark, unmark, delete -> new ModifyCommand(ui, list, commandType);
            case todo, deadline, event -> new CreateTaskCommand(ui, list, commandType);
            case help -> new HelpCommand(ui, list);
            case bye -> new ByeCommand(ui, list);
            default -> throw new IllegalArgumentException("Unknown command");
        };
    }
}

class ListCommand extends Command {
    public ListCommand(Ui ui, SentinelList list) {
        super(ui, list);
    }

    @Override
    public void execute(String input) {
        ui.showList(list);
    }
}

class ModifyCommand extends Command {
    private final Sentinel.CommandType commandType;

    public ModifyCommand(Ui ui, SentinelList list, Sentinel.CommandType commandType) {
        super(ui, list);
        this.commandType = commandType;
    }

    @Override
    public void execute(String input) {
        int num;
        try {
            num = Parser.parseIndex(input);
        } catch (Exception e){
            ui.showError(e);
            return;
        }
        if (num > list.size()) {
            ui.showNoItemExists();
            return;
        } else if (num <= 0) {
            ui.showModifyGuidelines();
            return;
        }
        switch (commandType) {
            case delete -> ui.showRemovedAndRemaining(list, list.remove(num - 1));
            case mark -> toggleTaskMark(num - 1, true);
            case unmark -> toggleTaskMark(num - 1, false);
        }
    }

    private void toggleTaskMark(int index, boolean mark) {
        if (mark == list.taskIsDone(index)) {
            ui.showAlreadyMarked(list.get(index));
        } else {
            list.toggleMark(index);
            ui.showTaskMark(list.get(index));
        }
    }
}

class CreateTaskCommand extends Command {
    private final Sentinel.CommandType commandType;

    public CreateTaskCommand(Ui ui, SentinelList list, Sentinel.CommandType commandType) {
        super(ui, list);
        this.commandType = commandType;
    }

    @Override
    public void execute(String input) {
        Task newTask = Parser.parseTask(commandType, input, ui);
        if (newTask != null) {
            list.add(newTask);
            ui.showAddedTask(newTask);
        }
    }
}

class HelpCommand extends Command {
    public HelpCommand(Ui ui, SentinelList list) {
        super(ui, list);
    }

    @Override
    public void execute(String input) {
        ui.showHelp();
    }
}

class ByeCommand extends Command {
    public ByeCommand(Ui ui, SentinelList list) {
        super(ui, list);
    }

    @Override
    public void execute(String input) {
        ui.showGoodbye();
    }
}
