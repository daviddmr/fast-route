angular.module("fastRoute")
    .controller('NewRouteController', function ($scope, $state, newRouteService) {

        $scope.title = "Nova Rota";
        $scope.isLoading = false;
        $scope.isLoadingRoute = false;
        $scope.isLoadingRouteRegister = false;
        $scope.isVisible = false;
        $scope.route = {};
        $scope.vehicle = {};

        $scope.coordinates = [];
        $scope.stops = [];
        $scope.responseCoordinates = [];

        var map = {};
        var markers = [];

        $scope.waypoints = [];

        startMap();

        $scope.saveRoute = function saveRoute() {
            $scope.isLoadingRouteRegister = true;
            $scope.route.vehicle = {};
            $scope.route.vehicle = $scope.vehicle;
            $scope.route.stops = [];
            $scope.route.stops = $scope.stops;
            $scope.route.coordinates = [];
            $scope.route.coordinates = $scope.responseCoordinates;
            newRouteService.save($scope.route).then(function onSuccess(response) {
                $scope.isLoadingRouteRegister = false;
                $scope.error = false;
                console.log('onSuccess');
                $state.go('dashboard.routes');
            }, function onFailure() {
                console.log('onFailure');
                $scope.isLoadingRouteRegister = false;
                $scope.error = true;
            });
        }

        $scope.findRouteByAddress = function findRouteByAddress() {
            $scope.isLoading = true;
            $scope.isVisible = false;
            newRouteService.findRouteByAddress($scope.address).then(function onSuccess(response) {
                    $scope.isLoading = false;
                    $scope.coordinate = response.data;
                    if ($scope.coordinate != null) {
                        $scope.isVisible = true;
                    }
                    console.log($scope.coordinate);
                },
                function onFailure(response) {
                    $scope.isLoading = false;
                    $scope.isVisible = false;
                    $scope.coordinate = {};
                    console.log(response);
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

        $scope.confirm = function confirm() {
            if ($scope.coordinates.indexOf($scope.coordinate) === -1) {
                marker($scope.coordinate);
                $scope.coordinates.push($scope.coordinate);
                var stop = {};
                stop.name = 'Route ' + $scope.coordinates.length;
                stop.coordinate = $scope.coordinate;
                $scope.stops.push(stop);
                removePolyline();
            }
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

        $scope.searchRoute = function searchRoute() {
            removePolyline();
            $scope.isLoadingRoute = true;
            console.log($scope.stops);
            newRouteService.findRouteByStops($scope.stops).then(function onSuccess(response) {
                $scope.isLoadingRoute = false;
                $scope.responseCoordinates = response.data;
                console.log($scope.responseCoordinates);
                polyline($scope.responseCoordinates);
            }, function onFailure(response) {
                $scope.isLoadingRoute = false;
                $scope.responseCoordinates = [];
                console.log(response);
            });
        }

        $scope.removeCoordinate = function removeCoordinate(index) {
            $scope.coordinates.splice(index, 1);
            $scope.stops.splice(index, 1);
            map.removeLayer(markers[index]);
            markers.splice(index, 1);
            removePolyline();
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