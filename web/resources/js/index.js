/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$(document).ready(function ()
{
  let action = "show";
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
});

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


let dropdown = document.querySelector('.dropdown');
let dropdownMenu = document.querySelector('.dropdown-menu');

dropdown.addEventListener('click', function (event) {
  event.stopPropagation();
  dropdownMenu.classList.toggle('show');
});

document.addEventListener('click', function (event) {
  if (!dropdown.contains(event.target)) {
    dropdownMenu.classList.remove('show');
  }
});