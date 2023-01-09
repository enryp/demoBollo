'use strict';

angular.module('app').controller('CarDetailAddController', CarDetailAddController);

CarDetailAddController.$inject = [
  '$scope',
  '$state',
  '$stateParams',
  'ModalService',
  'CarService',
  'tipoCarResolve'
];

function CarDetailAddController(
  $scope,
  $state,
  $stateParams,
  ModalService,
  CarService,
  tipoCarResolve
) {
  var self = this;

  self.car = {};

  self.creationCar = true;
  self.editEnabled = true;

  self.edit = edit;
  self.backToList = backToList;


  init();

  function init() {
    
    self.tipoCarList = tipoCarResolve;
    
    if ($state.params.creationCar) {
      self.creationCar = $state.params.creationCar == 'true';
    }
  }

  // Save
  function edit(form) {

    CarService.createCar(self.car).then(function(result) {
      console.log("@@@@@createCar "+JSON.stringify(result));
      $state.go('detail', {
        targa: result.targa
      },{
        reload: true
      });
      
    });

  }


  function backToList() {
    ModalService.closeInstance();
    $scope.$close();
  }



}
