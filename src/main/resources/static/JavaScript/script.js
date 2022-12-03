var selectFieldHave = document.getElementById("selectFieldHave");
var selectTextHave = document.getElementById("selectTextHave");
var optionsHave = document.getElementsByClassName("optionsHave");
var listHave = document.getElementById("listHave");
var currencyGet;
var currencyHave;
selectFieldHave.onclick = function(){
    listHave.classList.toggle("hideHave");
}

for(option of optionsHave){
    option.onclick = function(){
        selectTextHave.innerHTML = this.textContent;
        listHave.classList.toggle("hideHave");
        currencyHave = this.textContent;
    }
}

var selectFieldGet = document.getElementById("selectFieldGet");
var selectTextGet = document.getElementById("selectTextGet");
var optionsGet = document.getElementsByClassName("optionsGet");
var listGet = document.getElementById("listGet");

selectFieldGet.onclick = function(){
    listGet.classList.toggle("hideGet");
}

for(option of optionsGet){
    option.onclick = function(){
        selectTextGet.innerHTML = this.textContent;
        listGet.classList.toggle("hideGet");
        currencyGet = this.textContent;
    }
}

function loadingAnimation() {
    if (document.getElementById) {
        document.getElementById('exchangeImg').style.visibility = 'visible';
        }
    setTimeout(() => {
        const box = document.getElementById('exchangeImg');
        box.style.visibility = 'hidden';
    }, 1000);
}

function validation() {
    if(document.getElementById("amount").value == 0){
        document.getElementById("amount").value = 1;
    }
    if(document.getElementById("commission").value == 0){
        document.getElementById("commission").value = 0.05;
    }
}

function calculate() {
        validation();
        loadingAnimation();
    var amount  = document.getElementById("amount").value;
    var commission  = document.getElementById("commission").value;
    var commission  = amount * commission;

    var result = amount - commission;

    var resultString ="Wynik: "+ result.toFixed(2);
        document.getElementById("resultId").innerHTML = resultString;
}