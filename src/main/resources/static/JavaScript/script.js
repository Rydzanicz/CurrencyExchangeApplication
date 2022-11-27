var selectFieldHave = document.getElementById("selectFieldHave");
var selectText = document.getElementById("selectText");
var options = document.getElementsByClassName("options");
var list = document.getElementById("list");

selectFieldHave.onclick = function(){
    list.classList.toggle("hide");
}

for(option of options){
    option.onclick = function(){
        selectText.innerHTML = this.textContent;
        list.classList.toggle("hide");
    }
}