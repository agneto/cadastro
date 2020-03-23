var app = angular.module('estado', []);

app.controller('estadoController', function($scope, $http){
    $scope.estados = [];
    $scope.estado = {};

    $scope.carregarEstados = function() {
        $http({method:'GET', url:'/v1/estados'})
        .then(function(response) {
            $scope.estados = response.data._embedded.estados;
            $scope.estado = {};
        }, function(response) {
            console.log(response.data);
            console.log(response.status);
        });
    };

    $scope.salvarEstado = function() {
        if ($scope.estado.id) {
            $http({method:'PUT', url:'/v1/estados/'+$scope.estado.id, data: $scope.estado})
            .then(function(response) {
                $scope.carregarEstados();
            }, function(reponse) {
                console.log(response.data);
                console.log(response.status);
            });
        } else {
            $http({method:'POST', url:'/v1/estados', data: $scope.estado})
            .then(function(response) {
                $scope.carregarEstados();
            }, function(reponse) {
                console.log(response.data);
                console.log(response.status);
            });
        }
        $scope.estado = {};
    };
    
    $scope.removerEstado = function (codEstado) {
        $http({method:'DELETE', url:'/v1/estados/'+codEstado})
        .then(function(response) {
            $scope.carregarEstados();
        }, function(reponse) {
            console.log(response.data);
            console.log(response.status);
        });
    };
    
    $scope.editarEstado = function (codEstado) {
        $http({method:'GET', url:'/v1/estados/'+codEstado})
        .then(function(response) {
            console.log(response.data);
            $scope.estado = response.data;
        }, function(reponse) {
            console.log(response.data);
            console.log(response.status);
        });
    };

    $scope.limpar = function() {
        $scope.estado = {};
    };

});
