package model;

public class FactoryProducer {
	
	public static AbstractFactory getFactory (int n) {
		switch (n) {
			case 1:
				return new QueryFactory ();
		}
		
		return null;
	}
	
}