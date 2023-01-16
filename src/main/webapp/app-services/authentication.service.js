(function () {
    'use strict';

    angular
        .module('app')
        .factory('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = ['$http', '$httpParamSerializerJQLike', '$localStorage'];
    function AuthenticationService($http, $httpParamSerializerJQLike, $localStorage) {
        var service = {};

        service.login = login;
        service.logout = logout;

        return service;

        function login(username, password) {
            //http://localhost:8085/realms/SpringBootKeycloak/protocol/openid-connect/token

            // $http.post('/api/authenticate', { username: username, password: password })
            return $http({
                url: 'http://172.30.231.105:8085/realms/SpringBootKeycloak/protocol/openid-connect/token',
                method: 'POST',
                data: $httpParamSerializerJQLike({client_id: "login-app", username: username, password: password, grant_type: "password"}),
                headers: {
                  'Content-Type': 'application/x-www-form-urlencoded'
                }
              }).then(function (response) {
                    console.log("@@@@@@@@@@@ "+JSON.stringify(response));
                    // login successful if there's a token in the response
                    if (response.data.access_token) {
                        let accessToken = response.data.access_token;
                        console.log("@@@@@@@@@@@accessToken@@@ "+JSON.stringify(accessToken));
                        // store username and token in local storage to keep user logged in between page refreshes
                        $localStorage.currentUser = { username: username, token: accessToken };

                        // add jwt token to auth header for all requests made by the $http service
                        $http.defaults.headers.common.Authorization = 'Bearer ' + accessToken;

                        // execute callback with true to indicate successful login
                        //callback(true);
                        return response;
                    } else {
                        // execute callback with false to indicate failed login
                        //callback(false);
                        return response;
                    }
                })
                .catch((e) => {console.log("@@@@@@@@@@@error "+JSON.stringify(e)); return e;});
        }

        function logout() {
            // remove user from local storage and clear http auth header
            delete $localStorage.currentUser;
            $http.defaults.headers.common.Authorization = '';
        }
    }
})();