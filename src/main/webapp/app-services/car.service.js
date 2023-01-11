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
        service.getBollo = getBollo;

        return service;

        function getAll() {
            return $http.get('/api/cars').then(handleSuccess, handleError('Error getting all cars'));
        }

        function getByTarga(targa) {
            return $http.get('/api/car/' + targa).then(handleSuccess, handleError('Error getting car by targa'));
        }

        function getBollo(targa, tipoVeicolo) {
            return $http.get('/api/car/'+targa+'/'+tipoVeicolo+'/bollo').then(handleSuccess, handleError('Error getting bollo by targa and tipo veicolo'));
        }

        function createCar(veicolo) {
            return $http.post('/api/car', veicolo).then(handleSuccess, handleError('Error creating car'));
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
