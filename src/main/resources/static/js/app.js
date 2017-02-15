angular.module("fastRoute", ["ui.router", "tiny-leaflet-directive"])
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {
        $stateProvider
            .state("login", {
                url: "/login",
                templateUrl: "views/login.html",
                controller: "LoginController",
            })
            .state("register", {
                url: "/register",
                templateUrl: "views/register.html",
                controller: "RegisterController"
            })
            .state("dashboard", {
                abstract: true,
                templateUrl: "views/dashboard.html",
                controller: "DashboardController"
            })
            .state("dashboard.routes", {
                url: "^/dashboard/routes",
                views: {
                    "dashboard": {
                        templateUrl: "views/routes.html",
                        controller: "RoutesController"
                    }
                }
            })
            .state("dashboard.new-route", {
                url: "^/dashboard/new-route",
                views: {
                    "dashboard": {
                        templateUrl: "views/new-route.html",
                        controller: "NewRouteController"
                    }
                }
            });

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

        $urlRouterProvider.otherwise("/login");

    }).run(function ($rootScope, $state) {
    $rootScope.$on("$stateChangeStart", function (event, toState) {
        if (toState.name == 'login' || toState == 'register') {
            if ($rootScope.authenticated == true) {
                event.preventDefault();
            }
        } else {
            if ($rootScope.authenticated == false) {
                $state.go('login');
            }
        }
    });
});