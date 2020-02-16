<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../menu.jsp" %>
<%@include file="../header.html" %>


<table width="500">
  <tr>
    <th>出品名</th>
    <th>金額</th>
    <th>購入数</th>
    <th>出品者</th>
    <th>合計金額</th>
  </tr>
<c:forEach var="item" items="${cart}">
  <tr>
    <td>${item.name}</td>
    <td>${item.price}円</td>
    <td>${item.quantity}</td>
    <td>${item.seller}</td>
    <td>${item.price * item.quantity}円</td>
    <td><a href="CartRemove.action?ID=${item.id}">削除</a>
  </td>
  </tr>
</c:forEach>
</table>
<hr>
${total}円
<%@include file="../footer.html" %>
