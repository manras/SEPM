package Testpaket2;
public class euklid {
	
	public static void main(final String[] args) {

		int a = 36;
		int b;
		int ggt;
		int bInvers;
		
		for (b = 1; b<a; b++) {
			ggt = euclidianAlgorithm(a,b);
			if (ggt == 1) {
				System.out.printf("GgT ist 1 mit a = " + a + ", b = " + b);
				
				for (bInvers = 1; bInvers<a; bInvers++)
					if (((b*bInvers)%a) == 1) {
						System.out.println(".\t Damit ist b^(-1) = " + bInvers +".");
					}
				}
			}
		}
	
	static int euclidianAlgorithm(int a, int b) {
		int gcd;
		while (a != b) {
			if (a > b) {
				a = a - b;
			} else {
				b = b - a;
			}
		}
		gcd = a;
		return gcd;
	}
}