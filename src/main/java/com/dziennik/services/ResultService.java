package com.dziennik.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dziennik.domain.Result;
import com.dziennik.domain.dao.ResultDao;

@Service
public class ResultService {

	private final static Logger	resultServiceLogger = LoggerFactory.getLogger(com.dziennik.services.ResultService.class);
	
	@Autowired
	private ResultDao resultDao;

	public Result findById(int id) {
		Result result = resultDao.findById(id);
		resultServiceLogger.info("Returning result with id: " + id);
			return result;
	}

	public Result create(Result result) {
		resultServiceLogger.info("Creating new result");
		return resultDao.save(result);
	}

	public void delete(Result result) {
		resultServiceLogger.info("Deleting result");
		resultDao.delete(result);
	}
	
}
