$(function(){var pageInfo=JSON.stringify({"currentpage":1,"pagesize":11});$.ajax({type:'post',url:'../base/institutionList',dataType:'json',contentType:'application/json;charset=utf-8',data:pageInfo,success:function(list){if(list!=null){var ids=new Array();var count=0;for(var i=0;i<list.length;i++){$("ul[name='insul']").append(" <li > <a href='../ins/"+list[i].institution_id+"'>"+list[i].institution_name+"</a></li>")}}forumContent(1);getMyInfo();getPage()}})});function forumContent(page){if(null==page||page<1)page=1;var currentpage=page;var pagesize=6;var info=localStorage.getItem("Authorization");var prepage=page-1;var nextpage=page+1;$('#prebutton').attr("onclick","preContent("+prepage+")");$('#nextbutton').attr("onclick","nextContent("+nextpage+")");$.ajax({type:'post',url:'../base/indexForum',dataType:'json',data:{"currentpage":currentpage,"pagesize":pagesize},success:function(data){if(data!=null&&data.length>0){$('#midleDiv').empty();if(null!=info){for(var i=0;i<data.length;i++){$('#midleDiv').append("<article class='am-g blog-entry-article'><div class='am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img'><img src='../img/"+data[i].image+"'alt=''class='am-u-sm-12'></div><div class='am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text'><span><a href='../ins/"+data[i].institutionid+"'class='blog-color'>"+data[i].institutionname+" </a></span><span><a href='../user/otherUser?uid="+data[i].authorid+"&info="+info+"' target='_blank'>@"+data[i].authorname+" </a></span><span>"+data[i].createtime+"</span><h1><a href='../p/"+data[i].forumid+"'>"+data[i].title+"</a></h1><p>"+data[i].description+"</p><p><a href=''class='blog-continue'>continue</a></p></div></article>")}}else{for(var i=0;i<data.length;i++){$('#midleDiv').append("<article class='am-g blog-entry-article'><div class='am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img'><img src='../img/"+data[i].image+"'alt=''class='am-u-sm-12'></div><div class='am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text'><span><a href='../ins/"+data[i].institutionid+"'class='blog-color'>"+data[i].institutionname+" </a></span><span><a data-toggle='modal' data-target='#shouldloginmodal' >@"+data[i].authorname+" </a></span><span>"+data[i].createtime+"</span><h1><a href='../p/"+data[i].forumid+"'>"+data[i].title+"</a></h1><p>"+data[i].description+"</p><p><a href=''class='blog-continue'>continue</a></p></div></article>")}}}else{$('#midleDiv').append("<p class=''><h1 class='am-text-danger'>尽头了,谢谢</h1></p>")}}})};function submitlogin(){var uname=$('#uname').val().trim();var passwd=$('#password').val();if(null==uname||null==passwd){}else{$.ajax({type:'post',url:'../base/login',dataType:'json',data:{"username":uname,"password":passwd},success:function(resp){if(resp.code!=1){$('#loginfailedinfo').html(resp.msg);$('#password').val("")}else{localStorage.setItem("Authorization",resp.data);window.location.reload()}}})}};function hideins(){$('#insDiv').hide()};function getMyInfo(){var token=localStorage.getItem("Authorization");if(token!=null){$.ajax({type:'post',url:'../base/userInfo',dataType:'json',headers:{Authorization:token},success:function(resp){if(resp.code==3){localStorage.removeItem("Authorization");}else{var username=resp.username;var image=resp.image;var signature=resp.signature;var uid=resp.userid;var ins=resp.insid;$('#username').html(username);$('#username1').html(username);$('#signature').html(signature);$('#headerImg').attr('src',"../images/"+image);$('#schoolea').attr("href","../school/index?info="+token);$("#toEdit").attr("href","../user/toEditMe?uname="+username+"&uid="+uid+"&info="+token);$('#username').attr("href","../user/myHome?uid="+uid+"&uname="+username+"&info="+token);$('#siberHome').attr("href","../user/myHome?uid="+uid+"&uname="+username+"&info="+token);$('#mycomment').attr("href","../user/myComment?uid="+uid+"&uname="+username+"&info="+token);$('#mychart').attr("href","../user/chat?uid="+uid+"&uname="+username+"&info="+token);$('#myfriend').attr("href","../user/myFriend?info="+token);$('#username').attr("data-target","");$('#loginuserinfo').show();$('#siberlogin').hide();if(ins!=null&&ins!=undefined)$('#myins').attr("href","../ins/"+ins);else $('#myins').attr("href","../ins/"+1);if(resp.roleid===6){$('#managershow').show()}$('#shouldshow').hide();$('#shouldhide').show()}}})}else{$('#loginuserinfo').hide();$('#username').html("登陆");$('#username').attr("onmouseover","nothing()");$('#shouldshow').show();$('#shouldhide').hide()}};function loginthat(){var uname=$('#thatname').val().trim();var passwd=$('#thatpasswd').val();if(null==uname||null==passwd){}else{$.ajax({type:'post',url:'../base/login',dataType:'json',data:{"username":uname,"password":passwd},success:function(resp){if(resp.code!=1){$('#loginthatinfo').html(resp.msg);$('#thatpasswd').val("")}else{localStorage.setItem("Authorization",resp.data);window.location.reload()}}})}};function nothing(){console.log("hi")};function logOut(){localStorage.removeItem("Authorization");$.ajax({type:'post',url:'../logout',success:function(){window.location.reload()}})};function getPage(){$.post({type:'post',url:'../base/insForumPageInfo',data:{"code":"some requestforumpage","pagesize":6,"currentpage":1},dataType:'json',success:function(resp){var p=resp.currentpage-1;var pp=resp.currentpage+1;for(var i=1;i<=resp.totalpage;i++){if(i==1){$('#pageul').append(" <button id='prebutton' type='button' class='am-btn am-btn-primary am-pagination-prev' onclick='preContent("+p+")' >« 上一页</button>");$('#pageul').append(" <button type='button' class='am-btn am-btn-primary am-pagination-prev' onclick='forumContent("+i+")' >"+i+"</button>")}else if(i==resp.totalpage){$('#pageul').append("<button type='button' class='am-btn am-btn-primary' onclick='forumContent("+i+")' >"+i+"</button>");$('#pageul').append("<button id='nextbutton' type='button' class='am-btn am-btn-danger' onclick='nextContent("+pp+")' >下一页 »</button>")}else{$('#pageul').append("<button type='button' class='am-btn am-btn-primary' onclick='forumContent("+i+")' >"+i+"</button>")}}$('#totalpage').val(resp.totalpage)}})};function preContent(page){if(page<1){$('#midleDiv').append("<p class=''><h1 class='am-text-danger'>第一页了,谢谢</h1></p>")}else{forumContent(page);page=page-1;$('#prebutton').attr("onclick","preContent("+page+")")}};function nextContent(page){var totalpage=$('#totalpage').val();if(page>totalpage){$('#midleDiv').append("<p class=''><h1 class='am-text-danger'>尽头了,谢谢</h1></p>")}else{forumContent(page);page=page+1;$('#nextbutton').attr("onclick","nextContent("+page+")")}};function search(page){var searchw=$('#searchwhat').val().trim();if(null==page||page==undefined||page<1)page=1;if(null==searchw||searchw==""||searchw==undefined){console.log("failed")}else{var dat=JSON.stringify({"code":searchw,"currentpage":page,"pagesize":6});$.ajax({type:'post',url:'../forum/justSearch',contentType:'application/json;charset=utf-8',dataType:'json',data:dat,success:function(resp){var info=localStorage.getItem("Authorization");if(resp!=null&&resp.list!=null&&resp.list!=undefined&&resp.list.length>0){$('#midleDiv').empty();if(null!=info){for(var i=0;i<resp.list.length;i++){$('#midleDiv').append("<article class='am-g blog-entry-article'><div class='am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img'><img src='../img/"+resp.list[i].image+"'alt=''class='am-u-sm-12'></div><div class='am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text'><span><a href='../ins/"+resp.list[i].institutionid+"'class='blog-color'>"+resp.list[i].institutionname+" </a></span><span><a href='../user/otherUser?uid="+resp.list[i].authorid+"&info="+info+"' target='_blank'>@"+resp.list[i].authorname+" </a></span><span>"+resp.list[i].createtime+"</span><h1><a href='../p/"+resp.list[i].forumid+"'>"+resp.list[i].title+"</a></h1><p>"+resp.list[i].description+"</p><p><a href=''class='blog-continue'>continue</a></p></div></article>")}}else{for(var i=0;i<resp.list.length;i++){$('#midleDiv').append("<article class='am-g blog-entry-article'><div class='am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img'><img src='../img/"+resp.list[i].image+"'alt=''class='am-u-sm-12'></div><div class='am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text'><span><a href='../ins/"+resp.list[i].institutionid+"'class='blog-color'>"+resp.list[i].institutionname+" </a></span><span><a resp-toggle='modal' resp-target='#shouldloginmodal' >@"+resp.list[i].authorname+" </a></span><span>"+resp.list[i].createtime+"</span><h1><a href='../p/"+resp.list[i].forumid+"'>"+resp.list[i].title+"</a></h1><p>"+resp.list[i].description+"</p><p><a href=''class='blog-continue'>continue</a></p></div></article>")}}}else{$('#midleDiv').append("<p class=''><h1 class='am-text-danger'>尽头了,谢谢</h1></p>")}$('#pageul').empty();var p=resp.pageinfo.currentpage-1;var pp=resp.pageinfo.currentpage+1;for(var i=1;i<=resp.pageinfo.totalpage;i++){if(i==1){$('#pageul').append(" <button id='presearchbutton' type='button' class='am-btn am-btn-primary am-pagination-prev' onclick='preSearch("+p+")' >« 上一页</button>");$('#pageul').append(" <button type='button' class='am-btn am-btn-primary am-pagination-prev' onclick='search("+i+")' >"+i+"</button>")}else if(i==resp.pageinfo.totalpage){$('#pageul').append("<button type='button' class='am-btn am-btn-primary' onclick='search("+i+")' >"+i+"</button>");$('#pageul').append("<button id='nextsearbutton' type='button' class='am-btn am-btn-danger' onclick='nextSearch("+pp+")' >下一页 »</button>")}else{$('#pageul').append("<button type='button' class='am-btn am-btn-primary' onclick='search("+i+")' >"+i+"</button>")}}$('#totalpage').val(resp.pageinfo.totalpage)}})}};function preSearch(page){if(null==page||page==undefined){}else if(page<1){$('#midleDiv').append("<p class=''><h1 class='am-text-danger'>第一页了,谢谢</h1></p>")}else{$('#presearchbutton').attr("onclick","preSearch("+page+")");search(page);page=page-1}};function nextSearch(page){if(null==page||page==undefined){}else{var totalpage=$('#totalpage').val();if(page>totalpage){$('#midleDiv').append("<p class=''><h1 class='am-text-danger'>尽头了,谢谢</h1></p>")}else{search(page);$('#nextbutton').attr("onclick","forumContent("+page+")");page=page+1}}};