package yook.admin.review;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public interface AdminReviewService {

	public List<Map<String, Object>> adminReviewList(Map<String, Object> map) throws Exception;//���� ���丮��Ʈ
	
	public List<Map<String, Object>> adminReviewList2(Map<String, Object> map) throws Exception;//���� ���丮��Ʈ
		
	void insertReview(Map<String, Object> map, HttpServletRequest request) throws Exception; //�����ۼ�
	
	Map<String, Object> updateReviewForm(Map<String, Object> map)throws Exception;//������
	
	public void updateReview(Map<String, Object> map) throws Exception;//�����Ϸ�
	
	public void deleteReview(Map<String, Object> map) throws Exception;//����
	
	
}
