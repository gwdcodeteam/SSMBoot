package com.rainnie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rainnie.dao.BaseDictDao;
import com.rainnie.po.BaseDict;

@Service("baseDictService")
public class BaseDictServiceImpl implements BaseDictService {

	@Autowired
	private BaseDictDao baseDictDao;

	@Override
	public List<BaseDict> selectBaseDictByTypeCode(String typecode) {

		return baseDictDao.selectBaseDictByTypeCode(typecode);
	}

}
