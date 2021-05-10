package yook.member.join;

import yook.common.map.CommandMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import yook.member.join.JoinService;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class JoinController {
	@Resource(name="joinService")
	private JoinService joinService;
	
	
	@RequestMapping(value="/join.do")// 메인페이지 - 회원가입 버튼
	public ModelAndView openJoinForm(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("joinForm");
		
		return mv;
	}
	
	
	
	@RequestMapping(value="/member/insertJoin.do")//joinForm.jsp - 가입하기 버튼
	public ModelAndView insertJoin(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/LoginForm.do");
		joinService.insertJoin(commandMap.getMap());
		
		return mv;
	}
	
	@RequestMapping(value = "/member/idCheck.do") //ajax 통신을 이용해 페이지 이동 없이 id 중복체크를 한다.
	   public @ResponseBody String idCheck(CommandMap commandMap) throws Exception {
	   //@ResponseBody - 자바 객체를 HTTP 응답 몸체로 전송한다.
	      System.out.println(commandMap.getMap());
	      
	      String idCheck = String.valueOf(joinService.selectIdCheck(commandMap.getMap()));
	      
	      System.out.println(idCheck);

	      return idCheck;
	   }
	
	@RequestMapping(value="/member/openAgree.do") //약관동의 창을 띄운다.
	   public ModelAndView openAgree(CommandMap commandMap)throws Exception{
	      ModelAndView mv = new ModelAndView("/member/agree");
	      
	      return mv;
	   }
	
	
	
}
