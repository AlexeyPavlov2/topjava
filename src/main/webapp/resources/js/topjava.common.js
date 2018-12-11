let context, form;

/*//https://stackoverflow.com/questions/27707787/get-ajax-request-url-in-ajaxsetup-converters-for-logging-purposes
$(document).ajaxError(function(e, xhr, settings, err) {
    var message = 'url: ' + settings.url + ' ' + err.message;
    // push message as part of an error to custom error reporting
    alert(message);
});*/

//https://stackoverflow.com/questions/27707787/get-ajax-request-url-in-ajaxsetup-converters-for-logging-purposes
$.ajaxSetup({
    converters: {"text json": function(json_string)  {
            var json = JSON.parse(json_string);
            $(json).each(function () {
                //console.log(this);
                if (this.hasOwnProperty("dateTime")) {
                    this.dateTime = this.dateTime.replace("T", " ").substr(0, 16);
                }
                if (this.hasOwnProperty("registered")) {
                    this.registered = this.registered.replace("T", " ").substr(0, 16);
                }



                return this;
            });
            return json;
        }
    }
});


function makeEditable(ctx) {
    context = ctx;
    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

function add() {
    $("#modalTitle").html(i18n["addTitle"]);
    form.find(":input").val("");
    $("#editRow").modal();
}

function updateRow(id) {
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(context.ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
}

function deleteRow(id) {
    $.ajax({
        url: context.ajaxUrl + id,
        type: "DELETE"
    }).done(function () {
        context.updateTable();
        successNoty("common.deleted");
    });
}

function updateTableByData(data) {
    context.datatableApi.clear().rows.add(data).draw();
}

function save() {
    let form = $("#detailsForm");
    if (form.find("#dateTime").get().length > 0) {
        let input = form.find("#dateTime");
        let value =  input.val();
        input.val(value.replace(" ", "T"));
    }

    $.ajax({
        type: "POST",
        url: context.ajaxUrl,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        context.updateTable();
        successNoty("common.saved");
    });


}

let failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + i18n[key],
        type: 'success',
        layout: "bottomRight",
        timeout: 1000
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;" + i18n["common.errorStatus"] + ": " + jqXHR.status + (jqXHR.responseJSON ? "<br>" + jqXHR.responseJSON : ""),
        type: "error",
        layout: "bottomRight"
    }).show();
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");'><span class='fa fa-pencil'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.id + ");'><span class='fa fa-remove'></span></a>";
    }
}

function showDateTime(data, type, row) {
    let dtFormatted = data.replace("T", " ");
    if (dtFormatted.indexOf(".") > -1) {
        dtFormatted = dtFormatted.substr(0, dtFormatted.indexOf("."));
    }
    return dtFormatted;
}