/**
 * Created by panit on 5/4/2016.
 */
(function() {
  'use strict'
  angular
    .module('app')
    .factory('userService',userService)
    .factory('userSearchService',userSearchService);



  /** @ngInject */
  function userService($resource){
    return $resource('/user/:id', { id: '@_id' }, {
      update: {
        method: 'PUT' // this method issues a PUT request
      }});

  }
  

  /** @ngInject */
  function userSearchService($resource){
    return $resource('/user/search/?name=:name',
      {query:{method:'GET',params:{name:''},isArray:true}
      });
  }
})();
