(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['CarService', '$localStorage', '$state', '$location'];
    function HomeController(CarService, $localStorage, $state, $location) {
        var vm = this;

        vm.user = null;
        vm.allCars = [];
        vm.deleteCar = deleteCar;
        vm.openCarDetailModal = openCarDetailModal;

        initController();

        function initController() {
            loadCurrentUser();
            loadAllCars();
        }

        function loadCurrentUser() {
            if($localStorage.currentUser) {
                    vm.user = $localStorage.currentUser.username;
             };
        }

        function loadAllCars() {
            CarService.getAll()
                .then(function (cars) {
                    vm.allCars = cars;
                });
        }

        function deleteCar(targa) {
            CarService.deleteCar(targa)
                .then(function () {
                    loadAllCars();
                });
        }

        function openCarDetailModal(car) {
            console.log("@@@goto state car.detail "+JSON.stringify(car));
            $state.go('detail', {
                targa: car.targa
            });
        }

        
    }

})();