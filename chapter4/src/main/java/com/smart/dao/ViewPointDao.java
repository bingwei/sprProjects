package com.smart.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smart.domain.ViewPoint;
import oracle.jdbc.OracleConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.LobRetrievalFailureException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.CallableStatementCreatorFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

@Repository
public class ViewPointDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    private LobHandler lobHandler;

    @Autowired
    private DataFieldMaxValueIncrementer incre;

    public void getNativeConn() {
        try {

            Connection conn = DataSourceUtils.getConnection(getJdbcTemplate().getDataSource());

            conn = jdbcTemplate.getNativeJdbcExtractor().getNativeConnection(conn);

            OracleConnection oconn = (OracleConnection) conn;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addViewPoint(final ViewPoint viewPoint) {
        String sql = " INSERT INTO t_view_point(point_id,space_id,point_name,ticket_price,img_file,description)"
                + " VALUES(?,?,?,?,?,?)";
        jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
            protected void setValues(PreparedStatement ps, LobCreator lobCreator)
                    throws SQLException {
                //1：固定主键
                //ps.setInt(1,1);

                //2：通过自增键指定主键值
                ps.setInt(1, incre.nextIntValue());
                ps.setInt(2, viewPoint.getSpaceId());
                ps.setString(3, viewPoint.getPointName());
                ps.setDouble(4, viewPoint.getTicketPrice());
                lobCreator.setClobAsString(ps, 6, viewPoint.getDescription());
                lobCreator.setBlobAsBytes(ps, 5, viewPoint.getImgFile());
            }
        });

    }

    ;


    public List<ViewPoint> getImgFiles(final int spaceId) {
        String sql = " SELECT point_id,img_file FROM t_view_point where point_id =? and img_file is not null ";
        return jdbcTemplate.query(sql, new Object[]{spaceId},
                new RowMapper<ViewPoint>() {
                    public ViewPoint mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        int pointId = rs.getInt(1);
                        byte[] attach = lobHandler.getBlobAsBytes(rs, 2);
                        ViewPoint viewPoint = new ViewPoint();
                        viewPoint.setPointId(pointId);
                        viewPoint.setImgFile(attach);
                        return viewPoint;
                    }
                });

    }

    ;

    public void getImgFile(final int pointId, final OutputStream os) {
        String sql = " SELECT img_file FROM t_view_point where point_id =? ";
        jdbcTemplate.query(sql, new Object[]{pointId},
                new AbstractLobStreamingResultSetExtractor() {
                    protected void handleNoRowFound() throws LobRetrievalFailureException {
                        System.out.println("Not Found result!");
                    }

                    public void streamData(ResultSet rs) throws SQLException, IOException {
                        InputStream is = lobHandler.getBlobAsBinaryStream(rs, 1);
                        if (is != null) {
                            FileCopyUtils.copy(is, os);
                        }
                    }
                }
        );
    }

    ;

    public double getReplyRate(int userId) {
        String sql = "SELECT topic_replies,topic_views FROM t_topic WHERE user_id=?";
        double rate = jdbcTemplate.queryForObject(sql, new Object[]{userId},
                new RowMapper<Double>() {
                    public Double mapRow(ResultSet rs, int index)
                            throws SQLException {
                        int replies = rs.getInt("topic_replies");
                        int views = rs.getInt("topic_views");
                        if (views > 0)
                            return new Double((double) replies / views);
                        else
                            return new Double(0.0);
                    }
                });
        return rate;
    }

    ;

    public int getSpaceViewPointNum(final int spaceId) {
        String sql = "{call P_GET_VIEW_POINT_NUM(?,?)}";
        //方式1
/*		Integer num = jdbcTemplate.execute(sql,
				new CallableStatementCallback<Integer>() {
					public Integer doInCallableStatement(CallableStatement cs)
							throws SQLException, DataAccessException {
						cs.setInt(1, userId);
						cs.registerOutParameter(2, Types.INTEGER);
						cs.execute();
						return cs.getInt(2);
					}
				});
		return num;*/

        //方式2
        CallableStatementCreatorFactory fac = new CallableStatementCreatorFactory(sql);
        fac.addParameter(new SqlParameter("spaceId", Types.INTEGER));
        fac.addParameter(new SqlOutParameter("pointNum", Types.INTEGER));
        Map<String, Integer> paramsMap = new HashMap<String, Integer>();
        paramsMap.put("spaceId", spaceId);
        CallableStatementCreator csc = fac.newCallableStatementCreator(paramsMap);
        Integer num = jdbcTemplate.execute(csc, new CallableStatementCallback<Integer>() {
            public Integer doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                cs.execute();
                return cs.getInt(2);
            }
        });
        return num;
    }

    public int getUserTopicNum2(int userId) {
        return 0;
    }

    ;

    public SqlRowSet getViewPointRowSet(int spaceId) {
        String sql = "SELECT point_id,point_name FROM t_view_point where space_id =?";
        return jdbcTemplate.queryForRowSet(sql, spaceId);
    }

    ;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
