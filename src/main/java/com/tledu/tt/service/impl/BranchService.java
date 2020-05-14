package com.tledu.tt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.tt.dao.IBranchDao;
import com.tledu.tt.model.Branch;
import com.tledu.tt.service.IBranchService;
import com.tledu.tt.util.Pager;

@Service("branchService")
public class BranchService implements IBranchService {
	private IBranchDao branchDao;

	public IBranchDao getBranchDao() {
		return branchDao;
	}

	@Autowired
	public void setBranchDao(IBranchDao branchDao) {
		this.branchDao = branchDao;
	}

	@Override
	public Pager<Branch> find(String sreach, int page, int limit) {
		sreach = "%" + sreach + "%";
		return branchDao.find(sreach, page, limit);
	}

	@Override
	public void add(Branch branch) {
		branchDao.add(branch);
	}

	@Override
	public void update(Branch branch) {
		branchDao.update(branch);
	}

	@Override
	public void delete(int id) {
		branchDao.delete(id);
	}

	@Override
	public List<Branch> list() {
		return branchDao.list();
	}

	@Override
	public Branch load(int id) {
		return branchDao.load(id);
	}
}
