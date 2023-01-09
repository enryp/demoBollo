(function () {
    'use strict';

    angular
        .module('app')
        .factory('CarService', CarService);

    CarService.$inject = ['$http'];
    function CarService($http) {
        var service = {};

        service.getAll = getAll;
        service.getByTarga = getByTarga;
        service.createCar = createCar;
        service.updateCar = updateCar;
        service.deleteCar = deleteCar;

        return service;

        function getAll() {
            return $http.get('/api/cars').then(handleSuccess, handleError('Error getting all cars'));
        }

        function getByTarga(targa) {
            return $http.get('/api/car/' + targa).then(handleSuccess, handleError('Error getting car by targa'));
        }



        function createCar(veicolo) {
            return $http.post('/api/users', veicolo).then(handleSuccess, handleError('Error creating car'));
        }

        function updateCar(veicolo) {
            return $http.put('/api/car/' + veicolo.targa, veicolo).then(handleSuccess, handleError('Error updating car'));
        }

        function deleteCar(targa) {
            return $http.delete('/api/car/' + targa).then(handleSuccess, handleError('Error deleting car'));
        }

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();
