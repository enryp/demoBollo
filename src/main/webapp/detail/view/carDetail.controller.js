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

        self.car = carDetailResolve;
        self.editEnabled = false;    
        

        // State parameters
        //self.currentState = $rootScope.toState.name;
        //self.stateParent = $rootScope.stateParent;

        self.backToList = backToList;
        self.enableEdit = enableEdit;
        


        init();

        function init() {
            self.editEnabled = false;
            console.log("@@@@@editEnabled "+JSON.stringify(self.editEnabled));
            console.log("@@@@@ "+JSON.stringify(self.car));
        }

        // Annulla e chiudi modal
        function backToList() {
            ModalService.closeInstance();
            $scope.$close();
        }

        // Cambio stato --Edit
        function enableEdit() {
            $state.go(self.currentState + '.edit', {
                creationCodificaPrestazione: false
            });
        }


    }
})();
