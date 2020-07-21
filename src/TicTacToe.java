import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TicTacToe{
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	public static void main(String[] args) throws InterruptedException {
		char[][] gameBoard = {{' ','|',' ','|',' ',},
							  {'-','+','-','+','-',},
							  {' ','|',' ','|',' ',},
							  {'-','+','-','+','-',},
							  {' ','|',' ','|',' ',}};
		printGameBoard(gameBoard);
		
		while(true) {
			System.out.println("Enter Your Position From (1-9)");
			Scanner in = new Scanner(System.in);
			int playerPos = in.nextInt();
			while(playerPositions.contains(playerPos)||cpuPositions.contains(playerPos)) {
				System.err.println("Position Already Taken..!\nEnter a Correct Position");
				playerPos = in.nextInt();
			}
			placePos(gameBoard,playerPos,"player");
			printGameBoard(gameBoard);
			String result = checkWinner();
			if(result.length()>0) {
				printGameBoard(gameBoard);
				System.out.println(result);
				break;
			}
			System.out.println("Wait for CPU...");
			Thread.sleep(1000);
			Random ran = new Random();
			int cpuPos = ran.nextInt(9)+1;
			while(playerPositions.contains(cpuPos)||cpuPositions.contains(cpuPos)) {
				cpuPos = ran.nextInt(9)+1;
			}
			placePos(gameBoard,cpuPos,"cpu");
			printGameBoard(gameBoard);
			result = checkWinner();
			if(result.length()>0) {
				printGameBoard(gameBoard);
				System.out.println(result);
				break;
			}
		}
}
	public static void printGameBoard(char[][] gameBoard) {

		for(char r[] : gameBoard) {
			for(char c : r) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	public static void placePos(char[][] gameBoard,int pos,String user) {
		char symbol = 'X';
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		}
		if(user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		switch(pos)
		{
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
			default:
				break;
		}
	}
	public static String checkWinner() {
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		List leftCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8);
		List rightCol = Arrays.asList(3,6,9);
		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(3,5,7);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		for(List l : winning) {
			if(playerPositions.containsAll(l)) {
				return "Congratulations..!\nYou Won :)";
			}
			if(cpuPositions.containsAll(l)) {
				return "Sorry..!\nCPU Won :(";
			}
			if(playerPositions.size()+cpuPositions.size() == 9) {
				return "Oops..!\nNo One Won";
			}		
		}
		return "";
	}
}