(function($) {
    $.extend($.fn.validatebox.defaults.rules, {
        phone: {
            validator: function(value){
                return /^[\d]{11}$/.test(value);
            },
            message: '手机号请输入11位数字'
        }
    });
    $.extend($.fn.validatebox.defaults.rules, {
        sql: {
            validator: function(value,params){
                debugger;
                alert(params);
                var message;
                if(flag=="1"){
                    message={
                        "tableName":tableName,
                        "columnName":columnName,
                        "dateValue":dateValue

                    }
                }else if(flag=="2"){
                    message={
                        "tableName":tableName,
                        "columnName":columnName,
                        "dateValue":dateValue,
                        "tableIdName":tableIdName,
                        "tableIdValue":tableIdValue
                    }
                }
                var msg=jQuery.fn.commonPlugin.commonAjaxJson("/validate",message);
                if(msg.header.resultCode=="0"){
                    return true;
                }else if(msg.header.resultCode=="1"){
                    return false;
                }

            },
            message: '信息已经存在'
        }
    });
})(jQuery);
