<%@include file="templates/header.jsp"%>

<c:forEach items="${blogsList}" var="singleBlog">
    <hr>
    <div>

        ${singleBlog.id}
        ${singleBlog.blogTitle}
        ${singleBlog.blogDescription}
            <a href="/blogDetails${singleBlog.id}">details</a>
            <img src="${singleBlog.picture}" alt="">
    </div>
    <hr>

</c:forEach>





</body>
</html>
