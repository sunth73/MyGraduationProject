;(function ( $, window, document, undefined ){
    $.fn.commonPlugin={
        commonAjaxJson:function (actionName,message) {
            var result;
            jQuery.ajax({
                async:false,// (默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行
                type:"POST",
                url:actionName,
                dataType:"json",
                cache:false,//设置为false不会从缓存加载数据
                timeout:5000,
                // contentType: "application/x-www-form-urlencoded;charset=utf-8",
                contentType:"application/json; charset=utf-8",
                data: JSON.stringify(message),
                success:function (data) {
                    result=data;
                },
                error:function (jqXHR, textStatus, errorThrown) {
                    if(textStatus == "timeout"){
                        result="timeout"
                    }
                }
            });
            return result;
        },
        commonAjaxText:function (actionName,message) {
            var result;
            jQuery.ajax({
                async:false,// (默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行
                type:"POST",
                url:actionName,
                dataType:"text",
                cache:false,//设置为false不会从缓存加载数据
                timeout:5000,
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                data:{
                    message:message
                },
                success:function (data) {
                    result=data;
                },
                error:function (jqXHR, textStatus, errorThrown) {
                    if(textStatus == "timeout"){
                        result="timeout"
                    }
                }
            })
            return result;
        }
    };
})(jQuery, window,document);