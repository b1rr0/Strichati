let tg = window.Telegram.WebApp;

tg.expand();

tg.MainButton.textColor = '#FFFFFF';
tg.MainButton.color = '#2cab37';

let item = "";

let btn1 = document.getElementById("btn1");

btn1.addEventListener("click", function(){
    if (tg.MainButton.isVisible) {
        tg.MainButton.hide();
    }
    else {
        var myObject = {
            name: "John",
            age: 25,
            address: {
                city: "New York",
                country: "USA"
            }
        };
        item = JSON.stringify(myObject);
        tg.MainButton.setText("Вы выбрали товар datas");


        tg.MainButton.show();
    }
});

Telegram.WebApp.onEvent("mainButtonClicked", function(){
    console.log('71231231387128732872387')
    tg.sendData(item);
});
