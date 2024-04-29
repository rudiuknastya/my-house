let url = window.location.pathname;
let id = url.substring(url.lastIndexOf('/') + 1);
$(document).ready(function () {
    getTemplates();
});
$("#download-link").on("click", function (e) {
    e.preventDefault();
    blockBy(".card-body");
    let file = $('input[type=radio]:checked').attr("id");
    if(file === undefined){
        toastr.warning(chooseTemplate);
        unblockBy(".card-body")
    } else {
        $(this).attr("href", "download/" + id + "/" + file);
        window.location = $(this).attr("href");
        unblockBy(".card-body")
    }
});
function getTemplates() {
    blockCardDody();
    $.ajax({
        type: "GET",
        url: "../../templates-settings/get",
        success: function (response) {
            $("#templates").empty();
            showTemplates(response);
        },
        error: function () {
            toastr.error(errorMessage);
        }
    });
    setNumber();
}
function setNumber() {
    $.ajax({
        type: "GET",
        url: "../../get-number/"+id,
        success: function (response) {
            $("#invoice-number").attr("href", "../"+id);
            $("#invoice-number").text(invoice+""+response+"/");
        },
        error: function () {
            toastr.error(errorMessage);
        }
    });
}
function showTemplates(response) {
    for(let template of response){
        $("#templates").append(
            `<div class="form-check mt-2">
          <input name="default-radio-1" class="form-check-input" id="${template.file}" type="radio" ${isDefault(template.isDefault)} id="${template.id}">
          <label class="form-check-label" for="defaultRadio1"> ${template.name} </label>
        </div>`
        )
    }
}
function isDefault(isDefault) {
    return isDefault? "checked": "";
}
$("#send-button").on("click", function () {
    blockCardDody();
    let file = $('input[type=radio]:checked').attr("id");
    if(file === undefined){
        toastr.warning(chooseTemplate);
    } else {
        $.ajax({
            type: "POST",
            url: "../send-invoice/"+id + "/" + file,
            headers: {
                "X-CSRF-TOKEN": token
            },
            success: function () {
                toastr.success(messageSent);
            },
            error: function () {
                toastr.error(errorMessage);
            }
        });
    }

});



