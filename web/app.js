let tg = window.Telegram.WebApp;

tg.expand();

tg.MainButton.textColor = '#FFFFFF';
tg.MainButton.color = '#0007ff';

let item = "";

tg.MainButton.show();
tg.MainButton.setText("Oбновить данные");

var currentUrl = window.location.href;
var urlParams = new URLSearchParams(currentUrl);
//var jsonString = '{"name": "John", "age": 25, "about": "New York"}';
var data = JSON.parse(urlParams.get('data'));

document.getElementById('name').value = data.name || ''
document.getElementById('about').value = data.about || ''
document.getElementById('age').value = data.age || ''

document.getElementById('name').val

Telegram.WebApp.onEvent("mainButtonClicked", function () {
    item = JSON.stringify(submitForm());
    tg.sendData(item);
});

function submitForm() {
    // Получаем данные из формы
    var name = document.getElementById('name').value;
    var about = document.getElementById('about').value;
    var age = document.getElementById('age').value;

    var gender;
    var genderRadios = document.getElementsByName('gender');
    for (var i = 0; i < genderRadios.length; i++) {
        if (genderRadios[i].checked) {
            gender = genderRadios[i].value;
            break;
        }
    }

    var userData = {
        name: name,
        about: about,
        age: age,
        gender: gender
    };

    return userData;
}

