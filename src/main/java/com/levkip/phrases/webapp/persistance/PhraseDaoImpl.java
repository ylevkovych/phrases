package com.levkip.phrases.webapp.persistance;

import com.levkip.phrases.domain.Phrase;
import com.levkip.phrases.exception.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class PhraseDaoImpl implements PhraseDao{

    private static Logger logger = LoggerFactory.getLogger(PhraseDaoImpl.class);

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("dataSourcePhrases")
    public void setDataSource(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Phrase> getPhrases() {

        logger.debug("get all phrases");

        String sql = "SELECT * FROM phrase";

        logger.debug("Query: ["+sql+"]");

        try
        {
            return this.jdbcTemplate.query(sql, new PhraseRowMapper());
        } catch (EmptyResultDataAccessException erdae)
        {
            return Collections.emptyList();
        } catch (DataAccessException dae)
        {
            logger.warn(dae.getLocalizedMessage());
            throw new PersistenceException(dae.getLocalizedMessage());
        }
    }

    @Override
    public List<Phrase> searchPhrase(String searchPhrase, boolean whole) {

        logger.debug("phrase: "+searchPhrase+", whole: "+whole);

        String sql = "SELECT * FROM phrase";

        String searchClause = "";
        if (!StringUtils.isEmpty(searchPhrase))
        {
            String wholeClause = whole ? "" : "%";
            String searchEquality = whole ? " = " : " LIKE ";
            String searchRightClause = searchEquality + " " + wholeClause + searchPhrase + wholeClause;

            searchClause += " phrase " + searchRightClause;
            searchClause += " OR description " + searchRightClause;
            searchClause += " OR transcription " + searchRightClause;
        }

        if (!StringUtils.isEmpty(searchClause))
        {
            sql += " WHERE " + searchClause;
        }

        logger.debug("Query: ["+sql+"]");

        try
        {
            return this.jdbcTemplate.query(sql, new PhraseRowMapper());
        } catch (EmptyResultDataAccessException erdae)
        {
            return Collections.emptyList();
        } catch (DataAccessException dae)
        {
            logger.debug(dae.getLocalizedMessage());
            throw new PersistenceException(dae.getLocalizedMessage());
        }

    }

    @Override
    public Phrase getPhrase(Long id) {
        return null;
    }

    @Override
    public Long addPhrase(Phrase phrase) {

        logger.debug(phrase.toString());

        String sql  = "INSERT INTO phrase (id, phrase, transcription, description, created) VALUES (?,?,?,?,?)";

        logger.debug("Query: ["+sql+"]");

        Long nextId = getNextId();

        try
        {
            this.jdbcTemplate.update(sql, new Object[] {
                    nextId,
                    phrase.getPhrase(),
                    phrase.getTranscription(),
                    phrase.getDescription(),
                    System.currentTimeMillis()});

            return nextId;
        } catch (DataAccessException dae)
        {
            logger.debug(dae.getLocalizedMessage());
            throw new PersistenceException(dae.getLocalizedMessage());
        }

    }

    private Long getNextId() {
        Long nextId = null;

        String sql = "SELECT max(id)+1 FROM phrase";

        logger.debug("Query: ["+sql+"]");

        try
        {
            nextId = this.jdbcTemplate.queryForObject(sql, Long.class);
        } catch (DataAccessException dae)
        {
            ;
        }

        return nextId == null ? 0L : nextId;

    }

    @Override
    public boolean updatePhrase(Phrase phrase, Long id) {
        logger.debug("phrase: "+phrase);

        String sql = "UPDATE phrase SET phrase=?,description=?,transcription=?,created=? WHERE id = ?";

        logger.debug("Query: ["+sql+"]");

        try
        {
            return this.jdbcTemplate.update(sql, new Object[] {
                    phrase.getPhrase(),
                    phrase.getDescription(),
                    phrase.getTranscription(),
                    System.currentTimeMillis(),
                    id
            }) > 0;
        } catch (DataAccessException dae)
        {
            logger.warn(dae.getLocalizedMessage());
            throw new PersistenceException(dae.getLocalizedMessage());
        }

    }

    @Override
    public boolean deletePhrase(Long id) {
        logger.debug("id :"+id);

        String sql = "DELETE FROM phrase WHERE id=?";

        logger.debug("Query: ["+sql+"]");

        try
        {
            return this.jdbcTemplate.update(sql, new Object[] {id}) > 0;
        } catch (DataAccessException dae)
        {
            logger.warn(dae.getLocalizedMessage());
            throw new PersistenceException(dae.getLocalizedMessage());
        }

    }



    private class PhraseRowMapper implements RowMapper<Phrase> {
        @Override
        public Phrase mapRow(ResultSet rs, int rowNum) throws SQLException {
            Phrase phrase = new Phrase();

            phrase.setId(rs.getLong("id"));
            phrase.setDate(rs.getLong("created"));
            phrase.setDescription(rs.getString("description"));
            phrase.setPhrase(rs.getString("phrase"));
            phrase.setTranscription(rs.getString("transcription"));
            return phrase;
        }
    }
}
