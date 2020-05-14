package com.tledu.tt.dao;

import com.tledu.tt.model.OperationLog;
import com.tledu.tt.util.Pager;

public interface IOperationLogDao {
	public void add(OperationLog operationLog);
	
	public int find_count(String sreach);

	public Pager<OperationLog> find(String sreach, int page, int limit);
	
}
