<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome</title>
</head>
<body>
  <h2>Welcome to QnA</h2>
  Today is <javatime:format value="${now}" pattern="yyyy-MM-dd"/>.  
</body>
</html>