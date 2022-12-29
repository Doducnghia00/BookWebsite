document.getElementById("showCart").style.display="none";
//General
let cart = [];

//Home Page

function showMyCard(){
    let myCart = sessionStorage.getItem("myCart");
    let cart = JSON.parse(myCart);
    let x = "";

    let total = 0;
    for (let i = 0; i < cart.length; i++) {
        let itemTotal = cart[i][3] * cart[i][4];
        total += itemTotal;
        x += '<!-- Single item -->\n' +
            '                    <div class="row">\n' +
            '                        <div class="col-lg-2 col-md-12 mb-4 mb-lg-0">\n' +
            '                            <!-- Image -->\n' +
            '                            <div class="bg-image ripple rounded"\n' +
            '                                 data-mdb-ripple-color="light">\n' +
            '                                <img src=" '+ cart[i][1] +' "\n' +
            '                                     class="" alt="img" style="width: 3.5rem" />\n' +
            '                            </div>\n' +
            '                            <!-- Image -->\n' +
            '                        </div>\n' +
            '\n' +
            '                        <div class="col-lg-5 col-md-6 mb-4 mb-lg-0">\n' +
            '                            <!-- Data -->\n' +
            '                            <b class=""  style="font-size: 0.8rem" >'+ cart[i][2] +'</b>\n' +
            '                            <p style="font-size: 0.8rem">Price: $'+ cart[i][3] +'</p>\n' +
            '                            <button type="button" class="btn btn-secondary btn-sm me-1 mb-2"\n' +
            '                                    data-mdb-toggle="tooltip" title="Remove item" style="font-size: 0.8rem" >\n' +
            '                                <i class="" data-feather="trash-2">Remove</i>\n' +
            '                            </button>\n' +
            '                            <!-- Data -->\n' +
            '                        </div>\n' +
            '                        <div class="col-lg-4 col-md-6 mb-4 mb-lg-0 text-end" style="font-size: 0.8rem">\n' +
            '                            <p>Quantity: '+ cart[i][4] +'</p>\n' +
            '                            <p>Total: $'+ itemTotal +'</p>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <!-- Single item -->';
    }
    x+= '                    <hr class="my-4">\n' +
        '                    <!-- Total -->\n' +
        '                    <div class="row">' +
        '                           <div class="d-flex justify-content-start col">\n' +
        '                               <a href="/checkout" class="btn btn-success">Checkout</a>\n' +
        '                           </div>\n' +
        '                           <div class="d-flex justify-content-end col">\n' +
        '                               <b>Total: $'+total+'</b>\n' +
        '                           </div>\n' +
        '                    </div>'
    ;

    document.getElementById("myCart").innerHTML = x;
}
function showCart(){
    let cart = document.getElementById("showCart");
    console.log("click");
    console.log(cart.style.display);
    if(cart.style.display === "block"){
        cart.style.display="none";
    }else{
        cart.style.display="block";
        showMyCard();
    }


}
function addToCard(btAdd){
    const item = btAdd.parentElement.parentElement.parentElement.children;
    let idBook = item[0].innerText;
    let img = item[1].children[0].src;
    let title = item[2].innerText;
    let price = item[5].children[0].innerText;
    let quantity = btAdd.parentElement.children[0].value;

    let s = idBook +" "+ img +" "+ title +" "+ price +" "+ quantity;
    let book = [idBook,img, title,price,quantity];
    cart.push(book);
    console.log(cart);

    //alert(s);
    //alert(img + " Test 11");

    //save to session
    sessionStorage.setItem("myCart",JSON.stringify(cart));
    console.log(JSON.stringify(cart));

    //update
    showMyCard();

    // Get the snackbar DIV
    let x = document.getElementById("snackbar");
    x.innerText = "Added " + quantity + " '" + title + "' to cart";
    btAdd.parentElement.children[0].value = 1;

    // Add the "show" class to DIV
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function () {
        x.className = x.className.replace("show", "");
    }, 1000);

}

function RemoveItem(id){

}

