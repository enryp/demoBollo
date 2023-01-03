(function () {
    'use strict';

    angular
        //.module('app', ['ngRoute', 'ngCookies'])
        .module('app', ['ui.router', 'ngMessages', 'ngStorage'])
        .config(config)
        .run(run);

        config.$inject = ['$stateProvider', '$urlRouterProvider'];
        function config($stateProvider, $urlRouterProvider) {
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
            });
    }    

    // config.$inject = ['$routeProvider', '$locationProvider'];
    // function config($routeProvider, $locationProvider) {
    //     $routeProvider
    //         .when('/', {
    //             controller: 'HomeController',
    //             templateUrl: 'home/home.view.html',
    //             controllerAs: 'vm'
    //         })
    //         .when('/login', {
    //             controller: 'LoginController',
    //             templateUrl: 'login/login.view.html',
    //             controllerAs: 'vm'
    //         })

    //         // .when('/register', {
    //         //     controller: 'RegisterController',
    //         //     templateUrl: 'register/register.view.html',
    //         //     controllerAs: 'vm'
    //         // })

    //         .otherwise({ redirectTo: '/login' });
    // }

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

    // run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
    // function run($rootScope, $location, $cookies, $http) {
    //     // keep user logged in after page refresh
    //     $rootScope.globals = $cookies.getObject('globals') || {};
    //     if ($rootScope.globals.currentUser) {
    //         $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
    //     }

    //     // $rootScope.$on('$locationChangeStart', function (event, next, current) {
    //     //     // redirect to login page if not logged in and trying to access a restricted page
    //     //     var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
    //     //     var loggedIn = $rootScope.globals.currentUser;
    //     //     if (restrictedPage && !loggedIn) {
    //     //         $location.path('/login');
    //     //     }
    //     // });
    // }

})();