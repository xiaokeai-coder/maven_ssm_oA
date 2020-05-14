package com.tledu.tt.service;

import com.tledu.tt.model.OperationLog;
import com.tledu.tt.util.Pager;

public interface IOperationLogService {

	public void add(OperationLog operationLog);

	public Pager<OperationLog> find(String sreach, int page, int limit);

}
