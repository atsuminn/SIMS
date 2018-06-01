<%--top.jsp  ログイン後トップページ--%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理システム</title>
<link rel="stylesheet" href="css/common.css" />
</head>
<body>
	<jsp:include page="./common/header.jsp" />
	<jsp:include page="./common/leftmenu.jsp" />
	<!-- article要素は本文(記事)を表す -->
	<article>
		<h3>トップページ</h3>
		<small>社員情報管理システムの各機能の情報を表示します</small>
	</article>
	<jsp:include page="./common/footer.jsp" />
</body>
</html>