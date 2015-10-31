var scripts = [
    null,
    "/assets/js/jquery-ui.custom.js",
    "/assets/js/jquery.ui.touch-punch.js",
    "/assets/js/bootbox.js",
    "/assets/js/spin.js",
    "/assets/js/jsrender.js",
    "/assets/js/jquery.serializeObject.js",
    "/assets/js/jquery.pagination-1.2.7.js",
    null]
$('.page-content-area').ace_ajax('loadScripts', scripts, function () {
    //inline scripts related to this page
    $(function ($) {

        $("table tbody").on(ace.click_event, '.bootbox-confirm-edit', function () {
            var target = $(this);
            bootbox.confirm("<h1>确定修改吗?</h1>", function (result) {
                if (result) {
                    var url = '#led/edit?id=' + target.attr('identity');
                    $('.page-content-area[data-ajax-content=true]').ace_ajax('loadUrl', url);
                    window.location.href = url;
                }
            });
            return false;
        });

        $("table tbody").on(ace.click_event, '.bootbox-confirm-delete', function () {
            var target = $(this);
            bootbox.confirm("<h1>确定删除吗?</h1>", function (result) {
                if (result) {
                    $('.page-content-area').ace_ajax('startLoading');
                    var products = [{id: target.attr('identity'), remove: true}];
                    $.ajax('/led/remove',
                        {
                            contentType: 'application/json; charset=utf-8',
                            data: JSON.stringify(products),
                            dataType: "json",
                            type: "post"
                        })
                        .success(function (result) {
                            $.gritter.add({
                                title: '删除提示',
                                text: result.success ? '删除成功' : '删除失败',
                                class_name: 'gritter-center '+ (result.success ? 'gritter-success' : 'gritter-error'),
                                time: 700
                            });
                            var currentPageIndex = $("#pagination").data().page.currentPageIndex;
                            $("#pagination").page('remote', currentPageIndex);
                        }).complete(function (jqXHR, textStatus) {
                            $('.page-content-area').ace_ajax('stopLoading', true);
                        });
                }
            });
            return false;
        });
    });

    $("#pagination").page({
        showFirstLastBtn: true,
        firstBtnText: '首页',
        lastBtnText: '尾页',
        prevBtnText: '上一页',
        nextBtnText: '下一页',
        showInfo: true,
        showJump: true,
        jumpBtnText: '跳转',
        showPageSizes: true,
        pageSize: 20,
        pageSizeItems: [20, 30, 50, 100],
        infoFormat: '{start} ~ {end}条，共{total}条',
        remote: {
            sort:'sort=id,desc',
            params: function () {
                return $('.form-search').data('query');
            },
            url: '/led/list',
            beforeSend: function (XMLHttpRequest) {
                $('.page-content-area').ace_ajax('startLoading');
            },
            totalName: 'totalElements',
            pageSizeName: 'size',
            pageIndexName: 'page',
            success: function (data, pageIndex) {
                $(".m-pagination-group input[type='text']").css({'padding': '0px'});
                $("#dynamic-table tbody").empty().html($("#tableTmpl").render(data.content));
                $('[data-rel=tooltip]').tooltip();
            },
            complete: function (XMLHttpRequest, textStatu) {
                $('.page-content-area').ace_ajax('stopLoading', true);
            }
        }
    }).on("pageClicked", function (event, pageIndex) {
        $("#eventLog").append('EventName = pageClicked , pageIndex = ' + pageIndex + '<br />');
    }).on('jumpClicked', function (event, pageIndex) {
        $("#eventLog").append('EventName = jumpClicked , pageIndex = ' + pageIndex + '<br />');
    }).on('pageSizeChanged', function (event, pageSize) {
        $("#eventLog").append('EventName = pageSizeChanged , pageSize = ' + pageSize + '<br />');
    });

    $('.form-search[role="form"]').submit(function () {
        $("#pagination").page('remote', 0, $('.form-search').serializeObject());
        return false;
    });
});

