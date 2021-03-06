package yook.shop.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import yook.common.map.CommandMap;
import yook.shop.order.OrderDao;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
Logger log = Logger.getLogger(this.getClass()); // �α�
	
	@Resource(name="orderDao") 
	private OrderDao orderDao;

	@Override
	public Map<String, Object> orderMemberInfo(CommandMap commandMap, HttpServletRequest request) throws Exception {
		return (Map<String, Object>) orderDao.orderMemberInfo(commandMap);
	}
	
	@Override
	public void insertOrder(CommandMap commandMap, HttpServletRequest request) throws Exception {
		orderDao.insertOrder(commandMap);
	}
	
	@Override public Map<String, Object> selectOrder(CommandMap commandMap, HttpServletRequest request) throws Exception { 
		return (Map<String, Object>) orderDao.selectOrder(commandMap); 
	}


}
