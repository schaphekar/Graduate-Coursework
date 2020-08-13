import java.io.BufferedReader;
import java.io.IOException;

public class WithdrawCommand implements ICommand, IUndoable {

    BufferedReader br;
    BankAccount account;
    int withdrawAmount;

    WithdrawCommand(BufferedReader br, BankAccount account) {
        this.br = br;
        this.account = account;
    }

    @Override
    public void run() throws IOException {
        System.out.println("How much do you want to withdraw?");
        String withdrawResponse = br.readLine();

        withdrawAmount = Integer.parseInt(withdrawResponse);

        account.withdraw(withdrawAmount);

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        account.deposit(withdrawAmount);
    }

    @Override
    public void redo() {
        account.withdraw(withdrawAmount);
    }
}
