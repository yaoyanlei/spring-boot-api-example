package com.apelab.example.repository.handler;

import com.alibaba.fastjson.JSON;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * JSON自动转换
 */
public class JsonTypeHandler<T extends Object> extends BaseTypeHandler<T> {
  private Class<T> clazz;
  public JsonTypeHandler(Class<T> clazz) {
    if (clazz == null) throw new IllegalArgumentException("Type argument cannot be null");
    this.clazz = clazz;
  }

  @Override
  public void setNonNullParameter(final PreparedStatement ps, final int i, final T parameter,
      final JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, JSON.toJSONString(parameter));
  }

  @Override
  public T getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
    return JSON.parseObject(rs.getString(columnName), clazz);
  }

  @Override
  public T getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
    return JSON.parseObject(rs.getString(columnIndex), clazz);
  }

  @Override
  public T getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
    return JSON.parseObject(cs.getString(columnIndex), clazz);
  }
}
