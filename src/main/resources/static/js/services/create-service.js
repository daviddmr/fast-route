angular.module('fastRoute')
    .service('createService', function ($http) {

        var baseUrl = 'http://localhost:8080';

        this.httpGet = function httpGet(url) {
            return $http.get(baseUrl + url);
        }

        this.httpGetAuthenticate = function httpGetAuthenticate(url, headers) {
            return $http.get(baseUrl + url, headers);
        }

        this.httpPost = function httpPost(url, data) {
            return $http.post(baseUrl + url, data);
        }

        this.httpDelete = function httpDelete(url) {
            return $http.delete(baseUrl + url);
        }

        this.httpPut = function httpPut(url, data) {
            return $http.put(baseUrl + url, data);
        }
    });