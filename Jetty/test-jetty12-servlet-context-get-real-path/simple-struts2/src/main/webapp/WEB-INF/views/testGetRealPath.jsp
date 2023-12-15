<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>ServletContext#getRealPath() Test</title>
</head>
<body>
<h2>ServletContext#getRealPath() - testGetRealPath Action</h2>

<p>
  <h4>realPath: ${realPath}</h4>
</p>

<a href="<s:url action='index'/>">/index Action</a>
</body>
</html>
