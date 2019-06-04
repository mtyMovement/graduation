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
            $('#iframeVideoInterestHref').attr("href", "javascript:interestVideo(" + data.data.videoId + ")");
            $('#iframeVideoId').text(data.data.videoId);
            $('#iframeUserId').text(data.data.userId);
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
    $('#iframeVideoInterestHref').attr("href", "javascript:interestVideo(" + videoInfo.videoId + ")");
    $('#iframeVideoId').text(videoInfo.videoId);
    $('#iframeUserId').text(videoInfo.userId);
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

function initComment(resultData) {
    console.log(resultData);
    var html = "";
    if(resultData === null){
        html += "<div class='commentDiv'>"
            +"<div style='margin-left: 15px;margin-top: 15px'>"
            + "     <h3>暂无评论</h3>"
            + " </div>"
            + "</div>"
    }else{
        console.log(resultData);
        for (var i = 0; i < resultData.length; i++) {
            var dataVideoInfo = resultData[i];
            console.log(dataVideoInfo);
            console.log(dataVideoInfo["user_Name"]);
            html += "<div class='commentDiv'>"
                +"<div style='margin-left: 15px;margin-top: 15px'>"
                + "     <h3>" + dataVideoInfo["user_Name"] + ": </h3>"
                + " </div>"
                + " <div style='margin-left: 25px'>"
                + "     <p>" + dataVideoInfo["comment"] + "</p>"
                + " </div>"
                + "</div>"
        }
    }
    $("#singleComment").html(html);
}

//操作类型 0-视频点赞  1-视频分享  2-评论点赞
function interestVideo(videoId) {
    console.log(videoId);
    $.ajax({
        type: 'GET',
        url: "/video/operateRecord",
        data: {diffTypeId:videoId, operateType:0, content:""},
        cache: false,
        async: false,
        dataType: "json",
        success: function (data) {
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
            else {
                alert(data.message);
            }
            console.log(data.toString());
        }
    });
}

function shareVideo() {
    $('#shareInput').val("");
    $('#shareDiv').show();
    $('#commentDiv').hide();
}

function commentVideo() {
    $('#commentInput').val("");
    $('#shareDiv').hide();
    $('#commentDiv').show();
}

function submitShareVideo() {
    var videoId = $('#iframeVideoId').text();
    var content = $('#shareInput').val();
    $.ajax({
        type: 'GET',
        url: "/video/operateRecord",
        //操作类型 0-视频点赞  1-视频分享  2-评论点赞
        data: {diffTypeId:videoId, operateType:1, content:content},
        cache: false,
        async: true,
        dataType: "json",
        success: function (result) {
            if(result.success === false){
                alert(result.message);
            }else{
                alert("分享成功");
                $('#shareInput').val("");
                $('#shareDiv').hide();
            }
        }
    });
}

function submitCommentVideo() {
    var videoId = $('#iframeVideoId').text();
    var content = $('#commentInput').val();
    $.ajax({
        type: 'GET',
        url: "/video/operateRecord",
        //操作类型 0-视频点赞  1-视频分享  2-评论点赞  3-评论视频
        data: {diffTypeId:videoId, operateType:3, content:content},
        cache: false,
        async: true,
        dataType: "json",
        success: function (result) {
            if(result.success === false){
                alert(result.message);
            }else{
                alert("评论成功");
                $('#commentInput').val("");
                $('#commentDiv').hide();
            }
        }
    });
}

function submitSubscribeUser() {
    var userId = $('#iframeUserId').text();
    $.ajax({
        type: 'GET',
        url: "/video/operateRecord",
        //操作类型 0-视频点赞  1-视频分享  2-评论点赞  3-评论视频  4-关注用户
        data: {diffTypeId:userId, operateType:4},
        cache: false,
        async: true,
        dataType: "json",
        success: function (result) {
            if(result.success === false){
                alert(result.message);
            }else{
                alert("关注成功");
                $('#commentInput').val("");
                $('#commentDiv').hide();
            }
        }
    });
}