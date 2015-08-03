package com.ricston.encryptedpwd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class MySQLContactRepository implements ContactRepository {

	protected NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Contact> find(Integer offset, Integer rowCount) {
		offset = offset == null ? 0 : offset;
		rowCount = rowCount == null ? 10 : rowCount;
		final String SQL = "SELECT * FROM Contacts LIMIT :offset,:rowCount";
		return jdbcTemplate.query(SQL,
				new MapSqlParameterSource("offset", offset).addValue("rowCount", rowCount), new RowMapper<Contact>() {

					@Override
					public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
						Contact contact = new Contact();
						contact.setId(rs.getLong("id"));
						contact.setName(rs.getString("name"));
						contact.setEmail(rs.getString("email"));
						contact.setCompany(rs.getString("company"));
						return contact;
					}

				});
	}

}
