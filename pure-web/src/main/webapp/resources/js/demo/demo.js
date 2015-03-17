$(document).ready(function() {
	searchProduct();
});


function getProjectPath(){
	var pathName = window.document.location.pathname;
	var projectName = pathName.substring( 0 ,pathName.indexOf("/",1));
	return projectName;
}



function searchProduct(){
    	var path= getProjectPath();
    	
	     $('#productSearchList').datagrid({
	        url: path+'/datagrid_data1.json',
	        singleSelect:false,
	        striped:true,
	        idField:'rmaDetailId',
	        rownumbers:true,
	        checkOnSelect: true,
	        selectOnCheck:true,
	        frozenColumns: [
	            [
	                {
	                    title:'rmaDetailId',
	                    field:'rmaDetailId',
	                    checkbox: true
	                }
	            ]
	        ],
	        columns: [
	            [
	             	
	                {
	                    title: '型号',
	                    field: 'partNumber',
	                    width: 120
	                },
	                {
	                    title: '品牌',
	                    field: 'linecard',
	                    width: 100
	                },
	                {
	                    title: '产品描述',
	                    field: 'partNumberDesc',
	                    width: 100
	                },
	                {
	                    title: '批次',
	                    field: 'lotNo',
	                    width: 100
	                },
	                {
	                    title: '生产日期',
	                    field: 'produceDate',
	                    width: 100
	                },
	                {
	                    title: '当初采购价',
	                    field: 'unitePrice',
	                    align:'right',
	                    width: 100
	                },
	                {
	                    title: '采购订单',
	                    field: 'poNo',
	                    width: 100
	                },
	                {
	                    title: '剩余数量',
	                    field: 'remainCount',
	                    align:'right',
	                    width: 100
	                },
	                {
	                    title: '退换货数量',
	                    field: 'qty',
	                    align:'right',
	                    width: 100,
	                    editor:{type:'numberbox',options:{required:'true'}}
	                },
	                {
	                    title: '金额',
	                    field: 'money',
	                    align:'right',
	                    width: 100
	                },
	                {
	             		title: '单位',
	                    field: 'unit',
	                    width: 50,
	                    hidden:true
	             	},
	                {
	                	title:'币种',
	                	field:'currency',
	                	width: 50
	                }
	            ]
	        ],
	        onLoadSuccess: function () {
	        	
	        },
	        onDblClickRow:function (rowIndex, rowData) {
	        },
	        onClickRow:function(rowIndex,rowData){
	        	startEdt(rowIndex);
	        },
	        onAfterEdit: function (rowIndex, rowData, changes) {
	        	endEdt(rowIndex, rowData, changes);
	        }
	    });
    }
//编辑行标志
var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true;}
    if ($("#productSearchList").datagrid('validateRow', editIndex)){
    	$("#productSearchList").datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}

//开始编辑
function startEdt(index){
	 if (editIndex != index){
         if (endEditing()){
        	 $("#productSearchList").datagrid('selectRow', index).datagrid('beginEdit', index);
             editIndex = index;
         } else {
        	 $("#productSearchList").datagrid('selectRow', editIndex);
         }
     }
}

//结束编辑
function endEdt(rowIndex, rowData, changes){
	var qty=rowData.qty;
	var unitePrice=rowData.unitePrice;//给金额赋值
	var allMoney =(qty * unitePrice).toFixed(2);//保留小数点后两位
	$('#productSearchList').datagrid('updateRow',{
    	index: rowIndex,
    	row: {
    		money: allMoney
    	}
    });
}