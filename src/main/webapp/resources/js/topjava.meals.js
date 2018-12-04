const ajaxUrl = "ajax/profile/meals/";
let datatableApi;

function clearMealFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, fillTable);
}

function updateTable() {
    $.ajax({
        type: "GET",
        url: ajaxUrl + "filter",
        data: $("#filter").serialize()
    })
        .done(fillTable);
    console.log("update");
}

$(function () {
    datatableApi = $("#datatable").DataTable({
        "searching": false,
        "paging": false,
        "info": false,
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "language": {
            "url": ""
        },
        "initComplete": makeEditable,
        "columns": [
            {
                "data": "dateTime",
                "render": showDateTime
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "render": showEditCol,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": showDeleteCol,
                "defaultContent": "",
                "orderable": false
            }
        ],
        "createdRow": function (row, data, dataIndex) {
            $(row).attr("data-mealExceed", data.exceed);
        }

    });



    //$("#startDate").datepicker({
    $("#startDate").datepicker({
        beforeShowDay: function (date) {
            $('#ui-datepicker-div').css('clip', 'auto');
            return [true, '', ''];
        }
    });

});