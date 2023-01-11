(function () {
    'use strict';

    angular
        //.module('app', ['ngRoute', 'ngCookies'])
        .module('app', ['ui.router', 'ngMessages', 'ngStorage', 'ui.bootstrap'])
        .config(config)
        .run(run);

        config.$inject = ['$stateProvider', '$urlRouterProvider', '$provide', '$httpProvider'];
        function config($stateProvider, $urlRouterProvider, $provide, $httpProvider) {
        // default route
        $urlRouterProvider.otherwise("/");

        // app routes
        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'home/home.view.html',
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .state('login', {
                url: '/login',
                templateUrl: 'login/login.view.html',
                controller: 'LoginController',
                controllerAs: 'vm'
            })
            .state('detail', {
                url: '/detail?targa&tipo',
                onEnter: ['ModalService', '$state', function (ModalService, $state) {
                    ModalService.newInstance("modalContainer.html")
                        .result.finally(function () {
                        $state.go('home', {}, {
                            reload: true
                        });

                    });
                }],
                views: {
                    'modal@': {
                        templateUrl: 'detail/carDetail.html',
                        controller: 'CarDetailController',
                        controllerAs: 'vmDetail'
                }},
                resolve: {
                    carDetailResolve: ['$stateParams', '$q', 'CarService', function ($stateParams, $q, CarService) {
                        if ($stateParams.targa) {
                            return CarService.getByTarga($stateParams.targa).then(function (car) {
                                return car;
                            });
                        } else {
                            return null;
                        }
                    }],
                    carBolloResolve: ['$stateParams', '$q', 'CarService', function ($stateParams, $q, CarService) {
                        if ($stateParams.targa && $stateParams.tipo) {
                            return CarService.getBollo($stateParams.targa, $stateParams.tipo).then(function (bollo) {
                                return bollo;
                            });
                        } else {
                            return null;
                        }
                    }]
                }
            })
            .state('detail.add', {
                url: '/new?creationCar',
                params: {
                  obj: null
              },
                views: {
                  'modal@': {
                    templateUrl: 'detail/carDetail.html',
                    controller: 'CarDetailAddController',
                    controllerAs: 'vmDetail'
                  }
                },
                resolve: {
                    tipoCarResolve: ['$q', function ($q) {
                        return ["AUTO", "MOTO", "RIMORCHIO"];
                    }]
                }
            })
            .state('detail.edit', {
                url: '/edit?creationCar',
                params: {
                  obj: null
              },
                views: {
                  'modal@': {
                    templateUrl: 'detail/carDetail.html',
                    controller: 'CarDetailEditController',
                    controllerAs: 'vmDetail'
                  }
                },
                resolve: {
                    carDetailResolve: ['$stateParams', '$q', 'CarService', function ($stateParams, $q, CarService) {
                        if ($stateParams.targa) {
                            return CarService.getByTarga($stateParams.targa).then(function (car) {
                                return car;
                            });
                        } else {
                            return null;
                        }
                    }],
                    tipoCarResolve: ['$q', function ($q) {
                        return ["AUTO", "MOTO", "RIMORCHIO"];
                    }]
                }
            });

        //interceptor http
        $provide.factory('unauthorisedInterceptor', ['$q','$localStorage', function ($q, $localStorage) {
            
            return {
                'responseError': function (rejection) {
                    console.log("@@@@@@@@@@@@ interceptor "+JSON.stringify(rejection));
                    if (rejection.status === 401) {
                        if ($localStorage.currentUser) {
                            delete $localStorage.currentUser;
                        }
                        //$stateProvider.state('login');
                        window.location.href = '#!/login';
                    }
     
                    return $q.reject(rejection);
                }
            };
        }]);
     
        $httpProvider.interceptors.push('unauthorisedInterceptor');
    }    


    run.$inject = ['$rootScope', '$http', '$location', '$localStorage', '$state'];
    function run($rootScope, $http, $location, $localStorage, $state) {
        // keep user logged in after page refresh
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
        }

        // redirect to login page if not logged in and trying to access a restricted page
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            var publicPages = ['/login'];
            var restrictedPage = publicPages.indexOf($location.path()) === -1;
            if (restrictedPage && !$localStorage.currentUser) {
                $location.path('/login');
            }
        });
    }



})();