import Exceptions.*;
import TransactionHandlers.UserBalanceHandler;
import TransactionHandlers.MaxHeightUserHandler;
import javax.naming.OperationNotSupportedException;

public class RequestHandler implements Runnable {
    UserBalanceHandler dataStore1;
    MaxHeightUserHandler dataStore2;
    Thread thread;
    private int option;

    RequestHandler(UserBalanceHandler store) {
        dataStore1 = store;
        option = getOption();
        thread = new Thread(this);
        thread.start();
    }

    RequestHandler(MaxHeightUserHandler store) {
        dataStore2 = store;
        option = getOption();
        thread = new Thread(this);
        thread.start();
    }

    RequestHandler(UserBalanceHandler store1, MaxHeightUserHandler store2) {
        dataStore1 = store1;
        dataStore2 = store2;
        option = getOption();
        thread = new Thread(this);
        thread.start();
    }

    private int getOption() {
        if(this.dataStore1 != null && this.dataStore2 == null) {
            return 1;
        }
        else if(this.dataStore1 == null && this.dataStore2 != null) {
            return 2;
        }
        return 3;
    }

    @Override
    public void run() {
        switch (option) {
            case 1:
                try {
                    dataStore1.put("shubham", 5);
                    dataStore1.incrementBy("shubham", 5);
                    dataStore1.incrementBy("manit", 13);
                    System.out.println(dataStore1.decrementBy("manit", 2));
                    System.out.println(dataStore1.delete("shubham"));
                    System.out.println(dataStore1.get("shubham"));
//                    dataStore1.get("karan");
                }
                catch (UserNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalIncrementValueException e) {
                    e.printStackTrace();
                }
                catch (IllegalDecrementValueException e) {
                    e.printStackTrace();
                }
                catch (NegativeInitialBalanceException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    dataStore2.put("Rohit");
                    System.out.println(dataStore2.getTallestUser());
                    dataStore2.put(new String("kunal"), 179);
                    System.out.println(dataStore2.getTallestUser());
                    dataStore2.put("Sahil", Integer.valueOf("178"));
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
                catch (EmptyNameFieldException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    dataStore1.put("Karan", 5);
                    dataStore1.decrementBy("Karan", 1);
                    dataStore1.incrementBy("Nitish", -13);
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
                catch (EmptyNameFieldException e) {
                    e.printStackTrace();
                }
                catch (IllegalDecrementValueException e) {
                    e.printStackTrace();
                }
                catch (IllegalIncrementValueException e) {
                    e.printStackTrace();
                }
                catch (UserNotFoundException e) {
                    e.printStackTrace();
                }
                catch (NegativeInitialBalanceException e) {
                    e.printStackTrace();
                }
                break;
            default: {
                try {
                    throw new OperationNotSupportedException();
                }
                catch (OperationNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        UserBalanceHandler handler1 = new UserBalanceHandler("HashMap");
        MaxHeightUserHandler handler2 = new MaxHeightUserHandler();
        UserBalanceHandler handler3 = new UserBalanceHandler("HashMap");
        MaxHeightUserHandler handler4 = new MaxHeightUserHandler();
        RequestHandler req1 = new RequestHandler(handler1);
        RequestHandler req2 = new RequestHandler(handler2);
        RequestHandler req3 = new RequestHandler(handler3, handler4);
    }
}
