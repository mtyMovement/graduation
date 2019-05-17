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
            initNewestVideos(newestVideoResult);
            initHottestVideos(hottestVideoResult);
            initIndexMostViewedVideos(mostViewedResult);
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