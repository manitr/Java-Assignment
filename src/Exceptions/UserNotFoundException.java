package Exceptions;

public class UserNotFoundException extends Exception {
    final String message = "User does not exist! Please enter a valid user name";

    @Override
    public String toString() {
        return "UserNotFound Exception: "  + message;
    }
}