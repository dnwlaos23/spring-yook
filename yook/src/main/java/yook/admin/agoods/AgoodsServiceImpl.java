package yook.admin.agoods;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import yook.admin.agoods.AgoodsDAO;
import yook.shop.goods.GoodsDao;

@Service("agoodsService")
public class AgoodsServiceImpl implements AgoodsService {
	
	@Resource(name="agoodsDAO")
	private AgoodsDAO agoodsDAO;
	
	@Resource(name="goodsDao") 
	private GoodsDao goodsDao;
	
	public void deleteAdminGoods(Map<String, Object> map)throws Exception{
	      agoodsDAO.deleteAdminGoodsATT(map);
	      agoodsDAO.deleteAdminGoods(map);
	   }
	
	public List<Map<String, Object>> openAgoodsList(Map<String, Object> map)throws Exception{
		return agoodsDAO.openAgoodsList(map);
	}
	
	@Override
	   public void updateGoods(Map<String, Object> map, HttpServletRequest request) throws Exception { // 상품 수정
	      agoodsDAO.updateGoods(map); // 상품테이블 업데이트
			
		
			 
	      
	      map.get("GOODS_WEIGHT");
	      System.out.println("선택한 중량="+map.get("GOODS_WEIGHT"));
	      
	      
	      String Weight = map.get("GOODS_WEIGHT").toString();
	      
	      String WeightList[] = Weight.split(",");
	      
	      System.out.println(WeightList.length);
	      
	      for(int i=0; i <=WeightList.length-1; i++) {
	         
	            System.out.println("배열입니당="+WeightList[i]);
	            map.put("GOODS_WEIGHT", WeightList[i]);
	            goodsDao.goodsAttributeUpdate(map); // 상품 수정시 선택한 컬러 사이즈 다시 등록
	         
	      }
	      
	   }


}
