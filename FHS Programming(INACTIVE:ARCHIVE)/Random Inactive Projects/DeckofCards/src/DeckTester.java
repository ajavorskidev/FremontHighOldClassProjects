import javax.swing.JOptionPane;;
public class DeckTester {

	public static void main(String[] args) {
	/**declarations**/
		Card[] deck1,deck2;
		String[] name1;
		int[] value1;
		int count,card;
		Card currentCard;
		/**Initialization**/
		deck1 = new Card[52];
		deck2 = new Card[52];
		name1 = new String[13];
		value1 = new int[13];
		/*Name array initialization*/
		name1[0] = "Ace";
		name1[1] = "Two";
		name1[2] = "Three";
		name1[3] = "Four";
		name1[4] = "Five";
		name1[5] = "Six";
		name1[6] = "Seven";
		name1[7] = "Eight";
		name1[8] = "Nine";
		name1[9] =  "Ten";
		name1[10] = "Jack";
		name1[11] = "Queen";
		name1[12] = "King";
		/*Value Initialization*/
		value1[1] = 1;
		count = 1;
		card = 0;
		/*Card Initialization*/
		for(int i = 0; i < 13;i++) {
			if(i < 10) {
				deck1[card] = new Card("Hearts",name1[i],count,i+1);
			}else if(i>9 && i < 13){
				deck1[card] = new Card("Hearts",name1[i],count,10);
			}
			count = count + 1;
			card = card + 1;
		}
		for(int i = 0; i < 13;i++) {
			if(i < 10) {
				deck1[card] = new Card("Spades",name1[i],count,i+1);
			}else if(i>9 && i < 13){
				deck1[card] = new Card("Spades",name1[i],count,10);
			}
			count = count + 1;
			card = card + 1;
		}
		for(int i = 0; i < 13;i++) {
			if(i < 10) {
				deck1[card] = new Card("Diamonds",name1[i],count,i+1);
			}else if(i>9 && i < 13){
				deck1[card] = new Card("Diamonds",name1[i],count,10);
			}
			count = count + 1;
			card = card + 1;
		}
		for(int i = 0; i < 13;i++) {
			if(i < 10) {
				deck1[card] = new Card("Clubs",name1[i],count,i+1);
			}else if(i>9 && i < 13){
				deck1[card] = new Card("Clubs",name1[i],count,10);
			}
			count = count + 1;
			card = card + 1;
		}
		
		
		
	
		/**Initial shuffle**/
		for(int i = 0; i < deck1.length;i++) {
			currentCard = deck1[i];
			int a = (int)(Math.random()*52);
			if(deck2[a] == null) {
			deck2[a] = currentCard;
			}else if(deck2[a] != null) {
				do {
					a = (int)(Math.random()*52);
					}while(deck2[a] != null);
					deck2[a] = currentCard;
			}
			currentCard = deck2[a];
			System.out.print((a+1)+" ");
			currentCard.displayCard();
		}
		//Which Card is "Pulled" or on top of the deck, proving that it has been shuffled.
		System.out.println("__________________________");
		
//		currentCard = deck2[0];
		/*Card Puller*/
		int a = 0; //Integer.parseInt(JOptionPane.showInputDialog("Pick a card, any card"));
		do {
			a = Integer.parseInt(JOptionPane.showInputDialog("Enter an integer 1-52"));
		} while(a > 52 || a < 0);
		currentCard = deck2[a-1];
		System.out.print("The card you picked is: ");
		currentCard.displayCard();
	}
}
