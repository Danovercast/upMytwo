$(function(){
    var token=getToken();
    loadInfo();
})
function getToken(){
    return localStorage.getItem("Authorization");
}
function loadInfo(){
    var token=getToken();
    if(null===token){
        window.location.href="../html/error.html";
    }else{
        $.ajax({
            type:'post',
            url:'../base/userInfo',
            dataType:'json',
            headers:{
                Authorization:token
            },
            success:function(resp){
                if(resp.code==3){
                    localStorage.removeItem("Authorization");
                    //未登录时显示的状态
                }else{
                    var username=resp.username;
                    var image=resp.image;
                    var signature=resp.signature;
                    var uid=resp.userid;
                    var ins=resp.insid;
                    console.log("------------------------")
                    $("span[name='username']").html(resp.username);
                   $('#headerimg').attr("src","../images/"+image);
                    $('#headlink').attr("href","../user");
                   
                }
            }
        })
    }
}