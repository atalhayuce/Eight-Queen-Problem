
public class dene {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int myMaxIterations = 500; // Maximum iteration to find the result

		String SolutionStr = Final8Queen.Solve(myMaxIterations); // Calculate Fitness Value (56 - Attacks)
		System.out.println(SolutionStr);

	}

}
