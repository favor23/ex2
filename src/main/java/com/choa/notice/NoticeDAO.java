package com.choa.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.choa.util.DBConnector;
import com.choa.util.RowMaker;

@Repository("notice")
//NoticeDAO noticeDAO=new NoticeDAO();
public class NoticeDAO {
	

	//만들어진 객체를 주입시켜주세요
	@Autowired
	private DataSource dataSource;
	
	/*public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}*/


	public int noticeCount() throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		int count = 0;
		
		String sql = "select nvl(count(num),0) from notice";
		
		
			st = con.prepareStatement(sql);
			
			rs=st.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			DBConnector.disConnect(rs, st, con);
		
		return count;
		
	}
	
	
	public int noticeHit(int num) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		
		String sql = "update notice set hit = hit+1 where num=?";
		
		
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			
			result = st.executeUpdate();
	
			DBConnector.disConnect(st, con);
		
		
		return result;
	}
	// view
	public NoticeDTO noticeView(int num) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		NoticeDTO noticeDTO = null;
		ResultSet rs = null;
		
		String sql = "select * from notice where num=?";
		
		
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			rs = st.executeQuery();
			if(rs.next()) {
				noticeDTO = new NoticeDTO();
				noticeDTO.setNum(rs.getInt("num"));
				noticeDTO.setWriter(rs.getString("writer"));
				noticeDTO.setTitle(rs.getString("title"));
				noticeDTO.setContents(rs.getString("contents"));
				noticeDTO.setReg_date(rs.getDate("reg_date"));
				noticeDTO.setHit(rs.getInt("hit"));
			}
			DBConnector.disConnect(rs, st, con);
		
		return noticeDTO;
	}

	// list
	public List<NoticeDTO> noticeList(RowMaker rowMaker) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		List<NoticeDTO> ar = new ArrayList<NoticeDTO>();
		ResultSet rs = null;
		NoticeDTO noticeDTO = null;
		String sql = "select * from " + "(select N.*, rownum R from " + "(select * from notice "
					+"order by num desc)N) " 
				+ "where R between ? and ?";
		st = con.prepareStatement(sql);	
		st.setInt(1, rowMaker.getStartRow());
		st.setInt(2, rowMaker.getLastRow());

		rs = st.executeQuery();
		while (rs.next()) {
			noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));

			ar.add(noticeDTO);
		}
		DBConnector.disConnect(rs, st, con);
		return ar;
	}

	// write
	public int noticeWrite(NoticeDTO noticeDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "insert into notice values(notice_seq.nextval,?,?,?,sysdate,0)";

		st = con.prepareStatement(sql);
		st.setString(1, noticeDTO.getWriter());
		st.setString(2, noticeDTO.getTitle());
		st.setString(3, noticeDTO.getContents());

		result = st.executeUpdate();
		st.close();

		sql = "select max(num) from notice";
		st = con.prepareStatement(sql);
		rs = st.executeQuery();

		rs.next();
		result = rs.getInt(1);

		DBConnector.disConnect(st, con);

		return result;
	}

	// update
	public int noticeUpdate(NoticeDTO noticeDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;

		String sql = "update notice set title=?,contents=? where num=?";

		st = con.prepareStatement(sql);
		st.setString(1, noticeDTO.getTitle());
		st.setString(2, noticeDTO.getContents());
		st.setInt(3, noticeDTO.getNum());

		result = st.executeUpdate();

		DBConnector.disConnect(st, con);

		return result;
	}

	// delete
	public int noticeDelete(int num) throws SQLException {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;

		String sql = "delete notice where num=?";

		st = con.prepareStatement(sql);
		st.setInt(1, num);

		result = st.executeUpdate();

		DBConnector.disConnect(st, con);

		return result;
	}
}
