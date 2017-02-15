angular.module('fastRoute')
    .service('dashboardService', function (createService) {
        this.logout = function logout() {
            return createService.httpPost('/user/logout', {});
        }
    });