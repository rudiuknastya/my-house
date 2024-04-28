
$(document).ready(function () {
    console.log($('html')[0].lang);
    getContactsPage();
});

function getContactsPage() {
    blockBy('.content-wrapper');
    $.ajax({
        type: "GET",
        url: "contacts/get",
        success: function (response) {
            console.log(response);
            showPage(response);
            unblockBy('.content-wrapper');
        },
        error: function () {
            toastr.error(errorMessage);
        }
    });
}

function showPage(response) {
    $("#title").text(response.title);
    $("#text").html(response.text);
    let lang = $('html')[0].lang;
    let mapCode = response.mapCode;
    let index = mapCode.lastIndexOf("!");
    let firstIndex = index-4;
    let lastIndex = index+5;
    let languageCode = mapCode.substring(firstIndex, lastIndex);
    let newLanguageCode = "";
    if(lang.localeCompare("en") === 0){
        newLanguageCode = "1sen!2sus";
    } else {
        newLanguageCode = "1suk!2sua";
    }
    let changedMapCode = mapCode.replaceAll(languageCode, newLanguageCode);
    $("#map").append(changedMapCode);
    $("#contacts").append(
        `<div><i class="bi bi-person-circle"></i> ${response.fullName}</div>
         <div><i class="bi bi-geo-alt-fill"></i> ${response.location}</div>
         <div><i class="bi bi-compass"></i> ${response.address}</div>
         <div><i class="bi bi-telephone-fill"></i> ${response.phoneNumber}</div>
         <div><i class="bi bi-envelope"></i> ${response.email}</div>`
    );
}