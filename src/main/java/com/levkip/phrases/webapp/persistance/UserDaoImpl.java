package com.levkip.phrases.webapp.persistance;

import com.levkip.phrases.domain.User;
import com.levkip.phrases.exception.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDaoImpl implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("dataSourcePhrases")
    public void setJdbcTemplate(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUserById(Long id) {

        logger.debug("id: "+id);

        String sql = "SELECT * FROM user WHERE id=? LIMIT 0,1";

        try
        {
            return this.jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRowMapper());
        } catch (EmptyResultDataAccessException erdae)
        {
            return null;
        } catch (DataAccessException dae)
        {
            logger.warn(dae.getLocalizedMessage());
            throw new PersistenceException(dae.getLocalizedMessage());
        }

    }

    @Override
    public User getUserByEmail(String email) {

        logger.debug("email: "+email);

        String sql = "SELECT * FROM user WHERE email=? LIMIT 0,1";

        try
        {
            return this.jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserRowMapper());
        } catch (EmptyResultDataAccessException erdae)
        {
            return null;
        } catch (DataAccessException dae)
        {
            logger.warn(dae.getLocalizedMessage());
            throw new PersistenceException(dae.getLocalizedMessage());
        }

    }

    @Override
    public boolean isUserExist(String email, String password) {

        logger.debug("email: "+email+", password: "+password);

        String sql = "SELECT * FROM user WHERE email=? AND password=? LIMIT 0,1";

        try
        {
            return this.jdbcTemplate.queryForObject(sql, new Object[]{email, password}, Boolean.class);
        } catch (EmptyResultDataAccessException erdae)
        {
            return false;
        } catch (DataAccessException dae)
        {
            logger.warn(dae.getLocalizedMessage());
            throw new PersistenceException(dae.getLocalizedMessage());
        }

    }

    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            User user = new User();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));

            return user;
        }
    }
}
