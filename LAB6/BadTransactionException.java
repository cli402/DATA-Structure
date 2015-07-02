

public class BadTransactionException extends Exception {
public int amount;
public BadTransactionException(int amount){
	super("Invalid Transaction amount"+ amount);
	this.amount=amount;
}
}
