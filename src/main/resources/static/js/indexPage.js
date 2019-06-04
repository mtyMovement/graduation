jQuery(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: "/video/initPageIndex",
        cache: false,
        async: false,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (result) {
            console.log(result.data);
            var newestVideoResult = result.data["newestResult"];
            var hottestVideoResult = result.data["hottestResult"];
            var mostViewedResult = result.data["mostViewedResult"];
            var interestedResult = result.data["interestedVideoAPIResult"];
            var subscribeResult = result.data["subscribeUserVideoAPIResult"];
            initNewestVideos(newestVideoResult);
            initHottestVideos(hottestVideoResult);
            initIndexMostViewedVideos(mostViewedResult);
            initInterestedVideos(interestedResult);
            initSubscribeVideos(subscribeResult);
        }
    });
});

function initIndexMostViewedVideos(resultData){
    var htmlFirst = "";
    var htmlExtra = "";

    htmlFirst +="     <div class='col-2-4'>"
        + "         <div class=\"wrap-col\">"
        + "             <div class=\"zoom-container\">"
        + "                 <a href=\"initSingle?videoId=" + resultData[0].videoId + "\">"
        + "                     <span class=\"zoom-caption\">"
        + "                         <i class=\"icon-play fa fa-play\"></i>"
        + "                     </span>"
        + "                     <img src=\" " + resultData[0].videoJPGUrl +"\" />"
        + "                 </a>"
        + "             </div>"
        + "         </div>"
        + "     </div>"
    $("#mostViewedVideosFirst").html(htmlFirst);
    console.log(resultData);
    for (var i = 1; i < 3; i++) {
        var dataVideoInfo = resultData[i];
        htmlExtra +=  " <div class=\"zoom-container\">"
            + "             <a href=\"initSingle?videoId=" + resultData[i].videoId + "\">"
            + "                 <span class=\"zoom-caption\">"
            + "                     <i class=\"icon-play fa fa-play\"></i>"
            + "                 </span>"
            + "                 <img src=\" " + dataVideoInfo.videoJPGUrl +"\" />"
            + "             </a>"
            + "         </div>"
    }
    $("#mostViewedVideosExtraOne").html(htmlExtra);
    htmlExtra = "";
    for (var i = 3; i < resultData.length; i++) {
        var dataVideoInfo = resultData[i];
        htmlExtra +=  " <div class=\"zoom-container\">"
            + "             <a href=\"initSingle?videoId=" + resultData[i].videoId + "\">"
            + "                 <span class=\"zoom-caption\">"
            + "                     <i class=\"icon-play fa fa-play\"></i>"
            + "                 </span>"
            + "                 <img src=\" " + dataVideoInfo.videoJPGUrl +"\" />"
            + "             </a>"
            + "         </div>"
    }
    $("#mostViewedVideosExtraTwo").html(htmlExtra);
}

