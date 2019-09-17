package Exceptions;

public class IllegalIncrementValueException extends Throwable {
    final String message = "Increment value must be non-negative and integral";

    @Override
    public String toString() {
        return "IllegalIncrementValueException: "  + message;
    }
}
