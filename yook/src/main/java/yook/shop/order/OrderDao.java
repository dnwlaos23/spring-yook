package yook.shop.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import yook.common.map.CommandMap;
import yook.common.dao.AbstractDAO;

@Repository("orderDao")
public class OrderDao extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> orderMemberInfo(CommandMap commandMap) throws Exception{
		return (Map<String, Object>) selectOne("order.orderMemberInfo", commandMap.getMap());
	}

	public void insertOrder(CommandMap commandMap) throws Exception{
		insert("order.insertOrder", commandMap.getMap()); //ORDER���̺� INSERT
		
		//�ֹ��� ��ǰ�� ������ �Ѱ��϶�
		if(commandMap.get("GOODS_NUM").getClass().getName().equals("java.lang.String")){ 
		  Map<String,Object> dp = new HashMap<String, Object>(); 
		  dp.put("ORDER_DETAIL_GNUM",commandMap.get("GOODS_NUM"));
		  dp.put("GOODS_ATT_NO",commandMap.get("GOODS_ATT_NO")); 
		  dp.put("ORDER_TCOST",commandMap.get("ORDER_TCOST")); 
		  dp.put("ORDER_DETAIL_WEIGHT", commandMap.get("GOODS_WEIGHT"));  
		  dp.put("ORDER_DETAIL_AMOUNT",commandMap.get("BASKET_AMOUNT")); 
		  dp.put("MEM_NUM",commandMap.get("MEM_NUM")); 
		  
		  insert("order.insertOrderDetail", dp);
		  update("goods.updateGoodsAmount", dp);
		  update("goods.updateGoodsDisplay", dp);
		  
		  Map<String,Object> bod = new HashMap<String, Object>();
		  bod.put("BASKET_NUM", commandMap.get("BASKET_NUM"));
		  bod.put("GOODS_ATT_NO", commandMap.get("GOODS_ATT_NO")); 
		  bod.put("MEM_NUM", commandMap.get("MEM_NUM"));
		  delete("basketList.basketOrderDelete", bod);
		  
		  insert("order.insertAdmin", dp);
		}else {	 //�ֹ��� ��ǰ�� ������ �ΰ� �̻��϶�
			String[] ORDER_DETAIL_GNUM = (String[])commandMap.getMap().get("GOODS_NUM");
			String[] GOODS_ATT_NO = (String[])commandMap.getMap().get("GOODS_ATT_NO");
			String[] ORDER_DETAIL_WEIGHT = (String[])commandMap.getMap().get("GOODS_WEIGHT");
			String[] ORDER_DETAIL_AMOUNT = (String[])commandMap.getMap().get("BASKET_AMOUNT");
			String[] BASKET_NUM = (String[])commandMap.getMap().get("BASKET_NUM");
		
			String a=Arrays.toString(BASKET_NUM).replace("[", "");
			a=a.replace("]", "");
			String b=Arrays.toString(GOODS_ATT_NO).replace("[", "");
			b=b.replace("]", "");
			
			int len = BASKET_NUM.length;
			for(int i=0; i<len; i++ ) { 
			  Map<String,Object> dp = new HashMap<String, Object>();
			  dp.put("ORDER_DETAIL_GNUM", ORDER_DETAIL_GNUM[i]);
			  dp.put("GOODS_ATT_NO", GOODS_ATT_NO[i]);
			  dp.put("ORDER_DETAIL_WEIGHT", ORDER_DETAIL_WEIGHT[i]);
			  dp.put("ORDER_DETAIL_AMOUNT", ORDER_DETAIL_AMOUNT[i]); 
			  dp.put("ORDER_TCOST",commandMap.get("ORDER_TCOST")); 
			  dp.put("MEM_NUM",commandMap.get("MEM_NUM")); 
			  
			  
			  insert("order.insertOrderDetail", dp); 
			  update("goods.updateGoodsAmount", dp);
			  update("goods.updateGoodsDisplay", dp);
			  
			  insert("order.insertAdmin", dp);
			}
			Map<String,Object> bod = new HashMap<String, Object>();
			bod.put("MEM_NUM", commandMap.get("MEM_NUM"));
		 	bod.put("BASKET_NUM", a);
			bod.put("GOODS_ATT_NO", b);
			delete("basketList.basketOrderDelete", bod);
			
			
		}
		
		
		
	}
	
	 @SuppressWarnings("unchecked") 
	 public Map<String, Object> selectOrder(CommandMap commandMap) { 
		 return (Map<String, Object>) selectOne("order.selectOrder", commandMap.getMap()); 
	 }


}
