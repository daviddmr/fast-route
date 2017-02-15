angular.module('fastRoute')
    .service('routesService', function (createService) {

        this.findAll = function findAll() {
            return createService.httpGet('/route');
        }

    });