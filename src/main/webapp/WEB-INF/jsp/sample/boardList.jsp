<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
	<h2>게시판 목록</h2>
	<table class="board_list">
		<colgroup>
			<col width="10%"/>
			<col width="*"/>
			<col width="15%"/>
			<col width="20%"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">글번호</th>
				<th scope="col">제목</th>
				<th scope="col">조회수</th>
				<th scope="col">작성일</th>
			</tr>
		</thead>
		
		<tbody>
			<!--  
			<c:choose>
				<c:when test="${fn:length(list) > 0}">
					<c:forEach items="${list }" var="row" varStatus="status">
						<tr id = "${status.index}">
							<td>${row.IDX }</td>
							<td class="title">
								<a href="#this" name="title">${row.TITLE }</a>
								<input type="hidden" id="IDX" value="${row.IDX }">
							</td>
							<td>${row.HIT_CNT }</td>
							<td>${row.CREA_DTM }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">조회된 결과가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		-->
		</tbody>
	</table>
	
	<div id="PAGE_NAVI"></div> <!--  추가된 부분 -->
	<input type="hidden" id="PAGE_INDEX" name="PAGE_INDEX"/> <!--  추가된 부분 -->

	<br/>
	<a href="#this" class="btn" id="write">글쓰기</a>
	
	
	
	
	
	
	<li>
		<c:if test="${member != null}"><a href="logout.do">로그아웃</a></c:if>
		<c:if test="${member == null}"><a href="login.do">로그인</a></c:if>
	</li>
	<li>
		<c:if test="${member != null}">
			<p>${member.userId}님 안녕하세요.</p>
		</c:if>
	</li>
	
	
	
	
	
	
	<%@ include file="/WEB-INF/include/include-body.jspf" %>
	<script type="text/javascript">
	<!-- 
	$('#userList').DataTable(); 
	-->
		$(document).ready(function(){
			<!--
			fn_selectBoardList(1); // 기존꺼
			-->
			window.onload=function(){
				fn_selectBoardList(1);
			}; //바뀐거
			
			$("#write").on("click", function(e){ //글쓰기 버튼
				e.preventDefault();
				fn_openBoardWrite();
			});	
			
			$("a[name='title']").on("click", function(e){ //제목 
				e.preventDefault();
				fn_openBoardDetail($(this));
				
			});
		});			
		
		function fn_openBoardWrite(){ 
			var comSubmit = new ComSubmit(); 
			comSubmit.setUrl("<c:url value='/sample/openBoardWrite.do' />"); 
			comSubmit.submit(); 
		} 
		
		function fn_openBoardDetail(obj){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/sample/openBoardDetail.do' />");
			comSubmit.addParam("IDX", obj.parent().find("#IDX").val());
			//alert(obj.parent());
			/*
			for(var key in obj){
				alert("attributes :" + key + ",value : " + obj.parent().find("#IDX").val());
			}
			*/
			
			comSubmit.submit();
			
		}
		
		function fn_selectBoardList(pageNo){ 
			var comAjax = new ComAjax(); 
			comAjax.setUrl("<c:url value='/sample/selectBoardList.do' />"); 
			comAjax.setCallback("fn_selectBoardListCallback"); 
			comAjax.addParam("PAGE_INDEX",pageNo); 
			comAjax.addParam("PAGE_ROW", 20); 
			comAjax.ajax(); 
		}
		
		function fn_selectBoardListCallback(data){ 
			var total = data.TOTAL; 
			var body = $("table>tbody"); 
			body.empty(); 
			if(total == 0){ 
				var str = "<tr>" + "<td colspan='4'>조회된 결과가 없습니다.</td>" + "</tr>"; 
				body.append(str); 
				} 
			else{ 
				var params = { 
						divId : "PAGE_NAVI", 
						pageIndex : "PAGE_INDEX", 
						totalCount : total, 
						eventName : "fn_selectBoardList" 
						}; 
				gfn_renderPaging(params); 
				
				var str = ""; 
				$.each(data.list, function(key, value){ 
					str += "<tr>" + "<td>" + value.IDX + "</td>" + "<td class='title'>" + "<a href='#this' name='title'>" + value.TITLE + "</a>" + "<input type='hidden' id='IDX' value=" + value.IDX + ">" + "</td>" + "<td>" + value.HIT_CNT + "</td>" + "<td>" + value.CREA_DTM + "</td>" + "</tr>"; 
					}); 
				body.append(str); 
				$("a[name='title']").on("click", function(e){ //제목
					e.preventDefault(); 
					fn_openBoardDetail($(this));
					
				}); 
			} 
		}
	
	</script>	
</body>
</html>