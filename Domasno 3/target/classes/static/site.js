$(document).ready( () => {
    $("#list-wineries").fadeIn(500);
    var username = getCookie("user");
    if (username !== undefined) {
        $("#login").hide();
        $("#logout").show();
        $("#username").text("Welcome " + username);
    }
})

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length === 2) return parts.pop().split(";").shift();
}