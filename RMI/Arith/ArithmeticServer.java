import java.rmi.*;
import java.rmi.registry.*;

public class ArithmeticServer {
	public static void main (String[] args) {
		try {
			Arithmetic skeleton=new TestArithmetic();
			Naming.rebind("rmi://localhost:2000/test",skeleton);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}