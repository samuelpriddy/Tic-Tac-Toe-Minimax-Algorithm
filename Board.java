public class Board {
	private char[][] board;
	private int turns;
	
	public Board() {
		board = new char[][]{
					{' ', ' ', ' '},
					{' ', ' ', ' '},
					{' ', ' ', ' '}
				};
		turns = 0;
	}
	
	public Board(char[][] board, int turns) {
		this.board = board;
		this.turns = turns;
	}
	
	public boolean isWinner(char p) {
		return (
				(p == board[0][0] && p == board[0][1] && p == board[0][2]) ||
				(p == board[1][0] && p == board[1][1] && p == board[1][2]) ||
				(p == board[2][0] && p == board[2][1] && p == board[2][2]) ||
				(p == board[0][0] && p == board[1][0] && p == board[2][0]) ||
				(p == board[0][1] && p == board[1][1] && p == board[2][1]) ||
				(p == board[0][2] && p == board[1][2] && p == board[2][2]) ||
				(p == board[0][0] && p == board[1][1] && p == board[2][2]) ||
				(p == board[0][2] && p == board[1][1] && p == board[2][0])
				);
	}
	
	public boolean isFull() {
		return turns == 9;
	}
	
	public boolean isCat() {
		return (turns == 9 && !isWinner('X') && !isWinner('O'));
	}
	
	public boolean isValid(int r, int c) {
		if(r > 2 || r < 0 || c > 2 || c < 0) {return false;}
		return board[r][c] == ' ';
	}
	
	public int numTurns() {
		return turns;
	}
	
	public char playerAt(int r, int c) {
		return board[r][c];
	}
	
	public void displayBoard() {
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				System.out.print(board[r][c]);
				if(c < 2) {System.out.print('|');}
			}
			System.out.println();
			if(r < 2) {
				for(int i = 0; i < 3; i++) {
					System.out.print("- ");
				}
			}
			System.out.println();
		}
	}
	
	public void playMove(char p, int r, int c) {
		if(isValid(r, c)) {
			board[r][c] = p;
			turns++;
		}
	}
	
	public char[][] getBoard(){
		char[][] clone = new char[3][3];
		
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				clone[r][c] = board[r][c];
			}
		}
		return clone;
	}
	
	public int getTurns() {
		return turns;
	}
}
