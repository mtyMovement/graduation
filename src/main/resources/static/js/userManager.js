$(document).ready(function () {
    var html = "";
    $.ajax({
        type: 'GET',
        url: "/admin/userManagerQueryInfo",
        cache: false,
        async: false,
        dataType: "json",
        success: function (result) {
            if(result.success === false && result.code === 800){
                $('#userManagerTBody').html(result.message);
            }else if(result.success === false){
                alert(result.message);
            }else{
                var resultData = result.data;
                for (var i = 0; i < resultData.length; i++) {
                    var dataVideoInfo = resultData[i];
                    html += "<tr>"
                        + "     <th scope='row'>" + (i + 1) + "</th>"
                        + "     <td>" + dataVideoInfo.userId + "</td>"
                        + "     <td>" + dataVideoInfo.userNo + "</td>"
                        + "     <td>" + dataVideoInfo.userName + "</td>"
                        + "     <td>" + dataVideoInfo.userType + "</td>"
                        + "     <td>" + dataVideoInfo.userInterest + "</td>"
                        + "     <td>" + dataVideoInfo.createTime + "</td>"
                        + "     <td>" + "<a href='javascript:deleteUser(" + dataVideoInfo.userId + ")'>删除</a>" + "</td>"
                        + "</tr>";
                }
                $('#userManagerTBody').html(html);
            }
        }
    });
});

function deleteUser(userId) {
    var r = confirm("是否确认删除该用户？");
    if (r==true) {
        $.ajax({
            type: 'GET',
            url: "/admin/userManagerDeleteUser",
            data: {userId:userId},
            cache: false,
            async: false,
            dataType: "json",
            success: function (result) {
                if (result.success === false) {
                    alert(result.message);
                } else {
                    alert("删除成功");
                    window.location.href = "../test/userManager";
                }
            }
        });
    }
}

function searchUserInfo() {
    var html = "";
    var userName = $('#inlineFormInput').val();
    var userType = $('#inlineFormInputGroup').val();
    $.ajax({
        type: 'GET',
        url: "/admin/userManagerSearchInfo",
        data: {userName:userName, userType:userType},
        cache: false,
        async: false,
        dataType: "json",
        success: function (result) {
            if(result.success === false && result.code === 800){
                $('#userManagerTBody').html(result.message);
            }else if(result.success === false){
                alert(result.message);
            }else{
                var resultData = result.data;
                for (var i = 0; i < resultData.length; i++) {
                    var dataVideoInfo = resultData[i];
                    html += "<tr>"
                        + "     <th scope='row'>" + (i + 1) + "</th>"
                        + "     <td>" + dataVideoInfo.userId + "</td>"
                        + "     <td>" + dataVideoInfo.userNo + "</td>"
                        + "     <td>" + dataVideoInfo.userName + "</td>"
                        + "     <td>" + dataVideoInfo.userType + "</td>"
                        + "     <td>" + dataVideoInfo.userInterest + "</td>"
                        + "     <td>" + dataVideoInfo.createTime + "</td>"
                        + "     <td>" + "<a href='javascript:deleteUser(" + dataVideoInfo.userId + ")'>删除</a>" + "</td>"
                        + "</tr>";
                }
                $('#userManagerTBody').html(html);
            }
        }
    });
}