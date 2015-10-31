$(function ($) {

    $('form.form-horizontal input').on('invalid', function () {
        oninvalid(this);

    });

    $('form.form-horizontal').on('keyup','input', function () {
        var validityState = this.validity;
        oninvalid(this, validityState.valid);
    });

    $('form.form-horizontal').on('change','input', function () {
        var validityState = this.validity;
        oninvalid(this, validityState.valid);
    });

    function oninvalid(obj, valid) {
        var divGroup = $(obj).closest('.form-group');
        if (valid) {
            divGroup.removeClass('has-error');
            $(obj).next('i').removeClass('fa-times-circle');
            if (!divGroup.hasClass('has-success')) {
                divGroup.addClass('has-success');
                $(obj).next('i').addClass('fa-check-circle');
                $(obj).next('i').removeClass('hide');
                divGroup.find('div.help-block').addClass('hide');
            }
        } else {
            divGroup.removeClass('has-success');
            $(obj).next('i').removeClass('fa-check-circle');
            if (!divGroup.hasClass('has-error')) {
                divGroup.addClass('has-error');
                $(obj).next('i').addClass('fa-times-circle');
                $(obj).next('i').removeClass('hide');
                divGroup.find('div.help-block').show();
            }
        }
    }

    $.fn.aceInvalid = oninvalid;
});

