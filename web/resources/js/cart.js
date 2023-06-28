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
    if (form != null) {
      let quantity = form.elements.quantity.value;
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
    window.location.href = "./shop";
  });
}

let viewCart = document.getElementById("back--shop");
if (viewCart != null) {
  document.getElementById("view--cart").addEventListener("click", function ()
  {
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
    });
  }
}

addEventUpdate();
function addEventUpdate()
{
  let cartUpdate = document.querySelector(".cart-update").querySelector("a");

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


addEventQuantityBtn();
function addEventQuantityBtn()
{
  let upQuantity = document.querySelectorAll(".quantity-up");
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
  let downQuantity = document.querySelectorAll(".quantity-down");
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
addSubTotalEvent();
function addSubTotalEvent()
{
  let total = 0;
  let items = document.getElementById("tbody--cart").querySelectorAll("tr");
  items.forEach(function (element)
  {
    let subtotalString = element.querySelector(".pro-subtotal").textContent;
    let subtotal = subtotalString.split(",")[ 0 ] + "." + subtotalString.split(",")[ 1 ];
    total += subtotal * 1;
  });
  document.getElementById("subTotal").innerHTML = "$" + total.toFixed(2);
  document.querySelector(".total-amount").innerHTML = "$" + (total + 10).toFixed(2);
}