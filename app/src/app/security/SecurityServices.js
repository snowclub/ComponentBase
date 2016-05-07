/**
 * Created by panit on 5/4/2016.
 */
(function () {
  'use strict'
  angular.module('app').factory('UserService',UserService)
  /**ngInject*/
  function UserService($resource){
    return $resource('/watphasom/:action',{},
      {authenticate:
      {
        method:'POST',
        params:{'action':'authenticate'},
        header: {'Content-Type' : 'application/x-www-form-urlencoded'}
      }
      })
  }
})();
