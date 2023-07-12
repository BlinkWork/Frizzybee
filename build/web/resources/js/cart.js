/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$(document).ready(function ()
{
  showMiniCart("show");
  $('.addToCartBtn').click(function (event)
  {
    event.preventDefault();

    // Lấy id từ URL
    let urlParams = new URLSearchParams(window.location.search);

    let id = urlParams.get('id');
    let form = document.getElementById('cart-view');
    let quantity = 1;
    if (form != null) {
      quantity = form.elements.quantity.value;
    } else {
      console.log(1);
    }
    let action = "add";
    $.ajax({
      type: 'POST',
      url: '/FrizzyBee/cart',
      data: {id: id, quantity: quantity, action: action},
      success: function ()
      {
        showMiniCart("add");
      },
      error: function ()
      {
        alert('Error add request.');
      }
    });
  });



});
let backShop = document.getElementById("back--shop");
if (backShop != null) {
  backShop.addEventListener("click", function ()
  {
    $('#modal--warning--login').modal('hide');
    window.location.href = "./shop";
  });
}
let loginFunction = document.getElementById("login--function");
if (loginFunction != null) {
  loginFunction.addEventListener("click", function ()
  {
    $('#modal--warning--login').modal('hide');
    window.location.href = "./login";
  });
}

let viewCart = document.getElementById("view--cart");
if (viewCart != null) {
  document.getElementById("view--cart").addEventListener("click", function ()
  {
    $('#modal--warning--login').modal('hide');
    window.location.href = "./cart";
  });
}

function showMiniCart(action)
{
  $.ajax({
    type: 'POST',
    url: '/FrizzyBee/miniCart',
    data: {action: action},
    success: function (response)
    {
      document.querySelector(".mini-cart-icon").innerHTML = response;
      addRemoveButton();
    },
    error: function ()
    {
      alert('Error show request.');
    }
  });
}
function showCart(action)
{
  $.ajax({
    type: 'POST',
    url: '/FrizzyBee/cart',
    data: {action: action},
    success: function (response)
    {
      document.getElementById("tbody--cart").innerHTML = response;
      addEventQuantityBtn();
      addSubTotalEvent();

    },
    error: function ()
    {
      alert('Error show request.');
    }
  });
}

function addRemoveButton()
{
  let closeButton = document.querySelectorAll(".removeProduct");
  if (closeButton != null) {
    closeButton.forEach(element =>
    {
      let productId = element.className.split(" ")[ 1 ].split("_")[ 1 ];
      let action = "remove";
      element.addEventListener("click", function (event)
      {
        event.preventDefault();
        $.ajax({
          type: 'POST',
          url: '/FrizzyBee/cart',
          data: {id: productId, action: action},
          success: function (response)
          {
            showMiniCart("show");
            showCart("show");
          },
          error: function ()
          {
            alert('Error remove request.');
          }
        });
      });
    });
  }
}

addEventUpdate();
function addEventUpdate()
{
  let cartUpdateTemp = document.querySelector(".cart-update")
  if (cartUpdateTemp != null) {
    cartUpdate = cartUpdateTemp.querySelector("a");
    if (cartUpdate != null) {
      cartUpdate.addEventListener("click", function (event)
      {
        event.preventDefault();
        let updateItem = "update";
        let cartItems = document.getElementById("tbody--cart").querySelectorAll("tr");

        let dataTransfer = "";
        cartItems.forEach(function (element)
        {

          let productId = element.id.split("_")[ 1 ];

          let productQuantity = element.querySelector(".pro-quantity--btn").value;
          dataTransfer += productId + "_" + productQuantity + "@";
        });
        $.ajax({
          type: 'POST',
          url: '/FrizzyBee/cart',
          data: {dataTransfer: dataTransfer, action: updateItem},
          success: function ()
          {
            showMiniCart("show");
            showCart("show");


          },
          error: function ()
          {
            alert('Error remove request.');
          }
        });
      });
    }
  }

}


addEventQuantityBtn();
function addEventQuantityBtn()
{
  let cartQuantiy = document.querySelector(".cart-list-tbody");
  if (cartQuantiy != null) {

    let upQuantity = cartQuantiy.querySelectorAll(" .quantity-up");

    upQuantity.forEach(function (element)
    {

      element.addEventListener("click", function ()
      {
        let inputNumber = this.closest(".quantity").querySelector("input[type='number']");
        if (inputNumber.max - inputNumber.value > 0) {
          inputNumber.value++;
        }
      });
    });
    let downQuantity = cartQuantiy.querySelectorAll(".cart--quantity .quantity-down");
    downQuantity.forEach(function (element)
    {
      element.addEventListener("click", function ()
      {
        let inputNumber = this.closest(".quantity").querySelector("input[type='number']");
        if (inputNumber.value > inputNumber.min) {
          inputNumber.value--;
        }
      });
    });
  }

}
addSubTotalEvent();
function addSubTotalEvent()
{
  let total = 0;
  let term = document.getElementById("tbody--cart");
  if (term != null) {
    let items = term.querySelectorAll("tr");
    items.forEach(function (element)
    {
      let subtotalString = element.querySelector(".pro-subtotal").textContent;
      let subtotal;
      if (subtotalString * 1 + "" == "NaN") {
        subtotal = subtotalString.split(",")[ 0 ] + "." + subtotalString.split(",")[ 1 ];
      } else {
        subtotal = subtotalString;
      }
             
      total += subtotal * 1;
    });
    document.getElementById("subTotal").innerHTML = "$" + total.toFixed(2);
    document.querySelector(".total-amount").innerHTML = "$" + (total + 10).toFixed(2);
  }


}