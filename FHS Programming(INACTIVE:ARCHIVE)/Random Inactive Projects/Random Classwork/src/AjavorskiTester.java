
public class AjavorskiTester {

	public static void main(String[] args) {
		AjavorskiBankAccount a1 = new AjavorskiBankAccount("Adrian","Mochi");
		
		System.out.println(a1.getCurrentBalance());
		a1.deposit(699.23);
		System.out.println(a1.getCurrentBalance());
		a1.withdraw(100.00);
		System.out.println(a1.getCurrentBalance());
		System.out.println(a1.toString());
	}

}
