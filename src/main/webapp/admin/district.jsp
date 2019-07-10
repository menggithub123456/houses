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
                title:"区域信息表",
                fitColumns:true,
                toolbar: '#tb',
                url:'getDistrict',//服务器地址
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
                pageList:[3,5,7],//可选每页大小
                pageSize:3,//默认每页大小
                pagePosition:"bottom",
                columns:[[
                    {field:'ck',title:'',width:100,checkbox:true},
                    {field:'id',title:'编号',width:100},
                    {field:'name',title:'区域',width:100},
                    {field:'a',title:'操作',width:100,formatter: function(value,row,index){
                            return "<a href='javascript:del("+row.id+")'>删除</a>" +
                                "&nbsp;<a href='javascript:showStreet("+row.id+")'>查看街道</a>";
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
            $("#addDialogForm").form('submit', {
                url: 'insertDistrict',
                success: function (result) {
                    if (result) {
                        $("#AddDialog").dialog("close");//关闭弹窗
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
            $("#upDialog").dialog("open").dialog("setTitle","修改数据");
            //将数据回显示到表单中  键需和表单对象名称相同
            //一.
            /*var row=SelectRows[0];  获取选中行对象进行回显示 {"id":xxx,"name":sss}
            $('#upDialogForm').form('load',row);*/
            //二.通过主键从数据库查询单条对象进行回显(当datagrid的行数据无法满足表单显示时)
            //发送异步请求获取对象进行回显
            var row=SelectRows[0];
            $.post("getADistrict",{"id":row.id},function(data){
                //回显
                $('#upDialogForm').form('load',data);
            },"json");
        }
        function updateDialog() {
            //表单异步提交添加
            $("#upDialogForm").form('submit', {
                url: 'updateDistrict',
                success: function (result) {
                    if (result) {
                        $("#upDialog").dialog("close");//关闭弹窗
                        $('#dg').datagrid('reload');//自动刷新
                        $.messager.alert("系统提示", "修改成功");
                    } else {
                        $.messager.alert("系统提示", "修改失败");
                    }
                }
            });
        }
//删除》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》
        //》》》》》》》》》》》》批量删除
        function delBySelect(){
            //获取datagrid选中行  返回的数组
            var SelectRows = $("#dg").datagrid('getSelections');
            if (SelectRows.length==0){
                $.messager.alert('提示框','你还没有选中行','info');
                return;
            }
            $.messager.confirm('提示框', 'if you delete me,I will hate you?', function(r){
                if (r){
                    var myArray=new Array();
                    for (i=0;i<SelectRows.length;i++) {
                        myArray[i]=SelectRows[i].id;
                    }
                    $.ajax({
                        url:"delMoreDistrict",
                        data:{"id":myArray},
                        dataType:"json",
                        traditional:true,
                        type:"post",
                        success:function(result){
                            alert(result);
                            if (result) {
                                $('#dg').datagrid('reload');//自动刷新
                                $.messager.alert("系统提示", "删除成功");
                            } else {
                                $.messager.alert("系统提示", "删除失败");
                            }
                        }
                    });
                }
            });
        }
        //》》》》》》》》》》》》删除单条
        function del(id){
            $.messager.confirm('提示框', 'if you delete me,I will hate you?', function(r){
                if (r){
                    $.post("delDistrict",{"id":id},function(data){
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
//在区域页面中显示街道信息
    function showStreet(districtId) {
        //打开窗口
        $("#showStreetlist").dialog("open").dialog("setTitle","街道明细");
        $("#districtId").val(districtId);//存入区域id用于添加街道
        //显示当前区域下的街道
        $('#ss').datagrid({
            title:"街道详情",
            fitColumns:true,
            url:'getStreet?id='+districtId,//服务器地址
            pagination:true,//启用分页
            /*启动分页后
            * datagrid自动提交数据-->页码：page，页大小：rows
            *后台需要返回-->总行数：total，当前页数据：rows（[{},{},{}...]格式）
            * 才能进行分页显示
            * */
            pageList:[3,5,7],//可选每页大小
            pageSize:3,//默认每页大小
            pagePosition:"bottom",
            columns:[[
                {field:'ck',title:'',width:100,checkbox:true},
                {field:'name',title:'街道名称',width:100},
                {field:'id',title:'操作',width:100,formatter: function(value,row,index){
                        return "<a href='javascript:del("+value+")'>删除</a>";
                    }
                }
            ]]
        });
    }
  //添加街道
    function adds() {
        //取值
        var name=$("#name").val();
        var districtId=$("#districtId").val();
        alert(name+districtId);
        //发送异步请求
        $.post("insertStreet",{"name": name,"districtId":districtId},function (data) {
                if (data) {
                    //清空文本框 并获得焦点
                    document.getElementById('name').value="";
                    document.getElementById('name').focus();
                    //关闭对话框
                    //$("#ShowStreet").dialog("close");
                    //实现datagrid的刷新
                    $('#ss').datagrid('reload');
                    $.messager.alert('提示框', '添加成功！噢耶!!', 'info');
                } else {
                    $.messager.alert('提示框', '添加失败！^_^', 'info');
                }
            }, "json");
    }
    //按钮删除
    function dStreet() {
        //获取datagrid选中行  返回的数组
        var SelectRows = $("#ss").datagrid('getSelections');
        if (SelectRows.length==0){
            $.messager.alert('提示框','你还没有选中行','info');
            return;
        }
        var row=SelectRows[0];
        $.post("delAStreet",{"id":row.id},function(data){
            if (data){
                $('#ss').datagrid('reload');
                $.messager.alert('提示框', '删除成功', 'info');
            } else {
                $.messager.alert('提示框', '删除失败！^_^', 'info');
            }
        },"json");
    }
    function del(id) {
        $.messager.confirm('提示框', 'if you delete me,I will hate you?', function(r) {
            if (r) {
                $.post("delAStreet",{"id":id},function(data){
                    if (data){
                        $('#ss').datagrid('reload');
                        $.messager.alert('提示框', '删除成功', 'info');
                    } else {
                        $.messager.alert('提示框', '删除失败！^_^', 'info');
                    }},"json");
            }
        });
    }
    </script>
</head>
<body>
<table id="dg"></table>
<div id="tb">
    <a href="javascript:modifyBySelect()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    <a href="javascript:but('#AddDialog','添加数据')" class="easyui-linkbutton" data-options="iconCls:'icon-add' ,plain:true">添加</a>
    <a href="javascript:delBySelect()" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true">批量删除</a>
</div>
//添加
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 200px; padding: 10px 20px;" closed="true">
    <form id="addDialogForm" method="post">
        <table>
            <tr>
                <td>区域:</td>
                <td><input type="text" name="name" id="aname" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog('#AddDialog')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
//修改
<div id="upDialog" class="easyui-dialog" buttons="#upDialogButtons"
     style="width: 280px; height: 200px; padding: 10px 20px;" closed="true">
    <form id="upDialogForm" method="post">
        <table>
            <tr>
                <td>编号:</td>
                <td><input type="text" readonly style="border: none" name="id" id="namea" /></td>
            </tr>
            <tr>
                <td>区域:</td>
                <td><input type="text" name="name" id="nameb" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="upDialogButtons">
    <a href="javascript:updateDialog()" class="easyui-linkbutton" iconCls="icon-ok">跟新</a>
    <a href="javascript:CloseDialog('#upDialog')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
//显示街道
<div id="showStreetlist" class="easyui-dialog" buttons="#showButtons"
     style="width: 800px; height: 500px; padding: 10px 20px;" closed="true">
    <table id="ss"></table>
    <hr/>
    <a href="javascript:adds()" class="easyui-linkbutton" iconCls="icon-add">添加</a>
    <input type="text" class="easyui-validatebox" required name="name" id="name" />
    <input type="hidden" name="districtId" id="districtId"/>
    <input type="text" name="id" id="id"/>
    <div>
        <a href="javascript:dStreet()" class="easyui-linkbutton" iconCls="icon-no">删除</a>
    </div>
</div>
<div id="showButtons">
    <a href="javascript:CloseDialog('#showStreetlist')" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>
