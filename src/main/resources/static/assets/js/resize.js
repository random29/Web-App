$('textarea').each(function () {
    this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
}).on('input', function () {
    this.style.height = 'auto';
    this.style.height = (this.scrollHeight) + 'px';
});


var a = document.forms["Form"]["a"].value;
var b = document.forms["Form"]["b"].value;
var c = document.forms["Form"]["n"].value;
var d = document.forms["Form"]["result"].value;
if (a == "0") {
    document.forms["Form"]["a"].value == Null;
}
if (b == "0") {
    document.forms["Form"]["b"].value == Null;
}
if (n == "0") {
    document.forms["Form"]["n"].value == Null;
}
if (result == "0") {
    document.forms["Form"]["result"].value == Null;
}
