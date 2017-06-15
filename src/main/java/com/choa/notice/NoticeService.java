package com.choa.notice;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.choa.util.PageMaker;

@Service
//NoticeService noticeService=new NoticeService();
public class NoticeService {

	@Inject
	private NoticeDAO noticeDAO;
	
	/*//Constructor
	public NoticeService(NoticeDAO noticeDAO) {
		this.noticeDAO=noticeDAO;
	}		*/

	//view
	public NoticeDTO noticeView(int num) throws Exception{
		return noticeDAO.noticeView(num);
	}
	
	//List
	public List<NoticeDTO> noticeList(int curPage) throws Exception{
		
		int result=noticeDAO.noticeCount();
		PageMaker pageMaker=new PageMaker(curPage);
		return noticeDAO.noticeList(pageMaker.getRowMaker());
	}
	
	//write
	public int noticeWrite(NoticeDTO noticeDTO) throws Exception{
		noticeDAO.noticeHit(noticeDTO.getNum());
		return noticeDAO.noticeWrite(noticeDTO);
	}
	
	//update
	public int noticeUpdate(NoticeDTO noticeDTO) throws Exception{
		return noticeDAO.noticeUpdate(noticeDTO);
	}
	
	public int noticeDelete(int num) throws SQLException{
		return noticeDAO.noticeDelete(num);
	}
}
