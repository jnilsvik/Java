package Classes;

public class SimpleCalculator {

    public float calculate(float n1, float n2, String operator) {
        float result;

        switch (operator) {
            case "+":
                result = n1 + n2;
                break;
            case "-":
                result = n1 - n2;
                break;
            case "*":
                result = n1 * n2;
                break;
            case "%":
                result = n1 % n2;
                break;
            default:
                result = n1 / n2;
                break;
        }
        return result;
    }
}