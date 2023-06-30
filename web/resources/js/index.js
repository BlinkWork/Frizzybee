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
    data: { action: action },
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