/**
 * Created by panit on 5/4/2016.
 */
(function () {

  angular.module('app').controller('LoginController',LoginController);
  function serializeData(data) {
    //if this not an object
    if(!angular.isObject(data)){
      return((data == null)?"": data.toString());
    }
    var buffer = [];
    //Serializ each key in the obj
    for (var name in data){
      if(!data.hasOwnProperty(name)){
        continue;
      }
      var value =  data[name];
      buffer.push(
        encodeURIComponent(name)+"="+encodeURIComponent((value == null)?"": value)
      );
    }
    //
    var source = buffer.join("&").replace(/%20/g,"+");
    return(source);
  }
  /**ngInject*/
  function LoginController($rootScope,$location,$cookies,UserService)
  {
    var vm = this;
    vm.rememberMe = false;
    vm.login = function () {

      UserService.authenticate(serializeData({username:vm.username,password:vm.password}),
        function (authenticationResult) {
          var authToken = authenticationResult.token;
          $rootScope.authToken = authToken;
          if (vm.rememberMe) {
            $cookies.put('authToken', authToken);
          }
          UserService.get(function (user) {
            $rootScope.user = user;
            $location.path("/")
          })
          //delete $rootScope.error;
        },
        function(error){
          if (error.status == "401"){
            $rootScope.error =" username or passoword is not correct";
          }
        })
    }
  }
})();
