import java.util.Random;

class Final8Queen {

	public static void main(String[] args) {

		int myMaxIterations = 500; // Maximum iteration to find the result

		String SolutionStr = Solve(myMaxIterations); // Calculate Fitness Value (56 - Attacks)
		// System.out.println(SolutionStr);

	}

	// ************************ Q1 Check Character ******************

	public static boolean CheckCharacter(Character myChar1) {
		char myQueenChar = 'Q';
		char myEmptySquareChar = '.';

		if (myChar1 == null) {
			return false;
		}

		if (myChar1.equals(myQueenChar) || myChar1.equals(myEmptySquareChar)) {
			return true;
		} else {
			return false;
		}
	}

	// ***********************************************************************

	// ************************ Q2 Valid Board ******************

	public static boolean isValidBoard(char[][] board2) {
		int myRowsOfBoard = 8; // Number of Rows
		int myColumnsOfBoard = 8; // Number of Columns
		char myQueenChar = 'Q'; // Queen character

		if (board2 == null || board2[0].length != myRowsOfBoard || board2.length != myColumnsOfBoard) {
			return false;
		}

		int queensCnt = 0;
		for (int i = 0; i < myRowsOfBoard; i++) {
			if (board2[i] == null || board2[i].length != myRowsOfBoard || board2.length != myColumnsOfBoard) {

				return false;
			}
			for (int j = 0; j < myColumnsOfBoard; j++) {
				if (CheckCharacter(board2[i][j]) != true) {
					return false;
				}
				if (board2[i][j] == myQueenChar) {
					queensCnt++;
				}
			}
		}

		if (queensCnt == 8) {
			return true;
		} else {
			return false;
		}
	}

	// ***********************************************************************


	// ************************ Q3 Generate Binary String ******************

	private static String GenerateBinaryString(char[][] board3) {

		int myRowsOfBoard = 8; // Number of Rows
		int myColumnsOfBoard = 8; // Number of Columns
		char myQueenChar = 'Q'; // Queen character


		String myReturnString = "";

		if (board3 == null || board3[0].length != myRowsOfBoard || board3.length != myColumnsOfBoard) {
			return myReturnString;
		}

		for (int row = 0; row < myRowsOfBoard; row++) {
			for (int col = 0; col < myColumnsOfBoard; col++) {
				if (board3[row][col] == myQueenChar) {
					// This part converts column number to binary then format it as 3 digit
					myReturnString = myReturnString
							+ String.format("%3s", Integer.toBinaryString(col)).replaceAll(" ", "0");
					break;
				}
			}
		}
		return myReturnString;
	}

	// ***********************************************************************


	// ************************ Q4 Initial Starting Point ******************

	private static String RandomStartingPoint() {

		String myString = new String();
		int myScasolSize = 24; // Binary String Size

		// Create a random binary string of just ones and zeros of length myString
		for (int i = 0; i < myScasolSize; ++i) {
			int myRandomGeneratedInt = CS2004.UI(0, 1);
			myString = myString + Integer.toString(myRandomGeneratedInt);
		}
		return myString;
	}


	// ***********************************************************************

	// ************************ Q5 Fitness Function ******************

	private static char[][] CreateMyBoard(String myStr5) {
		char myQueenChar = 'Q'; // Queen character
		char myEmptySquareChar = '.'; // Empty cell character
		int myRowsOfBoard = 8; // Number of Rows
		int myColumnsOfBoard = 8; // Number of Columns

		String mySubScasol;
		int MyQueensPlaceInRow = 0;
		int SubStrStartIndex = 0;
		char[][] myBoard = new char[myRowsOfBoard][myColumnsOfBoard];

		if (myStr5 == null || myStr5.isEmpty() || myStr5.length() != 24) {
			return myBoard;
		}

		for (int row = 0; row < myRowsOfBoard; row++) {

// This part takes 3 digit of the string and converts it to decimal to find the Queens' place on the row
			mySubScasol = myStr5.substring(SubStrStartIndex, SubStrStartIndex + 3);
			MyQueensPlaceInRow = Integer.parseInt(mySubScasol, 2);
			SubStrStartIndex = SubStrStartIndex + 3;

			for (int col = 0; col < myColumnsOfBoard; col++) {

				if (col == MyQueensPlaceInRow) {
					myBoard[row][col] = myQueenChar;
				} else {
					myBoard[row][col] = myEmptySquareChar;
				}
			}
		}
		
		return myBoard;
	}


