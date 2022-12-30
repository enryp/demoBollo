﻿(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
    function LoginController($location, AuthenticationService, FlashService) {
        var vm = this;

        vm.login = login;

        (function initController() {
            // reset login status
            AuthenticationService.clearCredentials();
        })();

        function login() {
            vm.dataLoading = true;
            AuthenticationService.login(vm.username, vm.password).then(function(response) {
                console.log("@@@@@LoginController@@@@@@ "+JSON.stringify(response));
                if (response.data.access_token) {
                    //AuthenticationService.setCredentials(vm.username, vm.password);
                    $location.path('/');
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        };
    }

})();