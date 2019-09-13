import java.util.HashMap;
import java.util.Scanner;

public class TransactionHandler{
    private HashMap<String , Integer> database = new HashMap<String , Integer>();
    int selectOption;
    static int userCount = 0;

    TransactionHandler(int option){
        selectOption = option;
    }

    static int getUserCount() {
        return userCount++;
    }

    synchronized boolean addUser(String name) {
        return addUser(name, 0);
    }

    synchronized boolean addUser(String name, int amount) {
        if(database.containsKey(name)) {
            return false;
        }
        else {
            database.put(name, amount);
            userCount++;
        }
    }

    synchronized int getUserAmmount(String name) throws UserNotFoundException{
        if(database.containsKey(name)){
            return database.get(key);
        }
        else {
            throw new UserNotFoundException();
        }
    }

    synchronized int incrementBy(String key, int value) {
        System.out.println(Thread.currentThread().getName() + database);
        int sum = value;
        if (database.containsKey(key)) {
            sum += database.get(key);
            database.put(key, sum);
        } else {
            database.put(key, sum);
            userCount++;
        }
        return database.get(key);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter option: 1, 2 or 3");
        int option = s.nextInt();
        try {
            if(option < 1 || option > 3){
                throw new InvalidOptionException();
            }
        } catch (InvalidOptionException e){
            while (option < 1 || option > 3) {
                System.out.println(e.toString());
                System.out.println("Please enter a valid option");
                option = s.nextInt();
            }
        }
        TransactionHandler handler = new TransactionHandler(option);
        RequestHandler reqHandler = new RequestHandler(handler);
        //delay
        int a = 1, MOD = 999999999;
        for(int i = 0; i < 999999999; i++){
            a = a%MOD;
        }
        //final database state
        System.out.println("database " + handler.database);
    }
}


class RequestHandler implements Runnable{
    TransactionHandler Store;
    Thread thread;
    RequestHandler(TransactionHandler store) {
        Store = store;
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        switch (Store.selectOption){
            case 1: {
                Store.incrementBy("shubham", 5);
                Store.incrementBy("manit", 13);
                Store.incrementBy("karan", 21);
                Store.incrementBy("shubham", 2);
                break;
            }
            case 2: {
                Store.incrementBy("nitish", 13);
                Store.incrementBy("manit", 24);
                Store.incrementBy("harsh", 2);
                Store.incrementBy("noor", 6);
                Store.incrementBy("karan", 15);
                break;
            }
            case 3: {
                Store.incrementBy("shubham", 9);
                Store.incrementBy("noor", 18);
                Store.incrementBy("nitish", 5);
                Store.incrementBy("harsh", 16);
                break;
            }
            default: {
                System.out.println("Invalid option");
                break;
            }
        }
    }
}

class InvalidOptionException extends Exception {
    final String message = "Option should be one of 1, 2 or 3";

    @Override
    public String toString() {
        return "Invalid option Exception: "  + message;
    }
}

class UserNotFoundException extends Exception {
    final String message = "User does not exist! Please enter a valid user name";

    @Override
    public String toString() {
        return "UserNotFound Exception: "  + message;
    }
}