var handelerclick = function() {
document.title = "This is Youtube clicked";
alert(document.title);
}

var handelerplay = function() {
alert("pased");
}


var handelerchange = function() {
document.title = "This is Youtube change";
alert(document.title);
}

document.addEventListener('DOMContentLoaded', function () {
//    alert("sdfsd"+getQueryVariable("v"));
    var vid = document.getElementsByTagName('video')[0];
    vid.src = "https://127.0.0.1:8000/video.mp4";
  //  alert("sdfsd");
    vid.addEventListener('loadstart',loadstarthandaler);
});

function loadstarthandaler () {
    var vid = document.getElementsByTagName('video')[0];
    vid.src = "https://127.0.0.1:8000/video.mp4";
    vid.removeEventListener('loadstart',loadstarthandaler);

    }
function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split('&');
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split('=');
        if (decodeURIComponent(pair[0]) == variable) {
            return decodeURIComponent(pair[1]);
        }
    }
    console.log('Query variable %s not found', variable);
}
