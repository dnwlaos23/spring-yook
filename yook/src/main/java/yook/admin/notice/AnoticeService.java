package yook.admin.notice;

import java.util.List;
import java.util.Map;

public interface AnoticeService {
	List<Map<String, Object>> notice(Map<String, Object> map)throws Exception; //���� ����Ʈ
	Map<String, Object> anoticeDetail(Map<String, Object> map)throws Exception;	 //������
	void insertAnotice(Map<String, Object> map)throws Exception; //�� �ۼ�
	Map<String, Object> updateAnoticeForm(Map<String, Object> map)throws Exception; //���� ��
	void updateAnotice(Map<String, Object> map)throws Exception; //�����Ϸ�
	void deleteAnotice(Map<String, Object> map)throws Exception; //����
	
	
	
	
}
