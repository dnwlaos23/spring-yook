package yook.admin.qna;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import yook.admin.qna.QnaService;
import yook.common.map.CommandMap;

@Controller
public class QnaController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="qnaService")
	private QnaService qnaService;
	
	@RequestMapping(value="/adminQnaList.do")
	public ModelAndView qna(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("adminQnaList");
		List<Map<String, Object>> list = qnaService.qna(commandMap.getMap());
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping(value="/admin/openQnaDetail.do")//�� ������ ����
	   public ModelAndView openQnaDetail(CommandMap commandMap)throws Exception {
		
	      ModelAndView mv = new ModelAndView("adminQnaDetail");
	      Map<String, Object> map = qnaService.qnaDetail(commandMap.getMap());
	      mv.addObject("map", map);
	      return mv;
	}
	
	@RequestMapping(value="/adminQnaWrite.do") //�ۼ� ��
	public ModelAndView openQnaWrite(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("adminQnaWrite");
		
		return mv;
	}
	
	@RequestMapping(value="/admin/insertQna.do")//�ۼ��Ϸ�
	public ModelAndView insertQna(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/adminQnaList.do");
		qnaService.insertQna(commandMap.getMap());
		//mv.addObject("FAQ_NUM", commandMap.get("FAQ_NUM"));
		return mv;
	}
	
	@RequestMapping(value="/adminQnaUpdate.do") //������
	public ModelAndView updateQnaForm(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("adminQnaUpdate");
		Map<String, Object> map = qnaService.updateQnaForm(commandMap.getMap());
		mv.addObject("map", map);
		return mv;	
	}
	
	@RequestMapping(value="/admin/updateQna.do") //���� �Ϸ�
	public ModelAndView updateQna(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/adminQnaList.do");
		qnaService.updateQna(commandMap.getMap());
		return mv;
	}
	
	@RequestMapping(value="/admin/qnaDelete.do") //����
	public ModelAndView qnaDelete(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/adminQnaList.do");
		qnaService.deleteQna(commandMap.getMap());
		return mv;
	}
}
