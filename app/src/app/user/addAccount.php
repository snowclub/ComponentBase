<?php
include_once 'session.php';
// only admin has access
if ('admin' != $user_type) {
  $_SESSION['error_message'] = "You do not have sufficient permissions to access this page";
  $_SESSION['redirect'] = "<meta http-equiv='refresh' content='3;url=home.php'>";
  header('Location: error_message.php');
  exit;
}
if (isset($_POST['inputName']) && isset($_POST['inputEmail'])) {
  $name = $_POST['inputName'];
  $address = $_POST['inputAddress'];
  $email = $_POST['inputEmail'];
  $password = $_POST['inputPassword'];
  $phone = $_POST['inputPhoneNumber'];
  $latitude = $_POST['latitude'];
  $longitude = $_POST['longitude'];
  $comboDistrict = $_POST['comboDistrict'];
  $openTime = $_POST['inputOpenAndCloseTime'];
  $category = 'test';
  $description = $_POST['inputDescription'];

  $accountId = AccountController::addNewAccount(new AccountInfo("", $email, $password, "user", null, 1));
  ShopInformationController::addShopInformation(new ShopInformation($accountId, $name, $address, $phone, $comboDistrict, $latitude, $longitude, $openTime, $description, $category));
  //uplaod images
  $old = umask(0);
  $folderPath = "user_upload/".$accountId."/shop_images/";

  mkdir("../".$folderPath, 0777,true);
  $target_dir = $folderPath;
  umask($old);

  if(isset($_FILES)){
    $target_file = array();
    $realPath = array();
    $i=0;
    foreach ($_FILES["files"]["name"] as $aImage) {
      $realPath[] = $target_dir . basename($aImage);
      $target_file[] = "../".$realPath[$i];
      $i++;
    }

    $i = 0;
    $uploadPath = array();
    foreach ($_FILES["files"]["tmp_name"] as $imageTmp) {
      if (move_uploaded_file($imageTmp, $target_file[$i])) {
        $uploadPath[] = $realPath[$i];
      } else{
        // cant upload
      }
      $i++;
    }

    //add image

    foreach($uploadPath as $image){
      $shopImageController = new ShopImageController();
      $shopImageController->addImage(new ShopImage("",$accountId,$image,""));
    }
  }
//            add log
  $_SESSION['manageAccountStatus']= "true";
  $_SESSION['manageAccountAction'] = "add";
  header('Location: '.Config::PATH.'/accounts');
  exit;
}

?>
<!DOCTYPE html>
<html>
<head>
  <title>WAP / New account</title>
  <?php

  $assetPath = Config::PATH.'';

  include_once '../assets.php';
  ?>
  <style>
    #googleMap {
      width: 100%;
      height: 300px;
    }
  </style>
</head>
<body>

