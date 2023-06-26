/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$(document).ready(function () {
  showMiniCart("show");
  $('.button-1').click(function (event) {
    event.preventDefault();

    // Lấy id từ URL
    let urlParams = new URLSearchParams(window.location.search);

    let id = urlParams.get('id');
    let form = document.getElementById('cart-view');
    
    let quantity = form.elements.quantity.value;
    let action = "add";
    $.ajax({
      type: 'POST',
      url: '/FrizzyBee/cart',
      data: {id: id, quantity: quantity, action: action},
      success: function () {
        showMiniCart("add");
      },
      error: function () {
        alert('Error add request.');
      }
    });
  });



});
let backShop = document.getElementById("back--shop")
if (backShop != null) {
  backShop.addEventListener("click", function () {
    window.location.href = "./shop";
  });
}

let viewCart = document.getElementById("back--shop")
if (viewCart != null) {
  document.getElementById("view--cart").addEventListener("click", function () {
    window.location.href = "./cart";
  });
}

function showMiniCart(action) {
  $.ajax({
    type: 'POST',
    url: '/FrizzyBee/miniCart',
    data: {action: action},
    success: function (response) {
      document.querySelector(".mini-cart-icon").innerHTML = response;
      addRemoveButton();
    },
    error: function () {
      alert('Error show request.');
    }
  });
}
function showCart(action) {
  $.ajax({
    type: 'POST',
    url: '/FrizzyBee/cart',
    data: {action: action},
    success: function (response) {
      console.log(response);
      document.getElementById("tbody--cart").innerHTML = response;
    },
    error: function () {
      alert('Error show request.');
    }
  });
}

function addRemoveButton() {
  let closeButton = document.querySelectorAll(".removeProduct");
  if (closeButton != null) {
    closeButton.forEach(element => {
      let productId = element.className.split(" ")[1].split("_")[1];
      let action = "remove";
      element.addEventListener("click", function (event) {
        event.preventDefault();
        $.ajax({
          type: 'POST',
          url: '/FrizzyBee/cart',
          data: {id: productId, action: action},
          success: function () {
            showMiniCart("show");
            showCart("show");
          },
          error: function () {
            alert('Error remove request.');
          }
        });
      });
    });
  }
}
