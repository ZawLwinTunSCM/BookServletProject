<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/jsp/common/header.jsp"%>
<html>
<head>
<title>Book List</title>
</head>
<body>
  <div class="container">
    <h3 class="text-center">List of Books</h3>
    <hr>
    <div class="container text-left">
      <a href="<%=request.getContextPath()%>/new"
        class="btn btn-primary">Add New Book</a>
    </div>
    <br>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Author</th>
          <th>Price</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="book" items="${listBook}">
          <tr>
            <td><c:out value="${book.id}" /></td>
            <td><c:out value="${book.name}" /></td>
            <td><c:out value="${book.author}" /></td>
            <td><c:out value="${book.price}" /></td>
            <td><a href="edit?id=<c:out value='${book.id}' />">Edit</a>
              &nbsp;&nbsp;&nbsp;&nbsp; <a
              href="delete?id=<c:out value='${book.id}' />">Delete</a></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</body>
</html>