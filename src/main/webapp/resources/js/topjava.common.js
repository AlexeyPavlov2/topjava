function makeEditable() {
   /* $(".delete").click(function () {
        deleteRow($(this).attr("id"));
    });*/

    $(".delete").click(function () {
        deleteRow($(this).closest('tr').attr("id"));
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

function add() {
    $("#detailsForm").find(":input").val("");
    $("#editRow").modal();
}

function deleteRow(id) {
    //alert("ID:" + ajaxUrl + id);
    $.ajax({
        url: ajaxUrl + id,
        type: "DELETE"
    }).done(function () {
        updateTable();
        successNoty("Deleted");
    });
}

function editRow(id) {
    alert("ID:" + ajaxUrl + id);
}

function save() {
    let form = $("#detailsForm");
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        updateTable();
        successNoty("Saved");
    });
}

function fillTable(data) {
    datatableApi.clear().rows.add(data).draw();
}

let failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + text,
        type: 'success',
        layout: "bottomRight",
        timeout: 1000
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;Error status: " + jqXHR.status,
        type: "error",
        layout: "bottomRight"
    }).show();
}

function showEditCol(data, type, row) {
    if (type === "display") {
        return "<a onclick='editRow(" + row.id + ");'><span class='fa fa-pencil'></span></a>";
    }
}

function showDeleteCol(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.id + ");'><span class='fa fa-remove'></span></a>";
    }
}

function showDateTime(data, type, row) {
    //console.log("DATA: " + data + " " + type);
    if (type === "display") {
        return data.replace("T", " ");
    }
}

