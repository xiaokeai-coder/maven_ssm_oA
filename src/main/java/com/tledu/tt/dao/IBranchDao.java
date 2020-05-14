package com.tledu.tt.dao;

import java.util.List;

import com.tledu.tt.model.Branch;
import com.tledu.tt.util.Pager;

public interface IBranchDao {

	public int find_count(String sreach);

	public Pager<Branch> find(String sreach, int page, int limit);

	public void add(Branch branch);

	public void update(Branch branch);

	public void delete(int id);

	public List<Branch> list();

	public Branch load(int id);
}
