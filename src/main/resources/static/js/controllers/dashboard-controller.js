angular.module("fastRoute")
    .controller('DashboardController', function ($scope, $rootScope, $state, dashboardService) {

        $scope.title = "Dashboad";

        $scope.logout = function logout() {
            dashboardService.logout().then(function onSuccess() {
                $rootScope.authenticated = false;
                $state.go('login');
            }, function onFailure() {
                $scope.erro = true;
                $scope.message = 'Erro ao efeturar logout';
            });
        }
    });