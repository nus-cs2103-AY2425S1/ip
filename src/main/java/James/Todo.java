package james;

class Todo extends Task{
    public Todo(String desc, Boolean mark) throws MissingDescriptionException{
        super(desc, mark);
    }

    @Override
    public String printTask() {
        return String.format("[T]%s", super.printTask());
    }

    @Override
    public String convertToFileFormat() {
        return String.format("T | %s", super.convertToFileFormat());
    }
}
