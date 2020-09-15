import java.util.Scanner;

class lab2 {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		int again = 1;
		while(again == 1) {
			System.out.printf("\nEnter the month's payment:\n$");
			double payment = kb.nextDouble();
	
			System.out.printf("Enter the outstanding balance of the account:\n$");
			double balance = kb.nextDouble();
	
			double interest = balance * 0.0625 / 12;
			double principal = payment - interest;
			double new_balance = balance - principal;

			System.out.printf("\nHere is a breakdown of the payment:\n");
		
			System.out.printf("Interest paid:\n$%.2f\n", interest);
			System.out.printf("Amount applied to principal:\n$%.2f\n", principal);
			System.out.printf("New account balance:\n$%.2f\n", new_balance);

			System.out.printf("\nEnter 1 to continue, enter 0 to quit:\n");
			again = kb.nextInt();
		}
	}
}
