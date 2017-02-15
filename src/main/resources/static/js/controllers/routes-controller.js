angular.module("fastRoute")
    .controller('RoutesController', function ($scope, $state, routesService) {

        $scope.title = "Routes";
        $scope.routes = [];

        var map = {};
        var markers = [];

        findAll();
        startMap();

        $scope.newRoute = function newRoute() {
            $state.transitionTo('dashboard.new-route');
        }

        $scope.viewRoute = function viewRoute(route) {
            console.log(route);
            clearMarkers();
            removePolyline();
            plot(route);
        }

        function findAll() {
            routesService.findAll().then(function onSuccess(response) {
                $scope.routes = response.data;
            }, function onFailure() {
                $scope.routes = [];
            });
        }

        function startMap() {
            map = L.map('mapid').setView([-3.734137, -38.507601], 13);
            L.tileLayer('https://api.mapbox.com/styles/v1/kaynancoelho/ciy903sve001m2spgrahcisml/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1Ijoia2F5bmFuY29lbGhvIiwiYSI6ImNpeTh6b2dqNjAwOHkzM214bXhlOTFhbGEifQ.UssoxX2vCyP4IyX7elk3ew', {
                attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
                maxZoom: 20,
                id: 'kaynancoelho.bj1taztl',
                accessToken: 'pk.eyJ1Ijoia2F5bmFuY29lbGhvIiwiYSI6ImNpeTh6b2dqNjAwOHkzM214bXhlOTFhbGEifQ.UssoxX2vCyP4IyX7elk3ew'
            }).addTo(map);

        }

        function plot(route) {
            for (i = 0; i < route.stops.length; i++) {
                marker(route.stops[i].coordinate);
            }
            polyline(route.coordinates);
        }

        function marker(coordinate) {
            var latlng = [coordinate.lat, coordinate.lng];
            var marker = L.marker(latlng).bindPopup(coordinate.formatedName).addTo(map);
            markers.push(marker);
            map.setView(latlng, 20);
        }

        function polyline(coordinates) {
            var wayPoints = [];
            updateMarkers();
            for (i = 0; i < coordinates.length; i++) {
                var coordinate = coordinates[i];
                wayPoints.push(L.latLng(coordinate.lat, coordinate.lng));
            }
            var bounds = L.polyline(wayPoints, {color: 'blue'}).addTo(map);
            map.fitBounds(bounds.getBounds());
        }


        function updateMarkers() {
            var countStops = 0;
            for (i = 1; i < markers.length - 1; i++) {
                markers[i]._popup.setContent('Parada ' + ++countStops);
            }
            markers[0]._popup.setContent('Início');
            markers[markers.length - 1]._popup.setContent('Fim');
        }

        function clearMarkers() {
            for (i = 0; i < markers.length; i++) {
                map.removeLayer(markers[i]);
            }
            markers = [];
        }

        function removePolyline() {
            for (i in map._layers) {
                if (map._layers[i]._path != undefined) {
                    try {
                        map.removeLayer(map._layers[i]);
                    }
                    catch (e) {
                        console.log("problem with " + e + map._layers[i]);
                    }
                }
            }
        }
    });