<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/jsp/common/header.jsp"%>
<html>
<head>
<title>Book Register</title>
</head>
<body>
  <div class="container col-md-5">
    <div class="card">
      <div class="card-body">
        <form action="${book != null ? 'update' : 'insert'}"
          method="post">
          <h2>${book != null ? 'Edit' : 'Add New'}Book</h2>
          
          <c:if test="${book != null}">
            <input type="hidden" name="id" value="${book.id}" />
          </c:if>

          <fieldset class="form-group">
            <label>Book Name</label> <input type="text"
              value="${book.name}"
              class="form-control" name="name" required>
          </fieldset>

          <fieldset class="form-group">
            <label>Book Author</label> <input type="text"
              value="${book.author}"
              class="form-control" name="author" required>
          </fieldset>

          <fieldset class="form-group">
            <label>Book Price</label> <input type="number"
              value="${book.price}"
              class="form-control" name="price" required>
          </fieldset>

          <button type="submit" class="btn btn-success">${book != null ? 'Update' : 'Register'}
          </button>
          <c:if test="${book == null}">
          <a href="<%=request.getContextPath()%>/list"
            class="btn btn-primary">Book Lists</a></c:if>
          
        </form>
      </div>
    </div>
  </div>
</body>
</html>
