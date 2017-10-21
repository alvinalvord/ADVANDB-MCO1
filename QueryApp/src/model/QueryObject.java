package model;

import java.util.*;
import javafx.beans.property.*;

public abstract class QueryObject extends Model {
	
	protected DBConnection dbc;
	protected int viewing;
	protected List <String> variants;
	protected StringProperty query;
	protected LongProperty duration;
	protected Table table;
	
	public QueryObject () {
		dbc = DBConnection.getConnection ();
		viewing = 0;
		variants = new ArrayList <> ();
		query = new SimpleStringProperty ();
		duration = new SimpleLongProperty ();
		table = null;
	}
	
	public StringProperty queryProperty () {
		return query;
	}
	
	public void setQuery (String q) {
		query.set (q);
	}
	
	public String getQuery () {
		return query.get ();
	}
	
	public LongProperty durationProperty () {
		return duration;
	}
	
	public void setDuration (Long duration) {
		this.duration.set (duration);
	}
	
	public long getDuration () {
		return duration.get ();
	}
	
	public void setViewing (int v) {
		if (v < variants.size ())
			viewing = v;
		else
			viewing = 0;
		try {
			prepareUpdates ();
		} catch (Exception e) { e.printStackTrace (); }
	}
	
	public abstract void prepareUpdates () throws Exception;
	
	public Table getTable () {
		return table;
	}
	
	public int countVariants () {
		return variants.size ();
	}
	
}