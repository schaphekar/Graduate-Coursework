import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException{
		System.out.println("Welcome to Jeffrey Sharpe's \'Super-legit\' Bank");
		System.out.println("This ATM offers the following options:");
		System.out.println("Withdraw");
		System.out.println("Deposit");
		System.out.println("Balance");
        System.out.println("Undo");
        System.out.println("Redo");
		System.out.println("Exit");

		BankAccount account = new BankAccount();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while(!(input = br.readLine()).toLowerCase().equals("exit")) {
		    System.out.println("What would you like to do?");
        	ICommand command;

        	if(input.equalsIgnoreCase("withdraw")) {
				command = new WithdrawCommand(br, account);
			}
        	else if(input.equalsIgnoreCase("deposit")) {
				command = new DepositCommand(br, account);
			}
        	else if(input.equalsIgnoreCase("balance")) {
				command = new BalanceCommand(account);
			}
            else if(input.equalsIgnoreCase("undo")) {
                command = new UndoCommand();
            }
            else if(input.equalsIgnoreCase("redo")) {
                command = new RedoCommand();
            }
			else {
				System.out.println("Invalid option");
				continue;
			}

			command.run();
        }
	}
}
