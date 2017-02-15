angular.module('fastRoute')
    .service('newRouteService', function (createService) {

        this.findRouteByAddress = function findRouteByAddress(address) {
            return createService.httpGet('/coordinate?address=' + address);
        }

        this.findRouteByStops = function findRouteByStops(stops) {
            return createService.httpPost('/coordinate/route', stops);
        }

        this.save = function save(route) {
            return createService.httpPost('/route/save', route);
        }

    });