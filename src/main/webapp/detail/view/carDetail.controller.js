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
        'carDetailResolve',
        'carBolloResolve'
    ];

    /* @ngInject */
    function CarDetailController(
        $rootScope,
        $scope,
        $state,
        ModalService,
        carDetailResolve,
        carBolloResolve
        ) {
        
        var self = this;
        //{"targa":"en300zj","tipo":"AUTO","codiceFiscale":"PRGNRC76R25L219M","immatricolazione":"2012-09-12","cilindrata":110}
        self.car = {};
        //{"targa":"EN300ZJ","scadenza":"SETTEMBRE 2023","ultimoGiornoUtile":"31/10/2022","validita":12,"totale":267.37,"tassa":262.08,"sanzione":4.38,"interesse":0.91}
        self.bollo = {};
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
            self.bollo = carBolloResolve;
            console.log("@@@@@car Bollo "+JSON.stringify(self.bollo));
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
