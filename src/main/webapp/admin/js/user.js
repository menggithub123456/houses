$(function () {
    $('#dg').datagrid({
        title:"区域信息表",
        fitColumns:true,
        toolbar: '#tb',
        url:'getUsers',//服务器地址
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
            {field:'name',title:'名字',width:100},
            {field:'telephone',title:'电话',width:100},
            {field:'a',title:'删除',width:100,formatter: function(value,row,index){
                    return "<a href='javascript:del("+row.id+")'>删除</a>";
                }
            }
        ]]
    });
});
function search() {
    //实现搜索查询
    //datagrid的load方法是重新加载，它会将查询条件，随着页码,页大小
    //一起发送到当前控制所指定的服务器地址进行处理
  var u_name=$("#u_name").val();
  var u_tel=$("#u_tel").val();
  $("#dg").datagrid("load",{"name":u_name,"telephone":u_tel})
}
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
    $("#addForm").form('submit', {
        url: 'addUsers',
        success: function (result) {
            if (result) {
                $("#Add").dialog("close");//关闭弹窗
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
    $("#Update").dialog("open").dialog("setTitle","修改数据");
    //将数据回显示到表单中  键需和表单对象名称相同
    //一.
    /*var row=SelectRows[0];  获取选中行对象进行回显示 {"id":xxx,"name":sss}
    $('#upDialogForm').form('load',row);*/
    //二.通过主键从数据库查询单条对象进行回显(当datagrid的行数据无法满足表单显示时)
    //发送异步请求获取对象进行回显
    var row=SelectRows[0];
    $.post("getAUsers",{"id":row.id},function(data){
        //回显
        $('#UpdateForm').form('load',data);
    },"json");
}
function updateDialog() {
    //表单异步提交添加
    $("#UpdateForm").form('submit', {
        url: 'updateUsers',
        success: function (result) {
            if (result) {
                $("#Update").dialog("close");//关闭弹窗
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
            $.post("#",{"id":id},function(data){
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
            $.post("#",{"id":value},function(data){
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