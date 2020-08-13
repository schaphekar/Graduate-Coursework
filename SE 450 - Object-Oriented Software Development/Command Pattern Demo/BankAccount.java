
public class BankAccount {
	private int balance = 0;

	public void deposit(int depositAmount){
		balance += depositAmount;
	}

	public void withdraw(int withdrawalAmount){
		balance -= withdrawalAmount;
	}

	public int getBalance() {
		return balance;
	}
}
