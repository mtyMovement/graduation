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

function initAuditVideoDiv(resultData){
    var html = "";
    var page = resultData.current;
    var total = resultData.total;
    console.log(resultData);
    for (var i = 0; i < resultData.records.length; i++) {
        var dataVideoInfo = resultData.records[i];
        html += "<div class=\"col-1-4\">"
            + "     <div class=\"wrap-col\">"
            + "         <div class=\"wrap-vid\">"
            + "             <div class=\"zoom-container\">"
            + "                 <a href=\"#\">"
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
    }
    if(page === 1 && page > 0){
        $("#cuPreAudit").hide();
    }else{
        $("#cuPreAudit").show();
    }

    if(page > 0 && page < (total/resultData.size)){
        $("#cuPreAudit").text(page-1);
        $("#cuAudit").text(page);
        $("#cuNextAudit").text(page+1);

        document.getElementById("preAudit").href="javascript:changeAuditPage(" + (page-1) +  ")";
        document.getElementById("cuPreAudit").href="javascript:changeAuditPage(" + (page-1) +  ")";
        document.getElementById("cuAudit").href="javascript:changeAuditPage(" + page +  ")";
        document.getElementById("cuNextAudit").href="javascript:changeAuditPage(" + (page+1) +  ")";
        document.getElementById("nextAudit").href="javascript:changeAuditPage(" + (page+1) +  ")";
    }
    $("#auditVideoDiv").html(html);
}

function changeAuditPage(pageCurrent){
    $.ajax({
        type: 'GET',
        url: "/video/changeAuditVideoPage",
        data: {pageCurrent: pageCurrent, pageSize: 4},
        cache: false,
        async: true,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (result) {
            initAuditVideoDiv(result.data);
        }
    });

}



function initPublicVideoDiv(resultData){
    var html = "";
    var page = resultData.current;
    var total = resultData.total;
    console.log(resultData);
    for (var i = 0; i < resultData.records.length; i++) {
        var dataVideoInfo = resultData.records[i];
        html += "<div class=\"col-1-4\">"
            + "     <div class=\"wrap-col\">"
            + "         <div class=\"wrap-vid\">"
            + "             <div class=\"zoom-container\">"
            + "                 <a href='initPublicVideoSingle?videoId=" + dataVideoInfo.videoId + "'>"
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
    }
    if(page === 1 && page > 0){
        $("#cuPrePublic").hide();
    }else{
        $("#cuPrePublic").show();
    }

    if(page > 0 && page < (total/resultData.size)){
        $("#cuPrePublic").text(page-1);
        $("#cuPublic").text(page);
        $("#cuNextPublic").text(page+1);

        document.getElementById("prePublic").href="javascript:changePublicPage(" + (page-1) +  ")";
        document.getElementById("cuPrePublic").href="javascript:changePublicPage(" + (page-1) +  ")";
        document.getElementById("cuPublic").href="javascript:changePublicPage(" + page +  ")";
        document.getElementById("cuNextPublic").href="javascript:changePublicPage(" + (page+1) +  ")";
        document.getElementById("nextPublic").href="javascript:changePublicPage(" + (page+1) +  ")";
    }
    $("#publicVideoDiv").html(html);
}
function changePublicPage(pageCurrent){
    $.ajax({
        type: 'GET',
        url: "/video/changePublicVideoPage",
        data: {pageCurrent: pageCurrent, pageSize: 4},
        cache: false,
        async: true,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (result) {
            initPublicVideoDiv(result.data);
        }
    });

}



function initShareVideoDiv(resultData){
    var html = "";
    var page = resultData.current;
    var total = resultData.total;
    console.log(resultData);
    for (var i = 0; i < resultData.records.length; i++) {
        var dataVideoInfo = resultData.records[i];
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
    }
    if(page === 1 && page > 0){
        $("#cuPreShare").hide();
    }else{
        $("#cuPreShare").show();
    }

    if(page > 0 && page < (total/resultData.size)){
        $("#cuPreShare").text(page-1);
        $("#cuShare").text(page);
        $("#cuNextShare").text(page+1);

        document.getElementById("preShare").href="javascript:changeSharePage(" + (page-1) +  ")";
        document.getElementById("cuPreShare").href="javascript:changeSharePage(" + (page-1) +  ")";
        document.getElementById("cuShare").href="javascript:changeSharePage(" + page +  ")";
        document.getElementById("cuNextShare").href="javascript:changeSharePage(" + (page+1) +  ")";
        document.getElementById("nextShare").href="javascript:changeSharePage(" + (page+1) +  ")";
    }
    $("#shareVideoDiv").html(html);
}
function changeSharePage(pageCurrent){
    $.ajax({
        type: 'GET',
        url: "/video/changeShareVideoPage",
        data: {pageCurrent: pageCurrent, pageSize: 4},
        cache: false,
        async: true,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (result) {
            initShareVideoDiv(result.data);
        }
    });

}


function initInterestVideoDiv(resultData){
    var html = "";
    var page = resultData.current;
    var total = resultData.total;
    console.log(resultData);
    for (var i = 0; i < resultData.records.length; i++) {
        var dataVideoInfo = resultData.records[i];
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
    }
    if(page === 1 && page > 0){
        $("#cuPreInterest").hide();
    }else{
        $("#cuPreInterest").show();
    }

    if(page > 0 && page < (total/resultData.size)){

        $("#cuPreInterest").text(page-1);
        $("#cuInterest").text(page);
        $("#cuNextInterest").text(page+1);

        document.getElementById("preInterest").href="javascript:changeInterestPage(" + (page-1) +  ")";
        document.getElementById("cuPreInterest").href="javascript:changeInterestPage(" + (page-1) +  ")";
        document.getElementById("cuInterest").href="javascript:changeInterestPage(" + page +  ")";
        document.getElementById("cuNextInterest").href="javascript:changeInterestPage(" + (page+1) +  ")";
        document.getElementById("nextInterest").href="javascript:changeInterestPage(" + (page+1) +  ")";
    }
    $("#interestVideoDiv").html(html);
}
function changeInterestPage(pageCurrent){
    $.ajax({
        type: 'GET',
        url: "/video/changeInterestVideoPage",
        data: {pageCurrent: pageCurrent, pageSize: 4},
        cache: false,
        async: true,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (result) {
            initInterestVideoDiv(result.data);
        }
    });

}
