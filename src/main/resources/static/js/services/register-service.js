angular.module('fastRoute')
    .service('registerService', function (createService) {
        this.save = function save(user) {
            return createService.httpPost('/user/register', user);
        }
    });
