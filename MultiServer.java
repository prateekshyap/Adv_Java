import java.io.*;
import java.net.*;

public class MultiServer {
	public static int findLcm (int num1, int num2) {
		int n1=num1;
		int n2=num2;
		while(num1!=num2) {
			if(num1>num2)
				num1=num1-num2;
			else
				num2=num2-num1;
		}
		return (n1*n2)/num1;
	}

	public static int findGcd (int num1, int num2) {
		while(num1!=num2) {
			if(num1>num2)
				num1=num1-num2;
			else
				num2=num2-num1;
		}
		return num1;
	}

	public static int reverseNum (int num) {
		int returnVal=0,d;
		while(num>0) {
			d=num%10;
			returnVal=returnVal*10+d;
			num=num/10;
		}
		return returnVal;
	}

	public static double findCompoundInterest (int p, double r, int c, int y) {
		double rt=r/100;
		double f=1+(rt/c);
		int t=c*y;
		double returnVal=p*Math.pow(f,t);
		return returnVal;
	}

	public static void main (String[] args) throws IOException {
		ServerSocket serverSocket=new ServerSocket (10001);
		System.out.println ("Server is waiting at port "+serverSocket.getLocalPort ());
		Socket socket=serverSocket.accept();
		DataOutputStream dos=new DataOutputStream (socket.getOutputStream ());
		dos.writeUTF("1- Find LCM of two numbers\n2- Find GCD of two numbers\n3- Reverse a number\n4- Find the compound interest\nEnter your choice-");
		DataInputStream dis=new DataInputStream (socket.getInputStream ());
		int choice=Integer.parseInt (dis.readUTF ());
		switch (choice) {
			case 1:
				dos.writeUTF("Enter two numbers-");
				int num1=Integer.parseInt(dis.readUTF());
				int num2=Integer.parseInt(dis.readUTF());
				int lcm=findLcm(num1,num2);
				dos.writeUTF("LCM is "+lcm);
				break;
			case 2:
				dos.writeUTF("Enter two numbers-");
				int num3=Integer.parseInt(dis.readUTF());
				int num4=Integer.parseInt(dis.readUTF());
				int gcd=findGcd(num3,num4);
				dos.writeUTF("GCD is "+gcd);
				break;
			case 3:
				dos.writeUTF("Enter a number-");
				int num=Integer.parseInt(dis.readUTF());
				int reversedNum=reverseNum(num);
				dos.writeUTF("After reversing "+reversedNum);
				break;
			case 4:
				dos.writeUTF("Enter principal, rate of interest, number of compounding a year and the total number of years-");
				int principal=Integer.parseInt(dis.readUTF());
				double rate=Double.parseDouble(dis.readUTF());
				int noOfComp=Integer.parseInt(dis.readUTF());
				int years=Integer.parseInt(dis.readUTF());
				double compoundInterest=findCompoundInterest(principal,rate,noOfComp,years);
				dos.writeUTF("Compound interest is "+compoundInterest);
				break;
			default:
				dos.writeUTF("Please Enter A Valid Choice!");
				System.exit(0);
		}
	}
}