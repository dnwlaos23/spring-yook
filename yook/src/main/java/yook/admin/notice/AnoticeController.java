package yook.admin.notice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import yook.admin.notice.AnoticeService;
import yook.common.map.CommandMap;

@Controller
public class AnoticeController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="anoticeService")
	private AnoticeService anoticeService;
	
	@RequestMapping(value="/adminNoticeList.do")
	public ModelAndView notice(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("adminNoticeList");
		List<Map<String, Object>> list = anoticeService.notice(commandMap.getMap());
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping(value="/openAnoticeDetail.do")//�� ������ ����
	   public ModelAndView openAnoticeDetail(CommandMap commandMap)throws Exception {
		
	      ModelAndView mv = new ModelAndView("adminNoticeDetail");
	      Map<String, Object> map = anoticeService.anoticeDetail(commandMap.getMap());
	      mv.addObject("map", map);
	      return mv;
	}
	
	@RequestMapping(value="/adminNoticeWrite.do") //�ۼ� ��
	public ModelAndView openAnoticeWrite(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("adminNoticeWrite");
		
		return mv;
	}
	
	@RequestMapping(value="/admin/insertAnotice.do")//�ۼ��Ϸ�
	public ModelAndView insertAnotice(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/adminNoticeList.do");
		anoticeService.insertAnotice(commandMap.getMap());
		//mv.addObject("NOTICE_NUM", commandMap.get("NOTICE_NUM"));
		return mv;
	}
	
	@RequestMapping(value="/adminNoticeUpdate.do") //������
	public ModelAndView updateAnoticeForm(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("adminNoticeUpdate");
		Map<String, Object> map = anoticeService.updateAnoticeForm(commandMap.getMap());
		mv.addObject("map", map);
		return mv;	
	}
	
	@RequestMapping(value="/admin/updateAnotice.do") //���� �Ϸ�
	public ModelAndView updateAnotice(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/adminNoticeList.do");
		anoticeService.updateAnotice(commandMap.getMap());
		return mv;
	}
	
	@RequestMapping(value="/admin/anoticeDelete.do") //����
	public ModelAndView acsDelete(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/adminNoticeList.do");
		anoticeService.deleteAnotice(commandMap.getMap());
		return mv;
	}
}
