class Todo extends Task {
    public static String format = "todo <description>";

    public Todo (String description) {
        super(description);
    }
  
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
