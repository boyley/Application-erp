var scripts = [null,
    "/assets/js/jquery-ui.custom.js",
    "/assets/js/jquery.ui.touch-punch.js",
    '/assets/js/bootbox.js',
    "/assets/js/date-time/bootstrap-datepicker.js",
    {url: "/assets/js/validity.state.js", cache: false},
    {url: "/assets/js/jquery.iframe-post-form.js", cache: false},
    null]
$('.page-content-area').ace_ajax('loadScripts', scripts, function () {

    $('.date-picker').datepicker({
        autoclose: true,
        language: 'zh-CN',
        todayHighlight: true,
        clearBtn: true
    })

    //$(".date-picker").datetimepicker({
    //    format: 'yyyy-mm-dd',
    //    language: 'zh-CN',
    //    todayHighlight: true,
    //    autoclose: true,
    //    todayBtn: true
    //});

    $('form[role="form"]').iframePostForm({
        json: true,
        post: function () {
            $('.page-content-area').ace_ajax('startLoading');
        },
        complete: function (response) {
            $('.page-content-area').ace_ajax('stopLoading', true);
            if (response.success) {
                bootbox.dialog({
                    title: '提示',
                    size: 'small',
                    closeButton: false,
                    message: "<h3 class='bigger-110'>" + response.msg + "</h3>",
                    buttons: {
                        "proceed": {
                            "label": "<i class='ace-icon fa fa-check'></i> 继续添加",
                            "className": "btn-sm btn-success",
                            "callback": function () {
                                var url = '#order/publish';
                                $('.page-content-area[data-ajax-content=true]').ace_ajax('loadUrl', url);
                                window.location.href = url;
                            }
                        },
                        "back": {
                            "label": "<i class='ace-icon fa fa-reply'></i> 返回列表页面",
                            "className": "btn-sm btn-primary",
                            "callback": function () {
                                //Example.show("Primary button");
                                var url = '#page/admin/order/led-order-list.html';
                                $('.page-content-area[data-ajax-content=true]').ace_ajax('loadUrl', url);
                                window.location.href = url;
                            }
                        }
                    }
                });
            }  else if(!response.success && response.code == 501) {
                // 字段不合法
                var errors = response.data;
                console.info(errors);
                for (var i = 0; i < errors.length; i++) {
                    var error = errors[i];
                    var obj = $('input[name="' + error.field + '"]');
                    $.fn.aceInvalid(obj, false);
                }
            }
        }
    });


    /**选择LED产品事件*/
    if (!ace.vars['touch']) {
        $('select.chosen-select').chosen({allow_single_deselect: true, no_results_text: '无可匹配的结果'})
            .trigger("chosen:updated.chosen")
            .ready(function () {
                console.info($('div.form-group input[name="price"]').val());
                if (!$('div.form-group input[name="price"]').val()) {
                    $('div.form-group input[name="price"]').val($('select.chosen-select option').first().attr('price'));
                }
            })
            .change(function (event, option) {
                var id = option.selected;
                var selected = $('select.chosen-select option[value="' + id + '"]');
                console.info(selected);
                $('div.form-group input[name="price"]').val(selected.attr('price'));
            });
    }

    $('.calculate').on('keyup', function () {
        calculate();
    });

    $('.calculate').on('change', function () {
        calculate();
    });

    function calculate() {
        var number = parseInt($('.form-group input[name="number"]').val());
        var sale = parseFloat($('div.form-group input[name="sale"]').val());
        var price = parseFloat($('div.form-group input[name="price"]').val());
        if (isNaN(number)) {
            number = 0;
        }
        if (isNaN(sale)) {
            sale = 0;
        }
        if (isNaN(price)) {
            price = 0;
        }
        $('div.form-group input[name="allPrice"]').val(number * price);
        $('div.form-group input[name="allSale"]').val(number * sale);
        $('div.form-group input[name="profit"]').val(number * sale - number * price);

    }
});
