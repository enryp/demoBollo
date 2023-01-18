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
        vm.openAddCarDetailModal = openAddCarDetailModal;
        vm.orderByField = 'targa';
        vm.reverseSort = false;
        vm.orderBy= orderBy;
        vm.orderByDate= orderByDate;


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
                targa: car.targa,
                tipo: car.tipo
            });
        }

        function openAddCarDetailModal() {
            console.log("@@@goto state car.detail.add ");
            $state.go('detail.add',{
                creationCar: true
            });
        }

        function orderBy(columnName) {
            vm.orderByField = columnName;
        }

        function orderByDate(car) {
            return moment(car.immatricolazione, 'YYYY-MM-DD');
        }


        
    }

})();