<div id="top" style="margin: 80px;" class="container-fluid">

  <!-- Main component for a primary marketing message or call to action -->
  <div class="row">
    <form name="inputeditaccount" id="addAccountForm" action="" method="post" enctype="multipart/form-data">
      <div class="col-md-12">
        <label class="titleFontSize">New account</label>
      </div>
      <div class="col-md-6">
        <div class="form-group shopName">
          <label class="control-label" for="inputError2">Shop name</label>
          <input id="shopName" type="text" class="form-control" name="inputName" placeholder="WAP shop" required>
        </div>
      </div>
      <div class="col-md-6">
        <div class="form-group email">
          <label class="control-label" for="inputError2">Email address</label>
          <input type="email" class="form-control" name="inputEmail" placeholder="wapbar@wap.com"
                 required onkeyup="checkExistingEmail(this.value)">
        </div>
      </div>
      <div class="col-md-6">
        <div class="form-group password">
          <label class="control-label" for="inputError2">Create a password</label>
          <input id="password" type="password" class="form-control" name="inputPassword" placeholder="Your Password"
                 required>
        </div>
      </div>
      <div class="col-md-6">
        <div class="form-group confirmPassword">
          <label class="control-label" for="inputError2">Confirm Password</label>
          <input id="confirmPassword" type="password" class="form-control" name="inputPasswordAgain"
                 placeholder="Confirm Password" required>
        </div>
      </div>
      <div class="col-md-6">
        <div class="form-group phoneNumber">
          <label class="control-label" for="inputPhoneNumber">Phone number</label>
          <input id="phoneNumber" type="text" class="form-control bfh-phone" name="inputPhoneNumber"
                 placeholder="053222222" required>
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-6">
          <div class="form-group openAndCloseTime">
            <label class="control-label" for="inputOpenAndCloseTime">Open and Close</label>
            <input id="openAndCloseTime" type="text" class="form-control" name="inputOpenAndCloseTime"
                   placeholder="Mon - Sat from 09.00-23.00" required>
          </div>
        </div>
      </div>
      <div class="col-md-12">
        <div class="form-group address">
          <label class="control-label" for="inputAddress">Address</label>
          <input id="address" type="text" class="form-control" name="inputAddress"
                 placeholder="40 Nimmarnhemin Rd., T.Suthep, A.Muang, Chiang Mai, 50200" required>
        </div>
      </div>
      <div class="col-md-12">
        <div class="form-group">
          <label for="map">Pin your shop location on the map</label>
          <div id="googleMap"></div>
        </div>
      </div>

      <div class="col-md-6">
        <div class="form-group inputLatitude">
          <label class="control-label" for="inputLatitude">Latitude</label>
          <input type="text" class="form-control" id="inputLatitude" name="latitude" readonly>
        </div>
      </div>
      <div class="col-md-6">
        <div class="form-group inputLatitude">
          <label class="control-label" for="inputLongitude">Longitude</label>
          <input type="text" class="form-control" id="inputLongitude" name="longitude" readonly>
        </div>
      </div>
      <div class="col-md-12">
        <div class="form-group description">
          <label class="control-label" >A description for this shop</label>
          <textarea id="description" class="form-control" name="inputDescription" rows="3" required></textarea>
        </div>
      </div>
      <div class="col-md-6">
        <div class="form-group">
          <label for="inputSubDistrict">Area</label>
          <select name="comboDistrict" class="form-control" required>
            <option></option>
            <option value="1"> Suthep</option>
            ;
            <option value="2"> Nai Maung</option>
            ;
            <option value="3"> Si Phum</option>
            ;
            <option value="4"> Phra Sing</option>
            ;
            <option value="5"> Chang Phueak</option>
            ;
            <option value="6"> Mae Hia</option>
            ;
            <option value="7"> Pa Daet</option>
            ;
            <option value="8"> Pa Tan</option>
            ;
            <option value="9"> Chang Moi</option>
            ;
            <option value="10"> Chang Khlan</option>
            ;
          </select>
        </div>
      </div>
      <div class="col-md-6">
        <div class="form-group">
          <label for="files">Select image(s)</label>
          <input class="form-control" id="files" name="files[]" type="file" multiple/>
        </div>
      </div>
      <div class="col-md-12" id="result"></div>
      <div class="col-md-12">
        <a class="btn btn-default" href="account_list.php" role="button">Back</a>
        <input id="submitAddBtn" type="submit" value="Submit" name="add" class="btn btn-default"/>
      </div>
    </form>
    <!-- /row -->
  </div>

</div> <!-- /container -->

<script src="<?php echo $assetPath; ?>/jquery.js"></script>
<script src="<?php echo $assetPath; ?>/bootstrap/js/bootstrap.min.js"></script>
<script src="Whatapro/accountvalidation.js"></script>
<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false"></script>
<script type="text/javascript">

  window.onload = function(){
    //Check File API support
    if(window.File && window.FileList && window.FileReader)
    {
      var filesInput = document.getElementById("files");
      filesInput.addEventListener("change", function(event){
        var files = event.target.files; //FileList object
        var output = document.getElementById("result");
        for(var i = 0; i< files.length; i++)
        {
          var file = files[i];
          //Only pics
          if(!file.type.match('image'))
            continue;
          var picReader = new FileReader();
          picReader.addEventListener("load",function(event){
            var picFile = event.target;
            var div = document.createElement("div");
            div.innerHTML = "<img class='img-rounded img-thumbnail col-sm-3' src='" + picFile.result + "'" +"title='" + picFile.name + "'/>";
            output.insertBefore(div,null);
          });
          //Read the image
          picReader.readAsDataURL(file);
        }
      });
    }
    else
    {
//            console.log(“Your browser does not support File API”);
    }
  }


  var myCenter = new google.maps.LatLng(18.789570, 98.974244);
  var geocoder;
  var infowindow = new google.maps.InfoWindow();
  var map;
  function initialize() {
    geocoder = new google.maps.Geocoder();
    var mapProp = {
      center: myCenter,
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

    google.maps.event.addListener(map, 'click', function (event) {
      placeMarker(event.latLng);
    });
  }
  var marker;
  function placeMarker(location) {
    if (marker) {
      marker.setPosition(location);
    } else {
      marker = new google.maps.Marker({
        position: location,
        map: map
      });
    }

    var latlng = new google.maps.LatLng(location.lat(), location.lng());
    geocoder.geocode({'latLng': latlng}, function (results) {
      infowindow.setContent(results[1].formatted_address);
      infowindow.open(map, marker);
//            document.inputeditaccount.temp.value = results[1].formatted_address;
    });

    document.inputeditaccount.latitude.value = location.lat();
    document.inputeditaccount.longitude.value = location.lng();
    isValidateLatitude();
  }
  google.maps.event.addDomListener(window, 'load', initialize);
</script>

</body>
</html>
