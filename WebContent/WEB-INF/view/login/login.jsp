<%--ログイン画面--%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@include file="/WEB-INF/view/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理システム</title>
<link rel="stylesheet" href="css/common.css" />
</head>
<body>
	<header>
		<img src="images/HCSLogo.png" alt="(株)HCS" id="logo" /><small>資格保有率業界No1</small>
		<h1>社員情報管理システム</h1>
	</header>
	<jsp:include page="../common/leftmenu.jsp" />
	<article>
		<h3>ログイン</h3>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		<c:if test="${not empty errMsg}">
			<div class="error">${errMsg}</div>
		</c:if>
		<c:if test="${loginBean.errFlg}">
			<div class="error">
				${loginBean.errMsgSyainNo}<br /> ${loginBean.errMsgPassword}
			</div>
		</c:if>
		<br/>
		<form action="Login" method="post">
			<table class="basetable">
				<tr>
					<th><label for="syainNo">社員番号</label> <img alt="必須"
						src="images/required.jpg" class="req_img" /></th>
					<td><input type="text" name="syainNo" id="syainNo"
						class="tblInput" maxlength="5" value="${loginBean.syainNo}" /></td>
				</tr>
				<tr>
					<th><label for="password">パスワード</label> <img alt="必須"
						src="images/required.jpg" class="req_img" /></th>
					<td><input type="password" name="password" id="password"
						class="tblInput" maxlength="10" /></td>
				</tr>
			</table>
			<br />
			<div class="center">
				<input type="submit" name="button" value="ログイン" class="button" /> <input
					type="submit" name="button" value="クリア" class="button" />
			</div>
		</form>
	</article>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>