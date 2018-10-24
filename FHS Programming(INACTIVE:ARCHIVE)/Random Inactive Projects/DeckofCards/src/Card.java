
public class Card {
String suite,name;
//suite: Heart,spade,diamond,club
//13 cards per suite, royalty being jack,queen,king all valued at 10
//52 in a deck
int IDNum,value;
//create deck by end of the week
public Card(String s,String n,int num,int v) {
	this.suite = s;
	this.name = n;
	this.IDNum = num;
	this.value = v;
}
public void displayCard() {
	System.out.println(this.name+" of "+this.suite+" ; ID: "+this.IDNum);;
}
public void setSuite(String s) {
	this.suite = s;
}
public void setName(String s) {
	this.name = s;
}
public void setIDNum(int n) {
	this.IDNum = n;
}
public void setValue(int v) {
	this.value = v;
}
public String getSuite() {
	return this.suite;
}
public String getName() {
	return this.name;
}
public int getIDNum() {
	return this.IDNum;
}
public int getValue() {
	return this.value;
}
}
