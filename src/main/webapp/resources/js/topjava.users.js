const ajaxUrl = "ajax/admin/users/";
let datatableApi;

function showEmail(data, type, row) {
    return type === "display" ? "<a href='mailto:" + data + "'>" + data + "</a>" : data
}

function showCheckbox(data, type, row) {
    return type === "display" ? "<input type='checkbox' " + (data ? "checked" : "") + " onclick='setUserStatus($(this)," + row.id + ");'/>" : data;
}

function showRoles(data, type, row) {
    return type === "display" ? "[" + data + "]" : data;
}

function setUserStatus(checkbox, id) {
    let enabledStatus = checkbox.is(':checked');
    $.ajax({
        url: ajaxUrl + id,
        type: "POST",
        data: 'status=' + enabledStatus
    }).done(function () {
        successNoty("Status changed to " + '"' + enabledStatus + '"');
        checkbox.closest("tr").attr("data-statusEnable", enabledStatus);
        //updateTable();
    }).fail(function () {
        successNoty("Request failed");
    });
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
}

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": false,
        "searching": false,
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email",
                "render": showEmail
            },
            {
                "data": "roles",
                "render": showRoles
            },
            {
                "data": "enabled",
                "render": showCheckbox
            },
            {
                "data": "registered",
                "render": showDateTime
            },
            {
                "render": showEditCol,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": showDeleteCol,
                "defaultContent": "",
                "orderable": false,
            },
        ],

        "createdRow": function (row, data, dataIndex) {
            $(row).attr("data-statusEnable", data.enabled);
        },
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});