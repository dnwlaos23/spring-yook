package yook.member.myinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.servlet.ModelAndView;

import yook.common.map.CommandMap;
import yook.member.myinfo.MyInfoService;
import yook.member.myorder.MyOrderService;

@Controller
public class MyInfoController {
	
	Logger log = Logger.getLogger(this.getClass());
	   
	   @Resource(name="myInfoService")
	   private MyInfoService myInfoService;
	   
	   @Resource(name="myOrderService")
	   private MyOrderService myOrderService;
	   
	   @RequestMapping(value="/openMyInfoForm.do") 
	   public ModelAndView openMyInfoForm(CommandMap commandMap, HttpServletRequest request) throws Exception{
	      ModelAndView mv = new ModelAndView("myInfoForm");
	      HttpSession session = request.getSession();
	      commandMap.put("MEM_ID", session.getAttribute("session_MEM_ID"));
	      commandMap.put("MEM_NUM", ((Map)session.getAttribute("session_MEMBER")).get("MEM_NUM"));
	      Map<String, Object> map = myInfoService.selectMyInfoForm(commandMap.getMap());//회원 정보 가져오는 서비스
	      Map<String, Object> map1 = myOrderService.myOrderStatus(commandMap.getMap());//내 주문내역의 주문상태 가져오는 서비스
	     
	      List<Map<String, Object>> list = myOrderService.openMyOrderList(commandMap.getMap());//주문내역 리스트 가져오는 서비스
	      mv.addObject("list", list);   
	      mv.addObject("map", map); 
	      mv.addObject("map1", map1);
	            
	      return mv;
	   }
	   
	   @RequestMapping(value="/updateMyInfo.do") 
	   public ModelAndView updateMyInfo(CommandMap commandMap, HttpServletRequest request)throws Exception{
	      ModelAndView mv = new ModelAndView("redirect:/openMyInfoForm.do");      
			
	      myInfoService.updateMyInfo(commandMap.getMap());
	      
	      return mv;
	   }
	   	
	    @RequestMapping(value="/deleteMyInfo.do") 
	    public ModelAndView deleteMyInfo(CommandMap commandMap, HttpServletRequest request)throws Exception{
	       ModelAndView mv = new ModelAndView("redirect:/main.do");
	       HttpSession session = request.getSession();
	       
	       myInfoService.deleteMyInfo(commandMap.getMap());
	       session.invalidate(); //세션을 없애 로그아웃시켜준다.
	       
	       return mv;
	     }

}
