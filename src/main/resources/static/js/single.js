$(document).ready(function () {

    $("#owl-demo-1").owlCarousel({
        items: 4,
        lazyLoad: true,
        navigation: true
    });
    $("#owl-demo-2").owlCarousel({
        items: 4,
        lazyLoad: true,
        navigation: true
    });

});

jQuery(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: "/video/initPageSingle",
        cache: false,
        async: false,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (result) {
            var newestVideoResult = result.data["newestResult"];
            var hottestVideoResult = result.data["hottestResult"];
            initNewestVideos(newestVideoResult);
            initHottestVideos(hottestVideoResult);
        }
    });
});


function switchVideo(videoId) {
    console.log(videoId);
    $.ajax({
        type: 'POST',
        url: "/video/switchVideo",
        data: JSON.stringify(videoId),
        cache: false,
        async: false,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            /*if (data.code === "200") {
                localStorage.setItem("user", JSON.stringify(data.data));
                window.location.href = "../to/index";
            }
            if (data.code === "1000") {
                alert("手机号或密码错误! 请重新输入!");
                /!*$("#phone").val("");
                $("#password").val("");*!/
            }*/
            $('#iframeVideo').attr("src", data.data.videoUrl);
            console.log(data.toString());

        }
    });
}

function initNewestVideos(resultData){
    var html = "";
    console.log(resultData);
    for (var i = 0; i < resultData.length; i++) {
        var dataVideoInfo = resultData[i];
        html += "<div class='post wrap-vid'>"
            + "     <div class='zoom-container'>"
            + "         <a href='" + "javascript:switchVideo(" + dataVideoInfo.videoId +  ")" + "'>"
            + "             <span class='zoom-caption'><i class='icon-play fa fa-play'></i></span>"
            + "                 <img src='" + dataVideoInfo.videoJPGUrl + "'/>"
            + "         </a>"
            + "     </div>"
            + "     <div class='wrapper'>"
            + "         <h5 class='vid-name'><a href='" + "javascript:switchVideo(" + dataVideoInfo.videoId +  ")" + "'>" + dataVideoInfo.videoName + "</a></h5>"
            + "         <div class='info'>"
            + "             <h6>By <a href='#'>" + dataVideoInfo.userName + "</a></h6>"
            + "             <span><i class='fa fa-calendar'></i>" + dataVideoInfo.videoAuditTime + "</span>"
            + "             <span><i class='fa fa-heart'></i>" + dataVideoInfo.videoInterested + "</span>"
            + "         </div>"
            + "     </div>"
            + "</div>"
    }
    $("#latestPosts").html(html);
}

function initHottestVideos(resultData){
    var html = "";
    console.log(resultData);
    for (var i = 0; i < resultData.length; i++) {
        var dataVideoInfo = resultData[i];
        html += "<div class='row'>"
            + "     <div class='wrap-vid'>"
            + "         <div class='zoom-container'>"
            + "             <a href='" + "javascript:switchVideo(" + dataVideoInfo.videoId +  ")" + "'>"
            + "                 <span class='zoom-caption'>"
            + "                     <i class='icon-play fa fa-play'></i>"
            + "                 </span>"
            + "                 <img src='" + dataVideoInfo.videoJPGUrl + "'/>"
            + "             </a>"
            + "         </div>"
            + "         <h3 class='vid-name'>" + dataVideoInfo.videoName + "</h3>"
            + "         <div class='info'>"
            + "             <h5>By <a href='#'>" + dataVideoInfo.userName + "</a></h5>"
            + "             <span><i class='fa fa-calendar'></i>" + dataVideoInfo.videoAuditTime + "</span>"
            + "             <span><i class=\"fa fa-heart\"></i>" + dataVideoInfo.videoInterested + "</span>"
            + "         </div>"
            + "     </div>"
            + " </div>"

    }
    $("#topNews").html(html);
}