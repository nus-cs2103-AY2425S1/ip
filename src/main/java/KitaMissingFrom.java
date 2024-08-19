public class KitaMissingFrom extends RuntimeException {
    @Override
    public String toString() {
        return "Your task is missing the '/from <what date>' field :c";
    }
}
