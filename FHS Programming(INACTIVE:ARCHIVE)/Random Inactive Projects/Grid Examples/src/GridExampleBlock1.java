import javax.swing.JOptionPane;

public class GridExampleBlock1 {

	public static void main(String[] args) {
//		displayGrid();
//		challenge1();
//		challenge2();
//		challenge3();
//		challenge4();
//		challenge5();
//		diag();
		wah();
	}
	public static void wah() {
		int[][] arr = new int[5][5];
		for(int row = 1; row < 4;row++) {
			for(int col = 1;col < 4;col++)
			arr[row][col] = (int)(Math.random()*10);
		}
		for(int row = 0; row< arr.length; row++) {
			for(int col = 0;col < arr[0].length;col++) {
					System.out.print(arr[row][col] + " ");
			}
			System.out.println();
		}

	}
	public static void displayGrid() {
		int[][] wut = new int[3][5];
		wut[0][0] = 1;
		wut[0][wut[0].length - 1] = 1;
		wut[wut.length - 1][0] = 1;
		wut[wut.length - 1][wut[0].length - 1] = 1;
		for(int row = 0; row< wut.length; row++) {
			for(int col = 0;col < wut[0].length;col++) {
					System.out.print(wut[row][col] + " ");
			}
			System.out.println();
		}
	}
	/*Challenge No. 1*/
	public static void challenge1() {
		int[][] wut = new int[3][5];
		for(int row = 0; row< wut.length; row++) {
			for(int col = 0;col < wut[0].length;col++) {
					wut[row][col] = (int)(Math.random()*9+1);	
			}
		}
		for(int row = 0; row< wut.length; row++) {
			for(int col = 0;col < wut[0].length;col++) {
					System.out.print(wut[row][col] + " ");
			}
			System.out.println();
		}
	}
	/*Challenge No. 2*/
	public static void challenge2() {
		int[][] wut = new int[5][5];
		for(int x = 0; x < 5; x++) {
			int row = (int)(Math.random()*5);
			int col = (int)(Math.random()*5);
			wut[row][col] = 1;
		}
		for(int row = 0; row< wut.length; row++) {
			for(int col = 0;col < wut[0].length;col++) {
					System.out.print(wut[row][col] + " ");
			}
			System.out.println();
		}
	}
	/*Challenge No. 3*/
	public static void challenge3() {
		String s = JOptionPane.showInputDialog("choose a number for a size from 1 to 10 as a integer!");
		int size = Integer.parseInt(s);
		int[][] wuh = new int[size][size];
		s = JOptionPane.showInputDialog("Please choose a row number starting from zero and not exceeding " + (size-1));
		int row = Integer.parseInt(s);
		s = JOptionPane.showInputDialog("Please choose a collumn number starting from zero and not exceeding " + (size-1));
		int col = Integer.parseInt(s);
		s = JOptionPane.showInputDialog("What integer value would you like stored there?");
		int value = Integer.parseInt(s);
		wuh[row][col] = value;
		
		for(int ro = 0; ro< wuh.length; ro++) {
			for(int coll = 0;coll < wuh[0].length;coll++) {
					System.out.print(wuh[ro][coll] + " ");
			}
			System.out.println();
		}
	}
	/*Challenge No. 4*/
	public static void challenge4() {
		int[][] wut = new int[5][9];
		for(int row = 0; row< wut.length; row++) {
			for(int col = 0;col < wut[0].length;col++) {
					wut[row][col] = (int)(Math.random()*9+1);	
			}
		}
		for(int row = 0; row< wut.length; row++) {
			for(int col = 0;col < wut[0].length;col++) {
					System.out.print(wut[row][col] + " ");
			}
			System.out.println();
		}
		int specificValue = 7;
		System.out.println("There is " + countValue(wut,specificValue) + " of " + specificValue + "s");
	}
	public static int countValue(int[][] ar, int x) {
		int count = 0;
		for(int row = 0; row < ar.length;row++) {
			for(int col = 0; col < ar[0].length;col++) {
				if( x == ar[row][col]) {
					count++;
				}
			}
		}
		return count;
	}
	/*Challenge No. 5*/
	public static void challenge5() {
//		int num = 1;
		int[][] wut = new int[3][3];
		for(int row = 0; row< wut.length; row++) {
			for(int col = 0;col < wut[0].length;col++) {
				wut[row][col] = (int)(Math.random()*9+1);
//				wut[row][col] = num;
//				wut[2][1] = 8;
//				num++;
			}
		}
		for(int row = 0; row< wut.length; row++) {
			for(int col = 0;col < wut[0].length;col++) {
					System.out.print(wut[row][col] + " ");
			}
			System.out.println();
			
		}
		Valid(wut);
	}
	public static void Valid(int[][] ar) {
		int count = 0;
		for(int num = 0; num < 10; num++) {
			for(int row = 0;row < ar.length;row++) {
				for(int col = 0; col < ar[0].length;col++) {
					if(num == ar[row][col]) {
						count++;
					}
				}
			}
			if(count > 1) {
				System.out.println("Not Valid");
				break;
			}
			else if(count <= 1 ) {
				count = 0;
			}
			if(num == 9 && count <= 1) {
				System.out.println("Is Valid!");
			}
		}
		
	}
	public static void diag() {
		int[][] ar = new int[20][4];
		for(int loc = 0;loc < ar.length;loc++) {
			if(loc < ar[0].length) {
				ar[loc][loc] = 1;
			}
		}
		for(int row = 0; row< ar.length; row++) {
			for(int col = 0;col < ar[0].length;col++) {
					System.out.print(ar[row][col] + " ");
			}
			System.out.println();
		}
		
	}
}
