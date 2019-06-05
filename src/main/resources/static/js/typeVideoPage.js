jQuery(document).ready(function () {
    var typeName = $("#headerTypeName").val();
    $("#headerH2").text(typeName);
    console.log(typeName);
    $.ajax({
        type: 'GET',
        url: "/video/typeVideoQueryInfo",
        data: {typeName:typeName},
        cache: false,
        async: true,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (result) {
            if (result.success === false) {
                alert(result.message);
            } else {
                var newestVideoResult = result.data["newestResult"];
                var hottestVideoResult = result.data["hottestResult"];
                var typeVideoAPIResult = result.data["typeVideoAPIResult"];
                initNewestVideos(newestVideoResult);
                initHottestVideos(hottestVideoResult);
                initTypeVideos(typeVideoAPIResult);
            }
        }
    });
});

function initTypeVideos(resultData) {
    var html = "";
    console.log(resultData.length);
    for (var i = 0; i < resultData.length; i++) {
        var dataVideoInfo = resultData[i];
        if((i + 1) % 4 === 1){
            html += "<div class=\"row\" style=\"margin-bottom: 15px\">";
        }
        html += "<div class=\"col-1-4\">"
            + "     <div class=\"wrap-col\">"
            + "         <div class=\"wrap-vid\">"
            + "             <div class=\"zoom-container\">"
            + "                 <a href=\"initSingle?videoId=" + dataVideoInfo.videoId + "\">"
            + "                     <span class=\"zoom-caption\">"
            + "                         <i class=\"icon-play fa fa-play\"></i>"
            + "                     </span>"
            + "                     <img src='" + dataVideoInfo.videoJPGUrl + "'/>"
            + "                 </a>"
            + "             </div>"
            + "             <h3 class='vid-name'>" + dataVideoInfo.videoName + "</h3>"
            + "             <div class='info'>"
            + "                 <h5>By <a href='#'>" + dataVideoInfo.userName + "</a></h5>"
            + "                 <span><i class='fa fa-calendar'></i>" + dataVideoInfo.videoAuditTime + "</span>"
            + "                 <span><i class=\"fa fa-heart\"></i>" + dataVideoInfo.videoInterested + "</span>"
            + "             </div>"
            + "         </div>"
            + "     </div>"
            + " </div>";
        if((i + 1) % 4 === 0){
            html += "</div>";
        }
    }
    if((i + 1) % 4 > 0){
        html += "</div>";
    }
    $("#typeVideoDiv").html(html);
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