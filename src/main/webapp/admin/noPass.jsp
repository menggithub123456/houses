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
                title:"未审核房屋信息",
                fitColumns:true,
                toolbar: '#tb',//添加工具栏
                url:'Pass?ispass=0',//服务器地址
                //fit:true,//分页栏到底部
                pagination:true,//启用分页
                pageList:[3,5,7],//可选每页大小
                pageSize:3,//默认每页大小
                pagePosition:"bottom",
                columns:[[
                    {field:'ck',title:'',width:100,checkbox:true},
                    {field:'id',title:'编号',width:100},
                    {field:'title',title:'标题',width:100},
                    {field:'price',title:'价格',width:100},
                    {field:'pubdate',title:'发布日期',width:100,formatter: function(value,row,index){
                       var value=new Date(value);
                        var y=value.getFullYear();
                        var m=value.getMonth()+1;
                        var t=value.getDate();

                        return y+"年"+m+"月"+t+"日";
                        }},
                    {field:'contact',title:'联系方式',width:100},
                    {field:'floorage',title:'面积',width:100},
                    {field:'dname',title:'区域名称',width:100},
                    {field:'sname',title:'街道名称',width:100},
                    {field:'tname',title:'类型',width:100},
                    {field:'a',title:'操作',width:100,formatter: function(value,row,index){
                            return "<a href='javascript:ispassyOrn("+row.id+")'>设为审核</a>";
                        }
                    }
                ]]
            });
        });
        function ispassyOrn(id) {
            $.post("isPassyesOrno",{"id":id,"ispass":1},function (data) {
                if (data.result>0){
                    $("#dg").datagrid("reload");//刷新
                }else {
                    $.messager.alert('提示框','失败！^_^','info');
                }
            },"json");
        }
        function search() {
            var floorage=$("#floorage").val();
            var price=$("#price").val();
            $("#dg").datagrid("load",{"floorage":floorage,"price":price})
        }
    </script>
</head>
<body>
<table id="dg"></table>
<div id="tb">
    <div>
        <a href="javascript:" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">批量审核</a>
    </div>
    <div>
        面积:<input type="text" name="floorage" id="floorage">
    价格:<input type="text" name="price" id="price">
    <a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>
</body>
</html>
