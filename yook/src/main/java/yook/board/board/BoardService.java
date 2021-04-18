package yook.board.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

public interface BoardService {
      
   List<Map<String, Object>> selectReviewList(Map<String, Object> map) throws Exception; //����Ʈ
	/* Map<String, Object> reviewDetail(Map<String, Object> map)throws Exception; */
   
   List<Map<String, Object>> selectQnaList(Map<String, Object> map) throws Exception; //����Ʈ
   Map<String, Object> qnaDetail(Map<String, Object> map)throws Exception;	 //������
   
   List<Map<String, Object>> noticeList(Map<String, Object> map) throws Exception; //����Ʈ
   Map<String, Object> noticeDetail(Map<String, Object> map)throws Exception;	 //������
   
   List<Map<String, Object>> eventList(Map<String, Object> map)throws Exception; //����Ʈ
   Map<String, Object> eventDetail(Map<String, Object> map)throws Exception;	 //������
   
   //QNA
   void insertQna(Map<String, Object> map, HttpServletRequest request) throws Exception; //�� �ۼ�
   Map<String, Object> updateQnaForm(Map<String, Object> map)throws Exception; //���� ��
   void updateQna(Map<String, Object> map)throws Exception; //�����Ϸ�
   void deleteQna(Map<String, Object> map) throws Exception; //����
   //REVIEW
   void insertReview(Map<String, Object> map, HttpServletRequest request) throws Exception; //�� �ۼ�
   Map<String, Object> updatReviewForm(Map<String, Object> map)throws Exception; //���� ��
   void updateReview(Map<String, Object> map)throws Exception; //�����Ϸ�
   void deleteReview(Map<String, Object> map) throws Exception; //����
}