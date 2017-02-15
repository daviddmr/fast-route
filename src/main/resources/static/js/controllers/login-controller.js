angular.module("fastRoute")
    .controller('LoginController', function ($scope, $rootScope, $state, loginService) {

        $scope.title = "Login";
        $scope.error = false;

        $scope.user = {};

        var user = {};
        user = $scope.user;

        $scope.login = function login() {
            loginService.login(user).then(onSuccess, onFailure);
        }

        $scope.register = function register() {
            $state.go("register");
        }

        function onSuccess(data) {
            $scope.error = false;
            $rootScope.authenticated = true;
            $state.go("dashboard.routes");
        }

        function onFailure(data, status) {
            $scope.error = true;
            $rootScope.authenticated = false;
            $scope.message = 'Aconteceu um problema: ' + status;
        }

    });