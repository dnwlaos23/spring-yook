package yook.member.myorder;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import yook.common.map.CommandMap;
import yook.member.myorder.MyOrderService;

@Controller
public class MyOrderController {
   
   @Resource(name="myOrderService")
   private MyOrderService myOrderService;
      
   @RequestMapping(value="/openMyOrderDetail.do")
   public ModelAndView openMyOrderDetail(CommandMap commandMap)throws Exception{
      ModelAndView mv = new ModelAndView("myOrderDetail");
      List<Map<String, Object>> list = myOrderService.openMyOrderDetailList(commandMap.getMap());//주문 내역 리스트
      mv.addObject("list", list);
      Map<String, Object> map = myOrderService.openMyOrderDetail(commandMap.getMap());//주문 내역 상세보기
      mv.addObject("map", map);
      mv.addObject("ORDER_NUM", commandMap.get("ORDER_NUM"));
      mv.addObject("MEM_NUM", commandMap.get("MEM_NUM"));
      return mv;
   }
   
   @RequestMapping(value="/openMyChangeForm.do")
   public ModelAndView openMyChangeForm(CommandMap commandMap, HttpServletRequest request)throws Exception{
      ModelAndView mv = new ModelAndView("requestExchangeForm");
 
      System.out.println(commandMap.getMap());
      commandMap.put("ORDER_DETAIL_WEIGHT", request.getParameter("ORDER_DETAIL_WEIGHT"));
      
      System.out.println(commandMap.getMap());
      Map<String, Object> map = myOrderService.openMyChangeForm(commandMap.getMap());
      
      mv.addObject("map", map);
      
      return mv;
   }

   
   @RequestMapping(value="/updateMyChange.do")
   public ModelAndView updateMyChange(CommandMap commandMap)throws Exception{
      ModelAndView mv = new ModelAndView("redirect:/openMyInfoForm.do");
      System.out.println(commandMap.getMap());
      myOrderService.updateMyChange(commandMap.getMap());
      mv.addObject("ORDER_NUM", commandMap.get("ORDER_NUM"));
      mv.addObject("GOODS_NUM", commandMap.get("GOODS_NUM"));
      mv.addObject("MEM_NUM", commandMap.get("MEM_NUM"));
      
      return mv;
      
   }
   
      
}