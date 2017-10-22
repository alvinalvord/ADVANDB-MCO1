package model;

public class QueryFactory extends AbstractFactory {
	
	private static final Query1 q1 = new Query1 ();
	
	public QueryObject getQueryObject (int queryNum) {
		switch (queryNum) {
			case 1:
				return q1;
		}
		
		return null;
	}
	
}