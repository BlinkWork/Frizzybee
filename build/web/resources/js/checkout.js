
let shipCost = 0;
let expressShipping = document.getElementById("expressShipping");

let normalShipping = document.getElementById("normalShipping");

expressShipping.addEventListener("click", function ()
{
  shipCost = 20;
  calAmount(shipCost);
});
normalShipping.addEventListener("click", function ()
{
  shipCost = 10;
  calAmount(shipCost);
});
calAmount(20);
function calAmount(shipCost)
{
  let subPrice = document.getElementById("subPrice").textContent.substring(1);
  if (document.getElementById("subPrice").textContent.includes(",")) {
    let subPriceArr = document.getElementById("subPrice").textContent.substring(1).split(",");
    subPrice = subPriceArr[ 0 ] + "." + subPriceArr[ 1 ];
  }
  let totalAmount = ((subPrice * 1) + shipCost) + "";
  if (document.getElementById("subPrice").textContent.includes(",")) {
    totalAmount = totalAmount.split(".")[ 0 ] + "," + totalAmount.split(".")[ 1 ];
  }
  document.getElementById("totalAmount").textContent = "$" + totalAmount;
  if (document.querySelector(".grand-total") != null) {
    document.querySelector(".grand-total").querySelector("span").textContent = "$" + totalAmount;
  }
}


if (document.querySelector(".proceedOrder") != null) {
  let placeOrder = document.querySelector(".proceedOrder");
  placeOrder.addEventListener("click", function (event)
  {
    event.preventDefault();

    let f_name = document.getElementById("f_name").value;
    let l_name = document.getElementById("l_name").value;
    let l_email = document.getElementById("l_email").value;
    let street_address = document.getElementById("street_address").value;
    let city = document.getElementById("city").value;
    let phone = document.getElementById("phone").value;
    let order_note = document.getElementById("order_note").value;
    let shippingType = "";

    if (expressShipping.checked == true) {
      shippingType = "express";
    } else {
      shippingType = "normal";
    }
    if (f_name.trim() != "" && l_name.trim() != "" && l_email.trim() != "" && street_address.trim() != "" && city.trim() != "" && phone.trim() != "" && order_note.trim() != "") {
      showLoading();
      let grandTotalCost = document.querySelector(".grand-total").querySelector("span").textContent.substring(1).replace(",", ".");
      $.ajax({
        type: 'POST',
        url: '/FrizzyBee/checkout',
        data: { addresss: street_address, totalPrice: grandTotalCost, paymentMethod: shippingType },
        success: function ()
        {
          showModalSuccess();
        },
        error: function ()
        {
          showModalErrorFail();
        }
      });
    } else {
      showModalBlank();

    }

  });
}

function showModalSuccess()
{
  setTimeout(function ()
  {
    $('#my-modal').modal('show');
    setTimeout(function ()
    {
      $('#my-modal').modal('hide');
      window.location.href = "./shop";
    }, 1000);
  }, 2000);
}

function showModalErrorFail()
{
  setTimeout(function ()
  {
    $('#failed-order').modal('show');
    setTimeout(function ()
    {
      $('#failed-order').modal('hide');
      window.location.href = "./shop";
    }, 1000);
  }, 2000);
}

function showModalBlank()
{
  setTimeout(function ()
  {
    $('#blank-info').modal('show');
    setTimeout(function ()
    {
      $('#blank-info').modal('hide');
    }, 1000);
  }, 0);
}

function showLoading()
{
  let loadPlaced = document.querySelector(".loadPlaced");
  loadPlaced.style.display = "";
  setTimeout(function ()
  {
    loadPlaced.style.display = "none";
  }, 2000);
}

