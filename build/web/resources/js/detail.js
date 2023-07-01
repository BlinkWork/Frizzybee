/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */




$(document).ready(function () {
  $('.button-1').click(function (event) {
    event.preventDefault();

    // Lấy id từ URL
    var urlParams = new URLSearchParams(window.location.search);

    var id = urlParams.get('id');
    var form = document.getElementById('cart-view');

    var quantity = form.elements.quantity.value;
    $.ajax({
      type: 'POST',
      url: '/FrizzyBee/cart',
      data: {id: id, quantity: quantity},
      success: function () {
      },
      error: function () {
        alert('Error sending request.');
      }
    });
  });
});
document.getElementById("back--shop").addEventListener("click", function () {
  window.location.href = "./shop";
});

document.getElementById("view--cart").addEventListener("click", function () {
  window.location.href = "./cart";
});

