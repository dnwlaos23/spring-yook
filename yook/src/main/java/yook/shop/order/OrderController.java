package yook.shop.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import yook.common.map.CommandMap;
import yook.shop.basket.BasketService;
import yook.shop.order.OrderService;

@Controller
public class OrderController {
	
	Logger log = Logger.getLogger(this.getClass()); //�α�
	/*
	 * @Resource(name="orderService") private OrderService orderService;
	 */
	

	@Resource(name="basketService")
	private BasketService basketService;
	
	@Resource(name="orderService")
	private OrderService orderService;
	
	//��ٱ��� ��α���
		@RequestMapping(value="/basketAllOrderWrite.do")
		public ModelAndView basketAllOrderSelect(CommandMap commandMap, HttpServletRequest request) throws Exception {
			
			ModelAndView mv = new ModelAndView("orderForm");
			Object MEM_NUM = ""; //���ǰ� �������� 
			HttpSession session = request.getSession(); 
			commandMap.put("MEM_NUM", ((Map)session.getAttribute("session_MEMBER")).get("MEM_NUM"));
			
			List<Map<String,Object>> list = basketService.basketList(commandMap);
			Map<String,Object> map = orderService.orderMemberInfo(commandMap, request);
			mv.addObject("list", list);
			mv.addObject("map", map);
			
			System.out.println(list);
			System.out.println(map);
			
			return mv;
		}
		

		@RequestMapping(value="/orderPay.do")
		public ModelAndView orderPay(CommandMap commandMap, HttpServletRequest request) throws Exception {
			
			ModelAndView mv = new ModelAndView("orderCompleted");//주문 완료 페이지
			
			Object MEM_NUM = ""; 
			HttpSession session = request.getSession(); 
			//MEM_NUM 세션으로 받아와 쿼리문에서 사용
			commandMap.put("MEM_NUM", ((Map)session.getAttribute("session_MEMBER")).get("MEM_NUM"));
			
			System.out.println(commandMap.get("ORDER_PRICE"));
			System.out.println(commandMap.get("ORDER_TCOST"));
			System.out.println(commandMap.get("GOODS_NUM"));
			
			orderService.insertOrder(commandMap, request);//Order 테이블에 데이터 입력하는 서비스
		    //Order 테이블에서 데이터 가져와 map객체에 저장
			Map<String,Object> map = orderService.selectOrder(commandMap, request);
			
			mv.addObject("map", map);
			
			//주문 완료 페이지에서 보여주고 싶은 데이터들 map 객체에 저장하고 리턴
			map.put("ORDER_TCOST", commandMap.get("ORDER_TCOST"));
			map.put("ORDER_MEM_NAME", commandMap.get("ORDER_MEM_NAME"));
			map.put("ORDER_PHONE", commandMap.get("ORDER_PHONE"));
			map.put("ORDER_DZIPCODE", commandMap.get("ORDER_DZIPCODE"));
			map.put("ORDER_DADD1", commandMap.get("ORDER_DADD1"));
			map.put("ORDER_DADD2", commandMap.get("ORDER_DADD2"));
			map.put("ORDER_DMEMO", commandMap.get("ORDER_DMEMO"));	
			map.put("ORDER_PAY_NAME", commandMap.get("ORDER_PAY_NAME"));
		
			return mv;
			}
		
		//��ٱ��� ���û�ǰ ����
	      @RequestMapping(value="/basketSelectOrder.do")
	      public ModelAndView basketSelect(CommandMap commandMap, HttpServletRequest request) throws Exception {
	         
	         ModelAndView mv = new ModelAndView("orderForm");
	         Object MEM_NUM = ""; //���ǰ� �������� 
	         HttpSession session = request.getSession(); 
	         MEM_NUM = (Object)session.getAttribute("session_MEMBER"); 
	         commandMap.put("MEM_NUM", ((Map)session.getAttribute("session_MEMBER")).get("MEM_NUM"));
	         List<Map<String,Object>> list = basketService.basketSelectList(commandMap, request); //������ ��ٱ��Ϲ�ȣ�� ��ǰ 
	         Map<String,Object> map = orderService.orderMemberInfo(commandMap, request); //�ֹ�������

	         mv.addObject("list", list);
	         mv.addObject("map", map);

	         System.out.println(list);
	         System.out.println(map);

	         return mv;
	      }

}
