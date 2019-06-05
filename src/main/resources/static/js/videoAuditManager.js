jQuery(document).ready(function () {
    var html = "";
    $.ajax({
        type: 'GET',
        url: "/admin/videoAuditManagerQueryInfo",
        cache: false,
        async: true,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (result) {
            if(result.success === false){
                alert(result.message);
            }else{
                var resultData = result.data;
                console.log(resultData.length);
                for (var i = 0; i < resultData.length; i++) {
                    var dataVideoInfo = resultData[i];
                    if((i + 1) % 5 === 1){
                        html += "<div class=\"video_audit_row\">";
                    }
                    html += "<div class=\"owl-item\" style=\"width: 190px;margin-left: 25px;margin-top: 20px\">"
                        + "     <div class=\"item wrap-vid\">"
                        + "         <div class=\"zoom-container\">"
                        +"              <a href=\"#\">"
                        + "                 <span class=\"zoom-caption\">"
                        + "                     <i class=\"icon-play fa fa-play\"></i>"
                        + "                     <i class=\"icon-play fa fa-play\"></i>"
                        + "                 </span>"
                        + "                 <img src='" + dataVideoInfo.videoJPGUrl +  "'/>"
                        + "             </a>"
                        + "         </div>"
                        + "         <h3 class=\"vid-name\"><a href='#'>" + dataVideoInfo.videoName + "</a></h3>"
                        + "         <div class=\"info\">"
                        + "             <h5>By <a href='#'>" + dataVideoInfo.userName + "</a></h5>"
                        + "         </div>"
                        + "         <div class=\"form-group\" style=\"display: inline;width: 190px\">"
                        + "             <button type=\"button\" class=\"btnShare\" style=\"float: left;margin-left: 10px\" onclick='auditVideo("  + dataVideoInfo.videoId + ", 0" + ")'>通过</button>"
                        + "             <button type=\"button\" class=\"btnShare\" style=\"float: right;margin-right: 10px\" onclick='auditVideo("  + dataVideoInfo.videoId + ", 1" + ")'>淘汰</button>"
                        + "         </div>"
                        + "     </div>"
                        + " </div>";
                    if((i + 1) % 5 === 0){
                        html += "</div>";
                    }
                }
                if((i + 1) % 5 > 0){
                    html += "</div>";
                }
                $("#video_audit_info").html(html);
            }
        }
    });
});

function auditVideo(videoId, auditResult){
    $.ajax({
        type: 'GET',
        url: "/admin/auditVideo",
        data: {videoId: videoId, auditResult:auditResult},
        cache: false,
        async: false,
        dataType: "json",
        success: function (result) {
            if (result.success === false) {
                alert(result.message);
            } else {
                alert("审核成功");
                window.location.href = "../test/videoAuditManager";
            }
        }
    });
}
