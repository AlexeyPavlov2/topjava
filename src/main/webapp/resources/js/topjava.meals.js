const mealAjaxUrl = "ajax/profile/meals/";



function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: mealAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get("ajax/profile/meals/", updateTableByData);
}

$(function () {
    makeEditable({
        ajaxUrl: mealAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": mealAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "searching": true,
            "language":  dataTableLanguageData,
            "columns": [
                {
                    "data": "dateTime"/*,
                    "render": showDateTime*/
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }
            ],
            "createdRow": function (row, data, dataIndex) {
                $(row).attr("data-mealExcess", data.excess);
            },
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        }),
        updateTable: updateFilteredTable
    });

    $.datetimepicker.setLocale("ru");
    $("#startDate").datetimepicker(
        {
            timepicker: false,
            format: 'Y-m-d',
            todayButton: true,
            closeOnDateSelect: true,
            withoutCopyright: true,
            DayOfWeekStart: 1

        });

    $("#endDate").datetimepicker(
        {
            timepicker: false,
            format: 'Y-m-d',
            todayButton: true,
            closeOnDateSelect: true,
            withoutCopyright: true,
            DayOfWeekStart: 1

        });

    $("#startTime").datetimepicker(
        {
            datepicker: false,
            format: 'H:i',
            closeOnDateSelect: true,
            withoutCopyright: true,
        });

    $("#endTime").datetimepicker(
        {
            datepicker: false,
            format: 'H:i',
            closeOnDateSelect: true,
            withoutCopyright: true,
        });

    $("#dateTime").datetimepicker({
        format: 'Y-m-d H:i',
        todayButton: true,
        closeOnDateSelect: true,
        withoutCopyright: true,
        DayOfWeekStart: 1
    });





});