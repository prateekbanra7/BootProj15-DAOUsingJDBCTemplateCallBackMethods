package in.abc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import in.abc.bo.StudentBO;

@Repository
public class StudentDaoImpl implements IStudentDao {

	private static final String GET_STUDENT_BY_NO = "SELECT SID,SNAME,SAVG,SADDRESS FROM STUDENT01 WHERE SID=?";
	private static final String GET_STUDENT_BY_NAME = "SELECT SID,SNAME,SAVG,SADDRESS FROM STUDENT01 WHERE SNAME IN (?,?)";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public StudentBO getStudentByNo(int sno) {

		StudentBO bo = null;

		bo = jdbcTemplate.queryForObject(GET_STUDENT_BY_NO, new StudentMapper(), sno);
		System.out.println(bo);
		return bo;
	}

	private static class StudentMapper implements RowMapper<StudentBO> {

		@Override
		public StudentBO mapRow(ResultSet rs, int rowNum) throws SQLException {
			System.out.println("StudentDaoImpl.StudentMapper.mapRow()");
			StudentBO bo = new StudentBO();
			bo.setSid(rs.getInt(1));
			bo.setSname(rs.getString(2));
			bo.setAvg(rs.getFloat(3));
			bo.setSaddress(rs.getString(4));

			return bo;
		}

	}

	@Override
	public List<StudentBO> getStudentByName(String name1, String name2) {
		return jdbcTemplate.query(GET_STUDENT_BY_NAME, new StudentRowMapper(), name1, name2);
	}

	private static class StudentRowMapper implements RowMapper<StudentBO> {
		List<StudentBO> listBo = new ArrayList<StudentBO>();

		@Override
		public StudentBO mapRow(ResultSet rs, int rowNum) throws SQLException {
			System.out.println("StudentDaoImpl.StudentMapper.mapRow()");
			StudentBO bo = null;

				bo = new StudentBO();
				bo.setSid(rs.getInt(1));
				bo.setSname(rs.getString(2));
				bo.setAvg(rs.getFloat(3));
				bo.setSaddress(rs.getString(4));
				System.out.println(rs.getInt(1)+rs.getString(2));;
			return bo;
		}

	}

}
