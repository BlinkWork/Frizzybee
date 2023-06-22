const urlParams = new URLSearchParams(window.location.search);
let currentPage = urlParams.get('page');

if (currentPage == null) {
  currentPage = 1;
}


if (currentPage) {

  const pagination = document.querySelector('.page-pagination');
  const pages = pagination.querySelectorAll('.page-item a');
  for (let i = 0; i < pages.length; i++) {
    if (pages[ i ].textContent == currentPage) {

      let paginationItems = document.querySelectorAll('.page-pagination ul li');
      if (currentPage > 1) {

        let newListItem = document.createElement('li');
        newListItem.innerHTML = '<span>' + currentPage + '</span>';
        let before = parseInt(pages[ i ].textContent) - 1;
        paginationItems.forEach(function (item)
        {
          if (item.querySelector('a') && item.querySelector('a').textContent == before) {
            item.after(newListItem);
          }
        });
      } else {
        let paginationList = document.querySelector('.page-pagination ul');
        paginationList.insertAdjacentHTML('afterbegin', '<li><span>1</span></li>');

      }

      paginationItems.forEach(function (item)
      {
        if (item.querySelector('a') && item.querySelector('a').textContent == currentPage) {
          item.remove();
        }
      });

      break;
    }
  }
}

const select = document.getElementById('sort-select');

const sortOption = getCookie('sortOption');

const defaultValue = '1';
const value = (sortOption && ['1', '2', '3', '4'].includes(sortOption)) ? sortOption : defaultValue;

select.value = value;

select.addEventListener('change', function () {
  const value = this.value;
  const url = 'shop?page=' + currentPage + '&&sortOption=' + value;

  setCookie('sortOption', value, 7);

  window.location.href = url;
});

function getCookie(name) {
  const value = '; ' + document.cookie;
  const parts = value.split('; ' + name + '=');
  if (parts.length === 2) {
    return parts.pop().split(';').shift();
  }
}

function setCookie(name, value, days) {
  let expires = '';
  if (days) {
    const date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    expires = '; expires=' + date.toUTCString();
  }
  document.cookie = name + '=' + value + expires + '; path=/';
}
const pagination = document.querySelector('.page-pagination');
const pages = pagination.querySelectorAll('.page-item a');
for (let i = 0; i < pages.length; i++) {
  pages[i].addEventListener('click', function () {
    window.location.href = 'shop?page=' + pages[i].textContent + '&&sortOption=' + value;
  });
}