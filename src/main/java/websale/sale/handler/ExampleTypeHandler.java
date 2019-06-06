package websale.sale.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.CHAR)
public class ExampleTypeHandler extends BaseTypeHandler<String> {
    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        return super.getResult(rs, columnIndex);
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        return;
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) {
        return null;
    }


}
