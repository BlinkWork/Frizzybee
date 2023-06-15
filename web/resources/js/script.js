let navbarToggler = document.querySelector('.navbar-togglers');
let navList = document.querySelector('.nav-list');

navbarToggler.addEventListener("click", function ()
{

  if (navList.classList.contains('active') == false) {
    navList.classList.add('active');
  }
  else {
    navList.classList.remove('active');
  }
});

