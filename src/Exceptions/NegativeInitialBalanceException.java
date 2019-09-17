package Exceptions;

public class NegativeInitialBalanceException extends Throwable {
    final String message = "Initial balance must be non-negative and integral";

    @Override
    public String toString() {
        return "NegativeInitialBalanceException: "  + message;
    }
}
