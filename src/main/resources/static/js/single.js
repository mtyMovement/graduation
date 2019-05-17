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
            $('#iframeVideoIFrame').attr("src", data.data.videoUrl);
            $('.iframeVideoInterest').text(data.data.videoInterested);
            $('#iframeVideoName').text(data.data.videoName);
            $('#iframeVideoUserName').text(data.data.userName);
            $('#iframeVideoAuditTime').text(data.data.videoAuditTime);
            $('#iframeVideoInterestHref').attr("href", "javascript:interestVideo(1," + data.data.videoId + ")");
            console.log(data.toString());

        }
    });
}

function initPlayVideo(videoInfo) {
    console.log(videoInfo);
    $('#iframeVideoIFrame').attr("src", videoInfo.videoUrl);
    $('.iframeVideoInterest').text(videoInfo.videoInterested);
    $('#iframeVideoName').text(videoInfo.videoName);
    $('#iframeVideoUserName').text(videoInfo.userName);
    $('#iframeVideoAuditTime').text(videoInfo.videoAuditTime);
    $('#iframeVideoInterestHref').attr("href", "javascript:interestVideo(1," + videoInfo.videoId + ")");
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

//操作类型 0-视频点赞  1-视频分享  2-评论点赞
function interestVideo(userId,videoId) {
    console.log(userId,videoId);
    $.ajax({
        type: 'GET',
        url: "/video/operateRecord",
        data: {userId:userId, videoId:videoId, operateType:0},
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
            if (data.success === true) {
                var preInterestNum = $('#iframeVideoInterest').text();
                console.log(data.data + "," + preInterestNum);
                $('.iframeVideoInterest').text(data.data)
                if(preInterestNum < data.data){
                    alert("点赞成功");
                }else{
                    alert("取消点赞成功");
                }

            }
            console.log(data.toString());
        }
    });
}