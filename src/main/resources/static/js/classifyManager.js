$(document).ready(function () {
    var html = "";
    $.ajax({
        type: 'GET',
        url: "/admin/classifyManagerQueryInfo",
        cache: false,
        async: false,
        dataType: "json",
        success: function (result) {
            if(result.success === false && result.code === 800){
                $('#classifyManagerTBody').html(result.message);
            }else if(result.success === false){
                alert(result.message);
            }else{
                var resultData = result.data;
                for (var i = 0; i < resultData.length; i++) {
                    var dataVideoInfo = resultData[i];
                    html += "<tr>"
                        + "     <th scope='row'>" + (i + 1) + "</th>"
                        + "     <td>" + dataVideoInfo.classifyId + "</td>"
                        + "     <td>" + dataVideoInfo.classifyName + "</td>"
                        + "     <td>" + dataVideoInfo.classifyCode + "</td>"
                        + "     <td>" + dataVideoInfo.createTime + "</td>"
                        + "</tr>";
                }
                $('#classifyManagerTBody').html(html);
            }
        }
    });
});

function searchClassifyInfo(){
    var html = "";
    var classifyName = $('#inlineFormInput').val();
    var classifyCode = $('#inlineFormInputGroup').val();
    $.ajax({
        type: 'GET',
        url: "/admin/classifyManagerSearchInfo",
        data: {classifyName:classifyName, classifyCode:classifyCode},
        cache: false,
        async: false,
        dataType: "json",
        success: function (result) {
            if(result.success === false && result.code === 910){
                $('#classifyManagerTBody').html(result.message);
            }else if(result.success === false){
                alert(result.message);
            }else{
                var resultData = result.data;
                for (var i = 0; i < resultData.length; i++) {
                    var dataVideoInfo = resultData[i];
                    html += "<tr>"
                        + "     <th scope='row'>" + (i + 1) + "</th>"
                        + "     <td>" + dataVideoInfo.classifyId + "</td>"
                        + "     <td>" + dataVideoInfo.classifyName + "</td>"
                        + "     <td>" + dataVideoInfo.classifyCode + "</td>"
                        + "     <td>" + dataVideoInfo.createTime + "</td>"
                        + "</tr>";
                }
                $('#classifyManagerTBody').html(html);
            }
        }
    });
}

function addClassifyInfo(){
    var html = "";
    var classifyName = $('#addClassifyName').val();
    var classifyCode = $('#addClassifyCode').val();
    $.ajax({
        type: 'GET',
        url: "/admin/classifyManagerAddInfo",
        data: {classifyName:classifyName, classifyCode:classifyCode},
        cache: false,
        async: false,
        dataType: "json",
        success: function (result) {
            if(result.success === false && result.code === 910){
                $('#classifyManagerTBody').html(result.message);
            }else if(result.success === false){
                alert(result.message);
            }else{
                $.ajax({
                    type: 'GET',
                    url: "/admin/classifyManagerQueryInfo",
                    cache: false,
                    async: false,
                    dataType: "json",
                    success: function (result) {
                        if(result.success === false && result.code === 800){
                            $('#classifyManagerTBody').html(result.message);
                        }else if(result.success === false){
                            alert(result.message);
                        }else{
                            var resultData = result.data;
                            for (var i = 0; i < resultData.length; i++) {
                                var dataVideoInfo = resultData[i];
                                html += "<tr>"
                                    + "     <th scope='row'>" + (i + 1) + "</th>"
                                    + "     <td>" + dataVideoInfo.classifyId + "</td>"
                                    + "     <td>" + dataVideoInfo.classifyName + "</td>"
                                    + "     <td>" + dataVideoInfo.classifyCode + "</td>"
                                    + "     <td>" + dataVideoInfo.createTime + "</td>"
                                    + "</tr>";
                            }
                            $('#classifyManagerTBody').html(html);
                        }
                    }
                });
            }
        }
    });
}