	private static int Fitness(String myStr5_1) { // Calculate Fitness

		int myMaxAttack = 56; // Maximum attack count 8*7
		char myQueenChar = 'Q'; // Queen character
		int myRowsOfBoard = 8; // Number of Rows
		int myColumnsOfBoard = 8; // Number of Columns
		int myAttackCnt = 0;

		if (myStr5_1 == null || myStr5_1.isEmpty() || myStr5_1.length() != 24) {
			return Integer.MIN_VALUE;
		}

		char[][] board = CreateMyBoard(myStr5_1);

		for (int i = 0; i < myRowsOfBoard; i++) {

			for (int j = 0; j < myColumnsOfBoard; j++) {

				if (board[i][j] == myQueenChar) {

					// Check same row for other Queens
					for (int row = 0; row < myColumnsOfBoard; row++) {
						if (row != j && board[i][row] == myQueenChar) {

							myAttackCnt++;

						}
					}

					// Check same column for other Queens
					for (int col = 0; col < myRowsOfBoard; col++) {
						if (col != i && board[col][j] == myQueenChar) {

							myAttackCnt++;

						}
					}

					// Check diagonal for other Queens
					for (int diagonal = 1; diagonal < myColumnsOfBoard; diagonal++) {
						if (i + diagonal < myRowsOfBoard && j + diagonal < myColumnsOfBoard
								&& board[i + diagonal][j + diagonal] == myQueenChar) {

							myAttackCnt++;

						}
						if (i + diagonal < myRowsOfBoard && j - diagonal >= 0
								&& board[i + diagonal][j - diagonal] == myQueenChar) {

							myAttackCnt++;

						}
						if (i - diagonal >= 0 && j + diagonal < myColumnsOfBoard
								&& board[i - diagonal][j + diagonal] == myQueenChar) {

							myAttackCnt++;

						}
						if (i - diagonal >= 0 && j - diagonal >= 0
								&& board[i - diagonal][j - diagonal] == myQueenChar) {

							myAttackCnt++;

						}
					}
				}
			}
		}

		return myMaxAttack - myAttackCnt; // Fitness result (56-Attack Count)

	}

	// ************************** Q6 Small Change Operator ****************


	public static String SmallChange(String scasol6) {

		if (scasol6 == null || scasol6.isEmpty() || scasol6.length() != 24) {
			return null;

		}

		Random rand = new Random();
		int myRandomMin = 0;
		int myRandomMax = scasol6.length() - 1;

		String newScasol = scasol6;

		// Requirement: Make sure that the modified copy of the solution is returned.
		while (newScasol.equals(scasol6)) {

		int myRandomInt = rand.nextInt(myRandomMax - myRandomMin + 1) + myRandomMin;

			char myChar = newScasol.charAt(myRandomInt);

		if (myChar == '0') {
				myChar = '1'; // one bit changes
		} else {
				myChar = '0'; // one bit changes
		}


		// This code converts newScasol to char array, changes related bit then converts
		// char array to string again
		char[] myScasolChars = newScasol.toCharArray();
		myScasolChars[myRandomInt] = myChar;
		newScasol = String.valueOf(myScasolChars); // New 24 bit binary string created

	} // End of While loop

	// Requirement: Make sure that the modified copy of the solution is returned.
	if (newScasol != scasol6) { // Compare the initial value and changed value
		return newScasol;
	} else {

		return null; // if initial and changed value are same return null

	}

	}

	// ***********************************************************************

	// ************************** Q7 Solving the Problem ****************

	public static String Solve(int iterationLimit7) {

		int myMaxAttack = 56; // Maximum attack count 8*7
		String currentScasol = null;

		if (iterationLimit7 <= 1) {
			return currentScasol;
		}


			currentScasol = RandomStartingPoint();
			int currentFitness = Fitness(currentScasol);

			int iter = 0;

			while (currentFitness != myMaxAttack && iter < iterationLimit7) {
				String newScasol;
				int newFitness;

				newScasol = SmallChange(currentScasol);
				newFitness = Fitness(newScasol);

				if (newFitness >= currentFitness) {

					currentFitness = newFitness;
					currentScasol = newScasol;
				}

				iter++;
			}


		return currentScasol;
	}

	// ***********************************************************************

}
