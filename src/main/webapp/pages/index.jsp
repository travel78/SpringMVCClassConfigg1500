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

<form action="/saveUser" method="post" ng>
    <input value="" type="text" name="username" autocomplete="off">
    <input value="" type="password" name="password" autocomplete="off">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit">
</form>







</body>
</html>
