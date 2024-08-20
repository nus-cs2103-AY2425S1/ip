interface Commands {
    void run(Sentinel sentinel, String[] args);
}

class ListTasks implements Commands {
    @Override
    public void run(Sentinel sentinel, String[] args) {
        sentinel.outputTaskList();
    }
}

class markDone implements Commands {
    @Override
    public void run(Sentinel sentinel, String[] args) {
        if (args.length < 2) {
            sentinel.say("You didn't put in a valid number!");
            return;
        }

        try {
            int taskNumber = Integer.parseInt(args[1]);
            sentinel.markDone(taskNumber);
        } catch (NumberFormatException e) {
            sentinel.say("Invalid number!");
        }
    }
}

class markUndone implements Commands {
    @Override
    public void run(Sentinel sentinel, String[] args) {
        if (args.length < 2) {
            sentinel.say("You didn't put in a valid number!");
            return;
        }

        try {
            int taskNumber = Integer.parseInt(args[1]);
            sentinel.markUndone(taskNumber);
        } catch (NumberFormatException e) {
            sentinel.say("Invalid number!");
        }
    }
}


