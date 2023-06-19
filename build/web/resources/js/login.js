document.querySelector('.signin-form').addEventListener('submit', function (event)
{
  event.preventDefault(); // Ngăn chặn form submit mặc định

  let usernameInput = document.querySelector('input[type="text"]');
  let passwordInput = document.querySelector('input[type="password"]');

  let usernameError = document.querySelector('.username-error');
  if (usernameError) {
    usernameError.parentNode.removeChild(usernameError);
  }

  let passwordError = document.querySelector('.password-error');
  if (passwordError) {
    passwordError.parentNode.removeChild(passwordError);
  }

  if (!usernameInput.value) {
    // Nếu ô input username không có giá trị, thêm một thông báo lỗi bên cạnh nó
    let usernameError = document.createElement('span');
    usernameError.className = 'error-message username-error';
    usernameError.textContent = 'Please enter your username';
    usernameError.style = 'color: #FFFFFF; width: 200px; padding: 10px; background-color:#C40000; position: absolute; left: 80%; bottom: 30px; border-radius: 20px';
    usernameInput.parentNode.insertBefore(usernameError, usernameInput.nextSibling);
  }

  if (!passwordInput.value) {
    // Nếu ô input password không có giá trị, thêm một thông báo lỗi bên cạnh nó
    let passwordError = document.createElement('span');
    passwordError.className = 'error-message password-error';
    passwordError.textContent = 'Please enter your password';
    passwordError.style = 'color: #FFFFFF; width: 200px; padding: 10px; background-color:#C40000; position: absolute; left: 80%; bottom: 30px; border-radius: 20px';
    passwordInput.parentNode.insertBefore(passwordError, passwordInput.nextSibling);
  }
  if (usernameInput.value != "" && passwordInput.value != "") {
    let errorElement = document.createElement('div');
    if (check_input.classList.contains("check_input_false")) {
      // Thêm phần tử HTML thông báo lỗi vào trước ô mật khẩu
      let submitBtn = document.querySelector('.submit');

      errorElement.classList.add('invalid--input');
      errorElement.textContent = 'Tên tài khoản hoặc mật khẩu sai';
      submitBtn.parentNode.insertBefore(errorElement, submitBtn);
    }
    if (check_input.classList.contains("check_input_true")) {
      document.querySelector(".invalid--input").remove();
    }
  }
});
// Thêm trình xử lý sự kiện focus cho các ô input
let inputFields = document.querySelectorAll('input');
for (let i = 0; i < inputFields.length; i++) {
  inputFields[ i ].addEventListener('focus', function (event)
  {
    // Lấy tham chiếu đến thông báo lỗi liên quan đến ô input đó
    let errorMessage = event.target.nextSibling;

    // Nếu có thông báo lỗi, xóa nó
    if (errorMessage && errorMessage.classList.contains('error-message')) {
      errorMessage.parentNode.removeChild(errorMessage);
    }
  });
}


