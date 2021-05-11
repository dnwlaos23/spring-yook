package yook.admin.aorder;

import java.util.List;
import java.util.Map;

import yook.common.map.CommandMap;

public interface AdminOrdersService {
	List<Map<String, Object>> selectAdminOrdersList(Map<String, Object> commandMap) throws Exception;
	
	List<Map<String, Object>> order_admin_a(CommandMap map) throws Exception; 
	
	List<Map<String, Object>> order_detail(CommandMap commandMap) throws Exception; 

	List<Map<String, Object>> order_detail_sub(CommandMap commandMap) throws Exception;
	
	void order_state(CommandMap map) throws Exception; 
	
	void order_state_ex(CommandMap commandMap) throws Exception; 
	
	List<Map<String, Object>> as_admin_list(CommandMap commandMap) throws Exception; 
	
	void as_cancle_a(CommandMap commandMap) throws Exception; 
	
	void as_ok_a(CommandMap commandMap) throws Exception; 
	 	
}
