<!doctype html>
<html lang="en">

  <head>
    <title>Login 10</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

    <style>
      @font-face {
        font-family: "FontAwesome";
        src: url("../resources/fonts/fontawesome-webfont.eot?v=4.7.0");
        src: url("../resources/fonts/fontawesome-webfont.eot?#iefix&v=4.7.0") format("embedded-opentype"),
          url("../resources/fonts/fontawesome-webfont.woff2?v=4.7.0") format("woff2"),
          url("../resources/fonts/fontawesome-webfont.woff?v=4.7.0") format("woff"),
          url("../resources/fonts/fontawesome-webfont.ttf?v=4.7.0") format("truetype"),
          url("../resources/fonts/fontawesome-webfont.svg?v=4.7.0#fontawesomeregular") format("svg");
        font-weight: normal;
        font-style: normal;
      }
    </style>

    <link rel="stylesheet" href="../resources/css/login.css">

  </head>

  <body class="img js-fullheight" style="background-image: url(../resources/img/bg.jpg);">
    <section class="ftco-section">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-md-6 text-center mb-5">
            <h2 class="heading-section">Login #10</h2>
          </div>
        </div>
        <div class="row justify-content-center">
          <div class="col-md-6 col-lg-4">
            <div class="login-wrap p-0">
              <h3 class="mb-4 text-center">Have an account?</h3>
              <form action="#" class="signin-form">
                <div class="form-group">
                  <input type="text" class="form-control " placeholder="Username">
                </div>
                <div class="form-group">
                  <input id="password-field" type="password" class="form-control" placeholder="Password">
                  <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                </div>
                <div class="check_input_false" id="check_input"></div>
                <div class="form-group">
                  <button type="submit" class="form-control btn btn-primary submit px-3">Sign In</button>
                </div>
                <div class="form-group d-md-flex">
                  <div class="w-50">
                    <label class="checkbox-wrap checkbox-primary">Remember Me
                      <input type="checkbox" checked>
                      <span class="checkmark"></span>
                    </label>
                  </div>
                  <div class="w-50 text-md-right">
                    <a href="#" style="color: #fff">Forgot Password</a>
                  </div>
                </div>
              </form>
              <p class="w-100 text-center">&mdash; If you don't have account &mdash;</p>
              <div class="social d-flex text-center">
                <a href="#" class="px-2 py-2 mr-md-1 rounded"><span class="ion-logo-facebook mr-2"></span> Sign up</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <script src="../resources/js/login.js"></script>
  </body>

</html>