function initNewestVideos(resultData){
    var html = "";
    console.log(resultData);
    for (var i = 0; i < resultData.length; i++) {
        var dataVideoInfo = resultData[i];
        html += "<div class='post wrap-vid'>"
            + "     <div class='zoom-container'>"
            + "         <a href=\"initSingle?videoId=" + dataVideoInfo.videoId + "\">"
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
            + "             <a href=\"initSingle?videoId=" + dataVideoInfo.videoId + "\">"
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

function initInterestedVideos(resultData){
    var html = "";
    console.log(resultData);

    html += "<div class='header'>"
        + "     <h2>感兴趣类型</h2>"
        + " </div>"
        + " <div class='row'>"
        + "     <div id=\"owl-demo-2\" class=\"owl-carousel owl-theme\" style=\"opacity: 1; display: block;\">";
    for (var i = 0; i < resultData.length; i++) {
        var dataVideoInfo = resultData[i];
        html += "<div class=\"owl-item\" style=\"width: 190px;\">"
            + "<div class=\"item wrap-vid\">"
            + "     <div class=\"zoom-container\">"
            + "         <a href=\"initSingle?videoId=" + dataVideoInfo.videoId + "\">"
            + "             <span class=\"zoom-caption\">"
            + "                 <i class=\"icon-play fa fa-play\"></i>"
            + "                 <i class=\"icon-play fa fa-play\"></i>"
            + "             </span>"
            + "             <img src='" + dataVideoInfo.videoJPGUrl + "' />"
            + "         </a>"
            + "     </div>"
            + "     <h3 class=\"vid-name\"><a href='#'>" + dataVideoInfo.videoName + "</a></h3>"
            + "     <div class=\"info\">"
            + "         <h5>By <a href='#'>" + dataVideoInfo.userName + "</a></h5>"
            + "         <span><i class=\"fa fa-calendar\"></i>" + dataVideoInfo.videoAuditTime + "</span> "
            + "         <span><i class=\"fa fa-heart\"></i>" + dataVideoInfo.videoInterested + "</span>"
            + "     </div>"
            + " </div>"
            + "</div>"
    }
    html += " </div>"
        + "</div>";
    $("#interestedResult").html(html);
}

function initSubscribeVideos(resultData){
    var html = "";
    console.log(resultData);

    html += "<div class='header'>"
        + "     <h2>关注用户</h2>"
        + " </div>"
        + " <div class='row'>"
        + "     <div id=\"owl-demo-3\" class=\"owl-carousel owl-theme\" style=\"opacity: 1; display: block;\">";
    for (var i = 0; i < resultData.length; i++) {
        var dataVideoInfo = resultData[i];
        html += "<div class=\"owl-item\" style=\"width: 190px;\">"
            + "<div class=\"item wrap-vid\">"
            + "     <div class=\"zoom-container\">"
            + "         <a href=\"initSingle?videoId=" + dataVideoInfo.videoId + "\">"
            + "             <span class=\"zoom-caption\">"
            + "                 <i class=\"icon-play fa fa-play\"></i>"
            + "                 <i class=\"icon-play fa fa-play\"></i>"
            + "             </span>"
            + "             <img src='" + dataVideoInfo.videoJPGUrl + "' />"
            + "         </a>"
            + "     </div>"
            + "     <h3 class=\"vid-name\"><a href='#'>" + dataVideoInfo.videoName + "</a></h3>"
            + "     <div class=\"info\">"
            + "         <h5>By <a href='#'>" + dataVideoInfo.userName + "</a></h5>"
            + "         <span><i class=\"fa fa-calendar\"></i>" + dataVideoInfo.videoAuditTime + "</span> "
            + "         <span><i class=\"fa fa-heart\"></i>" + dataVideoInfo.videoInterested + "</span>"
            + "     </div>"
            + " </div>"
            + "</div>"
    }
    html += " </div>"
        + "</div>";
    $("#subscribeResult").html(html);
}

function searchVideoByName(){
    var html = "";
    var videoSearchName = $('#searchVideoInput').val();
    $.ajax({
        type: 'GET',
        url: "/video/searchVideoByName",
        data: {videoName:videoSearchName},
        cache: false,
        async: false,
        dataType: "json",
        success: function (result) {
            if(result.success === false){
                alert(result.message);
            }else{
                var resultData = result.data;
                alert("查询到" + resultData.length + "条相关视频");
                html += "<div class='header'>"
                    + "     <h2>搜索结果</h2>"
                    + " </div>"
                    + " <div class='row'>"
                    + "     <div id=\"owl-demo-1\" class=\"owl-carousel owl-theme\" style=\"opacity: 1; display: block;\">"
                    + "         <div class=\"owl-wrapper-outer\">"
                    + "             <div class=\"owl-wrapper\" style=\"width: 3040px; left: 0px; display: block;\">";
                for (var i = 0; i < resultData.length; i++) {
                    var dataVideoInfo = resultData[i];
                    html += "<div class=\"owl-item\" style=\"width: 190px;\">"
                        + "<div class=\"item wrap-vid\">"
                        + "     <div class=\"zoom-container\">"
                        + "         <a href=\"initSingle?videoId=" + dataVideoInfo.videoId + "\">"
                        + "             <span class=\"zoom-caption\">"
                        + "                 <i class=\"icon-play fa fa-play\"></i>"
                        + "                 <i class=\"icon-play fa fa-play\"></i>"
                        + "             </span>"
                        + "             <img src='" + dataVideoInfo.videoJPGUrl + "' />"
                        + "         </a>"
                        + "     </div>"
                        + "     <h3 class=\"vid-name\"><a href='#'>" + dataVideoInfo.videoName + "</a></h3>"
                        + "     <div class=\"info\">"
                        + "         <h5>By <a href='#'>" + dataVideoInfo.userName + "</a></h5>"
                        + "         <span><i class=\"fa fa-calendar\"></i>" + dataVideoInfo.videoAuditTime + "</span> "
                        + "         <span><i class=\"fa fa-heart\"></i>" + dataVideoInfo.videoInterested + "</span>"
                        + "     </div>"
                        + " </div>"
                        + "</div>"
                }
                html += "</div></div>"
                    + " <div class=\"owl-controls clickable\">"
                    + "     <div class=\"owl-pagination\">"
                    + "         <div class=\"owl-page active\">"
                    + "             <span class=\"\"></span>"
                    + "         </div>"
                    + "         <div class=\"owl-page\">"
                    + "             <span class=\"\"></span>"
                    + "         </div>"
                    + "     </div>"
                    + "     <div class=\"owl-buttons\">"
                    + "         <div class=\"owl-prev\">prev</div>"
                    + "         <div class=\"owl-next\">next</div>"
                    + "     </div>"
                    + " </div>"
                    + " </div>"
                    + "</div>";
                $('#searchResult').html(html);





            }
        }
    });
}