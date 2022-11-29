var selectFieldHave = document.getElementById("selectFieldHave");
var selectTextHave = document.getElementById("selectTextHave");
var optionsHave = document.getElementsByClassName("optionsHave");
var listHave = document.getElementById("listHave");

selectFieldHave.onclick = function(){
    listHave.classList.toggle("hideHave");
}

for(option of optionsHave){
    option.onclick = function(){
        selectTextHave.innerHTML = this.textContent;
        listHave.classList.toggle("hideHave");
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

function calculate() {
    loadingAnimation();


}