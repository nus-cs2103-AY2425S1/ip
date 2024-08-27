package Kita.Exceptions;

public class KitaMissingFrom extends KitaError {
    @Override
    public String toString() {
        return "Your task is missing the '/from <what date>' field :c";
    }
}
