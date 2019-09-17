package Exceptions;

public class IllegalDecrementValueException extends Throwable {
    final String message = "Decrement value must be non-positive and integral";

    @Override
    public String toString() {
        return "IllegalDecrementValueException: "  + message;
    }
}

