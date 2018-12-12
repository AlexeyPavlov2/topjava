const mealAjaxUrl = "ajax/profile/meals/";

//https://stackoverflow.com/questions/27707787/get-ajax-request-url-in-ajaxsetup-converters-for-logging-purposes
$.ajaxSetup({
    converters: {"text json": function(json_string)  {
            var json = JSON.parse(json_string);
            $(json).each(function () {
                    this.dateTime = this.dateTime.replace("T", " ").substr(0, 16);
                return this;
            });
            return json;
        }
    }
});

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
                    "data": "dateTime"
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
    $("#startDate, #endDate").datetimepicker(
        {
            timepicker: false,
            format: 'Y-m-d',
            todayButton: true,
            closeOnDateSelect: true,
            withoutCopyright: true,
            DayOfWeekStart: 1

        });

    $("#startTime, #endTime").datetimepicker(
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