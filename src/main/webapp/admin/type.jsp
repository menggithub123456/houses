<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <script src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript">
        $(function () {
            $('#dg').datagrid({
                title:"类型信息表",
                fitColumns:true,
                toolbar: '#tb',
                url:'getType',//服务器地址
                fit:true,//分页栏到底部
                sortName:'id',//按照某个字段名排序
                remoteSort:false,//设置为true在服务端排序，设置为false时在客户端排序
                sortOrder:'desc',
                pagination:true,//启用分页
                /*启动分页后
                * datagrid自动提交数据-->页码：page，页大小：rows
                *后台需要返回-->总行数：total，当前页数据：rows（[{},{},{}...]格式）
                * 才能进行分页显示
                * */
                pageList:[3,6,9],//可选每页大小
                pageSize:3,//默认每页大小
                pagePosition:"bottom",
                columns:[[
                    {field:'ck',title:'',width:100,checkbox:true},
                    {field:'id',title:'编号',width:100},
                    {field:'name',title:'类型',width:100},
                    {field:'a',title:'删除',width:100,formatter: function(value,row,index){
                            return "<a href='javascript:del("+row.id+")'>删除</a>";
                        }
                    }
                ]]
            });
        });
        //打开弹窗
        function but(id,msg){
            $(id).dialog('open').dialog('setTitle',msg);
        }
        //取消按钮
        function CloseDialog(id){
            $(id).dialog('close');
        }
//添加》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》
        function SaveDialog() {
            //表单异步提交添加
            $("#addTypeForm").form('submit', {
                url: 'insertType',
                success: function (result) {
                    if (result) {
                        $("#AddType").dialog("close");//关闭弹窗
                        $('#dg').datagrid('reload');//自动刷新
                        $.messager.alert("系统提示", "保存成功");
                    } else {
                        $.messager.alert("系统提示", "保存失败");
                    }
                }
            });
        }
//修改》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》
        function modifyBySelect() {
            //获取datagrid选中行  返回的数组
            var SelectRows = $("#dg").datagrid('getSelections');
            if (SelectRows.length!=1){
                $.messager.alert('提示框','你还没有选中行，或者选择了多行.','info');
                return;
            }
            //打开窗口
            $("#upType").dialog("open").dialog("setTitle","修改数据");
            //将数据回显示到表单中  键需和表单对象名称相同
            //一.
            /*var row=SelectRows[0];  获取选中行对象进行回显示 {"id":xxx,"name":sss}
            $('#upDialogForm').form('load',row);*/
            //二.通过主键从数据库查询单条对象进行回显(当datagrid的行数据无法满足表单显示时)
            //发送异步请求获取对象进行回显
            var row=SelectRows[0];
            $.post("getAType",{"id":row.id},function(data){
                //回显
                $('#upTypeForm').form('load',data);
            },"json");
        }
        function updateDialog() {
            //表单异步提交添加
            $("#upTypeForm").form('submit', {
                url: 'updateType',
                success: function (result) {
                    if (result) {
                        $("#upType").dialog("close");//关闭弹窗
                        $('#dg').datagrid('reload');//自动刷新
                        $.messager.alert("系统提示", "修改成功");
                    } else {
                        $.messager.alert("系统提示", "修改失败");
                    }
                }
            });
        }
//删除》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》
        //》》》》》》》》》》》》删除单条
        function del(id){
            $.messager.confirm('提示框', 'if you delete me,I will hate you?', function(r){
                if (r){
                    $.post("delType",{"id":id},function(data){
                        if (data){
                            $('#dg').datagrid('reload');//自动刷新
                            $.messager.alert("系统提示", "删除成功");
                        }else {
                            $.messager.alert("系统提示", "删除失败");
                        }
                    },"json");
                }
            });
        }
        //》》》》》》》》》》》》批量删除
        function delBySelect(){
            //获取datagrid选中行  返回的数组
            var SelectRows = $("#dg").datagrid('getSelections');
            if (SelectRows.length==0){
                $.messager.alert('提示框','你还没有选中行','info');
                return;
            }
            $.messager.confirm('提示框', 'if you delete me,I will hate you?', function(r){
                if (r){//为true实现删除
                    // 调用服务器接口进行删除
                    var value="";//获取选中项的值
                    for(var i=0;i<SelectRows.length;i++){
                        value=value+SelectRows[i].id+",";
                    }
                    value=value.substring(0,value.length-1);//去除最后的逗号
                    //发送异步请求到服务器
                    $.post("delMoreType",{"id":value},function(data){
                        if(data.result>0) {
                            //实现datagrid的刷新
                            $('#dg').datagrid('reload');
                        }else{
                            $.messager.alert('提示框','删除失败！^_^','info');
                        }
                    },"json");
                }
            });
        }
    </script>
</head>
<body>
<table id="dg"></table>
<div id="tb">
    <a href="javascript:modifyBySelect()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    <a href="javascript:but('#AddType','添加数据')" class="easyui-linkbutton" data-options="iconCls:'icon-add' ,plain:true">添加</a>
    <a href="javascript:delBySelect()" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true">批量删除</a>
</div>
//添加
<div id="AddType" class="easyui-dialog" buttons="#AddTypeButtons"
     style="width: 280px; height: 200px; padding: 10px 20px;" closed="true">
    <form id="addTypeForm" method="post">
        <table>
            <tr>
                <td>类型:</td>
                <td><input type="text" name="name" id="name" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="AddTypeButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog('#AddType')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
//修改
<div id="upType" class="easyui-dialog" buttons="#upTypeButtons"
     style="width: 280px; height: 200px; padding: 10px 20px;" closed="true">
    <form id="upTypeForm" method="post">
        <table>
            <tr>
                <td>编号:</td>
                <td><input type="text" readonly style="border: none" name="id" id="namea" /></td>
            </tr>
            <tr>
                <td>类型:</td>
                <td><input type="text" name="name" id="nameb" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="upTypeButtons">
    <a href="javascript:updateDialog()" class="easyui-linkbutton" iconCls="icon-ok">跟新</a>
    <a href="javascript:CloseDialog('#upDialog')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>
