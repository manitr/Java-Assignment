package Exceptions;

public class InsufficientBalanceException extends Throwable {
    final String message = "User doesn't have enough balance to make the transaction.";

    @Override
    public String toString() {
        return "InsufficientBalanceException: "  + message;
    }
}
