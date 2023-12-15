<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<!doctype html>
<html lang="ko">
<head>
  <title>${message}</title>
  <h1>Hello World Page</h1>
  <h3>${message}</h1>
  <img src="${contextPath}/resources/images/smile.png">
  <hr />
  <a href="${contextPath}/index.html">Go index</a>
</head>
<body>
</body>
</html>