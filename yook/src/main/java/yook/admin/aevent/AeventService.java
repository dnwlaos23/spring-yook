package yook.admin.aevent;

import java.util.List;
import java.util.Map;

public interface AeventService {
	List<Map<String, Object>> event(Map<String, Object> map)throws Exception; //���� ����Ʈ
	Map<String, Object> aeventDetail(Map<String, Object> map)throws Exception;	 //������
	void insertAevent(Map<String, Object> map)throws Exception; //�� �ۼ�
	Map<String, Object> updateAeventForm(Map<String, Object> map)throws Exception; //���� ��
	void updateAevent(Map<String, Object> map)throws Exception; //�����Ϸ�
	void deleteAevent(Map<String, Object> map)throws Exception; //����
	
	
	
	
}
