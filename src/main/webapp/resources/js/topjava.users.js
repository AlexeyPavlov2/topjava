const ajaxUrl = "ajax/admin/users/";
let datatableApi;

function updateTable() {
    $.get(ajaxUrl, fillTable());
}

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": showEditCol
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": showDeleteCol
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

function setUserStatus(checkbox, id) {
    let enabledStatus = checkbox.is(':checked');
    //alert(enabledStatus + " " + id);
    $.ajax({
        url: ajaxUrl + id,
        type: "POST",
        data: 'status=' + enabledStatus
    }).done(function () {
        successNoty("Status changed to " + enabledStatus);
    }).fail(function () {
        successNoty("Request failed");
    });
}