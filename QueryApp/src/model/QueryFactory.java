package model;

public class QueryFactory extends AbstractFactory {
	
	public QueryObject getQueryObject (int queryNum) {
		switch (queryNum) {
			case 1:
				return new Query1 ();
		}
		
		return null;
	}
	
}