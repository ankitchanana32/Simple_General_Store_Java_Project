import java.util.Scanner;

public class Entry {
	public static void main(String[] args) {

		System.out.println(
				"Welcome to AC general store \n We have following items. \n 1.\t Pencils \n 2.\t Pens \n 3.\t Notebooks \n 4.\t Books");
		System.out.println("Enter the id of the item \t 1,2,3 or 4");
		int bill=0;
		while (true) {
			Scanner obj = new Scanner(System.in);

			int itemId = obj.nextInt();
			if (itemId == 1 || itemId == 2 || itemId == 3 || itemId == 4) {
				System.out.println("Enter the total no. of the items you want to buy");
				int itemNo = obj.nextInt();
				DbHandler d = new DbHandler();
				int x = d.getTotalBill(itemId, itemNo);
				bill=bill+x;
				System.out.println(bill);

				System.out.println("Do you want to continue if yes enter next item code");
			} else {
				System.out.println("Provide valid input");
			}
		}
	}

}
