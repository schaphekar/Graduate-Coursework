import java.io.BufferedReader;
import java.io.IOException;

public class DepositCommand implements ICommand, IUndoable {

    private BufferedReader br;
    private BankAccount account;
    private int depositAmount;

    DepositCommand(BufferedReader br, BankAccount account){
        this.br = br;
        this.account = account;
    }

    @Override
    public void run() throws IOException {
        System.out.println("How much do you want to deposit?");
        String depositResponse = br.readLine();

        depositAmount = Integer.parseInt(depositResponse);

        account.deposit(depositAmount);

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        account.withdraw(depositAmount);
    }

    @Override
    public void redo() {
        account.deposit(depositAmount);
    }
}
