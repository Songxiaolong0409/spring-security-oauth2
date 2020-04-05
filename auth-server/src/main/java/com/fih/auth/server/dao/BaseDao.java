package com.fih.auth.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class BaseDao<T> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public <T> T queryForObject(String sql, Object[] args, Class<T> requiredType) throws DataAccessException {
        List<T> list=jdbcTemplate.query(sql,args,new BeanPropertyRowMapper<T>(requiredType));
        if(CollectionUtils.isEmpty(list))
            return null;
        else
            return list.get(0);
    }
}
