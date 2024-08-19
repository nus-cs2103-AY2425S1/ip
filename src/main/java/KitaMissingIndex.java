public class KitaMissingIndex extends RuntimeException {
    @Override
    public String toString() {
        return "Please specify what index this action should be carried out on (E.g unmark <index>) :c";
    }
}
