package model;

public class FactoryProducer {
	
	private static final QueryFactory qf = new QueryFactory ();
	
	public static AbstractFactory getFactory (int n) {
		switch (n) {
			case 1:
				return qf;
		}
		
		return null;
	}
	
}