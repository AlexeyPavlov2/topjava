const ajaxUrl = "ajax/profile/meals/";
let datatableApi;

function clearMealFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, fillTable);
}


function fillTable(data) {
    datatableApi.clear().rows.add(data).draw();
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
        "paging": false,
        "searching": false,
        "info": false,
        "ajax": {
            "url": ajaxUrl + "filter",
            "data": $("#filter").serialize(),
            "dataSrc": ""
        },
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
                "orderable": false,
            },
        ],
        "columnDefs": [
            {"className": "text-center", "targets": [0, 1, 2, 3, 4]}
        ],

        "rowCallback": function (row, data) {
            //console.log(row);
        },
        "createdRow": function (row, data, dataIndex) {
            $(row).attr("data-mealExcess", data.excess);
        }

    });
    makeEditable();


    $.datetimepicker.setLocale("ru");
    $("#startDate").datetimepicker(
        {
            timepicker: false,
            datepicker: true,
            format: 'Y-m-d H:i',
            formatTime: 'H:i',
            formatDate: 'Y-m-d',
            startDate:new Date(),
            todayButton: true,

            /*console - ???
﻿jquery.datetimepicker.js:1760 Uncaught TypeError: Cannot read property 'formatDate' of null*/
            closeOnDateSelect: true,
            todayButton: true
        }
    );

    //$("#startDate").datetimepicker('reset');
    $('#input').datetimepicker('show');


});