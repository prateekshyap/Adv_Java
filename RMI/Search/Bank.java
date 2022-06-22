import java.rmi.*;
import java.rmi.server.*;

public class Bank extends UnicastRemoteObject implements BankInterface {
	double mainBalance = 0;
	int pin= 1234;
	double[] arr= new double[5];
	int i=0, flag=0;
	public Bank() throws RemoteException {
		super ();
	}
	public double getMainBalance () {
		if (this.pin==pin)
			return this.mainBalance;
		else
			return -1;
	}
	public double deposit (double amount, int pin) {
		if (this.pin==pin) {
			this.mainBalance+=amount;
			if (i<=3 && flag==0) {
				arr[i]= amount;
				i++;
			}
			else if (i==4 && flag==0) {
				arr[i]= amount;
				flag++;
			}
			else if (i==4 && flag==1) {
				for (int j=0;j<4;j++) {
					arr[j]= arr[j+1];
				}
				arr[4]= amount;
			}
			return this.mainBalance;
		}
		else
			return -1;
	}
	public double withdraw (double amount, int pin) {
		if (this.pin==pin) {
			if (amount <= this.mainBalance) {
				this.mainBalance-=amount;
				if (i<=3 && flag==0) {
					arr[i]= amount-2*amount;
					i++;
				}
				else if (i==4 && flag==0) {
					arr[i]= amount-2*amount;
					flag++;
				}
				else if (i==4 && flag==1) {
					for (int j=0;j<4;j++) {
						arr[j]= arr[j+1];
					}
					arr[4]= amount-2*amount;
				}
				return this.mainBalance;
			}
			else
				return -2;
		}
		else
			return -1;
	}
	public double fastWithdraw (double amount, int pin) {
		if (this.pin==pin) {
			if (amount <= this.mainBalance) {
				this.mainBalance-=amount;
				if (i<=3 && flag==0) {
					arr[i]= amount-2*amount;
					i++;
				}
				else if (i==4 && flag==0) {
					arr[i]= amount-2*amount;
					flag++;
				}
				else if (i==4 && flag==1) {
					for (int j=0;j<4;j++) {
						arr[j]= arr[j+1];
					}
					arr[4]=amount-2*amount;
				}
				return this.mainBalance;
			}
			else
				return -2;
		}
		else
			return -1;
	}
	public int changePin (int halfPin, int oldPin) {
		if (this.pin==oldPin) {
			int randomHalf= (int)(Math.random()*((99-1)+1))+1;
			this.pin= (halfPin*100)+randomHalf;
			return pin;
		}
		else
			return -1;
	}
	public double[] miniStatement () {
		return this.arr;
	}
}