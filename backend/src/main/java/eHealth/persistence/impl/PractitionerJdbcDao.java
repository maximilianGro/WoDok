package eHealth.persistence.impl;

import eHealth.entity.Practitioner;
import eHealth.exception.PersistenceException;
import eHealth.persistence.PractitionerDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PractitionerJdbcDao implements PractitionerDao {
    private static final String TABLE_NAME = "practitioner";
    private static final String SQL_SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    private final JdbcTemplate jdbcTemplate;

    public PractitionerJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Practitioner> getAll() {
        try {
            return jdbcTemplate.query(SQL_SELECT_ALL, this::mapRow);
        } catch (DataAccessException e) {
            throw new PersistenceException("Could not query all practitioners", e);
        }
    }

    private Practitioner mapRow(ResultSet result, int rownum) throws SQLException {
        Practitioner practitioner = new Practitioner();
        practitioner.setId(result.getLong("id"));
        practitioner.setName(result.getString("name"));
        return practitioner;
    }
}
