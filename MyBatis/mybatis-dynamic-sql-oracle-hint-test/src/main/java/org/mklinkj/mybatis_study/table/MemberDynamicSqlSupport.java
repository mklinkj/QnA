package org.mklinkj.mybatis_study.table;

import java.sql.JDBCType;
import java.time.LocalDate;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class MemberDynamicSqlSupport {
  public static final Member member = new Member();
  public static final SqlColumn<String> id = member.id;
  public static final SqlColumn<String> pwd = member.pwd;

  public static final SqlColumn<String> name = member.name;
  public static final SqlColumn<LocalDate> joinDate = member.joinDate;

  public static final class Member extends SqlTable {
    public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);
    public final SqlColumn<String> pwd = column("pwd", JDBCType.VARCHAR);

    public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);
    public final SqlColumn<LocalDate> joinDate = column("joindate", JDBCType.DATE);

    public Member() {
      super("t_member_test");
    }
  }
}
