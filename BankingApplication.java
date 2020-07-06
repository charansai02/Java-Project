import java.util.Scanner;
public class BankingApplication {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Project by P. Charan Sai with SAP ID 51834606");
		System.out.print("\nEnter your name: ");//ASKS THE USER TO ENTER HIS/HER NAME.
		String cname=sc.nextLine();
		System.out.print("Enter your ID: ");//ASKS THE USER TO ENTER HIS/HER ID.
		String cid=sc.nextLine();
		System.out.print("Enter balance in your account: ");//ASKS THE USER TO ENTER THE BALANCE IN HIS/HER ACCOUNT.
		try{
			int bal=sc.nextInt();
		
			while(bal<0)
			{/*IF THE VALUE FOR BALANCE IS ENTERED NEGATIVE OR ZERO, THEN THE ACCOUNT IS UNDETERMINED.
			SO, IT WILL ASK THE USER TO ENTER AGAIN UNTIL THE USER ENTERED A POSITIVE VALUE..*/
			System.out.println("Enter positive value for bal: ");
			bal=sc.nextInt();
			}
			BankAccount b1=new BankAccount(cname,cid,bal);
		
			/*OBJECT WAS INITIALIZED TO THE CLASS USING ITS CONSTRUCTOR*/
			b1.showMenu();
		}
		catch(Exception e) {
			System.out.println("You have entered a string instead of int..!!");
		}
		sc.nextLine();
		sc.close();
	}
}
class BankAccount{
	int balance;
	int previousTransaction;
	String customer_name;
	String customer_id;
	
	BankAccount(String cname, String cid,int bal)
	{ //CONSTRUCTOR TO INITIALIZE THE OBJECT.
		customer_name=cname;
		customer_id=cid;
		balance=bal;
	}
	
	void deposit(int amount)
	{ //METHOD TO DEPOSIT MONEY.
		if(amount>0)
		{
			balance=balance+amount;
			previousTransaction=amount;
			System.out.println("Deposited..");
		}
		else
			System.out.println("You have entered negative value or Zero..");
	}
	void withdraw(int amount)
	{ //METHOD TO WITHDRAW MONEY.
		if(amount>0&&amount<balance)
		{ //AMOUNT TO WITHDRAW SHOULD BE AVAILABLE IN HIS/HER ACCOUNT.
			balance=balance-amount;
			previousTransaction=-amount; 
			/*IT IS NEGATIVE BECAUSE IF SOME MONEY IS WITHDRAWN MEANS SOME MONEY 
			 * SHOULD BE DEDUCTED FROM HIS BALANCE*/
			System.out.println("Withdrawn..");
		}
		else if(amount<0)
			System.out.println("You have entered negative value..!!");
		else
			System.out.println("Insufficient Balance!!");
	}
	void getPreviousTransaction()
	{
		if(previousTransaction>0)
		{ //DEPOSITED...
			System.out.println("Deposited: "+previousTransaction);
		}
		if(previousTransaction<0)
		{ //WITHDRAWN...
			System.out.println("Withdrawn: "+(-previousTransaction));
		}
		if(previousTransaction==0)
			//NOTHING HAPPEN...
			System.out.println("No Transaction occured!");
	}
	void showMenu()
	{ //METHOD TO DISPLAY THE MENU TO THE USER..
		char option='\0';
		Scanner sc2=new Scanner(System.in);
		System.out.println("Welcome "+customer_name);
		System.out.println("Your ID is "+customer_id);
		System.out.println("\n");
		
		
		do
		{ //THIS LOOP WILL BE EXECUTED UNTIL THE OPTION ENTERED IS 'E'.
			System.out.println("A: Check Balance");
			System.out.println("B: Deposit");
			System.out.println("C: Withdraw");
			System.out.println("D: Previous Transaction");
			System.out.println("E: EXIT");
			System.out.println("======================================");
			System.out.println("Enter your option");
			option=sc2.next().charAt(0);
			System.out.println();
			switch(option)
			{
			case 'A': //BALANCE CHECK..
				System.out.println("======================================");
				System.out.println("Balance in your account: "+balance);
				System.out.println("======================================");
				System.out.println();
				break;
			case 'B': //DEPOSIT..
				System.out.println("======================================");
				System.out.println("Enter an amount to deposit: ");
				System.out.println("======================================");
				try
				{
					int amount=sc2.nextInt();
				
					deposit(amount);
				}
				catch(Exception e) 
				{
					System.out.println("You have entered a string instead of int..!!");
				}
				sc2.nextLine();
				System.out.println();
				break;
			case 'C': //WITHDRAW..
				System.out.println("======================================");
				if(balance==0)
				{ //CANNOT WITHDRAW FROM ZERO ACCOUNT..
					System.out.println("You have 0 balance. You cannot withdraw!!");
				}
				else
				{
					System.out.println("Enter an amount to withdraw: ");
					System.out.println("======================================");
					try 
					{ /*IF THE USER ENTERS STRING INSTEAD OF INTEGER,
					EXCEPTION WILL BE THWORN TO CATCH BLOCK.*/
						int amount2=sc2.nextInt();
					
						withdraw(amount2);
					}
					catch(Exception e) 
					{ /*IF ANY EXCEPTION WAS CATCHED, IT WILL EXECUTE THE FOLLOWING BLOCK 
					TO AVOID ABNORMAL TERMINATION OF THE PROGRAM*/
						System.out.println("You have entered a string instead of int..!!");
					}
				}
				sc2.nextLine();
					System.out.println();
				break;
			case 'D': //CHECK THE PREVIOUS TRANSACTION..
				System.out.println("======================================");
				getPreviousTransaction();
				System.out.println("======================================");
				System.out.println();
				break;
			case 'E': //EXIT..
				System.out.println("**************************************");
				break;
			default: //OPTION ENTERED WHICH IS NOT IN THE MENU GIVEN..
				System.out.println("Invalid option..");
			}
		}
		while(option!='E'); 
		System.out.println("Thank you for using our services..");
		sc2.close();
	}
}