

/*//sum of products of digits of the same number
import java.io.*;

public class TestSocket {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a number- ");
		int num=Integer.parseInt(br.readLine());
		int numCopy=num;
		int sumOfProducts=0;
		int rem=numCopy%10;
		for(int i=1;numCopy>0;i++) {
			numCopy/=10;
			int nextRem=numCopy%10;
			sumOfProducts+=(rem*nextRem);
			rem=nextRem;
		}
		System.out.println(sumOfProducts);
	}
}*/

/*//sum of products of digits
import java.io.*;

public class TestSocket {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter two numbers- ");
		int num1=Integer.parseInt(br.readLine());
		int num2=Integer.parseInt(br.readLine());
		int digitsCounter1=0, digitsCounter2=0;
		int numCopy1=num1, numCopy2=num2;
		for(int i=0;numCopy1>0;i++) {
			numCopy1/=10;
			digitsCounter1++;
		}
		for(int i=0;numCopy2>0;i++) {
			numCopy2/=10;
			digitsCounter2++;
		}
		if(digitsCounter1!=digitsCounter2) {
			System.out.println("Error!");
			System.exit(0);
		}
		numCopy1=num1;
		numCopy2=num2;
		int sumOfProducts=0;
		for(int i=0;numCopy1>0&&numCopy2>0;i++) {
			sumOfProducts+=((numCopy1%10)*(numCopy2%10));
			numCopy1/=10;
			numCopy2/=10;
		}
		System.out.println(sumOfProducts);
	}
}
*/
/*//prime digit sum
import java.io.*;

public class TestSocket {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a number-");
		int num=Integer.parseInt(br.readLine());
		int numCopy=num;
		int digitCounter=0, primeSum=0;
		for(int i=0;numCopy>0;i++) {
			int rem=numCopy%10;
			boolean isPrime=checkPrime(rem);
			if(isPrime)
				primeSum+=rem;
			numCopy/=10;
		}
		System.out.println(primeSum);
	}
	public static boolean checkPrime(int rem) {
		int flag=0;
		boolean status=false;
		for(int i=2;i<=rem/2;i++)
			if(rem%i==0) {
				flag++;
				break;
			}
		if(flag==0)
			status=true;
		else
			status=false;
		return status;
	}
}*/