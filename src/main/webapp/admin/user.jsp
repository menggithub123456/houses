<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <script src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript" src="js/user.js"></script>
</head>
<body>
<table id="dg"></table>
<div id="tb">
<div>
    <a href="javascript:modifyBySelect()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    <a href="javascript:but('#Add','添加数据')" class="easyui-linkbutton" data-options="iconCls:'icon-add' ,plain:true">添加</a>
    <a href="javascript:delBySelect()" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true">批量删除</a>
</div>
<div>名称:<input type="text" name="name" id="u_name">
    电话:<input type="text" name="telephone" id="u_tel">
    <a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
</div>
</div>
//添加
<div id="Add" class="easyui-dialog" buttons="#AddButtons"
     style="width: 280px; height: 200px; padding: 10px 20px;" closed="true">
    <form id="addForm" method="post">
        <table>
            <tr>
                <td>编号:</td>
                <td><input type="text" name="id"  /></td>
            </tr>
            <tr>
                <td>姓名:</td>
                <td><input type="text" name="name"  /></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="text" name="password"  /></td>
            </tr>
            <tr>
            <td>电话:</td>
            <td><input type="text" name="telephone"/></td>
            </tr>
            <tr>
                <td>类型(0或1):</td>
                <td><input type="text" name="isadmin"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="AddButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog('#Add')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
//修改
<div id="Update" class="easyui-dialog" buttons="#UpdateButtons"
     style="width: 280px; height: 200px; padding: 10px 20px;" closed="true">
    <form id="UpdateForm" method="post">
        <table>
            <tr>
                <td>编号:</td>
                <td><input type="text" readonly style="border: none" name="id" id="namea" /></td>
            </tr>
            <tr>
                <td>姓名:</td>
                <td><input type="text" name="name"  /></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="text" name="password"  /></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" name="telephone"/></td>
            </tr>
            <tr>
                <td>类型(0或1):</td>
                <td><input type="text" name="isadmin"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="UpdateButtons">
    <a href="javascript:updateDialog()" class="easyui-linkbutton" iconCls="icon-ok">跟新</a>
    <a href="javascript:CloseDialog('#Update')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>
