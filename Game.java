import java.util.Scanner;

public class Game {
	static Board board = new Board();
	private static char userChar = ' ';
	private static char botChar;
	private static boolean userTurn;
	private static Bot bot;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(userChar == ' ') {
			System.out.println("Choose X or O(X goes first): ");
			char input = scanner.next().charAt(0);
			if(input == 'X') {
				userChar = 'X';
				botChar = 'O';
				userTurn = true;
			}
			else if(input == 'O') {
				userChar = 'O';
				botChar = 'X';
				userTurn = false;
			}
			bot = new Bot(board, botChar);
		}
		
		while(!board.isFull()) {
			int[] move = {-1, -1};
			
			if(userTurn) {
				while(!board.isValid(move[0], move[1])) {
					System.out.println("Choose your location(e.x. 0 1): ");
					move[0] = scanner.nextInt();
					move[1] = scanner.nextInt();
				}
				board.playMove(userChar, move[0], move[1]);
				board.displayBoard();
				if(board.isWinner(userChar)) {
					System.out.println("User Won!");
					scanner.close();
					return;
				}
				userTurn = !userTurn;
			}
			else {
				move = bot.findBestMove(board);
				board.playMove(botChar, move[0], move[1]);
				System.out.println("Bot Move: " + move[0] + " " + move[1]);
				board.displayBoard();
				if(board.isWinner(botChar)) {
					System.out.println("Bot Won!");
					scanner.close();
					return;
				}
				userTurn = !userTurn;
			}
		}
		System.out.println("Tie Game!");
		scanner.close();
	}

}
