(function() {
  'use strict';

  angular.module('app').factory('ModalService', ModalService);

  ModalService.$inject = ['$uibModal'];

  /* @ngInject */
  function ModalService($uibModal) {
    var factory = {};

    var singleton;

    factory.newInstance = newInstance;
    factory.closeInstance = closeInstance;
    factory.isSingleton = isSingleton;

    function newInstance(url, opts) {
      if (singleton) {
        return singleton;
      }
      var _opts = {};
      _opts.templateUrl = url;
      _opts.backdrop = false;
      _opts.keyboard = false;
      _opts.windowClass = 'zIndex1';

      if (opts && opts.ctrl) {
        _opts.controller = opts.ctrl;
        if (opts.ctrlAlias) {
          _opts.controller += ' as ' + opts.ctrlAlias;
        }
      }

      _opts.resolve = {};
      if (opts && opts.resolve) {
        Object.keys(opts.resolve).forEach(function(key) {
          _opts.resolve[key] = function() {
            return opts.resolve[key];
          };
        });
      }

      singleton = $uibModal.open(_opts);
      return singleton;
    }

    function closeInstance() {
      singleton = null;
    }

    function isSingleton() {
      return singleton;
    }

    return factory;

  }
})();
