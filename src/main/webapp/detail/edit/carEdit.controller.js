(function () {
    'use strict';

    angular
        .module('app')
        .controller('CarDetailEditController', CarDetailEditController);

        CarDetailEditController.$inject = [
        '$rootScope',
        '$scope',
        '$state',
        'ModalService',
        'carDetailResolve',
        'CarService',
        'tipoCarResolve'
    ];

    /* @ngInject */
    function CarDetailEditController(
        $rootScope,
        $scope,
        $state,
        ModalService,
        carDetailResolve,
        CarService,
        tipoCarResolve
        ) {
        
        var self = this;
        self.edit = edit;
        self.car = {};
        self.tipoCarList = [];
        self.carBackup = angular.copy(self.car);
        self.editEnabled = true;    
        self.creationCar = false;

        // State parameters
        //self.currentState = $rootScope.toState.name;
        //self.stateParent = $rootScope.stateParent;

        self.rollback = rollback;
      


        init();

        function init() {
            self.editEnabled = true;
            self.tipoCarList = tipoCarResolve;
            self.car = carDetailResolve;
            console.log("@@@@@car Edit "+JSON.stringify(self.car));
        }

        function rollback(car) {
            $state.go('detail', {}, {
              targa: car.targa
            });
        }

        // Save edit
        function edit(form) {

            CarService.updateCar(self.car).then(function(result) {
            console.log("@@@@@updateCar "+JSON.stringify(result));
            $state.go('detail', {
                targa: result.targa
            },{
                reload: true
              });
            
            });
        }


    }
})();
