import javax.swing.JOptionPane;
public class AjavorskiBankAccount {
String name,passwordID,a,b;
double Balance;
public AjavorskiBankAccount(String name,String pass) {
	this.name = name;
	this.passwordID = pass;
	this.Balance = 0;
}
public double getCurrentBalance() {
		return this.Balance;
}
public void deposit(double m) {
	this.Balance = this.Balance + m;
}
public void withdraw(double m) {
	a = JOptionPane.showInputDialog("Please enter your password.");
	if(new String(this.passwordID).equals(a)) {
		this.Balance = this.Balance - m;
	}else if(a != this.passwordID) {
		JOptionPane.showMessageDialog(null, "Incorrect!Please try again.");
	}	
}
public String toString() {
	return "account holder:"+this.name+"|Balance:"+this.Balance;	
			

}


}