//Checkout Page
function showItem(){
    let myCart = sessionStorage.getItem("myCart");
    let cart = JSON.parse(myCart);

    let y = "";

    let total = 0;
    for (let i = 0; i < cart.length; i++) {
        let itemTotal = cart[i][3] * cart[i][4];
        total += itemTotal;
        y += '<!-- Single item -->\n' +
            '                    <div class="row">\n' +
            '                        <div class="col-lg-2 col-md-12 mb-4 mb-lg-0">\n' +
            '                            <!-- Image -->\n' +
            '                            <div class="bg-image ripple rounded"\n' +
            '                                 data-mdb-ripple-color="light">\n' +
            '                                <img src=" '+ cart[i][1] +' "\n' +
            '                                     class="" alt="img" style="width: 3.5rem" />\n' +
            '                            </div>\n' +
            '                            <!-- Image -->\n' +
            '                        </div>\n' +
            '                        <div class="col-lg-5 col-md-6 mb-4 mb-lg-0">\n' +
            '                            <!-- Data -->\n' +
            '                            <b class=""  style="font-size: 0.8rem" >'+ cart[i][2] +'</b>\n' +
            '                            <p style="font-size: 0.8rem">Price: $'+ cart[i][3] +'</p>\n' +
            '                            <!-- Data -->\n' +
            '                        </div>\n' +
            '                        <div class="col-lg-4 col-md-6 mb-4 mb-lg-0 text-end" style="font-size: 0.8rem">\n' +
            '                            <p>Quantity: '+ cart[i][4] +'</p>\n' +
            '                            <p>Total: $'+ itemTotal +'</p>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <br>' +
            '                    <!-- Single item -->';
    }
    y+= '                    <hr class="my-4">\n' +
        '                    <!-- Total -->\n' +
        '                    <div class="row">' +
        '                           <div class="d-flex justify-content-start col">\n' +
        '                           </div>\n' +
        '                           <div class="d-flex justify-content-end col">\n' +
        '                               <b>Total: $'+total+'</b>\n' +
        '                           </div>\n' +
        '                    </div>'
    ;

    document.getElementById("myCart").innerHTML = y;
    document.getElementById("totalProduct").innerText = total +'';
    document.getElementById("totalAmount").innerText = total + 2 +'';
}

//Checkout page
function buy(){

    let address = document.getElementById("address").value;
    let selectProvince = document.getElementById("selectProvince").value;
    let selectDistrict = document.getElementById("selectDistrict").value;
    document.getElementById("deliveryAddress").value = address + selectDistrict + selectProvince;
    document.getElementById("totalAmount1").value = document.getElementById("totalAmount").value;

    //send item
    let xhr = new XMLHttpRequest();
    let url = "/add-item";
    xhr.open("POST",url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    // xhr.onreadystatechange = function () {
    //     if (xhr.readyState === 4 && xhr.status === 200) {
    //         result.innerHTML = this.responseText;
    //     }
    // };

    let myCart = sessionStorage.getItem("myCart");
    let cart = JSON.parse(myCart);
    let data = cart;
    xhr.send(data);


    document.getElementById("form-checkout").submit();
}




//Add & Edit Page
function enterEditMode(){
    document.getElementById("edit_button").style.display = "none";
    document.getElementById("save_button").style.display = "block";

    document.getElementById("fullName").disabled = false;
    document.getElementById("email").disabled = false;
    document.getElementById("dateOfBirth").disabled = false;


}

function exitEditMode(){
    document.getElementById("edit_button").style.display = "block";
    document.getElementById("save_button").style.display = "none";

    document.getElementById("fullName").disabled = true;
    document.getElementById("email").disabled = true;
    document.getElementById("dateOfBirth").disabled = true;

    document.getElementById("informationProfile").submit();
}

if ( window.history.replaceState ) {
    window.history.replaceState( null, null, window.location.href );
}

function showPreview(event){
    if (event.target.files.length > 0 ){
        let src = URL.createObjectURL(event.target.file[0]);
        let image = document.getElementById("image");
        image.src = src;
        // image.style.display = "block";
    }
}

function SelectImage(){
    document.getElementById("formFile").click();
}



