/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function handleProduct(productId, event) {
  var form = document.createElement("form");
  form.setAttribute("method", "post");
  form.setAttribute("action", "product-management");
  
  var eventInput = document.createElement("input");
  eventInput.setAttribute("type", "hidden");
  eventInput.setAttribute("name", "event");
  eventInput.setAttribute("value", event);
  form.appendChild(eventInput);
  
  var productIdInput = document.createElement("input");
  productIdInput.setAttribute("type", "hidden");
  productIdInput.setAttribute("name", "product-id");
  productIdInput.setAttribute("value", productId);
  form.appendChild(productIdInput);
  
  document.body.appendChild(form);
  form.submit();
}