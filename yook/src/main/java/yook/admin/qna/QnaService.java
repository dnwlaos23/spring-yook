package yook.admin.qna;

import java.util.List;
import java.util.Map;

public interface QnaService {
	List<Map<String, Object>> qna(Map<String, Object> map)throws Exception; //���� ����Ʈ
	Map<String, Object> qnaDetail(Map<String, Object> map)throws Exception;	 //������
	void insertQna(Map<String, Object> map)throws Exception; //�� �ۼ�
	Map<String, Object> updateQnaForm(Map<String, Object> map)throws Exception; //���� ��
	void updateQna(Map<String, Object> map)throws Exception; //�����Ϸ�
	void deleteQna(Map<String, Object> map)throws Exception; //����
	
	
	
	
}
