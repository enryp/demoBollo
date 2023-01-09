(function () {
    'use strict';

    angular
        .module('app')
        .controller('CarDetailController', CarDetailController);

    CarDetailController.$inject = [
        '$rootScope',
        '$scope',
        '$state',
        'ModalService',
        'carDetailResolve'
       // 'DefaultParameters',
        //'CodificaPrestazioneService'
    ];

    /* @ngInject */
    function CarDetailController(
        $rootScope,
        $scope,
        $state,
        ModalService,
        carDetailResolve
        // DefaultParameters,
        //CodificaPrestazioneService
        ) {
        
        var self = this;

        self.car = {};
        self.editEnabled = false;    
        

        // State parameters
        //self.currentState = $rootScope.toState.name;
        //self.stateParent = $rootScope.stateParent;

        self.backToList = backToList;
        self.enableEdit = enableEdit;
        


        init();

        function init() {
            self.editEnabled = false;
            self.car = carDetailResolve;
            console.log("@@@@@car Detail "+JSON.stringify(self.car));
        }

        // Annulla e chiudi modal
        function backToList() {
            ModalService.closeInstance();
            $scope.$close();
        }

        // Cambio stato --Edit
        function enableEdit(car) {
            $state.go('detail.edit', {
                targa: car.targa,
                creationCar: false
            });
        }


    }
})();
