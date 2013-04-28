package Testpaket2;

public class NewtonVerfahren {
	public static void main(String...args) {
		int n=20;
		double x=0.0;
		for(int i=1;i<n;i++) {
			x=x-(f(x)/f1(x));
			System.out.println(i+". Durchgang: "+x);
		}
	}
	
	private static double f(double x) {
		return Math.pow(x,3.0)-2*x+2;
	}
	
	private static double f1(double x) {
		return Math.pow(x,2)*3-2;
	}
}
