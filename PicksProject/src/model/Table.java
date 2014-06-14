package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class Table<Row, Column, Value> {

	
	private Map<Row, Map<Column, Value>> map;
	
	
	public Table()
	{
		map = new HashMap<Row, Map<Column,Value>>();
	}
	
	
	public Value get(Row r, Column c) {
		Map<Column,Value> rowMap = map.get(r);
		
		if (rowMap == null)
		{
			return null;
		} else
		{
			return rowMap.get(c);
		}

	}

	public void put(Row r, Column c, Value v) {
		Map<Column,Value> rowMap = map.get(r);
		
		if (rowMap == null)
		{
			rowMap = new HashMap<Column,Value>();
			map.put(r, rowMap);
		} 
		
		rowMap.put(c, v);
		
		
	}

	public Map<Column,Value> row(Row r) {
		
		Map<Column,Value> row = map.get(r);
		
		if (row == null)
		{
			return Collections.emptyMap();
		} else
		{
			return row;
		}
		
		
		

	}
	
	
	
	public Map<Row,Value> column(Column c)
	{
		Map<Row,Value> col = new HashMap<Row,Value>();
		
		for (Entry<Row, Map<Column, Value>> entry : map.entrySet())
		{
			Row row = entry.getKey();
			Map<Column,Value> rowMap = entry.getValue();
			
			Value val = rowMap.get(c);
			
			if (val != null)
			{
				col.put(row, val);
			}
			
			
		}
		
		
		return col;
	}
	
	
	
	public Set<Column> columnKeySet()
	{
		Set<Column> columns = new HashSet<Column>();
		
		for (Map<Column,Value> row : map.values())
		{
			columns.addAll(row.keySet());
		}
		
		
		return columns;
		
	}
	
	
	
	public Set<Row> rowKeySet()
	{
		return map.keySet();
	}
	
	

}
