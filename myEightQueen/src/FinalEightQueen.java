import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

class FinalEightQueen {
	// Autor: Ahmet Talha Yuce
	// Solution of the 8 Queens Problem using Random Mutation Hill Climbing (RMHC)
	// algorithm

	public static void main(String[] args) {


		int testCnt = 10; // Multiple times test if it is 1 runs one time
		int myMaxIterations = 1500; // Maximum iteration to find the result

		DecimalFormat decfor = new DecimalFormat("0.00"); // Calculate Average
		// FORMATLI GOSTERMEK ISTENMIYOSA KAPAT

		ArrayList<Integer> myFitnessResults = new ArrayList<Integer>(); // Calculate Average

		for (int i = 1; i <= testCnt; i++) { // Multiple times test

			// long startTime = System.nanoTime(); // Time to measure

			String SolutionStr = Solve(myMaxIterations); // Calculate Fitness Value (56 - Attacks)

			// long endTime = System.nanoTime(); //Time to measure
			// long elapsedTime = (endTime - startTime); //Time to measure


			int FitnessValue = Fitness(SolutionStr); // Calculate Fitness Value (56-Attacks) // Calculate Average
			myFitnessResults.add(FitnessValue); // Calculate Average

			System.out.println("Test Number: " + i); // Multiple times test
//		System.out.println("Elapsed Time: " + elapsedTime);  //Time to measure
			System.out.println("Binary String: " + SolutionStr); // print 24 bit binary string
			System.out.println("Fitness Value: " + FitnessValue); // Calculate Fitness Value (56 - Attacks)

			PrintMyBoard(CreateMyBoard(SolutionStr)); // Print Board on the screen
			System.out.println();

	} // Multiple times test

	// Calculate and print all the tests average Fitness value
	double sumOfMyFitnessResults = 0.0; // Calculate Average
	for (int i = 0; i < myFitnessResults.size(); i++) { // Calculate Average
		sumOfMyFitnessResults = sumOfMyFitnessResults + myFitnessResults.get(i); // Calculate Average
	} // Calculate Average

	double myTestAverage = sumOfMyFitnessResults / myFitnessResults.size(); // Calculate Average
	System.out.println("Calculated Fitness Test Mean: " + decfor.format(myTestAverage)); // Calculate Average
	System.out.println("Calculated Fitness Test Mean: " + myTestAverage); // Calculate Average
	// *******************************************************

	// Testing Q3 Generate Binary String: GenerateBinaryString Method

	/*
	 * String RandomStartStr = RandomStartingPoint(); char[][] Testboard =
	 * CreateMyBoard(RandomStartStr); String TestGBS =
	 * GenerateBinaryString(Testboard); PrintMyBoard(Testboard);
	 * System.out.println("1st Binary String: " + RandomStartStr);
	 * System.out.println("2nd Binary String: " + TestGBS); if
	 * (RandomStartStr.equals(TestGBS)) {
	 * System.out.println("Both of them are equal: " + TestGBS); }
	 * 
	 */
	// End of testing GenerateBinaryString

	}
// ******************** End of Void Main **************************************


// ******************** My Methods starts here ************************************

	// ************************ Q1 Check Character ******************

	// Write a method, that returns true or false depending on
	// whether a
	// character (Character) [the input] is a valid board square as
	// defined/described above. Note that null is not valid.
	public static boolean isValidBoardSquare(Character myChar1) {
		char myQueenChar = 'Q';
		char myEmptySquareChar = '.';

		if (myChar1.equals(null)) { // gerekli degilse koyma zaten asagida kontrol ediliyor.
			return false;
		}
		// Tum bu sartlar saglaniyor ise True doner yoksa False doner
		return !myChar1.equals(null) && (myChar1.equals(myQueenChar) || myChar1.equals(myEmptySquareChar));

	}

	// ***********************************************************************

	// ************************ Q2 Valid Board ******************

	// Write a method that returns true if the input board (Character
	// array) is a valid board (as defined/described above), return false
	// otherwise. Note that valid means the board is the correct size, not
	// null, each square contains a valid character and there are exactly
	// eight queens. For this question, you DO NOT need to check that the
	// board is correct
	public static boolean isValidBoard(char[][] board2) {
		int myRowsOfBoard = 8; // Number of Rows
		int myColumnsOfBoard = 8; // Number of Columns
		char myQueenChar = 'Q';
		// char myEmptySquareChar = '.';

		if (board2 == null || board2[0].length != myRowsOfBoard || board2.length != myColumnsOfBoard) {
			return false; // iceri gonderilen board bos ise boyutlari beklenen gibi degilse false donuyor
		}

		int queensCnt = 0;
		for (int i = 0; i < myRowsOfBoard; i++) {
			if (board2[i] == null || board2[i].length != myRowsOfBoard || board2.length != myColumnsOfBoard) {
				// Yukaridaki if de ayni seyi yapiyor. Ikisinden biri gereksiz.
				return false; // iceri gonderilen board bos ise boyutlari beklenen gibi degilse false donuyor
			}
			for (int j = 0; j < myColumnsOfBoard; j++) {
				if (isValidBoardSquare(board2[i][j]) != true) {
					return false; // Board square lerden biri bos ise yada Q veya . icermiyor ise false doner
				}
				if (board2[i][j] == myQueenChar) {
					queensCnt++;
				}
			}
		}

		if (queensCnt == 8) {
			return true; // Queen sayisi tam olarak 8 ise true doner
		} else {
			return false; // Queen sayisi tam olarak 8 degil ise false doner
		}
	}

