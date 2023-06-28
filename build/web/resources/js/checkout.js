
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
  let subPriceArr = document.getElementById("subPrice").textContent.substring(1).split(",");
  let subPrice = subPriceArr[ 0 ] + "." + subPriceArr[ 1 ];
  let totalAmount = ((subPrice * 1) + shipCost) + "";
  totalAmount = totalAmount.split(".")[ 0 ] + "," + totalAmount.split(".")[ 1 ];
  document.getElementById("totalAmount").textContent = "$" + totalAmount;
  if (document.querySelector(".grand-total") != null) {
    document.querySelector(".grand-total").querySelector("span").textContent = "$" + totalAmount;
  }
}


if (document.querySelector(".proceedOrder") != null) {
  let placeOrder = document.querySelector(".proceedOrder");
  placeOrder.addEventListener("click", function (event) {
    event.preventDefault();
    $.ajax({
      type: 'POST',
      url: '/FrizzyBee/checkout',
      data: {},
      success: function (response)
      {
        console.log(response)
      },
      error: function ()
      {
        alert('Error checkout request.');
      }
    });
    
//    showLoading();
//    showModalSuccess();
  })
}

function showModalSuccess() {
  setTimeout(function () {
    $('#my-modal').modal('show');
    setTimeout(function () {
      $('#my-modal').modal('hide');
      window.location.href = "./shop";
    }, 1000);
  }, 2000);
}

function showLoading() {
  let loadPlaced = document.querySelector(".loadPlaced");
  loadPlaced.style.display = "";
  setTimeout(function () {
    loadPlaced.style.display = "none";
  }, 2000);
}

