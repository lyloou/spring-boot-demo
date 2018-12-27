package com.lyloou.demo.typehandler;

import com.lyloou.demo.pojo.SexEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.TINYINT)
@MappedTypes(SexEnum.class)
public class SexTypeHandler implements TypeHandler<SexEnum> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setInt(i, SexEnum.MALE.ordinal()); // default is male
        } else {
            ps.setInt(i, parameter.getCode());
        }
    }

    @Override
    public SexEnum getResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        return SexEnum.getSexByCode(i);
    }

    @Override
    public SexEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        return SexEnum.getSexByCode(i);
    }

    @Override
    public SexEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        return SexEnum.getSexByCode(i);
    }
}
