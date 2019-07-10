<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0032)http://localhost:8080/HouseRent/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 用户登录</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
<script language="JavaScript" src="../admin/js/jquery-1.8.3.js"></script>
  <script language="JavaScript">
      var code; //在全局定义验证码
      //产生验证码
      window.onload = function() {
          createCode();
      }

      function createCode() {
          code = "";
          var codeLength = 5; //验证码的长度
          var checkCode = document.getElementById("checkCode");
          var random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
              'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //随机数
          for(var i = 0; i < codeLength; i++) { //循环操作
              var charIndex = Math.floor(Math.random() * 36); //取得随机数的索引
              code += random[charIndex]; //根据索引取得随机数加到code上
          }
          checkCode.value = code; //把code值赋给验证码
      }
      //校验验证码
      $(function () {
          $("#input").blur(function () {
              var inputCode = document.getElementById("input").value.toUpperCase(); //取得输入的验证码并转化为大写
              if (inputCode.length <= 0) { //若输入的验证码长度为0
                  $("#sp").html("请输入验证码！");//则弹出请输入验证码
              } else if (inputCode != code) { //若输入的验证码与产生的验证码不一致时
                  $("#sp").html("验证码输入错误！");//则弹出验证码输入错误
                  createCode(); //刷新验证码
              } else { //输入正确时
                  $("#sp").html("^-^");
              }
          });
      });
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DIV class=box>
<H4>用户登录</H4>
<FORM id=user method=post name=user action="loginUser">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD colSpan=2>${info}</TD></TR>
  <TR>
    <TD class=field>用 户 名：</TD>
    <TD><!-- <input type="text" class="text" name="name" /> --><INPUT 
      id=user_name class=text type=text name=name> </TD></TR>
  <TR>
    <TD class=field>密　　码：</TD>
    <TD><!-- <input type="password" class="text" name="password" /> --><INPUT 
      id=user_password class=text type=password name=password> </TD></TR>
  <tr>
		<td class="field">验 证 码：</td>
		<td><div>
    <input type="text" id="input" />
    <input type="button" id="checkCode" class="code" onclick="createCode()" />
    <a href="#" onclick="createCode()">看不清楚</a><br>
          <span id="sp"></span>
  </div></td>
</tr>
</TBODY></TABLE>
<DIV class=buttons> <INPUT value=登陆 type="submit">
  <INPUT onclick='document.location="regs.jsp"' value=注册 type=button>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
