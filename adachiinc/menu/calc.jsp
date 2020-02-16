<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="../menu.jsp" %>
<% int i = 0;%>
${user.name}
<form action="CartHistory.action" method="get">
  <c:forEach var="user" items="${userslist}">
    <input type="checkbox" name="name" value="${user.name}">${user.name}

  </c:forEach>
  <input type="submit" value="検索">
</form>
<hr>
  <c:forEach var="name" items="${names}">
  <table border="1" width="500">
  <c:if test="${buy_list.size()>0}">
${name}さんへの購入履歴
<tr>
  <th>購入者</th>
  <th>出品者</th>
  <th>商品名</th>
  <th>金額</th>
  <th>個数</th>
</tr>

  <c:forEach var="item" items="${sell_list}">
    <c:if test="${item.seller==name}">
    <tr>
    <td>${item.buyer}</td>
    <td>${item.seller}</td>
    <td>${item.name}</td>
    <td>${item.price}</td>
    <td>${item.quantity}</td>
  </tr>
</c:if>
</c:forEach>
</c:if>
</table>
<br>
  <table border="1" width="500">
    <c:if test="${buy_list.size()>0}">
      ${name}さんからの購入履歴
  <tr>
    <th>購入者</th>
    <th>出品者</th>
    <th>商品名</th>
    <th>金額</th>
    <th>個数</th>
  </tr>
<c:forEach var="item" items="${buy_list}">
  <c:if test="${item.buyer==name}">
  <tr>
  <td>${item.buyer}</td>
  <td>${item.seller}</td>
  <td>${item.name}</td>
  <td>${item.price}</td>
  <td>${item.quantity}</td>
</tr>
</c:if>
</c:forEach>
</c:if>
</table>
<br>
  <c:if test="${sell_list.size()>0 || buy_list.size()>0}">
${name}${message[i]}
<% i++; %>

</c:if>
<hr>
</c:forEach>



<%@include file="../footer.html" %>
