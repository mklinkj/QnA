package org.mklinkj.mybatis_study.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mklinkj.mybatis_study.Member;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

public interface MemberMapper {
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(
      id = "MemberResult",
      value = {
        @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
        @Result(column = "pwd", property = "pwd", jdbcType = JdbcType.VARCHAR),
        @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
        @Result(column = "joindate", property = "joinDate", jdbcType = JdbcType.DATE)
      })
  List<Member> selectMany(SelectStatementProvider selectStatement);
}
