package yook.member.login;


import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import yook.member.login.MailService;
import yook.common.map.CommandMap;
import yook.member.login.LoginService;



@Controller
public class LoginController {

	Logger log = Logger.getLogger(this.getClass());
	
	//@Resource, @Inject는 의존주입
	@Resource(name="mailService")
	private MailService mailService;
	
	@Inject
	private JavaMailSender mailSender;

	@Resource(name = "loginService")
	private LoginService loginService;
	

	@RequestMapping(value = "/LoginForm.do") //메인페이지 - 로그인버튼
	public ModelAndView loginForm(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView("loginForm");
		return mv;
		
	}

	@RequestMapping(value = "/login.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("login");
		String message = "";
		

		HttpSession session = request.getSession();

		//아이디 확인문
		Map<String, Object> chk = loginService.login(commandMap.getMap());
		if (chk==null) { // 아이디가 있는지 없는지를 확인
			message = "해당 아이디가 존재하지 않습니다.";
		} else {
			if (chk.get("MEM_PW").equals(commandMap.get("MEM_PW"))) {
				//chk가 null이 아니고 MEM_PW가 일치하면 세션에 데이터들을 저장한다.
				//메인페이지에서 정보(아이디)를 띄울때 세션으로 가져와서 띄운다.
				session.setAttribute("session_MEM_ID", commandMap.get("MEM_ID"));
				session.setAttribute("session_MEM_NUM", commandMap.get("MEM_NUM"));
				session.setAttribute("session_MEMBER", chk);
				
			} else { // if문을 통해 sql과 정보 일치 확인
				message = "비밀번호가 맞지 않습니다.";
			}
		}
		System.out.println(chk);
		mv.addObject("message",message);
		
		 
		return mv;
	}
	
	@RequestMapping(value="/logout.do") // 로그아웃
    public ModelAndView logout(CommandMap commandMap, HttpServletRequest request) throws Exception {
       HttpSession session = request.getSession();
       if (session != null)
          session.invalidate();//세션값이 존재하면 무효화시킨다.
       ModelAndView mv = new ModelAndView();
       mv.setViewName("redirect:/main/logoutSc.do");
       return mv;
    }
 
	 @RequestMapping(value="/main/logoutSc.do")
	 public ModelAndView openJoinForm(CommandMap commandMap)throws Exception{
	    ModelAndView mv = new ModelAndView("logout");//세션값을 무효화시키고 logout페이지로 이동시킨다.
	    
	    return mv;
	 }
	

	@RequestMapping(value = "/findId.do") // 아이디 찾기 폼을 보여주는 메소드
	public ModelAndView findId(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("memberFind");
		int ran = new Random().nextInt(900000) + 100000;
		
		mv.addObject("random",ran);
		
		return mv;
	}

	@RequestMapping(value = "/member/openFindIdResult.do", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/text; charset=utf8")

	public @ResponseBody String findIdResult(CommandMap commandMap) throws Exception {
		String id = String.valueOf(loginService.findId(commandMap.getMap()));
		
		if(id.equals("1")) {
			String findId = (String)(loginService.findIdWithEmail(commandMap.getMap()).get("MEM_ID"));
			System.out.println(findId);
			return findId;
			
		}else {
			return id;
		}
	}
	
	
	@RequestMapping(value = "/member/findPw.do") // 비밀번호 찾기 폼을 보여주는 메소드
	public ModelAndView findPw(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/member/findAccount");
		int ran = new Random().nextInt(900000) + 100000;
		
		mv.addObject("random",ran);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/member/openFindPwResult.do", method=RequestMethod.GET) 
	@ResponseBody //자바 객체를 HTTP 응답 몸체로 전송
	public boolean findPwEmail(CommandMap commandMap,@RequestParam String MEM_ID, @RequestParam String MEM_EMAIL, @RequestParam int random, HttpServletRequest req) throws Exception {
		
		String emailCheck = String.valueOf(loginService.findPwWithEmail(commandMap.getMap()));
		System.out.println(emailCheck);
		if(emailCheck.equals("1")) {
			int ran = new Random().nextInt(900000) + 100000;
			   HttpSession session = req.getSession(true);
			   String authCode = String.valueOf(ran);
			   session.setAttribute("authCode", authCode);
			   session.setAttribute("random", random);
			   String subject = "식육증가 비밀번호 변경 코드 안내 입니다."; // 전송할 메일의 제목
			   StringBuilder sb = new StringBuilder();
			   sb.append("귀하의 임시 비밀번호는 " + authCode + "입니다.");
			   
			   commandMap.put("MEM_ID", MEM_ID);
			   commandMap.put("MEM_EMAIL", MEM_EMAIL);
			   commandMap.put("authCode", authCode);
			   loginService.updateTempPw(commandMap.getMap());
			   return mailService.send(subject, sb.toString(),"dnwlaos23@gmail.com", MEM_EMAIL, null); //반송할 이메일 주소
		}else {
			  return false;
		}
	}
	
}