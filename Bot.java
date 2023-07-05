
public class Bot {
	private Board board;
	private char botChar;
	
	public Bot(Board b, char botChar) {
		this.board = b;
		this.botChar = botChar;
	}

	public int[] findBestMove(Board b) {
		int[] bestMove = {-1, -1};
		int bestEval = -1000;
		
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(b.isValid(r, c)) {
					Board tempBoard = new Board(b.getBoard(), b.getTurns());
					tempBoard.playMove(botChar, r, c);
					int currEval = minimax(tempBoard, false);
					
					if(currEval > bestEval || bestMove[0] == -1) {
						bestEval = currEval;
						bestMove[0] = r;
						bestMove[1] = c;
					}
				}
			}
		}	
		return bestMove;
	}
	
	private int evaluate(Board b) {
		if(!board.isFull() || board.isCat()) return 0;
		if(b.isWinner(botChar)) return 1;
		return -1;
	}
	
	private int minimax(Board b, boolean isMaxing) {
		int eval = evaluate(b);
		
		if(eval != 0 || b.isCat()) return eval;
		
		if(isMaxing) {
			int best = -1000;
			
			for(int r = 0; r < 3; r++) {
				for(int c = 0; c < 3; c++) {
					if(b.isValid(r, c)) {
						Board tempBoard = new Board(b.getBoard(), b.getTurns());
						tempBoard.playMove(botChar, r, c);
						best = Math.max(best, minimax(tempBoard, !isMaxing));
					}
				}
			}
			return best;
		}
		else {
			int best = 1000;
			
			for(int r = 0; r < 3; r++) {
				for(int c = 0; c < 3; c++) {
					if(b.isValid(r, c)) {
						Board tempBoard = new Board(b.getBoard(), b.getTurns());
						if(botChar == 'X'){tempBoard.playMove('O', r, c);}
						else {tempBoard.playMove('X', r, c);}
						best = Math.min(best, minimax(tempBoard, !isMaxing));
					}
				}
			}
			return best;
		}
	}
}