	// ***********************************************************************


	// ************************ Q3 Generate Binary String ******************

	// Q3 Generate Binary String: Write a method to convert an input board
	// (Character array) to the
	// corresponding binary String. Assume that the board is valid.
	private static String GenerateBinaryString(char[][] board3) {
		// Void main de kapali olarak nasil kullanilacagi var
		int myRowsOfBoard = 8; // Number of Rows
		int myColumnsOfBoard = 8; // Number of Columns
		char myQueenChar = 'Q'; // Queen character
		// char myEmptySquareChar = '.'; // Empty cell character

		String myReturnString = ""; // never make it null

		if (board3 == null || board3[0].length != myRowsOfBoard || board3.length != myColumnsOfBoard) {
			return myReturnString; // bos board geri donduruluyo. hoca baska bisey isteyebilir
			// return "ahmet";
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

	// Q4 Initial Starting Point: Write a method that creates the initial random
	// starting point (arrangement).
	// The random starting arrangement should be of a binary String format.
	private static String RandomStartingPoint() {

		String myString = new String();
		int myScasolSize = 24; // Binary String Size

		// Create a random binary string of just ones and zeros of length myScasolSize
		for (int i = 0; i < myScasolSize; ++i) {
			int myRandomGeneratedInt = CS2004.UI(0, 1); // RandomIntegerGenerator(0, 1);
			myString = myString + Integer.toString(myRandomGeneratedInt);
		}
		return myString;
	}

	// Create a uniformly distributed random integer between aa and bb inclusive
	public static int RandomIntegerGenerator(int aa, int bb) {
		Random rand;
		int minVal = Math.min(aa, bb);
		int maxVal = Math.max(aa, bb);

		rand = new Random();
		int upperRandomVal = maxVal - minVal + 1;
		int randomVal = rand.nextInt(upperRandomVal) + minVal;
		return (randomVal);
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
			return myBoard; // bos board geri donduruluyo. hoca baska bisey isteyebilir
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

	// Fitness Method uses CreateMyBoard method
	private static int Fitness(String myStr5_1) { // Calculate Fitness

		int myMaxAttack = 56; // Maximum attack count 8*7
		char myQueenChar = 'Q'; // Queen character
		// char myEmptySquareChar = '.'; // Empty cell character
		int myRowsOfBoard = 8; // Number of Rows
		int myColumnsOfBoard = 8; // Number of Columns
		int myAttackCnt = 0;

		if (myStr5_1 == null || myStr5_1.isEmpty() || myStr5_1.length() != 24) {
			return Integer.MIN_VALUE; // hoca return olarak farkli bisey isteyebilir
			// return 0;
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

	// Q6 Small Change Operator
	// Write a method that takes in a binary String as input, flips a single
	// random bit of the String then returns the changed copy of the
	// arrangement as output. Make sure that the modified copy of the
	// solution is returned.
	public static String SmallChange(String scasol6) {

		if (scasol6 == null || scasol6.isEmpty() || scasol6.length() != 24) {
			return null; // hoca return olarak farkli bisey isteyebilir

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
		// hoca return olarak farkli bisey isteyebilir
		return null; // if initial and changed value are same return null
		// return "000000000000000000000000";
		// return "111111111111111111111111";
	}

	}

	// ***********************************************************************

	// ************************** Q7 Solving the Problem ****************


	public static String Solve(int iterationLimit7) {

		int myMaxAttack = 56; // Maximum attack count 8*7
		String currentScasol = null;

		if (iterationLimit7 <= 1) {
			return currentScasol; // hoca return olarak farkli bisey isteyebilir
		}

		// if (iterationLimit <= 1) { //iterasyon > 1 ise diye israrla istenirse
		// yukaridaki if'i sil bunu ac

			currentScasol = RandomStartingPoint();
			int currentFitness = Fitness(currentScasol);

			int iter = 0;

			while (currentFitness != myMaxAttack && iter < iterationLimit7) {
				String newScasol;
				int newFitness;

				newScasol = SmallChange(currentScasol);
				newFitness = Fitness(newScasol);

				// DIKKAT CODERUNNER ">=" i KABUL ETMEZSE HOCA SADECE ">" ISTIYOR OLABILIR, "="
				// i sil

				if (newFitness >= currentFitness) { // burasi

					currentFitness = newFitness;
					currentScasol = newScasol;
				}


				iter++;
			}

//  } //iterasyon > 1 ise diye israrla isteniyorsa bunu ac

		return currentScasol;
	}

	// ***********************************************************************




	// Print board on the screen
	// Void main de kapali olarak nasil kullanilacagi var
	private static void PrintMyBoard(char[][] myBoard9) {
		int myRowsOfBoard = 8; // Number of Rows
		int myColumnsOfBoard = 8; // Number of Columns

		for (int row = 0; row < myRowsOfBoard; row++) {
			for (int col = 0; col < myColumnsOfBoard; col++) {
				System.out.print(myBoard9[row][col] + " ");
			}
			System.out.println();
		}
	}
}
