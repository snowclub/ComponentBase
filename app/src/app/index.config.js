(function() {
  'use strict';

  angular
    .module('app')
    .config(configTranslation)
    .config(configFailRequestRedirect);

  /** @ngInject */
  function configFailRequestRedirect($locationProvider,$httpProvider) {
    /*Register error
     * nauthentication requests*/
    $httpProvider.interceptors.push(function ($q,$rootScope,$location) {
      return{
        'responseError': function (rejection) {
          var status = rejection.status;
          var config = rejection.config;
          var method = config.method;
          var url = config.url;

          if (status==404){
            $location.path("/users");
          }else {
            $rootScope.error = method + "on"+url+"failed with status"+ status;
          }
          return $q.reject(rejection);
        }

      }
    });
    var authAppConfig={
      useAuthTokenHeader:true
    };
    $httpProvider.interceptors.push(function ($q, $rootScope) {
      return {
        'request': function (config) {
          if (angular.isDefined($rootScope.authToken)) {
            var authToken = $rootScope.authToken;
            if (authAppConfig.useAuthTokenHeader) {
              config.headers['X-Auth-Token'] = authToken;
            } else {
              config.url = config.url + "?token=" + authToken;
            }
          }
          return config || $q.when(config);
        }
      }
    })
  }
  /** @ngInject */
  function configTranslation($translateProvider){
    $translateProvider.useUrlLoader('http://localhost:8080/messageBundle');
    $translateProvider.useStorage('UrlLanguageStorage');
    $translateProvider.preferredLanguage('en');
    $translateProvider.fallbackLanguage('en');
  }

})();
