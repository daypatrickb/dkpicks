var app = angular.module("Picks", []);

app.controller("StandingsControl", function($scope, $http) {

	$http.get("./data/dummyStandings.json").
    success(function(data, status, headers, config) {
      $scope.standings = data;

      console.log($scope.standings);
    }).
    error(function(data, status, headers, config) {
      // log error
    });
});