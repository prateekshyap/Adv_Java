import java.rmi.*;
import java.rmi.server.*;

public class Implementation extends UnicastRemoteObject implements Interface {
	//automatic constructor
	public Implementation () throws RemoteException {
		super ();
	}

	//add method
	@Override
	public int add (int num1, int num2) {
		return num1+num2;
	}
	
	//subtract method
	@Override
	public int subtract (int num1, int num2) {
		return num1-num2;
	}

	//multiply method
	@Override
	public int multiply (int num1, int num2) {
		return num1*num2;
	}

	//divide method
	@Override
	public int divide (int num1, int num2) {
		return num1/num2;
	}
}