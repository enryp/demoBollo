(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', '$state', 'AuthenticationService', 'FlashService'];
    function LoginController($location, $state, AuthenticationService, FlashService) {
        var vm = this;

        vm.login = login;

        (function initController() {
            // reset login status
            AuthenticationService.logout();
        })();

        function login() {
            vm.dataLoading = true;
            AuthenticationService.login(vm.username, vm.password).then(function(response) {
                console.log("@@@@@LoginController@@@@@@ "+JSON.stringify(response));
                if (response.data.access_token) {
                    vm.dataLoading = false;
                    //$state.go("home");
                    $location.path('/');
                } else {
                    FlashService.Error(response.data.error);
                    vm.error = 'Username or password is incorrect';
                    vm.dataLoading = false;
                }
            });
        };
    }

})();
