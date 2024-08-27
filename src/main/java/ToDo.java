public class ToDo extends Task {

  // todo borrow book
  public ToDo (String task) {
    super(task);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}