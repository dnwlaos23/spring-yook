package yook.shop.goods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

import yook.common.map.CommandMap;
import yook.shop.basket.BasketService;
import yook.shop.goods.GoodsService;
import yook.shop.order.OrderService;
import yook.admin.review.AdminReviewService;
import yook.admin.agoods.AgoodsServiceImpl;

@Controller
public class GoodsController {

	Logger log = Logger.getLogger(this.getClass()); // �α�

	@Resource(name = "goodsService")
	private GoodsService goodsService;

	@Resource(name = "basketService")
	private BasketService basketService;

	@Resource(name = "orderService")
	private OrderService orderService;
	
	@Resource(name = "reviewService")
	private AdminReviewService reviewService;

	@RequestMapping(value = "/main.do") 
	public ModelAndView main(CommandMap commandMap) throws Exception { 

		ModelAndView mv = new ModelAndView("main");

		List<Map<String, Object>> list = goodsService.bestGoodsList(commandMap.getMap());

		mv.addObject("list", list);
		

		return mv;

	}
	
	@RequestMapping(value = "/GoodsListAll.do")
	public ModelAndView GoodsListAll(CommandMap commandMap) throws Exception { 

		ModelAndView mv = new ModelAndView("GoodsListAll");

		List<Map<String, Object>> list = goodsService.GoodsListAll(commandMap.getMap());

		mv.addObject("list", list);
		

		return mv;

	}
	
	@RequestMapping(value = "/GoodsListPig.do")
	public ModelAndView GoodsListPig(CommandMap commandMap) throws Exception { 

		ModelAndView mv = new ModelAndView("GoodsListPig");

		List<Map<String, Object>> list = goodsService.GoodsListPig(commandMap.getMap());

		mv.addObject("list", list);
		System.out.println("list"+list);
		

		return mv;

	}
	
	@RequestMapping(value = "/GoodsListCow.do")
	public ModelAndView GoodsListCow(CommandMap commandMap) throws Exception { 

		ModelAndView mv = new ModelAndView("GoodsListCow");

		List<Map<String, Object>> list = goodsService.GoodsListCow(commandMap.getMap());

		mv.addObject("list", list);
		

		return mv;

	}
	
	@RequestMapping(value = "/GoodsListChicken.do")
	public ModelAndView GoodsListChicken(CommandMap commandMap) throws Exception { 

		ModelAndView mv = new ModelAndView("GoodsListChicken");

		List<Map<String, Object>> list = goodsService.GoodsListChicken(commandMap.getMap());

		mv.addObject("list", list);
		

		return mv;

	}
	 
	//상품 등록 폼
	@RequestMapping(value = "/adminGoodsWrite.do") 
	public ModelAndView goodsWriteForm(CommandMap commandMap) throws Exception { 
	 
	 ModelAndView mv = new ModelAndView("adminGoodsWrite"); mv.addObject("type","write"); 
	 mv.addObject("title", "상품등록"); return mv;
	 
	 }  
	 
	//상품 등록 완료
	@RequestMapping(value = "/goodsWrite.do", method = RequestMethod.POST)
	public ModelAndView goodsWrite(CommandMap commandMap, HttpServletRequest request) throws Exception { 

		ModelAndView mv = new ModelAndView("redirect:/adminGoodsList.do");

		System.out.println(commandMap.getMap());

		commandMap.put("GOODS_IMAGE", request.getSession().getAttribute("GOODS_IMAGE"));

		goodsService.insertGoods(commandMap.getMap(), request);
		
		return mv;

	}

	//상품 상세
	@RequestMapping(value = "/goodsDetail.do")
	public ModelAndView goodsDetail(CommandMap commandMap, HttpServletRequest request) throws Exception { 
		
		
		ModelAndView mv = new ModelAndView("goods_detail");
		Map<String, Object> map = goodsService.selectGoodsDetail(commandMap.getMap(), request);
		System.out.println("IDX = " + commandMap.getMap());
		Map<String, Object> IDX = commandMap.getMap();
		System.out.println("map = " + map);

		
		mv.addObject("map", map); 
		mv.addObject("list", map); 

		Map<String, Object> map1 = goodsService.selectGoodsAtt(commandMap.getMap());
		
		List<Map<String, Object>> list1 = reviewService.adminReviewList(commandMap.getMap());
		
		System.out.println("map1=" + map1);
		System.out.println("list1=" + list1);

		String Weight = map1.get("GOODS_WEIGHT").toString();
		
		System.out.println("Weight=" + Weight);
		String[] WeightList = Weight.split(","); //중량을 배열에 저장

		System.out.println("WeightList" + WeightList.toString());

		ArrayList<String> arrWeight = new ArrayList<String>();
		
		for (String data : WeightList) {
			if (!arrWeight.contains(data))
				arrWeight.add(data);
		}

		System.out.println("arrWeight=" + arrWeight);

		int WeightSize = arrWeight.size();

		mv.addObject("Weight", arrWeight); 
		System.out.println("Weight=" + Weight);
		mv.addObject("WeightSize", WeightSize); 
		System.out.println("WeightSize=" + WeightSize);
		mv.addObject("list1", list1);
		return mv;

	}

	//장바구니 추가시 팝업창
	@RequestMapping(value = "/shop/basketPopUp.do", method = RequestMethod.GET)
	public ModelAndView basketPopUp(CommandMap commandMap) throws Exception { 
		ModelAndView mv = new ModelAndView("/basket/basketPopUp");

		return mv;
	}
	
	//장바구니에 추가
	@RequestMapping(value = "/shop/insertBasket.do", method = RequestMethod.POST)
	public ModelAndView insertBasket(CommandMap commandMap, HttpServletRequest request) throws Exception { 
		
		ModelAndView mv = new ModelAndView("redirect:/goodsDetail.do");
		commandMap.put("IDX", request.getParameter("IDX"));
		Object MEM_NUM = "";
		
		  HttpSession session = request.getSession();
		
		  if(((Map)session.getAttribute("session_MEMBER")).get("MEM_NUM")!=null) {
			  
		  
		  commandMap.put("MEM_NUM", ((Map)session.getAttribute("session_MEMBER")).get("MEM_NUM"));
		  System.out.println("MEM_NUM   " + ((Map)session.getAttribute("session_MEMBER")).get("MEM_NUM"));
		  }		
		
		  Map<String, Object> map = new HashMap<String, Object>();
			
		  System.out.println("CommandMap1=" + commandMap.getMap());
		  map.put("IDX", commandMap.get("IDX"));
		  map.put("MEM_NUM", commandMap.get("MEM_NUM"));
		  map.put("GOODS_WEIGHT", commandMap.get("GOODS_WEIGHT"));
			
		  goodsService.insertBasket(map, request);
		
		mv.addObject("IDX", commandMap.getMap().get("IDX"));
		return mv;
	}

	
	@RequestMapping(value = "/gd.do")
	public ModelAndView gd(CommandMap commandMap) throws Exception { 
		ModelAndView mv = new ModelAndView("review");

		return mv;
	}
	
	@RequestMapping(value = "/delivery.do")
	public ModelAndView delivery(CommandMap commandMap) throws Exception { 
		ModelAndView mv = new ModelAndView("delivery");

		return mv;
	}
	
	

}