package com.choa.ex2;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choa.notice.NoticeDTO;
import com.choa.notice.NoticeService;


@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {
	@Inject //인젝트는 type으로 찾는다
	private NoticeService noticeService;
	
	
	@RequestMapping(value="noticeList")
	public void noticeList(Model model,@RequestParam(defaultValue="1") Integer curPage) throws Exception{
		
		List<NoticeDTO> ar=noticeService.noticeList(curPage);
		model.addAttribute("list",ar);
	}


	@RequestMapping(value="noticeView")
	public void noticeView(Integer num, Model model) throws Exception{
		NoticeDTO noticeDTO=noticeService.noticeView(num);
		model.addAttribute("dto",noticeDTO);
	}
	
	@RequestMapping(value="noticeWrite")
	public void noticeWrite(NoticeDTO noticeDTO){
		
	}
	
	@RequestMapping(value="noticeWrite",method=RequestMethod.POST)
	public String noticeWrite(NoticeDTO noticeDTO,Model model,RedirectAttributes rd) throws Exception{
		int result=noticeService.noticeWrite(noticeDTO);
		String message="FAIL";
		if(result>0){
			message="SUCCESS";			
		}
		
		//주소창에 남지않는다
		rd.addFlashAttribute("message", message);
		
		
		//model.addAttribute("message",message);
		
		return "redirect:/notice/noticeList";
		//return "redirect:noticeList?curPage=2";
		/*this.noticeList(model, 1);
		return "notice/noticeList";*/
	}
	
	@RequestMapping(value="noticeUpdate")
	public void noticeUpdate(Integer num, Model model) throws Exception{
		NoticeDTO noticeDTO=noticeService.noticeView(num);
		model.addAttribute("dto",noticeDTO);
	}
	
	@RequestMapping(value="noticeUpdate", method=RequestMethod.POST)
	public String noticeUpdate(NoticeDTO noticeDTO,Model model) throws Exception{
		int result=noticeService.noticeUpdate(noticeDTO);
		this.noticeView(noticeDTO.getNum(), model);
		return "notice/noticeView";
	}
	
	@RequestMapping(value="noticeDelete")
	public String noticeDelete(Integer num,Model model,RedirectAttributes rd) throws Exception{
		int result=noticeService.noticeDelete(num);
		String message="FAIL";
		if(result>0){
			message="SUCCESS";			
		}
		rd.addAttribute("message", message);
		
		return "redirect:/notice/noticeList";
	}	
}
