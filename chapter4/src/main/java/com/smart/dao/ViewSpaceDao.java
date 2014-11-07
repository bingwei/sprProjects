package com.smart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.smart.domain.ViewSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class  ViewSpaceDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void initDb() {
		String sql = "create table t_user(user_id int primary key,user_name varchar(60))";
		jdbcTemplate.execute(sql);
	}

	/**
	 * 更新一条数据
	 * 
	 * @param forum
	 */
	public void addViewSpace(final ViewSpace viewSpace) {
		final String sql = "INSERT INTO t_view_space(space_name,description,address) VALUES(?,?,?)";
		Object[] params = new Object[] { viewSpace.getSpaceName(),viewSpace.getDescription(),viewSpace.getAddress() };
		// 方式1
		// jdbcTemplate.update(sql, params);

		// 方式2
		// jdbcTemplate.update(sql, params,new
		// int[]{Types.VARCHAR,Types.VARCHAR});

		// 方式3
		/*
		 * jdbcTemplate.update(sql, new PreparedStatementSetter() { public void
		 * setValues(PreparedStatement ps) throws SQLException { ps.setString(1,
		 * forum.getForumName()); ps.setString(2, forum.getForumDesc()); } });
		 */

		// 方式4
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, viewSpace.getSpaceName());
				ps.setString(2, viewSpace.getDescription());
				ps.setString(3, viewSpace.getAddress());
				return ps;
			}
		}, keyHolder);
        viewSpace.setSpaceId(keyHolder.getKey().intValue());
	}

	public void addViewSpaceByNamedParams(final ViewSpace viewSpace) {
		final String sql = "INSERT INTO t_view_space(space_name,description,address) VALUES(:spaceName,:description,:address)";
		SqlParameterSource sps = new BeanPropertySqlParameterSource(viewSpace);
		namedParameterJdbcTemplate.update(sql, sps);
	}

	/**
	 * 批量更新数据
	 * 
	 * @param forums
	 */
	public void addViewSpaces(final List<ViewSpace> viewSpaces) {
        final String sql = "INSERT INTO t_view_space(space_name,description,address) VALUES(?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public int getBatchSize() {
                return viewSpaces.size();
            }

            public void setValues(PreparedStatement ps, int index)
                    throws SQLException {
                ViewSpace viewSpace = viewSpaces.get(index);
                ps.setString(1, viewSpace.getSpaceName());
                ps.setString(2, viewSpace.getDescription());
                ps.setString(3, viewSpace.getAddress());
            }
        });
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public int getBatchSize() {
				return viewSpaces.size();
			}

			public void setValues(PreparedStatement ps, int index)
					throws SQLException {
                ViewSpace viewSpace = viewSpaces.get(index);
				ps.setString(1, viewSpace.getSpaceName());
				ps.setString(2, viewSpace.getDescription());
				ps.setString(3, viewSpace.getAddress());
			}
		});
	}

	/**
	 * 根据ID获取Forum对象
	 * 
	 * @param forumId
	 * @return
	 */
	public ViewSpace getViewSpace(final int spaceId) {
		String sql = "SELECT space_name,description,address FROM t_view_space WHERE space_id=?";
		final ViewSpace viewSpace = new ViewSpace();

		jdbcTemplate.query(sql, new Object[] { spaceId },
				new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
                        viewSpace.setSpaceId(spaceId);
                        viewSpace.setSpaceName(rs.getString("space_name"));
                        viewSpace.setDescription(rs.getString("description"));
                        viewSpace.setAddress(rs.getString("address"));
					}
				});
		return viewSpace;
	}

	public List<ViewSpace> getViewSpaces(final int fromId, final int toId) {
		String sql = "SELECT space_id, SPACE_NAME,description,address FROM t_view_space WHERE space_id between ? and ?";
		// 方法1：使用RowCallbackHandler接口
/*
		 * final List<ViewSpace> viewSpaces = new ArrayList<ViewSpace>();
		 * jdbcTemplate.query(sql,new Object[]{fromId,toId},new
		 * RowCallbackHandler(){ public void processRow(ResultSet rs) throws
		 * SQLException {
		 * ViewSpace viewSpace = new ViewSpace();
		 * viewSpace.setSpaceId(rs.getInt("space_id"));
		 * viewSpace.setSpaceName(rs.getString("space_name"));
		 * viewSpace.setDescription(rs.getString("description"));
		 * viewSpace.setAddress(rs.getString("address"));
		 * viewSpaces.add(viewSpace);
		 * }}); return forums;
*/

		return jdbcTemplate.query(sql, new Object[] { fromId, toId },
				new RowMapper<ViewSpace>() {
					public ViewSpace mapRow(ResultSet rs, int index)
							throws SQLException {
                        ViewSpace viewSpace = new ViewSpace();
                        viewSpace.setSpaceId(rs.getInt("space_id"));
                        viewSpace.setSpaceName(rs.getString("space_name"));
                        viewSpace.setDescription(rs.getString("description"));
                        viewSpace.setAddress(rs.getString("address"));
						return viewSpace;
					}
				});

	}

}
