package yook.member.myinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import yook.member.myinfo.MyInfoDAO;

@Service("myInfoService")
public class MyInfoServiceImpl implements MyInfoService{
	Logger log=Logger.getLogger(this.getClass());
	   
	@Resource(name="myInfoDAO")
	private MyInfoDAO myInfoDAO;

	@Override
	public Map<String, Object> selectMyInfoForm(Map<String, Object> map) throws Exception {

	    Map model = new HashMap();

	    model.putAll(myInfoDAO.selectMyInfo(map));
	    model.putAll(myInfoDAO.selectMyOrderInfo(map));

	    return model;		
	}

	@Override
	public Map<String, Object> updateMyInfoForm(Map<String, Object> map) throws Exception {
		
		return myInfoDAO.updateMyInfoForm(map);
	}

	@Override
	public void updateMyInfo(Map<String, Object> map) throws Exception {
		
		myInfoDAO.updateMyInfo(map);
	}

	@Override
	public void deleteMyInfo(Map<String, Object> map) throws Exception {
		
		myInfoDAO.deleteMyInfo(map);
	}

	
	
}
