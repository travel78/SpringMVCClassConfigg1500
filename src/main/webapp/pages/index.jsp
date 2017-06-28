<%@include file="templates/header.jsp" %>


<sec:authorize access="isAuthenticated()">
    <h1>hello! ${xxx}</h1>
</sec:authorize>
<hr>

<hr>
<a href="/showAllBlogs">
    <button>showAllBlogs</button>
</a>
<hr>
<a href="/admin/toAdminPage">toAdminPage</a>

<form action="/saveUser" method="post">
    <input type="text" name="username">
    <input type="password" name="password">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit">
</form>

</body>
</html>
