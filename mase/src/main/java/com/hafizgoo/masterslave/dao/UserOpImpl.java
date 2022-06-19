package com.hafizgoo.masterslave.dao;


import com.hafizgoo.masterslave.config.AnnoDataSource;
import com.hafizgoo.masterslave.config.MultiDatasource;
import com.hafizgoo.masterslave.service.UserOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOpImpl implements UserOp {

    @Autowired
    private MultiDatasource multiDatasource;

    @Override
    @AnnoDataSource("slave")
    public User getUserById(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(multiDatasource);
        RowMapper<User> userRowMapper = (resultSet, i) -> new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3));
        List<User> query = jdbcTemplate.query(String.format("select * from user where id = %d", id), userRowMapper);
        return query.get(0);
    }

    @Override
    @AnnoDataSource("master")
    public void addUser(User user) {
        String sql = "insert into user values(?,?,?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(multiDatasource);
        jdbcTemplate.update(sql, user.getId(),user.getName(),user.getAge());
    }
}
