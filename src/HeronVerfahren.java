
public class HeronVerfahren {
	public static void main(String...args) {
		double a=27;
		double x=a/2.0;
		for(int i=0;i<20;i++)  {
			x = (x+(a/x))/2;
			System.out.println(i+". Annäherung: "+x);
		}
	}
}
