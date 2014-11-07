package com.smart.dao;

import com.smart.domain.ViewSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlFunction;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@Repository
public class ViewSpaceOODao {
    @Autowired
    private DataSource dataSource;

    private ViewSpaceQuery viewSpaceQuery;//①声明ViewSpaceQuery变量

    private ViewSpaceInsert viewSpaceInsert;

    private GetViewPointNum viewPointNum;

    private SqlFunction<Integer> viewSpaceNumCount;

    @PostConstruct
    public void init() {
        this.viewSpaceQuery = new ViewSpaceQuery(this.dataSource); //②初始化ForumQuery对象
        this.viewSpaceInsert = new ViewSpaceInsert(this.dataSource);
        this.viewPointNum = new GetViewPointNum(this.dataSource);
        this.viewSpaceNumCount = new SqlFunction<Integer>(dataSource, "SELECT COUNT(*) FROM t_view_space");
        this.viewSpaceNumCount.compile();
    }

    public ViewSpace getViewSpace(int spaceId) {
        return viewSpaceQuery.findObject(spaceId); //③执行查询并返回结果
    }

    //④定义MappingSqlQuery
    private class ViewSpaceQuery extends MappingSqlQuery<ViewSpace> {
        public ViewSpaceQuery(DataSource ds) {//⑤定义查询语句并预编译
            super(ds, "SELECT space_id,space_name, description FROM t_view_space WHERE space_id=?");
            declareParameter(new SqlParameter(Types.INTEGER));
            compile();//⑤-1不能忘记这行编译语句，否则会发生错误
        }

        public ViewSpace mapRow(ResultSet rs, int rownum) throws SQLException {
            ViewSpace viewSpace = new ViewSpace();
            viewSpace.setSpaceId(rs.getInt("space_id"));
            viewSpace.setSpaceName(rs.getString("space_name"));
            viewSpace.setDescription(rs.getString("description"));
            return viewSpace;
        }
    }

    //①新增Forum对象
    public void addViewSpace(ViewSpace viewSpace) {
        viewSpaceInsert.insert(viewSpace);
    }

    //②扩展SqlUpdate定义子类
    private class ViewSpaceInsert extends SqlUpdate {
        public ViewSpaceInsert(DataSource ds) {//③定义SQL语句并预编译
            super(ds, "INSERT INTO t_view_space(space_name, description) VALUES(:spaceName,:description)");
            declareParameter(new SqlParameter("spaceName", Types.VARCHAR));
            declareParameter(new SqlParameter("description", Types.VARCHAR));
            compile();
        }

        public void insert(ViewSpace viewSpace) {//④绑定参数
            Map<String, String> params = new HashMap<String, String>();
            params.put("spaceName", viewSpace.getSpaceName());
            params.put("description", viewSpace.getDescription());
            super.updateByNamedParam(params);
        }
    }


    public int getViewPointNum(int userId) {
        return viewPointNum.getViewPointNum(userId);
    }

    //①扩展StoredProcedure
    private class GetViewPointNum extends StoredProcedure {
        private static final String SQL = "P_GET_VIEW_POINT_NUM";//②定义存储过程名

        public GetViewPointNum(DataSource ds) {
            setDataSource(ds);
            setSql(SQL);
            //③声明入参
            declareParameter(new SqlParameter("spaceId", Types.INTEGER));
            //④声明出参
            declareParameter(new SqlOutParameter("outNum", Types.INTEGER));
            compile();
        }

        public int getViewPointNum(int userId) {//⑤执行存储过程并返回结果
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("userId", userId);
            Map<String, Object> outMap = execute(map);
            return (Integer) outMap.get("outNum");
        }
    }

    public int getViewSpaceNum() {
        return viewSpaceNumCount.run();
    }
}
