public class BalanceCommand implements ICommand {

    BankAccount account;

    BalanceCommand(BankAccount account){
        this.account = account;
    }

    @Override
    public void run() {
        System.out.println("Balance: " + account.getBalance());
    }
}
