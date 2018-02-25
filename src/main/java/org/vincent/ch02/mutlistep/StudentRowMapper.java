package org.vincent.ch02.mutlistep;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * 数据库一行记录转换为一个javaBean实例对象映射
 * @Package: org.vincent.mutlistep <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 */

public class StudentRowMapper implements RowMapper{
		/**
		 * Implementations must implement this method to map each row of data
		 * in the ResultSet. This method should not call {@code next()} on
		 * the ResultSet; it is only supposed to map values of the current row.
		 *
		 * @param rs     the ResultSet to map (pre-initialized for the current row)
		 * @param rowNum the number of the current row
		 * @return the result object for the current row
		 * @throws SQLException if a SQLException is encountered getting
		 *                      column values (that is, there's no need to catch SQLException)
		 */
		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student=new Student();
				student.setId(rs.getInt("id"));
				student.setAge(rs.getInt("age"));
				student.setName(rs.getString("name"));
				student.setAddress(rs.getString("address"));
				return student;
		}
}
