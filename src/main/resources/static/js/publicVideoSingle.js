function initPlayVideo(videoInfo) {
    console.log(videoInfo);
    $('#iframeVideoIFrame').attr("src", videoInfo.videoUrl);
    $('.iframeVideoInterest').text(videoInfo.videoInterested);
    $('#iframeVideoName').text(videoInfo.videoName);
    $('#iframeVideoUserName').text(videoInfo.userName);
    $('#iframeVideoAuditTime').text(videoInfo.videoAuditTime);
    $('#iframeVideoId').text(videoInfo.videoId);
    $('#iframeUserId').text(videoInfo.userId);

    document.getElementById("iframeVideoDeleteHref").href="javascript:deleteVideo(" + videoInfo.videoId +  ")";
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

function deleteVideo(videoId) {
    var r = confirm("是否确认删除该视频？");
    if (r==true) {
        $.ajax({
            type: 'GET',
            url: "/video/personDeleteVideo",
            data: {videoId:videoId},
            cache: false,
            async: false,
            dataType: "json",
            success: function (result) {
                if (result.success === false) {
                    alert(result.message);
                } else {
                    alert("删除成功");
                    window.location.href = "/test/gallery";
                }
            }
        });
    }
}

function submitSubscribeUser() {
    alert("不可以关注自己哦")

}