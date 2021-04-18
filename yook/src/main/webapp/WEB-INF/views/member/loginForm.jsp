<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!doctype html>
<html lang="ko">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	
	<link href="<c:url value="/resources/css/btn.css"/>" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	
	<script type="text/javascript">


   
      function begin(){
         document.myform.MEM_ID.focus();
       }
       function checkId(){
         if(!document.myform.MEM_ID.value){
           alert("���̵� �Է����� �����̽��ϴ�.");
           document.myform.id.focus();
           return false;
         }
         if(!document.myform.MEM_PW.value){
           alert("��й�ȣ�� �Է����� �����̽��ϴ�.");
           document.myform.passwd.focus();
           return false;
         }

       }
       
       function fsubmit(){
          var id = $("#MEM_ID")[0].value;
          var pw = $("#MEM_PW")[0].value;
          if(id==null || id==''){
             alert("���̵� �Է��ϼ���.");
             return false;
          }
          if(pw==null || pw==''){
             alert("��й�ȣ�� �Է��ϼ���.");
             return false;
          }
          myform.submit();
       }       
       
       $(document).ready(function(){
          
          // ����� ��Ű�� �α���ȭ�鿡 �ҷ���������
          var userInputId = getCookie("userInputId");
          $("#MEM_ID").val(userInputId);  
          
          if($("#MEM_ID").val() != ""){ // �� ���� ID�� �����ؼ� ó�� ������ �ε� ��, �Է� ĭ�� ����� ID�� ǥ�õ� ���¶��,
             $("#idSaveCheck").attr("checked", true); // ID �����ϱ⸦ üũ ���·� �α�.
          }
          
          $("#idSaveCheck").change(function(){ // üũ�ڽ��� ��ȭ�� �ִٸ�,
             if($("#idSaveCheck").is(":checked")){ // ID �����ϱ� üũ���� ��,
                var userInputId = $("#MEM_ID").val();
                setCookie("userInputId", userInputId, 7); // 7�Ϻ���
             }else{ // ID �����ϱ� üũ ���� ��,
                deleteCookie("userInputId");
             }
          });
          
          // ID �����ϱ⸦ üũ�� ���¿��� ID�� �Է��ϴ� ���, �̷� ���� ��Ű ����.
          $("#MEM_ID").keyup(function(){ // ID �Է� ĭ�� ID�� �Է��� ��,
             if($("#idSaveCheck").is(":checked")){ // ID �����ϱ⸦ üũ�� ���¶��,
                var userInputId = $("#MEM_ID").val();
                setCookie("userInputId", userInputId, 7); // 7�� ���� ��Ű ����
             }
          });
       });
       
      
        
       function setCookie(cookieName, value, exdays){ //SET�� ����Ͽ� ��Ű����
           var exdate = new Date();
           exdate.setDate(exdate.getDate() + exdays); //���� �ϼ���ŭ ����ð��� ���ᰪ���� ����
           var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
           document.cookie = cookieName + "=" + cookieValue;
       }
        
       function deleteCookie(cookieName){
           var expireDate = new Date();
           expireDate.setDate(expireDate.getDate() - 1);
           document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
       }
        
       function getCookie(cookieName) {
           cookieName = cookieName + '=';
           var cookieData = document.cookie;
           var start = cookieData.indexOf(cookieName);
           var cookieValue = '';
           if(start != -1){
               start += cookieName.length;
               var end = cookieData.indexOf(';', start);
               if(end == -1)end = cookieData.length;
               cookieValue = cookieData.substring(start, end);
           }
           return unescape(cookieValue);
       }
   </script>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
    <title>�α���</title>
  </head>
  <body onload="begin()">
    <form name="myform" action="/yook/login.do" method="post" class="form-signin">


<div style="height: 30px;"></div>
<div align="center">
   <h3>Login</h3> 
</div>
<div style="height: 30px; margin-bottom:10px;"></div>

<div style="width:500px; margin-left:auto; margin-right:auto; margin-bottom:150px;">
   <div>
      <p style="font-size:13; color:gray;">
      �����Ͻ� ���̵�� ��й�ȣ�� �Է����ּ���.<br>
      ��й�ȣ�� ��ҹ��ڸ� �����մϴ�.
      </p>
   </div>
   

  
        <div class="form-group">
          <label for="inputId">���̵�</label>
        
        
          <input type="text" class="form-control" id="MEM_ID" placeholder="MEMBER ID" name="MEM_ID">
        </div>
        <div class="form-group">
          <label for="inputPw">��й�ȣ</label>
          <input type="password" class="form-control" id="MEM_PW" placeholder="PASSWORD" name="MEM_PW">
        </div>
        <div class="form-group form-check">
          <input type="checkbox" class="form-check-input" id="idSaveCheck">
          <label class="form-check-label" for="idSaveCheck">���̵� ����</label>
        </div>
   
   
   
        <input type="button" class="btn btn-lg btn-primary btn-block" style="margin-left:auto; width:100%;" value="�α���" onclick="fsubmit();">
         <div class="form-group form-check" style="margin-top:10px;">
         <div style="float:left;">
          <span style="margin-left:30;"><a href="${contextPath}/yook/findId.do">���̵�/��й�ȣ ã��</a></span>
          </div>
          <div style="float:right;">
          <span style="margin-right:90;"><a href="${contextPath}/yook/join.do">ȸ������</a></span>
          </div>
        </div>

   </form>
</div>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>