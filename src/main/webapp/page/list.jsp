<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script language="JavaScript" src="../admin/js/jquery-1.8.3.js"></script>
  <script>
      $(function () {
//异步请求类型数据
          $.post("getTypeddd",null,function (data) {
              for (var i=0;i<data.length;i++){
                  var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                  $("#typeId").append(node);
              }
              //设置选中项
            $("#typeId").val(${param.typeId});
          },"json");
//异步请求区域数据
          $.post("getDistrictddd",null,function (data) {
              for (var i=0;i<data.length;i++){
                  var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                  $("#district_id").append(node);
              }
              //设置选中项
             $("#district_id").val(${param.did});
              //加载街道
              loadStreet($("#district_id").val());
          },"json");
//通过区域id加载街道
          function loadStreet(did){
              $.post("getStreetbydid",{"id":did},function (data) {
                  //清空原有数据
                  $("#street_id>option:gt(0)").remove();
                  for (var i=0;i<data.length;i++){
                      var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                      $("#street_id").append(node);
                  }
                  $("#street_id").val(${param.streetId});
              },"json");
          }
//修改区域事件
          $("#district_id").change(function () {
              loadStreet($("#district_id").val());
          });
      });
      //条件分页查询
      function search(pageNum) {
          $("#page").val(pageNum);
          $("#sform").submit();
      }
</script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV >
  <DIV id=navbar class=wrap>
    <LI class=bold>房屋信息</LI>
    <FORM id="sform" method=post action="getAllHouses">
        <div>
        <input type="hidden" id="page" name="page" value="1">
        标题：<INPUT class=text type=text name=title value="${param.title}">
        类型：<select name="typeId" id="typeId">
                <option value="">不限</option>
        </select>
        区域：<select name="did" id="district_id">
        <option value="">不限</option>
      </select>
        街道：<select name="streetId" id="street_id">
        <option value="">不限</option>
      </select>
        价格：<input type="text" name="startprcie" id="startprcie" value="${param.startprcie}">--
        <input type="text" name="endprcie" id="endprcie" value="${param.endprcie}">
        <LABEL class=ui-blue>
          <INPUT value=搜索房屋 type=submit name=search></LABEL>
        </div>
    </form>
</DIV>
<DIV class="main wrap">
  <c:if test="${!empty info.list}">
  <c:forEach items="${info.list}" var="h">
<TABLE class=house-list>
  <TBODY>
  <TR>
    <TD class=house-thumb>
      <span><A href="details.jsp" target="_blank">
          <img src="http://localhost:80/${h.path}" width="100" height="75" alt=""></a></span></TD>
    <TD>
      <DL>
        <DT><A href="detail?id=${h.id}" target="_blank">${h.title}</A></DT>
        <DD>${h.dname}${h.sname},${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
    <TD class=house-type>${h.tname}</TD>
    <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD></TR>
  </TBODY></TABLE>
  </c:forEach>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:search(1)">首页</A></LI>
  <LI><A href="javascript:search(${info.prePage==0?1:info.prePage})">上一页</A></LI>
  <LI><A href="javascript:search(${info.nextPage==0?info.pages:info.nextPage})">下一页</A></LI>
  <LI><A href="javascript:search(${info.pages})">末页</A></LI></UL><SPAN
class=total>${info.pageNum}/${info.pages}页</SPAN></DIV>
  </c:if>
  <c:if test="${empty info.list}">
    <center  style="color: red; font-size: 24px;">暂无出租房信息</center>
  </c:if>
    </DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
