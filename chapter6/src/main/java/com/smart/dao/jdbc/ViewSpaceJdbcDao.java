package com.smart.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.smart.dao.ViewSpaceDao;
import com.smart.domain.*;

public class ViewSpaceJdbcDao extends JdbcDaoSupport implements ViewSpaceDao {
    public void addViewSpace(final ViewSpace viewSpace) {
        final String sql = "INSERT INTO t_view_space(space_name,user_id,description) VALUES(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, viewSpace.getSpaceName());
                ps.setInt(2, viewSpace.getUser().getUserId());
                ps.setString(3, viewSpace.getDescription());
                return ps;
            }
        }, keyHolder);
        viewSpace.setSpaceId(keyHolder.getKey().intValue());
    }

    public ViewSpace getViewSpace(final int viewSpaceId) {
        String sql = "SELECT space_name,description FROM t_view_space WHERE space_id=?";
        final ViewSpace viewSpace = new ViewSpace();
        getJdbcTemplate().query(sql, new Object[]{viewSpaceId},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        viewSpace.setSpaceId(viewSpaceId);
                        viewSpace.setSpaceName(rs.getString("space_name"));
                        viewSpace.setDescription(rs.getString("description"));
                    }
                });
        return viewSpace;
    }

    public int getViewSpaceNum() {
        String sql = "SELECT space_id FROM t_view_space ";
        return getJdbcTemplate().queryForInt(sql);
    }

    public void updateViewSpace(final ViewSpace viewSpace) {
        final String sql = "UPDATE  t_view_space SET space_name=?,description=? WHERE space_id=?";
        Object[] params = new Object[]{viewSpace.getSpaceName(), viewSpace.getDescription(), viewSpace.getSpaceId()};
        getJdbcTemplate().update(sql, params);
    }

    public void deleteViewSpace(int spaceId) {
        final String sql = "DELETE FROM t_view_space WHERE forum_id=?";
        Object[] params = new Object[]{spaceId};
        getJdbcTemplate().update(sql, params);
    }
}
