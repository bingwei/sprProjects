package com.smart.dao.jdbc;

import com.smart.dao.ViewPointDao;
import com.smart.domain.ViewPoint;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class ViewPointJdbcDao extends JdbcDaoSupport implements ViewPointDao {

	public void addViewPoint(final ViewPoint viewPoint) {
        String sql = " INSERT INTO t_view_point(space_id,point_name,ticket_price,description)"
                + " VALUES(?,?)";

        Object[] params = new Object[]{viewPoint.getSpaceId(),viewPoint.getPointName(),viewPoint.getTicketPrice(),viewPoint.getDescription()};
        getJdbcTemplate().update(sql, params);
	}

}
