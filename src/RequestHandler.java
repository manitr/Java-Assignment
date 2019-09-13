import Exceptions.InvalidHeightException;
import Exceptions.UserNotFoundException;
import TransactionHandlers.TransactionHandler1;
import TransactionHandlers.TransactionHandler2;
import javax.naming.OperationNotSupportedException;

public class RequestHandler implements Runnable {
    TransactionHandler1 dataStore1;
    TransactionHandler2 dataStore2;
    Thread thread;
    int option;

    RequestHandler(TransactionHandler1 store) {
        dataStore1 = store;
        option = 1;
        thread = new Thread(this);
        thread.start();
    }

    RequestHandler(TransactionHandler2 store) {
        dataStore2 = store;
        option = 2;
        thread = new Thread(this);
        thread.start();
    }

    RequestHandler(TransactionHandler1 store1, TransactionHandler2 store2) {
        dataStore1 = store1;
        dataStore2 = store2;
        option = 3;
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        switch (option) {
            case 1:
                try {
                    dataStore1.put("shubham", 5);
                    dataStore1.incrementBy("shubham", 5);
                    dataStore1.incrementBy("manit", 13);
                    dataStore1.incrementBy("manit", 2);
                    System.out.println(dataStore1.delete("shubham"));
                    System.out.println(dataStore1.get("shubham"));
                    dataStore1.get("karan");
                }
                catch (UserNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    dataStore2.put("Rohit");
                    System.out.println(dataStore2.getTallestUser());
                    dataStore2.put("Manit", 179);
                    System.out.println(dataStore2.getTallestUser());
                    dataStore2.put("Sahil", 178);
                    dataStore2.put("Yash", 181);
                    System.out.println(dataStore2.getTallestUser());
                    dataStore2.get("Sahil");
                }
                catch (InvalidHeightException e) {
                    e.printStackTrace();
                }
                catch (OperationNotSupportedException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    dataStore1.put("Karan", 5);
                    dataStore1.incrementBy("Karan", 5);
                    dataStore1.incrementBy("Nitish", 13);
                    dataStore2.put("Rohit");
                    System.out.println(dataStore2.getTallestUser());
                    dataStore2.put("Yash", 181);
                    dataStore1.put("Noor");
                    dataStore2.put("Sahil", 178);
                    System.out.println(dataStore2.getTallestUser());
                }
                catch (InvalidHeightException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public static void main(String[] args) {
        TransactionHandler1 handler1 = new TransactionHandler1("HashMap");
        TransactionHandler2  handler2 = new TransactionHandler2();
        TransactionHandler1 handler3 = new TransactionHandler1("HashMap");
        TransactionHandler2  handler4 = new TransactionHandler2();
        RequestHandler req1 = new RequestHandler(handler1);
        RequestHandler req2 = new RequestHandler(handler2);
        RequestHandler req3 = new RequestHandler(handler3, handler4);
    }
}
