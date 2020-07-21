import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TicTacToe1{
	static ArrayList<Integer> player1Positions = new ArrayList<Integer>();
	static ArrayList<Integer> player2Positions = new ArrayList<Integer>();
	public static void main(String[] args) {
		char[][] gameBoard = {{'1','|','2','|','3',},
				     {'-','+','-','+','-',},
		         	     {'4','|','5','|','6',},
				     {'-','+','-','+','-',},
				     {'7','|','8','|','9',}};
		printGameBoard(gameBoard);
		while(true) {
			System.out.println("Player1's Turn...");
			Scanner in = new Scanner(System.in);
			int player1Pos = in.nextInt();
			while(player1Positions.contains(player1Pos)||player2Positions.contains(player1Pos)) {
				System.err.println("Position Already Taken..!\nEnter a Correct Position");
				player1Pos = in.nextInt();
			}
			placePos(gameBoard,player1Pos,"player1");
			printGameBoard(gameBoard);
			String result = checkWinner();
			if(result.length()>0) {
				printGameBoard(gameBoard);
				System.out.println(result);
				break;
			}
			System.out.println("Player2's Turn...");
			int player2Pos = in.nextInt();
			while(player1Positions.contains(player2Pos)||player2Positions.contains(player2Pos)) {
				System.err.println("Position Already Taken..!\nEnter a Correct Position");
				player2Pos = in.nextInt();
			}
			placePos(gameBoard,player2Pos,"player2");
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
		if(user.equals("player1")) {
			symbol = 'X';
			player1Positions.add(pos);
		}
		if(user.equals("player2")) {
			symbol = 'O';
			player2Positions.add(pos);
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
			if(player1Positions.containsAll(l)) {
				return "Congratulations..!\nPlayer1 Won :)";
			}
			if(player2Positions.containsAll(l)) {
				return "Congratulations..!\nPlayer2 Won :)";
			}
			if(player1Positions.size()+player2Positions.size() == 9) {
				return "Oops..!\nNo One Won :(";
			}		
		}
		return "";
	}
}
