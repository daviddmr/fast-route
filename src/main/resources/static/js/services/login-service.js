angular.module('fastRoute')
    .service('loginService', function (createService) {

        this.login = function login(user) {
            var headers = user ? {
                    authorization: "Basic "
                    + btoa(user.username + ":" + user.password)
                } : {};
            return createService.httpGetAuthenticate("/user/login", {headers: headers});
        }
    });
