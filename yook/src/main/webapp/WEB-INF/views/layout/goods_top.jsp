<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!doctype html>
<html lang="ko">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">


<!-- icon -->
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
	
<script src="<c:url value='/js/common1.js'/>" charset="utf-8"></script>
<title>상품</title>

</head>

<body>

	<div class="album py-5 ">
		<div class="container">

			<div class="modal-body">
				<div class="row">
					
					<div class="col-md-4 product_img" style="margin-left:220px;">
						<img src="/yook/img/goods_upload/${map.GOODS_IMAGE}" width="330" height="330">
					</div>
					
					<div class="col-md-4" style="border: none; size:3px;">
						<div class="card mb-6 bg-white mt-5" style="border: none;">
							<h2 class="display-3">${list.GOODS_NAME} </h2>
							<span class="display-4">${Weight}</span>
							<span>${map.GOODS_CATE }</span>
							<p>
							<c:if test="${Weight == '[300g(14mm)]' }">
							100g당 ${map.GOODS_PRICE / 3}원
							</c:if>
							<c:if test="${Weight == '[300g(16mm)]' }">
							100g당 ${map.GOODS_PRICE / 3}원
							</c:if>
							<c:if test="${Weight == '[300g(18mm)]' }">
							100g당 ${map.GOODS_PRICE / 3}원
							</c:if>
							<c:if test="${Weight == '[300g]' }">
							100g당 ${map.GOODS_PRICE / 3}원
							</c:if>
							<c:if test="${Weight == '[200g]' }">
							100g당 ${map.GOODS_PRICE / 2}원
							</c:if>
							<c:if test="${Weight == '[150g]' }">
							100g당 ${map.GOODS_PRICE / 1.5}원
							</c:if>
							
							
							
							</p>
							<h3 class="cost display-3">${map.GOODS_PRICE } 원</h3>
							
							

							<div class="card-body">
								<div class="col-sm-8">
									
								</div>

								
							</div>
							<form id="frm" name="frm" method="post">
							<div class="btn-ground">
							
							<div style="height: 10px;"></div>
								<button style="width: 300px; height: 38px;" class="btn btn-danger" id="insertBasket" 
            					onclick="fn_InsertBasket()"><span class="glyphicon glyphicon-shopping-cart"></span> Add To Cart</button>
									
							<input type="hidden" name="IDX" id="IDX" value="${map.GOODS_NUM }"/>
							<input type="hidden" name="GOODS_WEIGHT" id="GOODS_WEIGHT" value="${map.GOODS_WEIGHT }	"/>	
													
								
							</div>
							</form>
							
							
						</div>
					</div>

					


          
				
				</div>
			</div>
		</div>
	</div>
<!-- buttons -->
<div class="container" align="center">
<button type="submit" class="btn btn-primary center">상품정보</button>
<button type="submit" class="btn btn-primary center" onclick="fn_GoodsDetail()">상품상세</button>
<button  class="btn btn-primary center" id="reviewDetail" onclick="fn_ReviewDetail()">상품리뷰
<input type="hidden" name="IDX" id="IDX" value="${map.GOODS_NUM }"/></button>
<button  class="btn btn-primary center" id="reviewWrite" onclick="fn_ReviewWrite()">리뷰작성</button>
</div><br><br></div>



	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>



<script type="text/javascript">


$(document).ready(function() {

   fn_selectGoodsList(1); // 상품문의 페이징x

});



function fn_InsertBasket() { // 장바구니
	   
	   
	   if(${session_MEM_ID != null}){
	      
	         var url = "/yook/shop/basketPopUp.do";
	         var name = "popup";
	         var option = "width=382, height=227, top=300, left=500, location=no";
	         
	          var comSubmit = new ComSubmit("frm");
	         comSubmit.setUrl("<c:url value='/shop/insertBasket.do'/>");
	         window.open(url,name,option);
	         comSubmit.submit();
	      
	   }else {
		  var comSubmit = new ComSubmit("frm"); 
	      alert("로그인 후 이용해주세요.");
	      comSubmit.setUrl("<c:url value='/LoginForm.do'/>");
	      comSubmit.submit();
	   }
	   
	}


function fn_GoodsDetail(){
    var comSubmit = new ComSubmit("frm");
    comSubmit.setUrl("<c:url value='/gd.do' />");
    
    
    comSubmit.submit();
 }
 
function fn_ReviewDetail(){
    var comSubmit = new ComSubmit("frm");
    comSubmit.setUrl("<c:url value='/reviewDetail.do' />");
    
    
    comSubmit.submit();
 }

function fn_ReviewWrite(){

	if(${session_MEM_ID != null}){
    var comSubmit = new ComSubmit("frm");
    comSubmit.setUrl("<c:url value='/reviewWrite.do' />");
    
    
    comSubmit.submit();
	} else{

	var comSubmit = new ComSubmit("frm"); 
	alert("로그인 후 이용해주세요.");
	comSubmit.setUrl("<c:url value='/LoginForm.do'/>");
	comSubmit.submit();
	}
 }

</script>