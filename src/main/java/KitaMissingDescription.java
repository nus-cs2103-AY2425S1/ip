public class KitaMissingDescription extends RuntimeException {
    @Override
    public String toString() {
        return "Your task is missing its description (E.g deadline <task> /by <deadline>) :c";
    }
}
