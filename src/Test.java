public class Test {
	public static void main(final String[] args) {

		for (int i = 8; i <= 13; i++) {
			int o = fac(i);
			System.out.println(i + "! \t= " + o);
		}
		System.out.println("2^32 -1 = 4294967295");
		int h = fac(14);
		System.out.println("14! \t= " + h + "\t FEHLER");
	}

	/*
	 * int a; int b; int p; int q; //int x;
	 * 
	 * a=2; b=4; p=11; q=9;
	 * 
	 * System.out.println("\ta="+a+", b="+b+", p="+p+", q="+q); for (int u = -5; u<6; u++) { for (int v = -30; v<30; v++) { if ((u*p + v*q) == 1) {
	 * System.out.printf("u= " +u+ ",\tv= " +v); System.out.println(";\t->\tvqa + upb % pq = " +(v*q*a+u*p*b)+ " % " +p*q+ " = " +
	 * ((v*q*a+u*p*b)%(p*q))); } } } }
	 */
	public static int fac(int p) {

		int g = p;
		while (g > 1) {
			p *= --g;

		}
		return p;
	}
}
