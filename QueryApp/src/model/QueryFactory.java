package model;

public class QueryFactory extends AbstractFactory {
	
	private static final Query1 q1 = new Query1 ();
	private static final Query2 q2 = new Query2 ();
	private static final Query3 q3 = new Query3 ();
	private static final Query4 q4 = new Query4 ();
	private static final Query5 q5 = new Query5 ();
	private static final Query6 q6 = new Query6 ();
	private static final Query7 q7 = new Query7 ();
	private static final Query8 q8 = new Query8 ();
	
	public QueryObject getQueryObject (int queryNum) {
		switch (queryNum) {
			case 1: return q1;
			case 2: return q2;
			case 3: return q3;
			case 4: return q4;
			case 5: return q5;
			case 6: return q6;
			case 7: return q7;
			case 8: return q8;
		}
		
		return null;
	}
	
}