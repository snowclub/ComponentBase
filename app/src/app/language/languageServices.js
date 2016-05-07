/**
 * Created by panit on 5/4/2016.
 */
(function () {
  'use strict';


  angular
    .module('app')
    .factory('UrlLanguageStorage', urlLanguageStorage);
  /** @ngInject */
  function urlLanguageStorage($location) {
    return {
      put: function () {
      },
      get: function () {
        return $location.search()['lang']
      }
    };
  }
})();
