<%@include file="templates/header.jsp" %>

<button id="btn">show all</button>
<div id="convert"></div>

<hr>

<input type="text" id="title">
<input type="text" id="text">
<button id="ppp">post Save</button>

<script>
    $('#ppp').click(function () {
        var text = $('#text').val();
        var title = $('#title').val();

        var post = {postText: text, postTitle: title};
        post = JSON.stringify(post);

        $.ajax({
            url: '/sp',
            type: "POST",
            contentType: 'application/json',
            data: post,
            success: function () {
                alert("Ok!");
            },
            error: function () {
                alert('error!');
            }
        });

    });
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);

        });
    });

    $("#btn").click(function () {
        $("#convert").empty();
        $.ajax({
            url: "/showAll",
            type: 'GET',
            success: function (result) {
                $(result).each(function () {
                    $('#convert').append($("<p/>", {text: this.postTitle + " " + this.postText}))
                })
            }
        });
    })
</script>

</body>
</html>
